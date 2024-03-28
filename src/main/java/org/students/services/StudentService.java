package org.students.services;

import org.students.domain.Student;

import java.util.List;

public interface StudentService {
    void addStudent(Student student);

    Student getStudentById(int id);

    void updateStudent(Student updatedStudent);

    void deleteStudent(int id);

    List<Student> getAllStudents();
}
