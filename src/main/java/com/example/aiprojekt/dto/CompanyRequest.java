package com.example.aiprojekt.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CompanyRequest {
    @NotNull
    @NotEmpty
    private String name;
    @NotNull
    @NotEmpty
    private String city;
    @NotNull
    @NotEmpty
    private String industryType;
}
