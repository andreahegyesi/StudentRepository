package org.example;
import java.util.Comparator;
public class LastNameComparator implements Comparator <Student> {
    @Override
    public int compare(Student s1, Student s2){
        return s1.lastName.compareTo(s2.lastName);
    }
}
