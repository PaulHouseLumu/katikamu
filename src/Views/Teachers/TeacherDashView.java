package Views.Teachers;

import Views.Students.StudentResultsView;
import Views.Students.StudentTimetable;
import Views.Template;
import Views.WelcomeView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Controller.Students.StudentHomeController.Name;
import static Controller.Teachers.TeacherHomeController.Role;

public class TeacherDashView implements ActionListener{
    Template frame = new Template("Teacher's Dashboard");
    JButton register =new JButton("Register Student");
    JButton search =new JButton("Search By Registration Number");
    JButton record =new JButton("Record Student's Marks");
    JButton filter =new JButton("Filter  Records By Subject/List all");
    JButton display =new JButton("Logout");
    JButton timetab =new JButton("Add Timetable Entry");
    JButton newTeacher =new JButton("New Teacher Entry");
    JButton reset =new JButton("Reset Password");
    JLabel welcome=new JLabel("Welcome ");
    public TeacherDashView() {
        welcome.setBounds(20,40,400,30);
        welcome.setForeground(Color.MAGENTA);
        welcome.setFont(new Font(null,Font.BOLD,20));
        frame.add(welcome);
        display.setBounds(400,10,100,30);
        search.setBounds(160,100,300,30);
        record.setBounds(160,150,300,30);
        register.setBounds(160,200,300,30);
        filter.setBounds(160,250,300,30);
        timetab.setBounds(160,300,300,30);
        newTeacher.setBounds(160,350,300,30);
        reset.setBounds(160,450,300,30);
        display.setFocusable(false);
        search.setFocusable(false);
        record.setFocusable(false);
        register.setFocusable(false);
        timetab.setFocusable(false);
        filter.setFocusable(false);
        display.addActionListener(this);
        search.addActionListener(this);
        record.addActionListener(this);
        register.addActionListener(this);
        timetab.addActionListener(this);
        newTeacher.addActionListener(this);
        reset.addActionListener(this);
        filter.addActionListener(this);
        if(Role==0) {
            frame.add(search);
            frame.add(record);
            frame.add(register);
        }
        frame.add(display);
        frame.add(reset);
        if(Role==1)frame.add(timetab);
        if(Role==1)frame.add(newTeacher);
        frame.add(filter);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(620,550);
        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==register){
            AddStudentDialog dialog = new AddStudentDialog(true);

            // show dialog
            dialog.setLocation(400,200);
            dialog.setSize(630,460);
            dialog.setVisible(true);
        }
        if(e.getSource()==search){
            new StudentSearch().setVisible(true);
        }
        if(e.getSource()==record){
            frame.dispose();
            new AddResultsView();
        }
        if(e.getSource()==display){
            frame.dispose();
            new WelcomeView();
        }
        if(e.getSource()==timetab){
            frame.dispose();
            new AddTimetableData();
        }
        if(e.getSource()==newTeacher){
            frame.dispose();
            new RegisterTeacher();
        }
        if(e.getSource()==filter){
            new FilterStudents().setVisible(true);
        }
        if(e.getSource()==reset){
            frame.dispose();
            new ResetPassword();
        }
    }
}
