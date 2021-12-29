package Controller.Teachers;

import Controller.User;
import DButils.DButil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
            statement= connection.prepareStatement("UPDATE results SET result=? ,grade=? WHERE student_id=? AND subject_id=?");
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
}
