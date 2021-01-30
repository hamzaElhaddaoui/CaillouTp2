package com.isima.TpJavaPro.models;

import lombok.Data;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name="Nutrition_score")
public class NutritionScore {
    @Id
    Long id;
    String classe;
    int lower_bound;
    int upper_bound;
    String color;
}
