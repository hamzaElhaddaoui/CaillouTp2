package com.isima.TpJavaPro.DTOs;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class ProductDTO implements Serializable {
    private String generic_name_fr;
    private NutritionScoreDTO nutriments;
}
