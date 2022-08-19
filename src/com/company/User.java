package com.company;

import com.company.data.StudentDao;
import com.company.data.CourseDao;
import com.company.data.TutorDao;

public class User {
    private int id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String role;
    private String confirmed;

    public User(int id){
        this.id = id;
    }
    public static void setCurUser(User user){
        if (user.getRole().equals("S")){
            Session.current = new Student(user.id, user.firstname, user.lastname, user.email, user.role, user.confirmed);
            Session.dao = new StudentDao();
            //System.out.println("s");
        }else if(user.getRole().equals("L")){
            Session.dao = new CourseDao();
            Session.current = new Teacher(user.id, user.firstname, user.lastname, user.email, user.role, user.confirmed);
            //System.out.println("l");
        }else if(user.getRole().equals("T")){
            Session.dao = new TutorDao();
            if(user.getConfirmed().equals("T")){
                Session.current = new Tutor(user.id, user.firstname, user.lastname, user.email, user.role, user.confirmed);
                //System.out.println("t");

            }else{

            }
        }
    }
    public void getUserDataLogin(){

    }
    public User(int id, String fname, String lname){
        this.firstname = fname;
        this.id = id;
        this.lastname = lname;
    }
    public User(int uid, String fn, String ln, String mail, String rol, String bev){
        this.id = uid;
        this.firstname = fn;
        this.lastname = ln;
        this.email = mail;
        this.role = rol;
        this.confirmed = bev;

    }
    public User(String email){
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public String getConfirmed() {
        return confirmed;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(String role) {
        this.role = role;
    }
    public void setConfirmed(String conf){
        this.confirmed = conf;
    }
}
