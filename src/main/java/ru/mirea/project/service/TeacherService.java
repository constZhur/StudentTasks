package ru.mirea.project.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mirea.project.model.Subject;
import ru.mirea.project.model.Teacher;
import ru.mirea.project.repository.TeacherRepository;

import java.util.List;

@Service
public class TeacherService {
    private final TeacherRepository teacherRepository;

    @Autowired
    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public Teacher create(Teacher teacher){
        return teacherRepository.save(teacher);
    }

    public Teacher getTeacherById(long id){
        return teacherRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("Teacher not found with id: " + id));
    }

    public List<Teacher> getAllTeachers(){
        return teacherRepository.findAll();
    }

    public Teacher update(Teacher teacher){
        return teacherRepository.save(teacher);
    }

    public void delete(long id){
        teacherRepository.deleteById(id);
    }

    public List<Subject> getAllSubjectForTeacher(long teacherId){
        Teacher teacher = teacherRepository.findById(teacherId).
                orElseThrow(() -> new EntityNotFoundException("Teacher with ID " + teacherId + " not found"));
        return teacher.getSubjects();
    }
}
