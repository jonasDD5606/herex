package com.company.data;

import com.company.*;

import java.sql.*;
import java.util.ArrayList;

public class StudentDao extends Dao {

    public void getDataFromGui() {

    }

    public void getStudentData(int id){
        String query = "select firstname, lastname, email, confirmed, role from users where id = "+ id +";"
                , fname = "", lname = "", email = "", role = "", conf = "";
        try{
            Statement stmt = getConn().createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                fname = rs.getString("firstname");
                lname = rs.getString("lastname");
                email = rs.getString("email");
                role = rs.getString("role");
                conf = rs.getString("confirmed");
            }
            Session.current = new User(id, fname, lname,email,role,conf);
            User.setCurUser(Session.current);
            rs.close();
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public static void askQuestion(String question, int receiver) throws SQLException {
        Connection c = getConn();
        int qid = 0;
        String sql = "INSERT INTO `questions`(`id`, `question`, `awnser`, `awnsered`) VALUES (NULL,'" + question + "', NULL,'F')";
        PreparedStatement ps = c.prepareStatement(sql);
        ps.executeUpdate();
        String sql3 = "SELECT `id`FROM `questions` WHERE question = '" + question + "';";
        Statement stmt = getConn().createStatement();
        ResultSet rs = stmt.executeQuery(sql3);
        while(rs.next()) {
            qid = rs.getInt("id");
        }
        String sql2 = "INSERT INTO users_questions VALUES (" + qid + ", " + Session.current.getId() + ", " + receiver + ")";
        PreparedStatement ps2 = c.prepareStatement(sql2);
        ps2.executeUpdate();
        conn.close();

    }
    public static ArrayList<Tutor> getTutorsPerStudent(int id) throws SQLException {
        ArrayList<Tutor> tutors = getTutorAndCourse(id);
        tutors = addTutorNameToArray(tutors);
        tutors = addCourseNameToArray(tutors);



        return tutors;
    }
    public static ArrayList<Tutor> getTutorAndCourse(int student) throws SQLException {
        int tid = 0, cid = 0;
        ArrayList<Tutor> tutors = new ArrayList<Tutor>();
        String sql = "SELECT tid, cid, confirmed FROM `tutoring` WHERE sid = " + student + ";";
        System.out.println(sql);
        Tutor tutor = null;
        Statement statement = getConn().createStatement();
        ResultSet rs = statement.executeQuery(sql);
        while(rs.next()){
            if (rs.getString("confirmed").equals("T")){
                tid = rs.getInt("tid");
                cid = rs.getInt("cid");
                Course crs = new Course(cid);
                tutor = new Tutor(tid);
                tutor.setCourse(crs);
                tutors.add(tutor);
            }
        }
        return tutors;
    }
    public static ArrayList<Tutor> addTutorNameToArray(ArrayList<Tutor> tutors ) throws SQLException {
        for (int i = 0; i < tutors.size(); i++){
            String sql = "SELECT * FROM users WHERE id=" + tutors.get(i).getId() + ";";
            Statement stmt = getConn().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                tutors.get(i).setTutorName(rs.getString("firstname") + " " + rs.getString("lastname"));

            }
        }
        return tutors;
    }
    public static ArrayList<Tutor> addCourseNameToArray(ArrayList<Tutor> tutors) throws SQLException {
        for(int i = 0; i < tutors.size(); i++){
            String cname;
            int id,  year , admin;

            String sql3 = "SELECT * FROM `courses` WHERE id =" + tutors.get(i).getCourse().getId()+";";
            Statement stmt = getConn().createStatement();
            ResultSet rs = stmt.executeQuery(sql3);
            while(rs.next()){
                cname = rs.getString("name");
                id = rs.getInt("id");
                year = rs.getInt("year");
                admin = rs.getInt("admin");
                Course crs = new Course(id, cname, year, admin);
                tutors.get(i).setCourse(crs);
            }

        }


        return tutors;
    }



}
