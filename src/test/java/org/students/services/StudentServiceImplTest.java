package org.students.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.students.domain.Student;
import org.students.repositories.StudentRepository;
import org.students.repositories.StudentRepositoryImpl;

import static org.junit.jupiter.api.Assertions.*;

class StudentServiceImplTest {

    private static StudentService studentService;

    @BeforeEach
    @Tag("Student_Service")
    void setUp() {
        studentService = new StudentServiceImpl(new StudentRepositoryImpl());
        studentService.addStudent(new Student("Jonh", 19));
        studentService.addStudent(new Student("Bob", 22));
        studentService.addStudent(new Student("Gina", 18));
        studentService.addStudent(new Student("Mary", 23));
    }

    @Test
    @DisplayName("Add new student")
    @Tag("Student_Service")
    void addStudent() {
        Student newStudent = new Student("Tina", 10);
        studentService.addStudent(newStudent);

        int expectedSize = 5;
        int actualSize = studentService.getAllStudents().size();
        assertEquals(expectedSize, actualSize);
    }

    @Test
    @DisplayName("Find student by ID")
    @Tag("Student_Service")
    void getStudentById() {
        Student student = studentService.getStudentById(1);
        assertNotNull(student);
        assertEquals("Jonh", student.getName());
    }

    @Test
    @DisplayName("Update name of student with ID 1")
    @Tag("Student_Service")
    void updateStudent() {
        Student studentOldInfo = studentService.getStudentById(1);
        studentOldInfo.setName("Jonh Update");
        studentService.updateStudent(studentOldInfo);

        Student updatedStudent = studentService.getStudentById(1);
        String expectedName = "Jonh Update";

        assertEquals(expectedName, updatedStudent.getName());
    }

    @Test
    @DisplayName("Delete student with ID 2 and 3")
    @Tag("Student_Service")
    void deleteStudent() {
        studentService.deleteStudent(2);
        studentService.deleteStudent(3);

        int expectedSize = 2;
        int actualSize = studentService.getAllStudents().size();

        assertEquals(expectedSize, actualSize);
        assertNull(studentService.getStudentById(2));
        assertNull(studentService.getStudentById(3));
    }

    @Test
    @DisplayName("Get all students")
    @Tag("Student_Service")
    void getAllStudents() {
        int expectedSize = 4;
        int actualSize = studentService.getAllStudents().size();

        assertEquals(expectedSize, actualSize);
    }
}