package Controller.Teachers;

import Controller.User;
import DButils.DButil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TeacherHomeController implements User {
    @Override
    public boolean loginUser(String username, String password) {
        boolean status= false;
        Connection connection = new DButil().getConnection();
        try {
            PreparedStatement st = (PreparedStatement) connection
                    .prepareStatement("Select * from Teachers where email=? and password=SHA1(?)");

            st.setString(1, username);
            st.setString(2, password);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                status=true;
                System.out.println("Logged in");
            }
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
        return status;
    }

    public static void main(String[] args) {
        new TeacherHomeController().loginUser("aksam@gmail.com","1234");
    }
}
