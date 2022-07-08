package com.example.learning.jpa_hibernate.repository;

import com.example.learning.jpa_hibernate.entity.Passport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Repository
@Transactional
public class PassportRepository {

    @Autowired
    EntityManager em;

    //save and update passport
    public Passport save(Passport passport){
        if(passport.getId() == null){
            em.persist(passport);
        }
        else{
            em.merge(passport);
        }
        return passport;
    }

    //get password and student details
    public Passport getPassportWithStudent(Long id){
        Passport passport = em.find(Passport.class,id);
        return passport;
    }
}
