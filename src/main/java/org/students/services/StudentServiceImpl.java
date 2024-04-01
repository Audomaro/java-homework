package org.students.services;

import org.students.dao.DAO;
import org.students.dao.DAOFactory;
import org.students.domain.Student;
import org.students.dao.InMemoryDAO;

import java.util.List;

public class StudentServiceImpl implements StudentService {
    private DAO studentDAO;

    public StudentServiceImpl() {
        this.studentDAO = DAOFactory.studentDAO();
    }

    public StudentServiceImpl(DAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    @Override
    public Student addStudent(Student student) {
        return studentDAO.insert(student);
    }

    @Override
    public Student getStudent(int id) {
        return studentDAO.findByID(id);
    }

    @Override
    public boolean updateStudent(Student updatedStudent) {
        return studentDAO.update(updatedStudent);
    }

    @Override
    public boolean deleteStudent(int id) {
        return studentDAO.delete(id);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentDAO.findAll();
    }

    public void setDAO(DAO studentDAO) {
        this.studentDAO = studentDAO;
    }
}