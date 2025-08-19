package com.example.examservice.service.impl;

import com.example.examservice.entity.Semester;
import com.example.examservice.repository.SemesterRepository;
import com.example.examservice.service.SemesterService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SemesterServiceImpl implements SemesterService {

    private final SemesterRepository semesterRepository;

    public SemesterServiceImpl(SemesterRepository semesterRepository) {
        this.semesterRepository = semesterRepository;
    }

    @Override
    public List<Semester> getAllSemesters() {
        List<Semester> semesters = semesterRepository.findAll();

        // ✅ Calculate SGPA for each semester
        for (Semester sem : semesters) {
            double total = 0.0;
            int count = 0;
            if (sem.getSubjects() != null) {
                for (var sub : sem.getSubjects()) {
                    total += sub.getGradePoint();
                    count++;
                }
            }
            sem.setSgpa(count > 0 ? total / count : 0.0);
        }
        return semesters;
    }
}
