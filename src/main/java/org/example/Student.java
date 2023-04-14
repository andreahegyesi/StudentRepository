package org.example;
import java.time.*;
/**
 * For each Student the following information needs to be collected:
 * First Name
 * Last Name
 * Date of Birth
 * Gender
 * ID (CNP)
 */
public class Student {
    String firstName;
    String lastName;
    LocalDate dateOfBirth;
    Gender gender;
    String id;

    public Student(String firstName, String lastName, LocalDate dateOfBirth, Gender gender, String id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.id = id;
    }

    @Override
    public String toString() {
        return "Student{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", gender=" + gender +
                ", id=" + id +
                '}';
    }
}
