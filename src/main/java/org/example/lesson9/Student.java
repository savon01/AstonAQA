package org.example.lesson9;

public class Student {
    private String name;
    private int age;
    private String gender; // "M" для мужского, "F" для женского

    public Student(String name, int age, String gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Student{name='" + name + "', age=" + age + ", gender='" + gender + "'}";
    }
}