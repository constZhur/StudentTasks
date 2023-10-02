package ru.mirea.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@EntityScan("ru.mirea.project.model")
//@EnableJpaRepositories("ru.mirea.project.repository")
public class StudentTasksApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentTasksApplication.class, args);
    }

}
