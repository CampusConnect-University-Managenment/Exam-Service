package com.example.examservice.entity;

import ch.qos.logback.core.joran.action.Action;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "studentsResult")


public class StudentResultEntity {
    @Id
    private String studentId;

    private String studentName;
    private String sem;
    private String regNo;
    private String courseCode;
    private String courseTitle;
    private String credits;
    private String gradePoints;
    private String letterGrade;


}

