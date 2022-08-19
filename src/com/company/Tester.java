package com.company;

import com.company.data.Database;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.SQLException;

public class Tester {
    public static boolean testInternet(){
        boolean isConnected;
        try {
            URL url = new URL("http://www.google.com");
            URLConnection connection = url.openConnection();
            connection.connect();
            //System.out.println("Internet is connected");
            isConnected = true;
        } catch (MalformedURLException e) {
            //System.out.println("Internet is not connected");
            isConnected = false;
        } catch (IOException e) {
            //System.out.println("Internet is not connected");
            isConnected = false;
        }
        return isConnected;
    }
    public static boolean testSql(){
        boolean sqlTest;
        try{
            Connection conn = Database.getInstance().getConnection();
            sqlTest = true;
            conn.close();
        } catch (SQLException throwables) {
            sqlTest = false;
            throwables.printStackTrace();
        }
        return sqlTest;
    }

}