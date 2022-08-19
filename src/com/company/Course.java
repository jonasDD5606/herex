package com.company;

import java.util.ArrayList;

public class Course {
    int id;
    String name;
    int year;
    int admin;
    public static ArrayList<Course> courses = new ArrayList<Course>();
    public Course(int id, String name, int year, int admin){
        this.id = id;
        this.name = name;
        this.year = year;
        this.admin = admin;
        Course.courses.add(this);
    }
    public Course(int id){
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Course(String name){
        this.name = name;
    }
}
