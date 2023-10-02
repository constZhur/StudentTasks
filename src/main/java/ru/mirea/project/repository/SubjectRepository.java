package ru.mirea.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mirea.project.model.Subject;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
}
