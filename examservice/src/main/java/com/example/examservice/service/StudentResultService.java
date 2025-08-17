package com.example.examservice.service;

import com.example.examservice.entity.StudentResultEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface StudentResultService {
    void saveExcelData(MultipartFile file);
    List<StudentResultEntity> getAllResults();
    List<StudentResultEntity> getResultsByRegNo(String regNo);
    List<StudentResultEntity> getResultsByRegNoAndSem(String regNo, String sem);
}
