package com.example.examservice.repository;

import com.example.examservice.entity.StudentResultEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentResultRepository extends MongoRepository<StudentResultEntity, String> {
    List<StudentResultEntity> findByRegNo(String regNo);
    List<StudentResultEntity> findByRegNoAndSem(String regNo, String sem);
}
