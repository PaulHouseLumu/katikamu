package Views;

import Views.Students.StudentLoginView;
import Views.Students.StudentResultsView;
import Views.Students.StudentTimetable;
import Views.Teachers.TeacherLoginView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomeView implements ActionListener {
    Template frame = new Template("Welcome to Katikamu");
    JButton teacher =new JButton("View As Teacher");
    JButton student =new JButton("View As Student");

    public WelcomeView(){

        student.setBounds(160,130,300,30);
        teacher.setBounds(160,170,300,30);
        student.setFocusable(false);
        teacher.setFocusable(false);
        student.addActionListener(this);
        teacher.addActionListener(this);
        frame.add(teacher);
        frame.add(student);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(620,420);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==teacher){
            frame.dispose();
            new TeacherLoginView();
        }
        if(e.getSource()==student){
            frame.dispose();
            new StudentLoginView();
        }
    }


}
