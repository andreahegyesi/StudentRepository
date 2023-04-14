package org.example;
import java.time.LocalDate;
import java.util.*;
import java.time.*;
/**
 * Creating a Student repository with methods that validate inputs and throw exceptions if necessary
 */
public class StudRepo {
    static LocalDate currentDate = LocalDate.now();
    List<Student> students = new ArrayList<>();

    public static void main(String[] args) {
        StudRepo studRepo = new StudRepo();
        try {
            studRepo.addStudent("Andrea", "Hegyesi", LocalDate.of(1995, 7, 12), "F", "2950712313232");
            studRepo.addStudent("John", "Snow", LocalDate.of(1988, 12, 24), "male", "1881224195796");
            studRepo.addStudent("Arya", "Stark", LocalDate.of(2002, 2, 5), "FEMALE", "1920216418111");
            studRepo.listStudentsByLastName();
            studRepo.listStudentsByBirthDate();
            studRepo.deleteStudent("1881224195796");
            studRepo.listStudentsByLastName();
            studRepo.retrieveByAge(27);
        } catch (ValidationException e) {
            System.out.println("Please input a valid student. " + e.getMessage());
        }
    }

    /**
     * Adding a student
     *
     * @param firstName    should not be empty
     * @param lastName     should not be empty
     * @param dateOfBirth  between 1900 and current year -18
     * @param genderString should be male or female (or M and F), upper / lower case should both be accepted
     * @param id           must have 13 characters, it is validated based on the last number
     * @return list of students
     * @throws ValidationException
     */
    public List<Student> addStudent(String firstName, String lastName, LocalDate dateOfBirth, String genderString, String id) throws ValidationException {
        if ((firstName == null) || (firstName.equalsIgnoreCase("")) || (lastName == null)
                || (lastName.equalsIgnoreCase(""))) {
            throw new ValidationException("First name and Last name should not be empty.");
        }
        if ((dateOfBirth.getYear() < 1900) || (dateOfBirth.getYear() > currentDate.getYear() - 18)) {
            throw new ValidationException("Date of birth should be between 1900 and current year - 18.");
        }
        if (!((genderString.equalsIgnoreCase("FEMALE"))
                || (genderString.equalsIgnoreCase("MALE"))
                || (genderString.equalsIgnoreCase("F"))
                || genderString.equalsIgnoreCase("M"))) {
            throw new ValidationException("Gender should be male or female.");
        }
        if (id.length() != 13) {
            throw new ValidationException("Id must have 13 characters.");
        }
        String testId = "279146358279";
        int count = 0;
        int lastNumofId = Integer.parseInt(String.valueOf(id.charAt(12)));
        for (int i = 0; i < testId.length(); i++) {
            count += Integer.parseInt(String.valueOf(testId.charAt(i))) * Integer.parseInt(String.valueOf(id.charAt(i)));
        }
        if (((count % 11 == 10) && (lastNumofId != 1)) || (((count % 11) < 10) && (lastNumofId != count % 11))) {
            throw new ValidationException("Id is not valid");
        }
        if ((genderString.equalsIgnoreCase("FEMALE"))
                || (genderString.equalsIgnoreCase("F"))) {
            students.add(new Student(firstName, lastName, dateOfBirth, Gender.FEMALE, id));
        } else {
            students.add(new Student(firstName, lastName, dateOfBirth, Gender.MALE, id));
        }
        return students;
    }

    /**
     * Deleting a student by ID (identifier)
     *
     * @param idToRemove should not be empty
     * @return list of students
     * @throws ValidationException
     */
    public List<Student> deleteStudent(String idToRemove) throws ValidationException {
        if (idToRemove == null || idToRemove.equals("")) {
            throw new ValidationException("ID is empty.");
        }
        boolean found = false;
        for (Student student : students) {
            if (((student.id).equals(idToRemove))) {
                found = true;
                students.remove(student);
                System.out.println("Student with ID " + idToRemove + " was deleted");
                break;
            }
        }
        if (!found) {
            throw new ValidationException("Student with ID " + idToRemove + " was not found.");
        }
        return students;
    }

    /**
     * Calculating the age of a student
     *
     * @param student
     * @return age
     */
    public int calculateAge(Student student) {
        int studentAge = Period.between(student.dateOfBirth, currentDate).getYears();
        return studentAge;
    }

    /**
     * Retrieving all students with a given age
     *
     * @param givenAge should be a number and should not be negative
     * @return a list with students with the given age
     * @throws ValidationException
     */
    public List<Student> retrieveByAge(int givenAge) throws ValidationException {
        List<Student> studentsWithGivenAge = new ArrayList<>();
        if (givenAge < 0) {
            throw new ValidationException("Age can't be negative.");
        }
        try {
            givenAge = Integer.parseInt(String.valueOf(givenAge));
        } catch (NumberFormatException e) {
            throw new ValidationException("Age is not a number");
        }
        for (Student student : students) {
            if (calculateAge(student) == givenAge) {
                studentsWithGivenAge.add(student);
            }
        }
        System.out.println("Retrieving by age " + givenAge + ": " + studentsWithGivenAge);
        return studentsWithGivenAge;
    }

    /**
     * Listing students ordered by last name
     *
     * @return ordered list
     * @throws ValidationException
     */
    public List<Student> listStudentsByLastName() throws ValidationException {
        for (Student student : students) {
            if (student == null) {
                throw new ValidationException("Empty input.");
            }
        }
        Collections.sort(students, new LastNameComparator());
        System.out.println("Ordered by last name: " + students);
        return students;
    }

    /**
     * Listing students ordered by birth date
     *
     * @return ordered list
     * @throws ValidationException
     */
    public List<Student> listStudentsByBirthDate() throws ValidationException {
        for (Student student : students) {
            if (student == null) {
                throw new ValidationException("Empty input.");
            }
        }
        Collections.sort(students, new BirthDateComparator());
        System.out.println("Ordered by birth dates: " + students);
        return students;
    }
}
