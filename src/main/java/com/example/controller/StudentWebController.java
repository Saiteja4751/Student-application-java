package com.example.controller;


import com.example.model.Student;
import com.example.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/students")
public class StudentWebController {

    private final StudentService service;

    public StudentWebController(StudentService service) {
        this.service = service;
    }

    @GetMapping("/view")
    public String viewStudents(Model model) {
        model.addAttribute("students", service.getAllStudents());
        model.addAttribute("student", new Student());
        return "student"; // JSP name
    }

    @PostMapping("/save")
    public String saveOrUpdateStudent(@ModelAttribute("student") Student student) {
        if (student.getId() != null) {
            service.updateStudent(student.getId(), student);
        } else {
            service.saveStudent(student);
        }
        return "redirect:/students/view";
    }

    @GetMapping("/edit/{id}")
    public String editStudent(@PathVariable Long id, Model model) {
        model.addAttribute("student", service.getStudentById(id));
        model.addAttribute("students", service.getAllStudents());
        return "student";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {
        service.deleteStudent(id);
        return "redirect:/students/view";
    }
}

