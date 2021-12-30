package Views.Students;

import Controller.Students.StudentHomeController;
import Models.Results.StudentResult;
import Models.Timetable.Timetable;
import Views.Template;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class StudentTimetable implements ActionListener {
    Template frame= new Template("Time table");
    JLabel heading = new JLabel("Class Timetable");
    DefaultTableModel defaultTableModel;//creating object of DefaultTableModel
    JTable table;//Creating object of JTable
    JButton back = new JButton("Back");
    public StudentTimetable(){
        back.setBounds(10,10,80,20);
        back.addActionListener(this);
        frame.add(back);
        heading.setBounds(170,25,200,30);
        heading.setForeground(Color.blue);
        heading.setFont(new Font(null,Font.BOLD,25));
        frame.add(heading);
        frame.setLayout(new FlowLayout());

        //Setting the properties of JTable and DefaultTableModel
        defaultTableModel = new DefaultTableModel();
        table = new JTable(defaultTableModel);
        table.setPreferredScrollableViewportSize(new Dimension(460, 250));
        table.setFillsViewportHeight(true);
        frame.add(new JScrollPane(table));
        defaultTableModel.addColumn("Day");
        defaultTableModel.addColumn("Subject");
        defaultTableModel.addColumn("Teacher");
        defaultTableModel.addColumn("Starts");
        defaultTableModel.addColumn("Ends");

        // add rows
        ArrayList<Timetable> results=new StudentHomeController().viewTimetable();
        for(Timetable result : results){
            defaultTableModel.addRow(new Object[]{result.getDay(), result.getSubject(), result.getTeacher(),result.getTime_start()
            ,result.getTime_end()});
            frame.validate();
        }
        JTableUtilities.setCellsAlignment(table, SwingConstants.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(620,420);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==back){
            frame.dispose();
            new StudentDashView();
        }
    }
}
