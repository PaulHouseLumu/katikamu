package Controller.Teachers;

import Controller.User;
import DButils.DButil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TeacherHomeController implements User {
    @Override
    public void loginUser(String username, String password) {
        Connection connection = new DButil().getConnection();
        try {
            PreparedStatement st = (PreparedStatement) connection
                    .prepareStatement("Select * from Teachers where email=? and password=SHA1(?)");

            st.setString(1, username);
            st.setString(2, password);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {

                System.out.println("Logged in");
            } else {
                // message to show to teacher the details are wrong.
                System.out.println("Not Logged in");
            }
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
    }

    public static void main(String[] args) {
        new TeacherHomeController().loginUser("aksam@gmail.com","1234");
    }
}
