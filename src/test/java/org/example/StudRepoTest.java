package org.example;
import java.util.*;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.function.Executable;
class StudRepoTest {
    @org.junit.jupiter.api.Test
    void givenValidStudentExpectedListSize() throws ValidationException {
        StudRepo testRepository = new StudRepo();
        List<Student> studentList = new ArrayList<>();
        assertEquals(1, (testRepository.addStudent("And", "He", LocalDate.of(1995, 7, 12), "F", "1920216419912")).size());

    }
    @org.junit.jupiter.api.Test
    void givenEmptyFirstNameExpectedException() throws ValidationException {
        Exception exception = assertThrows(ValidationException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                StudRepo testRepository = new StudRepo();
                testRepository.addStudent("", "He", LocalDate.of(1995, 7, 12), "female", "1920216419912");
            }
        }, "First name and Last name should not be empty.");
    }

    @org.junit.jupiter.api.Test
    void givenDateOfBirthBefore1900ExpectedException() throws ValidationException {
        Exception exception = assertThrows(ValidationException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                StudRepo testRepository = new StudRepo();
                testRepository.addStudent("And", "He", LocalDate.of(1771, 7, 12), "female", "1920216417492");
            }
        }, "Date of birth should be between 1900 and current year - 18.");
    }
    @org.junit.jupiter.api.Test
    void givenWrongGenderExpectedException() {
        Exception exception = assertThrows(ValidationException.class, () -> {
            StudRepo testRepository = new StudRepo();
            testRepository.addStudent("And", "He", LocalDate.of(1995, 7, 12), "femal", "1920216419912");
        });

        String expectedMessage = "Gender should be male or female.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @org.junit.jupiter.api.Test
    void givenEmptyIdToDeleteStudentExpectedException() throws ValidationException {
        Exception exception = assertThrows(ValidationException.class, () -> {
            StudRepo testRepository = new StudRepo();
            testRepository.deleteStudent("");
        });

        String expectedMessage = "ID is empty.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @org.junit.jupiter.api.Test
    void givenStudentCalculateAge() {
        StudRepo testRepository = new StudRepo();
        Student testStudent = new Student("And", "He", LocalDate.of(1989, 1, 21), Gender.FEMALE, "1920216419939");
        assertEquals(34,testRepository.calculateAge(testStudent));
    }

    @org.junit.jupiter.api.Test
    void testRetrieveByAge() throws ValidationException {
        StudRepo testRepository = new StudRepo();
        testRepository.addStudent("A","Hi", LocalDate.of(1995, 7, 12), "female", "1920216416933" );
        testRepository.addStudent("B", "La", LocalDate.of(1973, 2, 11), "male", "1920216415376");
        testRepository.addStudent("C", "Vd", LocalDate.of(1975, 11, 11), "female", "1920216417628");
        assertEquals("Hi", testRepository.retrieveByAge(27).get(0).lastName);
    }
}