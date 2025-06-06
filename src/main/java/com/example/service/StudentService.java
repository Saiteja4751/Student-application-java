package com.example.service;

import com.example.model.Student;
import com.example.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public void saveStudent(Student student) {
        studentRepository.save(student);
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    public List<Student> getAllStudents() {
        if(studentRepository.count()!= 0) {
            return studentRepository.findAll();

        }
        return null;
    }

    public Student deleteStudent(Long id) {
      if(studentRepository.existsById(id)) {
          studentRepository.deleteById(id);
      }
      return null;
    }

//    public Student updateStudent(Long id, Student student) {
//        if (studentRepository.existsById(id)) {
//            student.setId(id);
//            studentRepository.save(student);
//        }
//        return null;
//    }
    public Student updateStudent(Long id, Student updatedUser) {
        return studentRepository.findById(id).map(user -> {
            user.setName(updatedUser.getName());
            user.setEmail(updatedUser.getEmail());
            user.setPhone(updatedUser.getPhone());
            user.setGrade(updatedUser.getGrade());
            return studentRepository.save(user);
        }).orElse(null);
    }


}
