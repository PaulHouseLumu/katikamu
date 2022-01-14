package Views.Students;

import Views.Teachers.ResetPassword;
import Views.Template;
import Views.WelcomeView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Controller.Students.StudentHomeController.*;

public class StudentDashView implements ActionListener {
    Template frame = new Template("Student Dashboard");
    JButton viewResults =new JButton("View Your Results");
    JButton viewTimetable =new JButton("View Timetable");
    JButton logout =new JButton("logout");
    JLabel welcome=new JLabel("Welcome "+Name);
    JButton reset =new JButton("Reset Password");

    /**
     * StudentDashView Constructor
     */
    public StudentDashView(){
        welcome.setBounds(20,40,400,30);
        welcome.setForeground(Color.MAGENTA);
        welcome.setFont(new Font(null,Font.BOLD,20));
        logout.setBounds(400,10,100,30);
        logout.addActionListener(this);
        frame.add(welcome);
        frame.add(logout);
        viewTimetable.setBounds(160,130,300,30);
        viewResults.setBounds(160,170,300,30);
        reset.setBounds(160,300,300,30);
        viewTimetable.setFocusable(false);
        viewResults.setFocusable(false);
        viewTimetable.addActionListener(this);
        viewResults.addActionListener(this);
        reset.addActionListener(this);
        frame.add(viewResults);
        frame.add(viewTimetable);
        frame.add(reset);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(620,420);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
          if(e.getSource()==viewResults){
              frame.dispose();
              new StudentResultsView();
          }
          if(e.getSource()==viewTimetable){
              frame.dispose();
              new StudentTimetable();
          }
        if(e.getSource()==logout){
            ClASS_ID=0;
            STUDENT_ID=0;
            frame.dispose();
            new WelcomeView();
        }
        if(e.getSource()==reset){
            frame.dispose();
            new ResetPassword();
        }
    }
}
