package com.isima.TpJavaPro.DTOs;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class ProductDTO implements Serializable {
    private String product_name;
    private NutritionScoreDTO nutriments;
}
