package org.students.app;

import org.students.domain.Student;
import org.students.repositories.StudentRepositoryImpl;
import org.students.services.StudentServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        StudentRepositoryImpl studentRepository = new StudentRepositoryImpl();
        StudentServiceImpl studentService = new StudentServiceImpl(studentRepository);

        studentService.addStudent(new Student("John", 20));
        studentService.addStudent(new Student("Alice", 22));
        studentService.addStudent(new Student("Bob", 33));
        studentService.addStudent(new Student("Jane", 44));

        // Get all students
        System.out.println("=============== Listar todos los estudiantes");
        List<Student> students = studentService.getAllStudents();
        for (Student student : students) {
            System.out.println(student);
        }

        System.out.println("=============== Estudiante con ID 2");

        Student studentById = studentService.getStudentById(2);
        System.out.println("Estudiante con ID 2: " + studentById.getName());

        System.out.println("=============== Actualizar estudiante con ID 2");
        studentById.setName("Alice Update");
        studentService.updateStudent(studentById);

        studentById = studentService.getStudentById(2);
        System.out.println("Estudiante con ID 2: " + studentById.getName());

        System.out.println("=============== Eliminar estudiante con ID 1 y 5");
        studentService.deleteStudent(1);
        studentService.deleteStudent(5);

        List<Student> updatedStudents = studentService.getAllStudents();
        System.out.println("Estudiantes después de eliminar:");
        for (Student student : updatedStudents) {
            System.out.println(student);
        }
    }
}
