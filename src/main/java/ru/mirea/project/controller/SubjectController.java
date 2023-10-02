package ru.mirea.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mirea.project.model.Group;
import ru.mirea.project.model.Subject;
import ru.mirea.project.model.Teacher;
import ru.mirea.project.service.SubjectService;

import java.util.List;

@RestController
@RequestMapping("/subjects")
public class SubjectController {
    private final SubjectService subjectService;

    @Autowired
    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping
    public ResponseEntity<List<Subject>> getAllSubjects(){
        List<Subject> subjects = subjectService.getAllSubjects();
        return ResponseEntity.ok(subjects);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Subject> getSubjectById(@PathVariable long id){
        Subject subject = subjectService.getSubjectById(id);
        return ResponseEntity.ok(subject);
    }

    @PostMapping("/create")
    public ResponseEntity<Subject> create(@RequestBody Subject subject){
        Subject createdSubject = subjectService.create(subject);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSubject);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Subject> update(@PathVariable long id, @RequestBody Subject subject){
        subject.setId(id);
        Subject updatedSubject = subjectService.update(subject);
        return ResponseEntity.ok(updatedSubject);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        subjectService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{subjectId}/teachers")
    public ResponseEntity<List<Teacher>> getAllTeachersForSubject(@PathVariable long subjectId) {
        List<Teacher> teachers = subjectService.getAllTeachersForSubject(subjectId);
        return ResponseEntity.ok(teachers);
    }

    @GetMapping("/{subjectId}/groups")
    public ResponseEntity<List<Group>> getAllGroupsForSubject(@PathVariable long subjectId) {
        List<Group> groups = subjectService.getAllGroupsForSubject(subjectId);
        return ResponseEntity.ok(groups);
    }

    @PostMapping("/{subjectId}/addTeacher/{teacherId}")
    public ResponseEntity<Void> addTeacherToSubject(@PathVariable long subjectId, @PathVariable long teacherId) {
        subjectService.addTeacherToSubject(subjectId, teacherId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/{subjectId}/addGroup/{groupId}")
    public ResponseEntity<Void> addGroupToSubject(@PathVariable long subjectId, @PathVariable long groupId) {
        subjectService.addGroupToSubject(subjectId, groupId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


}
