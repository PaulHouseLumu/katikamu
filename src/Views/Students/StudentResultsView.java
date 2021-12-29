package Views.Students;

import Controller.Students.StudentHomeController;
import Models.Results.StudentResult;
import Views.Template;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class StudentResultsView implements ActionListener {
    Template frame=new Template("Student Results");
    JLabel heading = new JLabel("Your Term Results");
    DefaultTableModel defaultTableModel;//creating object of DefaultTableModel
    JTable table;//Creating object of JTable
    JButton back = new JButton("Back");
    public StudentResultsView(){
        back.setBounds(10,10,80,20);
        back.addActionListener(this);
        frame.add(back);
        heading.setBounds(170,25,400,30);
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
        defaultTableModel.addColumn("Subject Name");
        defaultTableModel.addColumn("Mark");
        defaultTableModel.addColumn("Grade");

        // add rows
        ArrayList<StudentResult> results=new StudentHomeController().viewResults();
        for(StudentResult result : results){
            defaultTableModel.addRow(new Object[]{result.getName(), result.getResult(), result.getGrade()});
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
class JTableUtilities
{
    public static void setCellsAlignment(JTable table, int alignment)
    {
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(alignment);

        TableModel tableModel = table.getModel();

        for (int columnIndex = 0; columnIndex < tableModel.getColumnCount(); columnIndex++)
        {
            table.getColumnModel().getColumn(columnIndex).setCellRenderer(rightRenderer);
        }
    }
}