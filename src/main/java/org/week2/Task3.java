package org.week2;

import org.week1.Main;

import java.util.ArrayList;
import java.util.List;

public class Task3 {
    public static void main(String[] args) {
        List<Student> list = new ArrayList<>();

        list.add(new Student("alpha","10"));
        list.add(new Student("mega","11"));
        list.add(new Student("zeta","01"));

        for (Student a : list) {
            System.out.println(a.toString());
        }

        list.size();

    }
}
