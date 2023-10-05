package ru.mirea.project.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mirea.project.model.Group;
import ru.mirea.project.model.Student;
import ru.mirea.project.model.Subject;
import ru.mirea.project.model.Teacher;
import ru.mirea.project.repository.GroupRepository;
import ru.mirea.project.repository.StudentRepository;
import ru.mirea.project.repository.SubjectRepository;

import java.util.List;

@Service
public class GroupService {
    private final GroupRepository groupRepository;
    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;

    @Autowired
    public GroupService(GroupRepository groupRepository, StudentRepository studentRepository, SubjectRepository subjectRepository) {
        this.groupRepository = groupRepository;
        this.studentRepository = studentRepository;
        this.subjectRepository = subjectRepository;
    }

    public Group create(Group group){
        return groupRepository.save(group);
    }

    public Group getGroupById(long id){
        return groupRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("Subject not found with id: " + id));
    }

    public List<Group> getAllGroups(){
        return groupRepository.findAll();
    }

    public Group update(Group group){
        return groupRepository.save(group);
    }

    public void delete(long id){
        groupRepository.deleteById(id);
    }


    public void addStudentToGroup(long groupId, long studentId){
        Group group = getGroupById(groupId);
        Student student = studentRepository.findById(studentId).
                orElseThrow(() -> new EntityNotFoundException("Student not found with id: " + studentId));
        if (!group.getStudents().contains(student)) {
            group.getStudents().add(student);
            student.setGroup(group);
            groupRepository.save(group);
            studentRepository.save(student);
        }
    }

    public void addSubjectToGroup(long groupId, long subjectId){
        Group group = getGroupById(groupId);
        Subject subject = subjectRepository.findById(subjectId).
                orElseThrow(() -> new EntityNotFoundException("Subject not found with id: " + subjectId));
        if (!group.getSubjects().contains(subject)) {
            group.getSubjects().add(subject);
            subject.getGroups().add(group);
            groupRepository.save(group);
            subjectRepository.save(subject);
        }
    }


    public void removeStudentFromGroup(long groupId, long studentId){
        Group group = getGroupById(groupId);
        List<Student> students = group.getStudents();
        students.removeIf(student -> student.getId() == studentId && group.getStudents().contains(student));
        group.setStudents(students);
        update(group);

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with id: " + studentId));
        student.setGroup(null);
        studentRepository.save(student);
    }

    public void removeSubjectFromGroup(long groupId, long subjectId){
        Group group = getGroupById(groupId);
        List<Subject> subjects = group.getSubjects();
        subjects.removeIf(subject -> subject.getId() == subjectId && group.getSubjects().contains(subject));
        group.setSubjects(subjects);
        update(group);

        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new EntityNotFoundException("Subject not found with id: " + subjectId));
        subject.getGroups().removeIf(g -> g.getId() == groupId);
        subjectRepository.save(subject);
    }

    public List<Student> getAllStudentsByGroup(long groupId){
        return groupRepository.getAllStudentsByGroup(groupId);
    }

    public List<Subject> getAllSubjectsByGroup(long groupId){
        return groupRepository.getAllSubjectsByGroup(groupId);
    }

}