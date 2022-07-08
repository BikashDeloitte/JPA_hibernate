package com.example.learning.jpa_hibernate.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Setter @Getter @NoArgsConstructor @AllArgsConstructor
public class Passport {

    @Id
    @GeneratedValue
    private Long id;
    @NonNull
    private String number;

    /*
    * mappdeBy -> means extra column passport which is in Student table will be use here instead of student_id column.
    *
    * */
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "passport")
    private Student student;

    public Passport(@NonNull String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Passport{" +
                "id=" + id +
                ", number='" + number + '\'' +
                '}';
    }
}
