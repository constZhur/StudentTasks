package ru.mirea.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mirea.project.model.Group;
import ru.mirea.project.model.Student;
import ru.mirea.project.model.Subject;
import ru.mirea.project.service.GroupService;

import java.util.List;

@RestController
@RequestMapping("/groups")
public class GroupController {
    private final GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping
    public ResponseEntity<List<Group>> getAllGroups() {
        List<Group> groups = groupService.getAllGroups();
        return ResponseEntity.ok(groups);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Group> getGroupById(@PathVariable long id) {
        Group group = groupService.getGroupById(id);
        return ResponseEntity.ok(group);
    }

    @PostMapping("/create")
    public ResponseEntity<Group> create(@RequestBody Group group) {
        Group createdGroup = groupService.create(group);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdGroup);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Group> update(@PathVariable long id, @RequestBody Group group) {
        group.setId(id);
        Group updatedGroup = groupService.update(group);
        return ResponseEntity.ok(updatedGroup);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        groupService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{groupId}/students")
    public ResponseEntity<List<Student>> getAllStudentsByGroup(@PathVariable long groupId) {
        List<Student> students = groupService.getAllStudentsByGroup(groupId);
        return ResponseEntity.ok(students);
    }

    @GetMapping("/{groupId}/subjects")
    public ResponseEntity<List<Subject>> getAllSubjectsByGroup(@PathVariable long groupId) {
        List<Subject> subjects = groupService.getAllSubjectsByGroup(groupId);
        return ResponseEntity.ok(subjects);
    }

    @PostMapping("/{groupId}/addStudent/{studentId}")
    public ResponseEntity<Void> addStudentToGroup(@PathVariable long groupId, @PathVariable long studentId) {
        groupService.addStudentToGroup(groupId, studentId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{groupId}/removeStudent/{studentId}")
    public ResponseEntity<Void> removeStudentFromGroup(@PathVariable long groupId, @PathVariable long studentId) {
        groupService.removeStudentFromGroup(groupId, studentId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{groupId}/addSubject/{subjectId}")
    public ResponseEntity<Void> addSubjectToGroup(@PathVariable long groupId, @PathVariable long subjectId) {
        groupService.addSubjectToGroup(groupId, subjectId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{groupId}/removeSubject/{subjectId}")
    public ResponseEntity<Void> removeSubjectFromGroup(@PathVariable long groupId, @PathVariable long subjectId) {
        groupService.removeSubjectFromGroup(groupId, subjectId);
        return ResponseEntity.noContent().build();
    }
}