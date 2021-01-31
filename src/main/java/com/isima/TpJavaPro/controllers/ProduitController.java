package com.isima.TpJavaPro.controllers;

import com.isima.TpJavaPro.models.Product;
import com.isima.TpJavaPro.services.ProduitService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException;

import java.util.NoSuchElementException;
import java.util.Optional;


@RestController
@RequestMapping("/product")
public class ProduitController {
    private static final Logger LOG = LoggerFactory.getLogger(ProduitController.class);

    @Autowired
    private ProduitService produitService;

    @GetMapping("/{bareCode}")
    public ResponseEntity<Product> getProduct(@PathVariable String bareCode){

        try {
            Optional<Product> resultat = produitService.getProduct(bareCode);
            return ResponseEntity.of(resultat);
        }catch (HttpServerErrorException e){
            LOG.error("Probleme de connexion avec le serveur");
            return ResponseEntity.badRequest().build();
        }catch (NoSuchElementException e) {
            LOG.error("Le code bare "+bareCode+" ne corresponds pas a un produit");
            return ResponseEntity.notFound().build();
        }
    }
}
