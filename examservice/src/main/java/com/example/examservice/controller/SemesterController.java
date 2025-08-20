package com.example.examservice.controller;


import com.example.examservice.entity.Semester;
import com.example.examservice.service.SemesterService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/semesters")
@CrossOrigin(origins = "http://localhost:3000") // adjust frontend port if needed
public class SemesterController {

    private final SemesterService semesterService;

    public SemesterController(SemesterService semesterService) {
        this.semesterService = semesterService;
    }

    @GetMapping
    public List<Semester> getSemesters() {
        return semesterService.getAllSemesters();
    }
}
