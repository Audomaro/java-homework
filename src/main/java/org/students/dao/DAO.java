package org.students.dao;

import org.students.domain.Student;

import java.util.List;

public interface DAO {
    Student insert(Student student);

    Student findByID(int id);

    boolean update(Student updatedStudent);

    boolean delete(int id);

    List<Student> findAll();
}
