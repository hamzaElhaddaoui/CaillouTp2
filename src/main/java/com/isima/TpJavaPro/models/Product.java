package com.isima.TpJavaPro.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
public class Product {
    @Id
    @GeneratedValue
    private Long id;
    private String barCode;
    private String name;
    private int nutritionScore;
    private String classe;
    private String color;
}
