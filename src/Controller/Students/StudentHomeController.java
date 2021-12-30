package Controller.Students;

import Controller.User;
import DButils.DButil;
import Models.Results.StudentResult;
import Models.Timetable.Timetable;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentHomeController implements User {
    // Method to login the student
    public static int STUDENT_ID;
    public static int ClASS_ID;
    public static String Name;
    @Override
    public boolean loginUser(String username,String password) {
        boolean status = false;
        // establish connection
        Connection connection = new DButil().getConnection();
        try {
            PreparedStatement st = (PreparedStatement) connection
                    .prepareStatement("Select * from Students where registration_number=? and password=SHA1(?)");

            st.setString(1, username);
            st.setString(2, password);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                STUDENT_ID=  rs.getInt(1);
                ClASS_ID = rs.getInt("class_id");
                Name =rs.getString("last_name") +" "+rs.getString("first_name");
                status= true;

            } else {
                // message to show to student the details are wrong.
              status= false;
            }
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
        return status;
    }

    /**
     * Used to compute all the things need to get the timetable data
     * @return
     */
    public ArrayList<Timetable> viewTimetable(){
        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection = new DButil().getConnection();
        ArrayList<Timetable> rs=new ArrayList<>();

        try {
            statement = connection.createStatement();
            resultSet=statement.executeQuery( "SELECT teachers.name as teacher,subjects.name,time_start,time_end,day FROM timetable " +
                    "INNER JOIN teachers USING(id) " +
                    "INNER JOIN subjects USING(id) " +
                    "WHERE class_id="+ClASS_ID);
            while (resultSet.next()) {
                Timetable  t = new Timetable();
                t.setDay(resultSet.getString("day"));
                t.setSubject(resultSet.getString("name"));
                t.setTeacher(resultSet.getString("teacher"));
                t.setTime_start(resultSet.getString("time_start"));
                t.setTime_end(resultSet.getString("time_end"));
                rs.add(t);
            }
        }catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }

        return rs;
    }

    /**
     * Used to retrieve data about student performance
     * @return
     */
    public ArrayList<StudentResult> viewResults(){
        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection = new DButil().getConnection();
        List<StudentResult> rs=new ArrayList<>();

        try {
            statement = connection.createStatement();
            resultSet=statement.executeQuery( "SELECT student_id,result,grade,subjects.name FROM  results "+
                    "INNER JOIN subjects ON results.subject_id=subjects.id "+
                    "WHERE results.student_id="+STUDENT_ID);
            while (resultSet.next()) {
                StudentResult  t = new StudentResult();
                t.setGrade(resultSet.getString("grade"));
                t.setResult(resultSet.getFloat("result"));
                t.setName(resultSet.getString("name"));
                rs.add(t);
            }
        }catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }

        return (ArrayList<StudentResult>) rs;
    }

}

