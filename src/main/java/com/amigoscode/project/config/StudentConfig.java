package com.amigoscode.project.config;

import com.amigoscode.project.entity.Student;
import com.amigoscode.project.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args -> {
            Student jack=new Student(
                    1L,
                    "Jack",
                    "jack@email.com",
                    LocalDate.of(2004,3,5)

            );
            Student mariam=new Student(
                    2L,
                    "Mariam",
                    "mariam@email.com",
                    LocalDate.of(2008,4,26)

            );
            repository.saveAll(List.of(jack,mariam));
        };

    }
}
