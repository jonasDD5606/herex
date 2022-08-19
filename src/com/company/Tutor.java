package com.company;

import com.company.data.TutorDao;

import java.util.ArrayList;

public class Tutor extends  User{
    public  Course course;
    private String tutorName;
    public Tutor(String name, int id){
        super(id);
        this.tutorName = name;

    }
    public static ArrayList<Course> courselist;
    public Tutor(int uid, String fn, String ln, String mail, String rol, String bev) {
        super(uid, fn, ln, mail, rol, bev);
    }
    public Tutor(){
        super(Session.current.getId(), Session.current.getFirstname(), Session.current.getLastname(), Session.current.getEmail(), Session.current.getRole(),"T");
    }
    public Tutor(int id){
        super(id);
    }

    public String getTutorName() {
        return tutorName;
    }

    public void setTutorName(String tutorName) {
        this.tutorName = tutorName;
    }

    public Course getCourse() {
        return course;
    }
    public void setCourse(Course course) {
        this.course = course;
    }
}
