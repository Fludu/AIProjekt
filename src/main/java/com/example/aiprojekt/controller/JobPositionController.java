package com.example.aiprojekt.controller;

import com.example.aiprojekt.dto.JobPositionRequest;
import com.example.aiprojekt.models.JobPosition;
import com.example.aiprojekt.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/job-positions")
@RequiredArgsConstructor
public class JobPositionController {

    private final JobService jobService;


    @PostMapping
    public ResponseEntity<JobPosition> createRole(@Valid @RequestBody JobPositionRequest jobPositionRequest) {
        JobPosition jobPosition = jobService.saveJobPosition(jobPositionRequest);
        return ResponseEntity.ok(jobPosition);
    }

    @GetMapping
    public ResponseEntity<List<JobPosition>> getAllJobPositions() {
        List<JobPosition> jobPositions = jobService.getAllJobPositions();
        return ResponseEntity.ok(jobPositions);
    }
}
