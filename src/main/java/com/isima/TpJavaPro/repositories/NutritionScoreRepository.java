package com.isima.TpJavaPro.repositories;

import com.isima.TpJavaPro.models.NutritionScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface NutritionScoreRepository extends JpaRepository<NutritionScore, Long> {

    @Query("SELECT n FROM NutritionScore n where upper_bound >= :score AND lower_bound<= :score")
    NutritionScore retriveClassAndcolore(@Param(value = "score") int score);

}
