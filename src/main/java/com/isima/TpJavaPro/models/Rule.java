package com.isima.TpJavaPro.models;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@ToString
@Entity
public class Rule {

    @Id
    private Long id;

    private String name;

    @Column(name = "min_bound")
    private double minBound;

    private int points;

    private String component;

}
