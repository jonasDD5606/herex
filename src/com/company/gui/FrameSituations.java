package com.company.gui;

import com.company.*;
import com.company.data.CourseDao;
import com.company.data.StudentDao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static javax.swing.JComponent.getDefaultLocale;
import static javax.swing.JOptionPane.showMessageDialog;

public class FrameSituations extends JFrame {
    int idtutor = 0, idstudent= 0;
    Dimension screenSize;

    public  void setScreenSize(Dimension screenSize) {
        this.screenSize = screenSize;
    }

    public void clearNav(JPanel pan){
        pan.removeAll();
        pan.setBackground(Color.black);

    }

    public void setTutorCourseSituation(JPanel pnl, int course, int student){
        pnl.removeAll();
        pnl.setVisible(true);
        setScreenSize(Toolkit.getDefaultToolkit().getScreenSize());
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(screenSize.width / 6, screenSize.height / 25));
        JButton searchforstudent = new JButton("Stel je aan als tutor voor dit vak");
        searchforstudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (StudentDao.studentAlreadyInTutoring(course, student)){
                        showMessageDialog(null, "Je tutort al voor dit vak");
                    }else{
                        CourseDao.giveStudentPerCourse(course, student);
                        showMessageDialog(null, "je aanvraag is verwerkt");
                    }
                } catch (SQLException throwables) {
                    showMessageDialog(null, "Je aanvraag zit in de wachtlijst voor dit vak");
                    throwables.printStackTrace();
                }
                searchforstudent.setVisible(false);
            }
        });
        panel.add(searchforstudent);
        pnl.add(panel);
    }
    public void setStudentCourseSituation(JPanel pnl, JPanel goback, int course, int student){
        pnl.removeAll();
        pnl.setVisible(true);
        setScreenSize(Toolkit.getDefaultToolkit().getScreenSize());
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(screenSize.width / 6, screenSize.height / 25));
        JButton searchtutor = new JButton("Zoek een tutor voor dit vak");
        searchtutor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (StudentDao.studentAlreadyInTutoring(course, student)){
                        showMessageDialog(null, "Je wordt al getutord voor dit vak");
                    }else{
                        CourseDao.giveStudentPerCourse(course, student);
                        showMessageDialog(null, "je aanvraag is verwerkt");
                    }

                } catch (SQLException throwables) {
                    showMessageDialog(null, "Je aanvraag zit in de wachtlijst voor dit vak");
                    throwables.printStackTrace();
                }
                searchtutor.setVisible(false);
            }
        });
        panel.add(searchtutor);
        pnl.add(panel);



    }
    public void setTeacherOnCourseSituation(JPanel pnl, JPanel pnl2, int course, JPanel pan3) throws SQLException {
        setScreenSize(Toolkit.getDefaultToolkit().getScreenSize());
        pnl.removeAll();
        pnl2.removeAll();
        pnl.setVisible(true);
        CourseDao.getAllStudentPerCourse(course);
        JPanel welcoms = new JPanel();
        welcoms.add(new JLabel("Studenten : " + GuiHelpers.targetCourse.getName()));
        welcoms.setPreferredSize(new Dimension(screenSize.width / 6, screenSize.height / 25));
        pnl.add(welcoms);
        for(int i = 0; i < Session.studentPerCourse.size(); i++){
            JPanel panel = new JPanel();
            JLabel namelbl = new JLabel(Session.studentPerCourse.get(i).getFirstname() + "" + Session.studentPerCourse.get(i).getLastname());
            JButton matchbtn = new JButton("Koppel");
            int id = Session.studentPerCourse.get(i).getId();
            matchbtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    idstudent = id;
                    if(checkButtons() == true){
                        CourseDao.deleteFromWaitList(course, idtutor);
                        CourseDao.deleteFromWaitList(course, idstudent);
                        CourseDao.askHelp(idtutor, idstudent, course);
                        showMessageDialog(null, "leeringen zijn gekoppeld");
                        try {
                            pnl.removeAll();
                            pnl2.removeAll();
                            setTeacherOnCourseSituation(pnl, pnl2, course,  pan3);
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                        idtutor = 0;
                        idstudent = 0;
                    }
                }
            });
            panel.setPreferredSize(new Dimension(screenSize.width / 6, screenSize.height / 25));
            panel.add(namelbl);
            panel.add(matchbtn);
            panel.setBackground(Color.gray);
            pnl.add(panel);
        }
        pnl2.setVisible(true);
        CourseDao.getAllTutorsPerCourse(course);
        JPanel welcomt = new JPanel();
        welcomt.add(new JLabel("Tutors : " + GuiHelpers.targetCourse.getName()));
        welcomt.setPreferredSize(new Dimension(screenSize.width / 6, screenSize.height / 25));
        pnl2.add(welcomt);
        for(int i = 0; i < Session.tutorPerCourse.size(); i++){
            JPanel panel = new JPanel();
            JLabel namelbl = new JLabel(Session.tutorPerCourse.get(i).getTutorName());
            JButton matchbtn = new JButton("Koppel");
            int id = Session.tutorPerCourse.get(i).getId();
            matchbtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    idtutor = id;
                    if(checkButtons() == true){
                        CourseDao.deleteFromWaitList(course, idstudent);
                        CourseDao.deleteFromWaitList(course, idtutor);
                        CourseDao.askHelp(idtutor, idstudent, course);
                        showMessageDialog(null, "leeringen zijn gekoppeld");
                        JButton backbtn = new JButton("terug gaan");

                        backbtn.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {

                            }
                        });
                        try {
                            setTeacherOnCourseSituation(pnl, pnl2, course, pan3);
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                        idtutor = 0;
                        idstudent = 0;
                    }
                }
            });
            panel.setPreferredSize(new Dimension(screenSize.width / 6, screenSize.height / 25));
            panel.add(namelbl);
            panel.add(matchbtn);
            panel.setBackground(Color.gray);
            pnl2.add(panel);
        }
    }
    public boolean checkButtons(){
        if ((idstudent != 0) && (idtutor != 0)){
            return true;
        }
        return false;
    }
    public void setNavPan(JPanel navpan, JPanel targetpan, JPanel shutdownpan, ScrollPane sp , ScrollPane sp2){
        setScreenSize(Toolkit.getDefaultToolkit().getScreenSize());
        JPanel main = new JPanel();
        main.setPreferredSize(new Dimension(screenSize.width / 16, screenSize.height/22));
        main.setVisible(true);
        JButton btnmain = new JButton("Vakken");
        main.setBackground(Color.black);
        main.add(btnmain);
        navpan.add(main);

        btnmain.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                shutdownpan.setVisible(true);
                sp.setVisible(false);
                sp2.setVisible(true);
                targetpan.setVisible(false);
            }
        });

        if(Session.current instanceof Teacher){
            JPanel vaktoev = new JPanel();
            vaktoev.setPreferredSize(new Dimension(screenSize.width / 16, screenSize.height/22));
            vaktoev.setVisible(true);
            vaktoev.setBackground(Color.black);
            JButton btnvaktoev = new JButton("Vak Toeveogen");
            btnvaktoev.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    sp.setVisible(true);
                    shutdownpan.setVisible(false);
                    JPanel panel = new JPanel();
                    JLabel naam = new JLabel("Vak naam: ");
                    JTextField vaknaam = new JTextField(11);
                    JLabel jaar = new JLabel("Jaar van het vak :");
                    JTextField vakjaar = new JTextField(11);
                    JButton submit = new JButton("Voeg toe");
                    submit.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String crsname = vaknaam.getText();
                            String crsyear = vakjaar.getText();
                            int year = Integer.parseInt(crsyear);
                            try {
                                CourseDao.insertCour(crsname, year);
                                showMessageDialog(null, "vak is toegevoegd");
                                vakjaar.setText("");
                                vaknaam.setText("");
                            } catch (SQLException throwables) {
                                showMessageDialog(null, "vak bestaat al");
                                throwables.printStackTrace();

                            }
                        }
                    });
                    JButton backbtn = new JButton("terug gaan");

                    backbtn.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            targetpan.setVisible(false);
                            shutdownpan.setVisible(true);
                        }
                    });
                    JPanel panel2 = new JPanel();
                    panel.add(naam);
                    panel.add(vaknaam);
                    panel.add(jaar);
                    panel.add(vakjaar);
                    panel2.add(backbtn);
                    panel2.add(submit);
                    targetpan.setVisible(false);
                    targetpan.removeAll();
                    targetpan.setVisible(true);
                    targetpan.add(panel);
                    targetpan.add(panel2);


                }
            });



           vaktoev.add(btnvaktoev);
           navpan.add(vaktoev);
        }else if(Session.current instanceof Tutor){
            JPanel tutorpan = new JPanel();

        }

    }




}


