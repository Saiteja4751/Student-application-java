package com.example.controller;

import com.example.model.Student;
import com.example.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
@CrossOrigin(origins = "*")
public class StudentController {

    private  StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @PostMapping("/add")
    public String addStudent(@RequestBody Student student) {
        service.saveStudent(student);
        return "Student with Name " + student.getName() + " added successfully.";
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return service.getAllStudents();
    }
    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Long id) {
        return service.getStudentById(id);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {
        service.deleteStudent(id);
        return "Student with ID " + id + " deleted successfully.";
    }
    @PutMapping("/update/{id}")
    public String updateStudent(@PathVariable Long id, @RequestBody Student student) {
        service.updateStudent(id, student);
        return "Student with ID " + id + " updated successfully.";
    }

}
