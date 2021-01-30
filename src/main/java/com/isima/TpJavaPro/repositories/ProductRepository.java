package com.isima.TpJavaPro.repositories;

import com.isima.TpJavaPro.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByBarCode(String bareCode);
}
