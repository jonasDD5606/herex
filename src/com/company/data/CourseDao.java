package com.company.data;

import com.company.Session;
import com.company.Student;
import com.company.Tutor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CourseDao extends Dao{
    public static void getAllTutorsPerCourse(int courseid) {
        Session.tutorPerCourse = new ArrayList<Tutor>();
        String query = "SELECT uid FROM users_courses RIGHT JOIN users ON users_courses.uid = users.id WHERE (cid=" + courseid + " AND role = 'T');";
        int id = 0;
        String tutorname;
        String role;
        try {
            Statement stmt = getConn().createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                id = rs.getInt("uid");
                String query2 = "SELECT firstname, lastname FROM users WHERE id =" + id +";";
                Statement statement = getConn().createStatement();
                ResultSet result = statement.executeQuery(query2);
                while(result.next()){
                    tutorname = result.getString("firstname") +" " + result.getString("lastname");
                    Tutor tutor = new Tutor(tutorname, id);
                    Session.tutorPerCourse.add(tutor);
                }

            }


            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public static void getAllStudentPerCourse(int courseid) {
        Session.studentPerCourse = new ArrayList<Student>();
        String query = "SELECT uid FROM users_courses RIGHT JOIN users ON users_courses.uid = users.id WHERE (cid=" + courseid + " AND role = 'S');";
        System.out.println(query);
        int id = 0;
        String tutorname;
        String role;
        try {
            Statement stmt = getConn().createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                id = rs.getInt("uid");
                System.out.println(id);


            }
            String query2 = "SELECT firstname, lastname FROM users WHERE id =" + id +";";
            System.out.println(query2);
            Statement statement = getConn().createStatement();
            ResultSet result = statement.executeQuery(query2);
            while(result.next()){
                String fname = result.getString("firstname");
                String lname= result.getString("lastname");
                Student student = new Student(id, fname, lname);
                Session.studentPerCourse.add(student);
            }

            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public static void giveTutorPerCourse(int course, int student) throws SQLException {
        String sql = "INSERT INTO `users_courses`(`cid`, `uid`) VALUES ('"+ course + "','" + student + "')";
        PreparedStatement ps = getConn().prepareStatement(sql);
        ps.executeUpdate();

    }
    public static void insertCour(String name, int year) throws SQLException {
        String sql = "INSERT INTO courses ( id, name, year) VALUES (NULL, '" + name +"', " + year + " )";
        System.out.println(sql);
        PreparedStatement ps = getConn().prepareStatement(sql);
        ps.executeUpdate();
    }
}
