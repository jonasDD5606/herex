package com.company;

import com.company.data.Dao;

import java.net.URL;
import java.util.ArrayList;

public class Session {
   public static User current;
   public static Dao dao = null;
   public final String url = "http://127.0.0.1/HerEx/";
   public static ArrayList<Tutor> tutorPerCourse;
   public static ArrayList<Tutor> tutorPerStud;
   public static ArrayList<Student> studentPerCourse;

}

