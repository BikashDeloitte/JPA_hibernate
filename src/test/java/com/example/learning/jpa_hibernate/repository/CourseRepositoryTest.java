package com.example.learning.jpa_hibernate.repository;

import com.example.learning.jpa_hibernate.entity.Course;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
class CourseRepositoryTest {

    /*
    * Autowired is use because we are using MangerEntity in CourseRepository
    * */
    @Autowired
    CourseRepository courseRepository;

    /*
    * @DirtiesContext tells the testing framework to close and recreate the context for later tests.
    * */
    @Test
    @DirtiesContext
    public void deleteByIdTest(){
        courseRepository.deleteById(1003L);
        assertNull(courseRepository.findById(1003L));
    }

    @Test
    public void findByIdTest(){
        Course course = courseRepository.findById(1003L);
        assertEquals("Computer",course.getName());
    }

    @Test
    @DirtiesContext
    public void saveTest(){
        //update
        Course course = courseRepository.findById(1003L);
        assertEquals("Computer",course.getName());

        courseRepository.save(new Course(1003L,"update"));
        Course course1 = courseRepository.findById(1003L);
        assertEquals("update",course1.getName());
    }

    @Test
    @DirtiesContext
    public void entityManagerBehaviourTest(){
        Course course = courseRepository.entityManagerBehaviour(new Course("Biology"));
        assertEquals("Biology -  updated by entityManager",course.getName());
    }

    @Test
    @DirtiesContext
    public void entityManagerBehaviour2Test(){
        Course course = courseRepository.entityManagerBehaviour2(new Course("Biology"));
        assertEquals("Biology",course.getName());
    }
}