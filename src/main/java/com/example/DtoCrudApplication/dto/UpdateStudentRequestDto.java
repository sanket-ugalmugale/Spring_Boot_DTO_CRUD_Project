package com.example.DtoCrudApplication.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UpdateStudentRequestDto {
    @NotBlank(message="Name cannot be blank")
    @Size(min=2, max=50, message = "Name must be within 2 to 50 characters long")
    private String name;

    @NotBlank(message = "Subject is required")
    private String subject;

    @NotNull(message = "Age is required")
    @Min(value = 18, message = "student must be at least 18 years old")
    private Integer age;

    @NotNull(message = "Roll no is required")
    private Integer rollNo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Integer getRollNo() {
        return rollNo;
    }

    public void setRollNo(int rollNo) {
        this.rollNo = rollNo;
    }
}
