package Views.Teachers;

import Controller.Teachers.TeacherHomeController;
import Models.Timetable.Timetable;
import Views.Template;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import static Controller.Teachers.TeacherHomeController.*;

public class AddTimetableData implements ActionListener {
    Template frame=new Template("Add TimeTable");
    JButton back = new JButton("Back");
    String days[]={"Monday", "Tuesday", "Wednesday", "Thursday","Friday"};
    String subjects[]={"ENGLISH","MATH", "SST","SCIENCE"};
    JComboBox day=new JComboBox(days);
    JLabel heading = new JLabel("Time Table Entry");
    JComboBox<Object> class_id=  new JComboBox<>();
    JComboBox subject = new JComboBox(subjects);
    JComboBox<Object> teacher = new JComboBox<>();
    JLabel startLabel= new JLabel("Start time(HH:MM:SS):");
    JLabel endLabel= new JLabel("End time(HH:MM:SS):");
    JLabel dayLabel = new JLabel("Day:");
    JLabel classLabel = new JLabel("Class:");
    JLabel subjectLabel = new JLabel("Subject:");
    JLabel teacherLabel = new JLabel("Teacher:");
    JTextField start =  new JTextField();
    JTextField end =  new JTextField();
    JButton sub = new JButton("Submit");

    public AddTimetableData(){
        heading.setBounds(170,25,500,30);
        heading.setForeground(Color.blue);
        back.setBounds(10,10,80,20);
        back.addActionListener(this);
        frame.add(back);
        heading.setFont(new Font(null,Font.BOLD,25));
        frame.add(heading);
        classLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        classLabel.setBounds(100,90,300, 25);
        frame.add(classLabel);
        for(Object sub:retrieveClass()){
            class_id.addItem(sub);
        }
        class_id.setFont(new Font("Arial", Font.PLAIN, 15));
        class_id.setBounds(200,90,300, 25);
        class_id.addActionListener(this);
        class_id.setRenderer(new ListRenderer());
        frame.add(class_id);

        teacherLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        teacherLabel.setBounds(100,120,300, 25);
        frame.add(teacherLabel);
        for(Object sub:retrieveTeachers()){
            teacher.addItem(sub);
        }
        teacher.setFont(new Font("Arial", Font.PLAIN, 15));
        teacher.setBounds(200,120,300, 25);
        teacher.addActionListener(this);
        teacher.setRenderer(new ListRenderer());
        frame.add(teacher);


        subjectLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        subjectLabel.setBounds(100,150,300, 25);
        frame.add(subjectLabel);

        subject.setFont(new Font("Arial", Font.PLAIN, 15));
        subject.setBounds(200,150,300, 25);
        subject.addActionListener(this);
        frame.add(subject);
        dayLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        dayLabel.setBounds(120,180,300, 25);
        frame.add(dayLabel);

        day.setFont(new Font("Arial", Font.PLAIN, 15));
        day.setBounds(200,180,300, 25);
        day.addActionListener(this);
        frame.add(day);
        startLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        startLabel.setBounds(100,210,300, 20);
        frame.add(startLabel);

        start.setFont(new Font("Arial", Font.PLAIN, 20));
        start.setBounds(200,210,300, 20);
        frame.add(start);

        endLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        endLabel.setBounds(100,240,300, 20);
        frame.add(endLabel);

        end.setFont(new Font("Arial", Font.PLAIN, 20));
        end.setBounds(200,240,300, 20);
        frame.add(end);

        sub.setFont(new Font("Arial", Font.PLAIN, 15));
        sub.setBounds(150,270,300, 20);
        sub.addActionListener(this);
        frame.add(sub);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(620,420);
        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back) {
            frame.dispose();
            new TeacherDashView();
        }

        if (e.getSource() == sub) {
            try {
                Object[] selected_class = (Object[]) class_id.getSelectedItem();
                Object[] selected_teacher = (Object[]) teacher.getSelectedItem();
                Timetable tt = new Timetable();
                String start_time = start.getText();
                String end_time = end.getText();
                String Day = (String) day.getSelectedItem();
                String Subject = (String) subject.getSelectedItem();
                String teacherInd = String.valueOf(selected_teacher[0]);
                int classID = (Integer) selected_class[0];
                int subjectInd = 0;
                assert Subject != null;
                switch (Subject) {
                    case "ENGLISH":
                        subjectInd = 1;
                        break;
                    case "MATH":
                        subjectInd = 2;
                    case "SST":
                        subjectInd = 3;
                        break;
                    case "SCIENCE":
                        subjectInd = 4;
                        break;
                }

                if (start_time != null && end_time != null && day != null) {

                    tt.setTeacher(teacherInd);
                    tt.setClass_id(classID);
                    tt.setDay(Day);
                    tt.setSubject(String.valueOf(subjectInd));
                    tt.setTime_start(start_time);
                    tt.setTime_end(end_time);
                    if (addTimeTable(tt)) {
                        start.setText("");
                        end.setText("");
                        JOptionPane.showMessageDialog(null, "Time added successfully.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Something Went wrong");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "All fields are required");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

}
