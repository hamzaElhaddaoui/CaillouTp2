package com.isima.TpJavaPro.DTOs;

import com.google.gson.annotations.SerializedName;
import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class NutritionScoreDTO implements Serializable {

    @SerializedName(value="energy_100g")
    private double energy_100g;

    @SerializedName(value="saturated-fat_100g")
    private double saturatedFat_100g;

    @SerializedName(value= "sugars_100g")
    private double sugars_100g;

    @SerializedName(value = "salt_100g")
    private double salt_100g;

    @SerializedName(value="fiber_100g")
    private double fiber_100g;

    @SerializedName(value="proteins_100g")
    private double proteins_100g;
}
