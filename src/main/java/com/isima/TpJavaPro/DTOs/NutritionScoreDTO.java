package com.isima.TpJavaPro.DTOs;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NutritionScoreDTO implements Serializable {
    @SerializedName(value="nutrition-score-fr")
    private String nutritionScore;

    private String energy_value;
}
