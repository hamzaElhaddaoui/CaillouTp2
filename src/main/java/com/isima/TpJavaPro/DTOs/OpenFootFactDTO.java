package com.isima.TpJavaPro.DTOs;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class OpenFootFactDTO implements Serializable{
    private String code;
    private String status_verbose;
    private ProductDTO product;
}
