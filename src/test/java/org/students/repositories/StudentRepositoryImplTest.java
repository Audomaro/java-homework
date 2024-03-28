package org.students.repositories;

import jdk.jfr.Description;
import org.junit.jupiter.api.*;
import org.students.domain.Student;

import static org.junit.jupiter.api.Assertions.*;

class StudentRepositoryImplTest {

    private static StudentRepository studentRepository;

    @BeforeEach
     void setUp() {
        studentRepository = new StudentRepositoryImpl();
        studentRepository.addStudent(new Student("Jonh", 19));
        studentRepository.addStudent(new Student("Bob", 22));
        studentRepository.addStudent(new Student("Gina", 18));
        studentRepository.addStudent(new Student("Mary", 23));
    }

    @Test
    @DisplayName("Add new student")
    @Tag("Student_Repository")
    void addStudent() {
        Student newStudent = new Student("Tina", 10);
        studentRepository.addStudent(newStudent);

        int expectedSize = 5;
        int actualSize = studentRepository.getAllStudents().size();
        assertEquals(expectedSize, actualSize);
    }

    @Test
    @DisplayName("Find student by ID")
    @Tag("Student_Repository")
    void getStudentById() {
        Student student = studentRepository.getStudentById(1);
        assertNotNull(student);
        assertEquals("Jonh", student.getName());
    }

    @Test
    @DisplayName("Update name of student with ID 1")
    @Tag("Student_Repository")
    void updateStudent() {
        Student studentOldInfo = studentRepository.getStudentById(1);
        studentOldInfo.setName("Jonh Update");
        studentRepository.updateStudent(studentOldInfo);

        Student updatedStudent = studentRepository.getStudentById(1);
        String expectedName = "Jonh Update";

        assertEquals(expectedName, updatedStudent.getName());
    }

    @Test
    @DisplayName("Delete student with ID 2 and 3")
    @Tag("Student_Repository")
    void deleteStudent() {
        studentRepository.deleteStudent(2);
        studentRepository.deleteStudent(3);

        int expectedSize = 2;
        int actualSize = studentRepository.getAllStudents().size();

        assertEquals(expectedSize, actualSize);
        assertNull(studentRepository.getStudentById(2));
        assertNull(studentRepository.getStudentById(3));
    }

    @Test
    @DisplayName("Get all students")
    @Tag("Student_Repository")
    void getAllStudents() {
        int expectedSize = 4;
        int actualSize = studentRepository.getAllStudents().size();

        assertEquals(expectedSize, actualSize);
    }
}