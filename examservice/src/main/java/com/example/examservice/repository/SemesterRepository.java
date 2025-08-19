package com.example.examservice.repository;

import com.example.examservice.entity.Semester;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SemesterRepository extends MongoRepository<Semester, String> {

}