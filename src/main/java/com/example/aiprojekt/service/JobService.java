package com.example.aiprojekt.service;

import com.example.aiprojekt.dto.JobPositionRequest;
import com.example.aiprojekt.models.JobPosition;
import com.example.aiprojekt.repository.JobPositionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobService {
    private final JobPositionRepository jobPositionRepository;


    public List<JobPosition> getAllJobPositions() {
        return jobPositionRepository.findAll();
    }


    public JobPosition saveJobPosition(JobPositionRequest jobPositionRequest) {
        return jobPositionRepository.save(JobPositionRequest.of(jobPositionRequest));
    }
}
