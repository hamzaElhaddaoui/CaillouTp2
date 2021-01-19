package com.isima.TpJavaPro.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Product {
    private Long id;
    private String barCode;
    private String name;
    private int nutritionScore;
    private String classe;
    private String color;
}
