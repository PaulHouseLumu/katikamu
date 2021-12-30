package Controller.Teachers;

import Controller.User;
import DButils.DButil;
import Models.Timetable.Timetable;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Controllers part of the Activities performed by a teacher.
 */
public class TeacherHomeController implements User {
    @Override
    public boolean loginUser(String username, String password) {
        boolean status = false;
        Connection connection = new DButil().getConnection();
        try {
            PreparedStatement st = (PreparedStatement) connection
                    .prepareStatement("Select * from Teachers where email=? and password=SHA1(?)");

            st.setString(1, username);
            st.setString(2, password);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                status = true;
                System.out.println("Logged in");
            }
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
        return status;
    }

    /**
     * Function responsible to add new students marks.
     * @param student_id
     * @param mark
     * @param subject_id
     * @return
     */
    public boolean recordStudentMarks(int student_id,float mark,int subject_id ){
        String grade;
        if(mark>=79.5) grade="D1";
        else if(mark >=70 && mark <= 79.4)grade="D2";
        else if (mark >= 60 && mark >= 69.5) grade ="C3";
        else  grade ="C4";
        boolean status = false;
        Connection connection = new DButil().getConnection();
        PreparedStatement statement = null;
        try{
            statement= connection.prepareStatement("UPDATE Results SET mark=? ,grade=? WHERE student_id=? AND subject_id=?");
            statement.setFloat(1, mark);
            statement.setString(2, grade);
            statement.setInt(3, student_id);
            statement.setInt(4,subject_id);
            int rowAffected = statement.executeUpdate();
            if(rowAffected>0){
                status=true;
            }else{
               status = false;
            }
        }catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
        return status;
    }

    /**
     * Gets all the data subject and returns a list of objects
     * @return
     */
    public static List<Object> retrieveSubjects() {
        List<Object> ls = new ArrayList<>();
        Connection connection = new DButil().getConnection();
        try {
            PreparedStatement st = (PreparedStatement) connection.prepareStatement("SELECT * FROM Subjects");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                ls.add(new Object[]{id, name});
            }

        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
        return ls;
    }

    /**
     * Gets All teachers
     * @return
     */
    public static List<Object> retrieveTeachers() {
        List<Object> ls = new ArrayList<>();
        Connection connection = new DButil().getConnection();
        try {
            PreparedStatement st = (PreparedStatement) connection.prepareStatement("SELECT * FROM Teachers");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                ls.add(new Object[]{id, name});
            }

        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
        return ls;
    }

    /**
     * Gets all Classes
     * @return
     */
    public static List<Object> retrieveClass() {
        List<Object> ls = new ArrayList<>();
        Connection connection = new DButil().getConnection();
        try {
            PreparedStatement st = (PreparedStatement) connection.prepareStatement("SELECT * FROM Classes");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name")+"- "+rs.getString("year");
                ls.add(new Object[]{id, name});
            }

        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
        return ls;
    }

    /**
     * Gets all students and returns a list of objects for students
     * @return
     */
    public static List<Object> retrieveStudents() {
        List<Object> ls = new ArrayList<>();
        Connection connection = new DButil().getConnection();
        try {
            PreparedStatement st = (PreparedStatement) connection.prepareStatement("SELECT * FROM Students");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("last_name")+" "+rs.getString("first_name")+" - "+rs.getString("registration_number");
                ls.add(new Object[]{id, name});
            }

        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
        return ls;
    }

    /**
     * Adds a new timetable Entry
     * @param tt
     * @return
     * @throws SQLException
     */
    public static  boolean addTimeTable(Timetable tt) throws SQLException {
        boolean status;
        Connection connection = new DButil().getConnection();
        // Prepare Statement
        PreparedStatement st  = connection.prepareStatement("INSERT INTO Timetable(teacher_id,class_id,subject_id,day,time_start,time_end) VALUES (?,?,?,?,?,?)");

        // Set Parameters
        st.setString(1, tt.getTeacher());
        st.setInt(2, tt.getClass_id());
        st.setString(3, tt.getSubject());
        st.setString(4, tt.getDay());
        st.setString(5, tt.getTime_start());
        st.setString(6, tt.getTime_end());

        //Execute SQL
        int rowAffected=st.executeUpdate();
        if(rowAffected>0){
            status=true;
        }else{
            status = false;
         }
        return status;
    }
}
