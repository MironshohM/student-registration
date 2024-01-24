package com.amigoscode.project.service;

import com.amigoscode.project.entity.Student;
import com.amigoscode.project.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository repository;

    @Autowired
    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public List<Student> getStudents(){
        return repository.findAll();
    }

    public void addNewStudent(Student student){
        Optional<Student> studentByEmail=repository.findByEmail(student.getEmail());
        if(studentByEmail.isPresent()){
            throw new IllegalStateException("Email taken");
        }
        repository.save(student);
    }

    public void deleteById(Long id){
//        Optional<Student> studentOptional=repository.findById(id);
//        if(!studentOptional.isPresent()){
//            throw new IllegalStateException("There is no this kind of student "+studentOptional);
//        }
//        Student student=studentOptional.get();
//        repository.delete(student);

        boolean exists=repository.existsById(id);
        if(!exists){
            throw new IllegalStateException("There is no this kind of student id"+id);
        }
        repository.deleteById(id);

    }

    public void updateById(Long id,Student newStudent){
        if(repository.findById(id).isPresent()){
            Student student=repository.findById(id).get();
            student.setEmail(newStudent.getEmail());
            student.setName(newStudent.getName());
            student.setDob(newStudent.getDob());
            student.setAge(newStudent.getAge());
        }else {
            addNewStudent(newStudent);
        }



    }

}
