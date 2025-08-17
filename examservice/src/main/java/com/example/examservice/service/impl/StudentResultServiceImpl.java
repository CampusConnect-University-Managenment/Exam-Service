package com.example.examservice.service.impl;

import com.example.examservice.entity.StudentResultEntity;
import com.example.examservice.repository.StudentResultRepository;
import com.example.examservice.service.StudentResultService;
import com.example.examservice.util.ExcelHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentResultServiceImpl implements StudentResultService {

    private final StudentResultRepository repository;

    @Override
    public void saveExcelData(MultipartFile file) {
        try {
            List<StudentResultEntity> results = ExcelHelper.excelToStudentResults(file.getInputStream());
            repository.saveAll(results);
        } catch (IOException e) {
            throw new RuntimeException("Failed to store Excel data: " + e.getMessage());
        }
    }

    @Override
    public List<StudentResultEntity> getAllResults() {
        return repository.findAll();
    }

    @Override
    public List<StudentResultEntity> getResultsByRegNo(String regNo) {
        return repository.findByRegNo(regNo);
    }

    @Override
    public List<StudentResultEntity> getResultsByRegNoAndSem(String regNo, String sem) {
        return repository.findByRegNoAndSem(regNo, sem);
    }
}
