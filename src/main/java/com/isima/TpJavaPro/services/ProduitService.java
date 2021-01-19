package com.isima.TpJavaPro.services;

import com.google.gson.Gson;
import com.isima.TpJavaPro.DTOs.OpenFootFactDTO;
import com.isima.TpJavaPro.DTOs.ProductDTO;
import com.isima.TpJavaPro.models.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;


@Service
public class ProduitService {

    public ProduitService(){
        restTemplate = new RestTemplate();
    }

    private static final String baseUrl = "https://world.openfoodfacts.org/api/v0/product/";
    private RestTemplate restTemplate;

    public Optional<Product> getProduct(String barreCode){

        // verification de la base de donnée

        // sinon chercher le produit dans L'api
        ResponseEntity<String> response = this.restTemplate.getForEntity(baseUrl + "/"+barreCode, String.class);
        Product product=null;

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

                // persister l'object

            }else{

            }

        }

        Optional<Product> resultat = Optional.ofNullable(product);

        return resultat;
    }

    private Product fromProductDTOtoProduct(OpenFootFactDTO openFootFactDTO){
        Product product = new Product();
        product.setName(openFootFactDTO.getProduct().getGeneric_name_fr());
        product.setNutritionScore(Integer.parseInt(openFootFactDTO.getProduct().getNutriments().getNutritionScore()));
        product.setBarCode(openFootFactDTO.getCode());
        return product;
    }

}
