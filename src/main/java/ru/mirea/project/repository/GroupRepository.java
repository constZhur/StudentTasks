package ru.mirea.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.mirea.project.model.Group;
import ru.mirea.project.model.Student;
import ru.mirea.project.model.Subject;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
//    @Query("SELECT s FROM Student s WHERE s.group.id = :groupId")
//    List<Student> getAllStudentsByGroup(@Param("groupId") long groupId);
//
//    @Query("SELECT s FROM Subject s JOIN s.groups g WHERE g.id = :groupId")
//    List<Subject> getAllSubjectsByGroup(@Param("groupId") long groupId);
}
