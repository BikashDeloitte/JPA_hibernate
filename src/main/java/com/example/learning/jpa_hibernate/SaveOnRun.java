package com.example.learning.jpa_hibernate;

import com.example.learning.jpa_hibernate.entity.Course;
import com.example.learning.jpa_hibernate.entity.Passport;
import com.example.learning.jpa_hibernate.entity.Student;
import com.example.learning.jpa_hibernate.repository.CourseRepository;
import com.example.learning.jpa_hibernate.repository.PassportRepository;
import com.example.learning.jpa_hibernate.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SaveOnRun implements CommandLineRunner {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CourseRepository courseRepository;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    PassportRepository passportRepository;

    @Override
    public void run(String... args) {

        //getting course detail by id
        Course course = courseRepository.findById(1001L);
        logger.info("Course -> {} {}",course.getId(), course.getName());

        //deleting a course by Id
        courseRepository.deleteById(1004L);

        //save new course
        courseRepository.save(new Course("Hindi"));
        logger.info("Course add Hindi");

        //update course
        courseRepository.save(new Course(1001L,"Chemistry-Update"));
        Course course1 = courseRepository.findById(1001L);
        logger.info("Course update -> {} ",course1);

        //entityManager tracing behaviour.
        Course course2 = courseRepository.entityManagerBehaviour(new Course("Java"));
        logger.info("Course name - java to -> {}", course2);

        //stop entityManager tracing behaviour.
        Course course3 = courseRepository.entityManagerBehaviour2(new Course("Python"));
        logger.info("Course name - python to -> {}", course3);

        //get all courses details
        List<Course> allCourses = courseRepository.getAllCourses();
        logger.info("All course detail");
        allCourses.forEach(ele -> logger.info("course -> {} ",ele));

        //get all course name
        logger.info("name of course -> {} ",courseRepository.getAllCourseName());

        //get course by name
        logger.info("Course by name -> {}",courseRepository.findByName("English"));

        //saving student with passport.
        logger.info("Student with passport -> {} ",studentRepository.saveStudentWithPassword(
                new Student("Hinata","", "Hyuga"), new Passport("NARUTO1234")
        ));

        //Lazy fetching of student and passport
        studentRepository.getStudentDetails(205L);

        //bidirectional mapping
        Passport passport = passportRepository.getPassportWithStudent(303L);
        logger.info("Password -> {}",passport);
        logger.info("Student for Passport -> {}",passport.getStudent());

    }
}
