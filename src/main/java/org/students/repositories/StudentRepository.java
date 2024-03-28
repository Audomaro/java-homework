package org.students.repositories;

import org.students.domain.Student;

import java.util.List;

public interface StudentRepository {
    void addStudent(Student student);

    Student getStudentById(int id);

    void updateStudent(Student updatedStudent);

    void deleteStudent(int id);

    List<Student> getAllStudents();
}
