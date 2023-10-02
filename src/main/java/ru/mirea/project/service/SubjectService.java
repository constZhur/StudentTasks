package ru.mirea.project.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mirea.project.model.Group;
import ru.mirea.project.model.Subject;
import ru.mirea.project.model.Teacher;
import ru.mirea.project.repository.GroupRepository;
import ru.mirea.project.repository.SubjectRepository;
import ru.mirea.project.repository.TeacherRepository;

import java.util.List;

@Service
public class SubjectService {
    private final SubjectRepository subjectRepository;
    private final TeacherRepository teacherRepository;
    private final GroupRepository groupRepository;

    @Autowired
    public SubjectService(SubjectRepository subjectRepository, TeacherRepository teacherRepository, GroupRepository groupRepository) {
        this.subjectRepository = subjectRepository;
        this.teacherRepository = teacherRepository;
        this.groupRepository = groupRepository;
    }

    public Subject create(Subject subject){
        return subjectRepository.save(subject);
    }

    public Subject getSubjectById(long id){
        return subjectRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("Subject not found with id: " + id));
    }

    public List<Subject> getAllSubjects(){
        return subjectRepository.findAll();
    }

    public Subject update(Subject subject){
        return subjectRepository.save(subject);
    }

    public void delete(long id){
        subjectRepository.deleteById(id);
    }

    public List<Teacher> getAllTeachersForSubject(long subjectId){
        Subject subject = getSubjectById(subjectId);
        return subject.getTeachers();
    }

    public List<Group> getAllGroupsForSubject(long subjectId){
        Subject subject = getSubjectById(subjectId);
        return subject.getGroups();
    }

    public void addTeacherToSubject(long subjectId, long teacherId){
        Subject subject = getSubjectById(subjectId);
        Teacher teacher = teacherRepository.findById(teacherId).
                orElseThrow(() -> new EntityNotFoundException("Teacher not found with id: " + teacherId));

        if (!subject.getTeachers().contains(teacher)) {
            subject.getTeachers().add(teacher);
            teacher.getSubjects().add(subject);
            subjectRepository.save(subject);
            teacherRepository.save(teacher);
        }
    }

    public void addGroupToSubject(long subjectId, long groupId){
        Subject subject = getSubjectById(subjectId);
        Group group = groupRepository.findById(groupId).
                orElseThrow(() -> new EntityNotFoundException("Group not found with id: " + groupId));

        if (!subject.getGroups().contains(group)) {
            subject.getGroups().add(group);
            group.getSubjects().add(subject);
            subjectRepository.save(subject);
            groupRepository.save(group);
        }
    }
}
