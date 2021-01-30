package com.isima.TpJavaPro.services;

import com.google.gson.Gson;
import com.isima.TpJavaPro.DTOs.NutritionScoreDTO;
import com.isima.TpJavaPro.DTOs.OpenFootFactDTO;
import com.isima.TpJavaPro.models.NutritionScore;
import com.isima.TpJavaPro.models.Product;
import com.isima.TpJavaPro.repositories.NutritionScoreRepository;
import com.isima.TpJavaPro.repositories.ProductRepository;
import com.isima.TpJavaPro.repositories.RuleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;


@Service
public class ProduitService {

    private static final String BASEURL = "https://world.openfoodfacts.org/api/v0/product/";
    private static final Logger LOG = LoggerFactory.getLogger(ProduitService.class);

    @Autowired
    private RuleRepository ruleRepository;
    @Autowired
    private NutritionScoreRepository nutritionScoreRepository;
    @Autowired
    private ProductRepository productRepository;

    private RestTemplate restTemplate;

    public ProduitService(){
        restTemplate = new RestTemplate();
    }

    public Optional<Product> getProduct(String bareCode){
        LOG.debug("Enter to the method getProduct of ProduitService");
        Product product=null;
        // verification du produit dans la base de donnée
        Optional<Product> productCache =  productRepository.findByBarCode(bareCode);
        if(productCache.isPresent()){

            product = productCache.get();

        }else{// sinon chercher le produit dans L'api

            ResponseEntity<String> response = this.restTemplate.getForEntity(BASEURL + "/"+bareCode, String.class);

            // Si un problem est servenu
            if(response.getStatusCode()!=HttpStatus.OK){
                // bad request
            }else{
                Gson gson = new Gson();
                OpenFootFactDTO openFootFactDTO = gson.fromJson(response.getBody(),OpenFootFactDTO.class);

                // le status du produit
                if(openFootFactDTO.getStatus_verbose().equals("product found")){

                    // parser l'object dans le model
                    product = fromProductDTOtoProduct(openFootFactDTO);

                    // calcule de score
                    int score = calculerScore(openFootFactDTO.getProduct().getNutriments());
                    product.setNutritionScore(score);

                    // calcule de la classe et couleur
                    NutritionScore nutritionScore=this.nutritionScoreRepository.retriveClassAndcolore(score);
                    product.setColor(nutritionScore.getColor());
                    product.setClasse(nutritionScore.getClasse());
                    LOG.info("Le produit est cree");
                    productRepository.save(product);

                }else{
                    // produit not found
                }

            }
        }

        Optional<Product> resultat = Optional.ofNullable(product);
        LOG.debug("Exit from getProduct of ProductService");
        return resultat;
    }

    private int calculerScore(NutritionScoreDTO nutriments) {
        //calcule score negatif
        int negatif = this.ruleRepository.retirePoint("energy_100g", nutriments.getEnergy_100g());
        negatif += this.ruleRepository.retirePoint("saturated-fat_100g", nutriments.getSaturatedFat_100g());
        negatif+=this.ruleRepository.retirePoint("sugars_100g", nutriments.getSugars_100g());
        negatif+=this.ruleRepository.retirePoint("salt_100g", nutriments.getSalt_100g());

        //calcule score positif
        int positif = this.ruleRepository.retirePoint("fiber_100g",nutriments.getFiber_100g());
        positif += this.ruleRepository.retirePoint("proteins_100g",nutriments.getProteins_100g());
        return negatif - positif;
    }

    private Product fromProductDTOtoProduct(OpenFootFactDTO openFootFactDTO){
        Product product = new Product();
        product.setName(openFootFactDTO.getProduct().getGeneric_name_fr());
        product.setNutritionScore(Integer.parseInt(openFootFactDTO.getProduct().getNutriments().getNutritionScore()));
        product.setBarCode(openFootFactDTO.getCode());
        return product;
    }

}
