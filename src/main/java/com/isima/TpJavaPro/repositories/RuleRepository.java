package com.isima.TpJavaPro.repositories;

import com.isima.TpJavaPro.models.Rule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RuleRepository extends JpaRepository<Rule, Long> {

    @Query("SELECT MAX(points) FROM Rule WHERE name= :name AND min_bound< :minBound")
    int retirePoint(@Param("name") String name,@Param("minBound") double minBound);

}
