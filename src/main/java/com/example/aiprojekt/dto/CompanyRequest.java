package com.example.aiprojekt.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CompanyRequest {

    @NotNull
    String name;
    @NotNull
    String city;
    @NotNull
    String industryType;
}
