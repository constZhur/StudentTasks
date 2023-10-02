package ru.mirea.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mirea.project.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
