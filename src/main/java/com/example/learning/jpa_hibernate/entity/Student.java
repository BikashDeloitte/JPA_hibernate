package com.example.learning.jpa_hibernate.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue
    private Long id;
    @NonNull
    private String firstName;
    private String middleName;
    private String lastName;

    /*
    * FetchType.EAGER ->  it will fetch all the data even it is required or not.
    * i.e -> it will fetch student as well as password details, even if you ask for student details only.
    *
    * FetchType.LAZY -> it will fetch only required data not the whole data. This may lead to LazyInitializationException.
    * LazyInitializationException is cause due to not having Passport detail, solution is to use @Transactional annotation.
    * i.e -> it will fetch student details only, not passport if it is not asked.
    * */
    @OneToOne(fetch = FetchType.LAZY)
    private Passport passport;

    public Student(String firstName, String middleName, String lastName) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
