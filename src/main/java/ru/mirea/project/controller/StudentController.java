package ru.mirea.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mirea.project.model.Student;
import ru.mirea.project.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable long id) {
        Student student = studentService.getStudentById(id);
        return ResponseEntity.ok(student);
    }

    @PostMapping("/create")
    public ResponseEntity<Student> create(@RequestBody Student student) {
        Student createdStudent = studentService.create(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdStudent);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<Student> update(@PathVariable long id, @RequestBody Student student) {
        student.setId(id);
        Student updatedStudent = studentService.update(student);
        return ResponseEntity.ok(updatedStudent);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        studentService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
