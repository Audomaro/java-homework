package org.students.app;

import org.students.dao.DAO;
import org.students.dao.DAOFactory;
import org.students.domain.Student;
import org.students.dao.InMemoryDAO;
import org.students.services.StudentServiceImpl;

public class Main {
    public static void main(String[] args) {
        StudentServiceImpl studentService = DAOFactory.studentService();

        studentService.addStudent(new Student("John", 20));
        studentService.addStudent(new Student("Alice", 22));
        studentService.addStudent(new Student("Bob", 33));
        studentService.addStudent(new Student("Jane", 44));

        // Get all students
        System.out.println("=============== Listar todos los estudiantes");
        studentService.getAllStudents().forEach(System.out::println);

        System.out.println("=============== Estudiante con ID 2");

        Student studentById = studentService.getStudent(2);
        System.out.println("Estudiante con ID 2: " + studentById.getName());

        System.out.println("=============== Actualizar estudiante con ID 2");
        studentById.setName("Alice Update");
        studentService.updateStudent(studentById);

        studentById = studentService.getStudent(2);
        System.out.println("Estudiante con ID 2: " + studentById.getName());

        System.out.println("=============== Eliminar estudiante con ID 1 y 5");
        studentService.deleteStudent(1);
        studentService.deleteStudent(5);

        System.out.println("Estudiantes después de eliminar:");
        studentService.getAllStudents().forEach(System.out::println);
    }
}
