package com.example.examservice.entity;


import com.example.examservice.model.Subject;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "semesters")
public class Semester {

    @Id
    private String id;
    private String name; // e.g., "Sem 1"
    private List<Subject> subjects;

    // ✅ Derived field (not stored, calculated)
    private Double sgpa;

    // Getters and setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public List<Subject> getSubjects() { return subjects; }
    public void setSubjects(List<Subject> subjects) { this.subjects = subjects; }

    public Double getSgpa() { return sgpa; }
    public void setSgpa(Double sgpa) { this.sgpa = sgpa; }
}
