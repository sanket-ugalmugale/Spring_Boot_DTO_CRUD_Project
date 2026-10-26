package com.example.DtoCrudApplication.service;

import com.example.DtoCrudApplication.dto.CreateStudentRequestDto;
import com.example.DtoCrudApplication.dto.CreateStudentResponseDto;
import com.example.DtoCrudApplication.dto.UpdateStudentRequestDto;
import com.example.DtoCrudApplication.dto.UpdateStudentResponseDto;
import com.example.DtoCrudApplication.entity.Student;
import com.example.DtoCrudApplication.exception.DuplicateResourceException;
import com.example.DtoCrudApplication.exception.ResourceNotFoundException;
import com.example.DtoCrudApplication.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StudentService {

    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository)
    {
        this.studentRepository=studentRepository;
    }

    public CreateStudentResponseDto createStudent(CreateStudentRequestDto studentReqDto)
    {
        Student student = mapToEntity(studentReqDto);

        if (emailExists(student))
        {
            throw new DuplicateResourceException("Student with email "+ student.getEmail() + "is already exists");
        }

        Student studentResp = studentRepository.save(student);

        return mapToDto(studentResp);
    }


    public CreateStudentResponseDto getStudent(Long id)
    {
        Student studentRes = studentRepository
                .findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Student with id " +id+ " not found"));

        return mapToDto(studentRes);
    }

    public List<CreateStudentResponseDto> getAllStudent()
    {
        List<Student> studentList = studentRepository.findByDeletedIsFalse();

        return studentList.stream()
                .map(this::mapToDto)
                .toList();
    }

    public UpdateStudentResponseDto updateStudent(Long id, UpdateStudentRequestDto studentReq) {
        Student existingStudent = studentRepository
                .findByIdAndDeletedIsFalse(id)
                .orElseThrow(()-> new ResourceNotFoundException("Student with id " +id+ " not found"));

        existingStudent.setName(studentReq.getName());
        existingStudent.setAge(studentReq.getAge());
        existingStudent.setRollNo(studentReq.getRollNo());
        existingStudent.setSubject(studentReq.getSubject());
        existingStudent.setDeleted(false);
        existingStudent.setUpdatedAt(LocalDateTime.now());

        Student savedStudent = studentRepository.save(existingStudent);

        return mapToUpdateDto(savedStudent);
    }

    public void deleteStudent(Long id) {
        Student studentToBeDeleted = studentRepository
                .findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Student with id " + id + " not found"));

        studentRepository.delete(studentToBeDeleted);
    }

    public void deleteSoftly(Long id)
    {
        Student studentToBeDeleted = studentRepository
                .findByIdAndDeletedIsFalse(id)
                .orElseThrow(()-> new ResourceNotFoundException("Student with id " +id+ " not found"));

        studentToBeDeleted.setDeleted(true);

        studentRepository.save(studentToBeDeleted);
    }

    private Student mapToEntity(CreateStudentRequestDto studentReqDto) {
        Student student = new Student();

        student.setName(studentReqDto.getName());
        student.setAge(studentReqDto.getAge());
        student.setEmail(studentReqDto.getEmail());
        student.setRollNo(studentReqDto.getRollNo());
        student.setSubject(studentReqDto.getSubject());
        student.setCreatedAt(LocalDateTime.now());
        student.setUpdatedAt(LocalDateTime.now());
        student.setDeleted(false);

        return student;

    }

    private CreateStudentResponseDto mapToDto(Student student)
    {
        CreateStudentResponseDto responseDto = new CreateStudentResponseDto();

        responseDto.setId(student.getId());
        responseDto.setName(student.getName());
        responseDto.setEmail(student.getEmail());
        responseDto.setAge(student.getAge());
        responseDto.setRollNo(student.getRollNo());
        responseDto.setSubject(student.getSubject());
        responseDto.setCreatedAt(LocalDateTime.now());
        responseDto.setMessage("Student saved successfully");

        return responseDto;
    }

    private UpdateStudentResponseDto mapToUpdateDto(Student student)
    {
        UpdateStudentResponseDto responseDto = new UpdateStudentResponseDto();

        responseDto.setId(student.getId());
        responseDto.setName(student.getName());
        responseDto.setEmail(student.getEmail());
        responseDto.setAge(student.getAge());
        responseDto.setRollNo(student.getRollNo());
        responseDto.setSubject(student.getSubject());
        responseDto.setUpdatedAt(LocalDateTime.now());
        responseDto.setMessage("Student Updated Successfully");

        return responseDto;
    }

    private boolean emailExists(Student student)
    {
        return studentRepository.existsByEmail(student.getEmail());
    }

}

