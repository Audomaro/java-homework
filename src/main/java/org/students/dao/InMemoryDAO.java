package org.students.dao;

import org.students.domain.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryDAO implements DAO {
    private static Map<Integer, Student> students;
    private final AtomicInteger nextId ;

    public InMemoryDAO() {
        this.students = new ConcurrentHashMap<>();
        this.nextId = new AtomicInteger(1);
    }

    @Override
    public Student insert(Student student) {
        student.setId(nextId.getAndIncrement());
        students.put(student.getId(), student);
        return student;
    }

    @Override
    public Student findByID(int id) {
        return students.get(id);
    }

    @Override
    public boolean update(Student updatedStudent) {
        return students.replace(updatedStudent.getId(), updatedStudent) != null;
    }

    @Override
    public boolean delete(int id) {
        return  students.remove(id) != null;
    }

    @Override
    public List<Student> findAll() {
        return new ArrayList<>(students.values());
    }
}