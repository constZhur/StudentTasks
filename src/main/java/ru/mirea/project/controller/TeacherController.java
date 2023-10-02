package ru.mirea.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mirea.project.model.Subject;
import ru.mirea.project.model.Teacher;
import ru.mirea.project.service.TeacherService;

import java.util.List;

@RestController
@RequestMapping("/teachers")
public class TeacherController {
    private final TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping
    public ResponseEntity<List<Teacher>> getAllTeachers() {
        List<Teacher> teachers = teacherService.getAllTeachers();
        return ResponseEntity.ok(teachers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Teacher> getTeacherById(@PathVariable long id) {
        Teacher teacher = teacherService.getTeacherById(id);
        return ResponseEntity.ok(teacher);
    }

    @PostMapping("/create")
    public ResponseEntity<Teacher> create(@RequestBody Teacher teacher) {
        Teacher createdTeacher = teacherService.create(teacher);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTeacher);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Teacher> update(@PathVariable long id, @RequestBody Teacher teacher) {
        teacher.setId(id);
        Teacher updatedTeacher = teacherService.update(teacher);
        return ResponseEntity.ok(updatedTeacher);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        teacherService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/subjects")
    public ResponseEntity<List<Subject>> getAllSubjectsForTeacher(@PathVariable long id) {
        List<Subject> subjects = teacherService.getAllSubjectForTeacher(id);
        return ResponseEntity.ok(subjects);
    }


}
