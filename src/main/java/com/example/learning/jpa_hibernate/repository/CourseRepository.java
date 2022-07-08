package com.example.learning.jpa_hibernate.repository;

import com.example.learning.jpa_hibernate.entity.Course;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Transactional //as we are preforming deleting we need transactional
public class CourseRepository {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EntityManager em;

    public Course findById(Long id){
        return em.find(Course.class,id);
    }


    /*
    * for deleting find the course first then remove the course using entityManager
    * */
    public void deleteById(Long id){
        Course course = findById(id);
        em.remove(course);
    }

    /*
    * if course contain id means course have to update (merge() method)
    * if course don't contains id mean new course is added (persist() method)
    * */
    public Course save(Course course){
        if(course.getId() == null){
            em.persist(course);
        }else{
            em.merge(course);
        }

        return course;
    }

    /*
    * if we are in Transactional then entityManager will keep track of change done by entityManager.
    * In this course name will be change even if we save first then change course name
    * */
    public Course entityManagerBehaviour(Course course){
        save(course);
        course.setName(course.getName().concat(" -  updated by entityManager"));
        return findById(course.getId());
    }

    /*
    * flush() - write all changes to the database before the transaction is committed.
    * detach(entity) - this method will stop entityManager from further tracing.
    * clear() - will stop all entities from tracing..
    * refresh() - remove the extra change made after saving the data in database.
    * */
    public Course entityManagerBehaviour2(Course course){
        save(course);
        Long id = course.getId();
        em.flush();

        course.setName(course.getName().concat(" -  flush() method"));
        logger.info("Course after flush and before refresh -> {} ",findById(id).getName());

        em.refresh(course);
        logger.info("Course after flush and refresh -> {} ",findById(id).getName());

        em.detach(course);

        course.setName(course.getName().concat(" -  updated by entityManager"));
        return findById(id);
    }

    /*
    * TypedQuery<Entity> -> here we have to provide query type.
    * i.e -> TypedQuery<Course> query1 = em.createQuery("select c from Course c",Course.class)
    *
    * Query -> we don't have to provide query type.
    * i.e -> Query query = em.createQuery("select c from Course c")
    *
    * createQuery() -> we write jpql query in this method.Like above example.
    * createNativeQuery() -> we write sql query in this method, also we have to use table, column same as in database.
    * createNamedQuery() -> we provide the name of @NamedQuery in this method.
    *
    * setParameter() -> this method is use for user input in query by name, position ,etc. Can be use in both sql and jpql.
    * */
    public List<Course> getAllCourses(){
        TypedQuery<Course> query1 = em.createNamedQuery("get_all_courses",Course.class);
        logger.info("Select c from Course c -> executed ");
        return query1.getResultList();
    }

    public List<String> getAllCourseName(){
        Query query = em.createQuery("select c.name from Course c ");
        return query.getResultList();
    }

    public Course findByName(String name){
        Query query = em.createNativeQuery("select * from course_details where name = :name",Course.class);
        query.setParameter("name",name);
        return (Course) query.getSingleResult();
    }


}
