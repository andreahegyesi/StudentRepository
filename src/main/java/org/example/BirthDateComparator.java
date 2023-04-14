package org.example;
import java.util.Comparator;
public class BirthDateComparator implements Comparator<Student> {
    public int compare(Student s1, Student s2) {
        return s1.dateOfBirth.compareTo(s2.dateOfBirth);
    }
}
