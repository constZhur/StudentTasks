package ru.mirea.project.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mirea.project.model.Group;
import ru.mirea.project.model.Student;
import ru.mirea.project.repository.StudentRepository;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student create(Student student){
        return studentRepository.save(student);
    }

    public Student getStudentById(long id){
        return studentRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("Student not found with id: " + id));
    }

    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    public Student update(Student student){
        return studentRepository.save(student);
    }

    public void delete(long id){
        studentRepository.deleteById(id);
    }

}
