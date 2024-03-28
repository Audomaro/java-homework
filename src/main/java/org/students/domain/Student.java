package org.students.domain;

public class Student {
    private int id;
    private String name;
    private int age;
    private int grade;

    public Student( String name, int age) {
        this.setName(name);
        this.setAge(age);
    }

    public Student( int id, String name, int age) {
        this.setId(id);
        this.setName(name);
        this.setAge(age);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString(){
        return "[ ID: " + this.getId() + ", Name=" + this.getName() + ", Age=" + this.getName() + " ]";
    }

}
