package com.example.aiprojekt.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class CompanyRequest {

    @NotBlank
    String name;
    @NotBlank
    String city;
    @NotBlank
    String industryType;
}
