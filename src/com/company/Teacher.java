package com.company;

import com.company.data.CourseDao;

public class Teacher extends User{
    CourseDao dao = new CourseDao();
    public Teacher(int uid, String fn, String ln, String mail, String rol ,String bev) {
        super(uid, fn, ln, mail, rol, bev);
    }
    public Teacher(){
        super(Session.current.getId(), Session.current.getFirstname(), Session.current.getLastname(), Session.current.getEmail(), Session.current.getRole(),"T");
    }
}
