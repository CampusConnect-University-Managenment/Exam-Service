package com.example.examservice.controller;

import com.example.examservice.service.StudentResultService;
import com.example.examservice.util.ExcelHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/excelresult")
public class ExcelController {

    private final StudentResultService service;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        if (!ExcelHelper.hasExcelFormat(file)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please upload an Excel file!");
        }

        try {
            service.saveExcelData(file);
            return ResponseEntity.ok("File uploaded and data saved successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body("Could not upload the file: " + e.getMessage());
        }
    }
}
