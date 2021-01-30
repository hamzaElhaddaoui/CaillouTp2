package com.isima.TpJavaPro.controllers;

import com.isima.TpJavaPro.models.Product;
import com.isima.TpJavaPro.services.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


@RestController
@RequestMapping("/product")
public class ProduitController {

    @Autowired
    private ProduitService produitService;

    @GetMapping("/{barreCode}")
    public ResponseEntity<Product> getProduct(@PathVariable String barreCode){

        Optional<Product> resultat = produitService.getProduct(barreCode);

        return ResponseEntity.of(resultat);
    }
}
