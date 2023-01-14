package com.example.aiprojekt.dto;

import com.example.aiprojekt.models.JobPosition;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class JobPositionRequest {
    @NotBlank
    String name;

    public static JobPosition of(JobPositionRequest jobPositionRequest) {
        return new JobPosition(jobPositionRequest.getName());
    }
}
