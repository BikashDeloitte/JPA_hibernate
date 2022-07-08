package com.example.learning.jpa_hibernate.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

/*
* There can be only one @NamedQuery() annotation,
* For multiple @NamedQuery() annotation, they have to be written in the @NamedQueries() annotation.
* */
@Entity
@Getter @Setter @NoArgsConstructor
@Table(name = "CourseDetails")
@NamedQueries(value = {
        @NamedQuery(name = "get_all_courses", query = "select c from Course c"),
        @NamedQuery(name = "all_course_name", query = "select c.name from Course c ")
})
public class Course {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @CreationTimestamp
    private LocalDateTime createdDate;
    @UpdateTimestamp
    private LocalDateTime updatedDate;

    public Course(String name, LocalDateTime createdDate, LocalDateTime updatedDate) {
        this.name = name;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    public Course(String name){
        this.name = name;
    }

    public Course(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                '}';
    }
}
