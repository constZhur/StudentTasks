package ru.mirea.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mirea.project.model.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}
