package org.students.services;

import org.students.domain.Student;
import org.students.repositories.StudentRepositoryImpl;

import java.util.List;

public class StudentServiceImpl implements StudentService {
    private final StudentRepositoryImpl studentRepository;

    public StudentServiceImpl() {
        this.studentRepository = new StudentRepositoryImpl();
    }

    public StudentServiceImpl(StudentRepositoryImpl studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public void addStudent(Student student) {
        studentRepository.addStudent(student);
    }

    @Override
    public Student getStudentById(int id) {
        return studentRepository.getStudentById(id);
    }

    @Override
    public void updateStudent(Student updatedStudent) {
        studentRepository.updateStudent(updatedStudent);
    }

    @Override
    public void deleteStudent(int id) {
        studentRepository.deleteStudent(id);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.getAllStudents();
    }
}