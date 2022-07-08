package com.example.learning.jpa_hibernate.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter @Setter @NoArgsConstructor @ToString @AllArgsConstructor
public class Review {

    @Id
    @GeneratedValue
    private Long id;
    private int rating;
    private String description;

    public Review(int rating, String description) {
        this.rating = rating;
        this.description = description;
    }
}
