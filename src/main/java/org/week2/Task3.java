package org.week2;

import org.week1.Main;

import java.util.ArrayList;
import java.util.List;

public class Task3 {
    public static void main(String[] args) {
        List<Student> list = new ArrayList<Student>();

        list.add(new Student("alpha","s"));
        list.add(new Student("mega","s"));
        list.add(new Student("zeta","s"));

        for (Student a : list) {
            System.out.println(a.toString());
        }

    }
}
