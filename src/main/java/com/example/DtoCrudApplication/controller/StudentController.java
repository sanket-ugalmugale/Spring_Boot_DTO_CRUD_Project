package com.example.DtoCrudApplication.controller;

import com.example.DtoCrudApplication.dto.CreateStudentResponseDto;
import com.example.DtoCrudApplication.dto.CreateStudentRequestDto;
import com.example.DtoCrudApplication.dto.UpdateStudentRequestDto;
import com.example.DtoCrudApplication.dto.UpdateStudentResponseDto;
import com.example.DtoCrudApplication.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students/api")
public class StudentController {
    private StudentService studentService;

    public StudentController(StudentService studentService)
    {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<CreateStudentResponseDto> createStudent(@Valid @RequestBody CreateStudentRequestDto student)
    {
        CreateStudentResponseDto createdStudent = studentService.createStudent(student);

        return ResponseEntity.ok(createdStudent);
    }


    @GetMapping("/{id}")
    public ResponseEntity<CreateStudentResponseDto> getstudent(@PathVariable Long id)
    {
        CreateStudentResponseDto studentResp = studentService.getStudent(id);

        return ResponseEntity.ok(studentResp);
    }

    @GetMapping
    public ResponseEntity<List<CreateStudentResponseDto>> getAllStudent()
    {
        List <CreateStudentResponseDto> studentList = studentService.getAllStudent();

        return ResponseEntity.ok(studentList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateStudentResponseDto> updateStudent(@PathVariable Long id, @Valid @RequestBody UpdateStudentRequestDto studentReq)
    {
        UpdateStudentResponseDto updatedStudent = studentService.updateStudent(id, studentReq);

        return ResponseEntity.ok(updatedStudent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id)
    {
        studentService.deleteStudent(id);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> deleteSoftly(@PathVariable Long id)
    {
        studentService.deleteSoftly(id);

        return ResponseEntity.noContent().build();
    }
}

