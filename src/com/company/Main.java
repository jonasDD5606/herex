package com.company;

import com.company.data.*;
import com.company.gui.GuiHelpers;
import com.company.gui.MainFrame;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws SQLException, IOException, IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException {

        if ((Tester.testInternet() == false) && (Tester.testSql() == false)){

        }else{
            /*
            MainFrame mf = new MainFrame();
            // System.out.println(AdminDao.Login("ruben.ruben@gmail.com", "777"));
            // AdminDao.getLineByLine();
            StudentDao dao = new StudentDao();
            dao.getAllCourses();

             */
            //MainFrame mf = new MainFrame();
            //SecretKey key = AES.getKeyFromPassword("hello world", "");
            //System.out.println(AES.encrypt("AES", "hello world", key));
            //System.out.println(key);User user = new User(null, "")
            MainFrame mainFrame = new MainFrame();




    }
}
}
