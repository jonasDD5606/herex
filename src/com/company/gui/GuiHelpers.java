package com.company.gui;

import com.company.*;
import com.company.data.AdminDao;
import com.company.data.Dao;
import com.company.data.StudentDao;

import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;

public class GuiHelpers extends MainFrame{
    public GuiHelpers() throws IOException, SQLException {
    }

    public static void showCourses() throws SQLException {
        StudentDao dao = new StudentDao();
        dao.getAllCourses();
        for(int i = 0; i < Course.courses.size(); i++){
            Course course = Course.courses.get(i);

        }

    }
    static Course targetCourse;
    static Tutor targetTutor;
    public static void setTargetCourse(Course course){
        targetCourse = course;
    }

    public Course getTargetCourse() {
        return targetCourse;
    }




}
