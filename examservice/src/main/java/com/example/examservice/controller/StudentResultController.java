package com.example.examservice.controller;

import com.example.examservice.entity.StudentResultEntity;
import com.example.examservice.service.StudentResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/results")
//@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000") // ✅ your React runs on 3000
public class StudentResultController {

    private final StudentResultService service;

    public StudentResultController(StudentResultService service) {
        this.service = service;
    }

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

    // ✅ NEW: Upload Excel file
    @PostMapping("/upload")
    public ResponseEntity<String> uploadResults(@RequestParam("file") MultipartFile file) {
        try {
            if (file.isEmpty()) {
                return ResponseEntity.badRequest().body("Please upload a valid Excel file.");
            }

            // delegate to service
            service.saveExcelData(file);

            return ResponseEntity.ok("File uploaded successfully: " + file.getOriginalFilename());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error processing file: " + e.getMessage());
        }
    }
}
