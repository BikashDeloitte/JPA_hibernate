package com.example.learning.jpa_hibernate.repository;

import com.example.learning.jpa_hibernate.entity.Passport;
import com.example.learning.jpa_hibernate.entity.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Repository
@Transactional
public class StudentRepository {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EntityManager em;
    @Autowired
    PassportRepository passportRepository;

    //save and update student
    public Student save(Student student){
        if(student.getId() == null){
            em.persist(student);
        }
        else {
            em.merge(student);
        }
        return  student;
    }

    /*
    * Hibernate is lazy, it will wait as long as it can before upload data to database.
    * it form a hibernate sequence and execute it at last.
    * */
    public Student saveStudentWithPassword(Student student, Passport passport){
        passportRepository.save(passport);
        student.setPassport(passport);
        save(student);
        return student;
    }

    //get student with passport details
    public Student getStudentDetails(Long id){
        Student student = em.find(Student.class,id);
        logger.info("Student -> {}",student);
        logger.info("Passport from Student -> {}",student.getPassport());
        return student;
    }
}
