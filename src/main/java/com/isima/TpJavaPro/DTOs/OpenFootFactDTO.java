package com.isima.TpJavaPro.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OpenFootFactDTO implements Serializable {
    private String code;
    private String status_verbose;
    private ProductDTO product;
}
