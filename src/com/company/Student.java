package com.company;

import com.company.data.StudentDao;

public class Student extends User{
    StudentDao dao = new StudentDao();
    public Student(int uid, String fn, String ln, String mail, String rol, String bev) {
        super(uid, fn, ln, mail, rol, bev);
    }
    public Student(){
        super(Session.current.getId(), Session.current.getFirstname(), Session.current.getLastname(), Session.current.getEmail(), Session.current.getRole(),"T");
    }
    public Student(int id, String firstname, String lastname){
        super(id, firstname, lastname);
    }

}
