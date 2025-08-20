package com.example.examservice.model;


public class Subject {
    private String code;
    private String name;
    private Double gradePoint; // Example: 8.5, 9.0

    // Getters and setters
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Double getGradePoint() { return gradePoint; }
    public void setGradePoint(Double gradePoint) { this.gradePoint = gradePoint; }
}
