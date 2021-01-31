package com.isima.TpJavaPro;

import com.isima.TpJavaPro.DTOs.NutritionScoreDTO;
import com.isima.TpJavaPro.models.NutritionScore;
import com.isima.TpJavaPro.models.Product;
import com.isima.TpJavaPro.repositories.NutritionScoreRepository;
import com.isima.TpJavaPro.repositories.RuleRepository;
import com.isima.TpJavaPro.services.ProduitService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.NoSuchElementException;
import java.util.Optional;

@SpringBootTest
class TpJavaProApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private RuleRepository ruleRepository;

	@Autowired
	private ProduitService produitService;

	@Autowired
	private NutritionScoreRepository nutritionScoreRepository;

	@Test
	void testRulePoints(){
		//energy_100g
		int point = ruleRepository.retirePoint("energy_100g", 50);
		Assertions.assertEquals(point,0);

		point = ruleRepository.retirePoint("energy_100g",335);
		Assertions.assertEquals(point,0);

		point = ruleRepository.retirePoint("energy_100g", 336);
		Assertions.assertEquals(point,1);

		point = ruleRepository.retirePoint("energy_100g",3351);
		Assertions.assertEquals(point,10);

		//saturated-fat_100g
		point = ruleRepository.retirePoint("saturated-fat_100g", 1);
		Assertions.assertEquals(point,0);

		point = ruleRepository.retirePoint("saturated-fat_100g",4);
		Assertions.assertEquals(point,3);

		point = ruleRepository.retirePoint("saturated-fat_100g", 11);
		Assertions.assertEquals(point,10);

		//sugars_100g
		point = ruleRepository.retirePoint("sugars_100g", 4.5);
		Assertions.assertEquals(point, 0);

		point = ruleRepository.retirePoint("sugars_100g", 4.6);
		Assertions.assertEquals(point,1);

		point = ruleRepository.retirePoint("sugars_100g", 46);
		Assertions.assertEquals(point,10);

		//salt_100g
		point = ruleRepository.retirePoint("salt_100g", 90);
		Assertions.assertEquals(point,0);

		point = ruleRepository.retirePoint("salt_100g", 901);
		Assertions.assertEquals(point,10);

		//fiber_100g
		point = ruleRepository.retirePoint("fiber_100g", 0.9);
		Assertions.assertEquals(point,0);

		point = ruleRepository.retirePoint("fiber_100g", 1);
		Assertions.assertEquals(point,1);

		//proteins_100g
		point = ruleRepository.retirePoint("proteins_100g", 1.6);
		Assertions.assertEquals(point,0);

		point = ruleRepository.retirePoint("proteins_100g", 2);
		Assertions.assertEquals(point,1);

		point = ruleRepository.retirePoint("proteins_100g", 8.1);
		Assertions.assertEquals(point,5);

	}

	@Test
	void testCalculeScore(){

		NutritionScoreDTO nutritionScoreDTO = new NutritionScoreDTO(340,11,9,40,5,5);
		int score = produitService.calculerScore(nutritionScoreDTO);
		Assertions.assertEquals(4,score);

		NutritionScore nutritionScore = nutritionScoreRepository.retriveClassAndcolore(score);
		Assertions.assertEquals("Mangeable",nutritionScore.getClasse());
		Assertions.assertEquals("yellow", nutritionScore.getColor());

	}

	@Test
	void TestProduitServiceTest(){

		// Test premiere produit
		Optional<Product> optionalProduct = this.produitService.getProduct("8000500203873");
		Product product = optionalProduct.get();
		Assertions.assertEquals(15,product.getNutritionScore());
		Assertions.assertEquals("Mouai",product.getClasse());
		Assertions.assertEquals("orange",product.getColor());

		// Test deuxieme produit
		Optional<Product> optionalProduct1 = this.produitService.getProduct("04963406");
		Product product1 = optionalProduct1.get();
		Assertions.assertEquals(2,product1.getNutritionScore());
		Assertions.assertEquals("Bon",  product1.getClasse());
		Assertions.assertEquals("light green", product1.getColor());

		// BareCode aléatoire
		Assertions.assertThrows(NoSuchElementException.class , () -> this.produitService.getProduct("5461321679831231365976"));
	}

}