package com.example.examservice.controller;

import com.example.examservice.entity.StudentResultEntity;
import com.example.examservice.service.StudentResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/results")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3001")
public class StudentResultController {

    private final StudentResultService service;
    @GetMapping
    public List<StudentResultEntity> getAllResults() {
        return service.getAllResults();
    }

    @GetMapping("/{regNo}")
    public List<StudentResultEntity> getResultsByRegNo(@PathVariable String regNo) {
        return service.getResultsByRegNo(regNo);
    }

    @GetMapping("/{regNo}/{sem}")
    public List<StudentResultEntity> getResultsByRegNoAndSem(@PathVariable String regNo,
                                                             @PathVariable String sem) {
        return service.getResultsByRegNoAndSem(regNo, sem);
    }
}
