package org.students.repositories;

import org.students.domain.Student;

import java.util.ArrayList;
import java.util.List;


public class StudentRepositoryImpl implements StudentRepository {
    private final List<Student> students;

    public StudentRepositoryImpl() {
        this.students = new ArrayList<>();
    }

    private int nextId = 1;

    @Override
    public void addStudent(Student student) {
        student.setId(nextId++);
        students.add(student);
    }

    @Override
    public Student getStudentById(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    @Override
    public void updateStudent(Student updatedStudent) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId() == updatedStudent.getId()) {
                students.set(i, updatedStudent);
                return;
            }
        }
    }

    @Override
    public void deleteStudent(int id) {
        students.removeIf(student -> student.getId() == id);
    }

    @Override
    public List<Student> getAllStudents() {
        return students;
    }
}