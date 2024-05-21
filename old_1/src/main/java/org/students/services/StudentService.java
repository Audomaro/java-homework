package org.students.services;

import org.students.dao.DAO;
import org.students.domain.Student;

import java.util.List;

public interface StudentService {
    Student addStudent(Student student);

    Student getStudent(int id);

    boolean updateStudent(Student updatedStudent);

    boolean deleteStudent(int id);

    List<Student> getAllStudents();

    public void setDAO(DAO studentDAO);
}
