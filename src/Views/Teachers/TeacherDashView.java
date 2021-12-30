package Views.Teachers;

import Views.Students.StudentResultsView;
import Views.Students.StudentTimetable;
import Views.Template;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Controller.Students.StudentHomeController.Name;

public class TeacherDashView implements ActionListener{
    Template frame = new Template("Teacher's Dashboard");
    JButton register =new JButton("Register Student");
    JButton record =new JButton("Record Student's Marks");
    JButton search =new JButton("Search For a Record");
    JButton display =new JButton("Display Students' List");
    JButton timetab =new JButton("Add Time Entry");
    JLabel welcome=new JLabel("Welcome ");
    public TeacherDashView() {
        welcome.setBounds(20,40,400,30);
        welcome.setForeground(Color.MAGENTA);
        welcome.setFont(new Font(null,Font.BOLD,20));
        frame.add(welcome);
        display.setBounds(160,130,300,30);
        search.setBounds(160,170,300,30);
        record.setBounds(160,210,300,30);
        register.setBounds(160,250,300,30);
        timetab.setBounds(160,290,300,30);
        display.setFocusable(false);
        search.setFocusable(false);
        record.setFocusable(false);
        register.setFocusable(false);
        timetab.setFocusable(false);
        display.addActionListener(this);
        search.addActionListener(this);
        record.addActionListener(this);
        register.addActionListener(this);
        timetab.addActionListener(this);
        frame.add(search);
        frame.add(display);
        frame.add(record);
        frame.add(register);
        frame.add(timetab);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(620,420);
        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==register){
//            frame.dispose();
//            new AddResultsView();
        }
        if(e.getSource()==search){
            frame.dispose();
            new StudentSearch().setVisible(true);
        }
        if(e.getSource()==record){
            frame.dispose();
            new AddResultsView();
        }
        if(e.getSource()==display){
            frame.dispose();
            new StudentSearch().setVisible(true);
        }
        if(e.getSource()==timetab){
            frame.dispose();
            new AddTimetableData();
        }
    }
}
