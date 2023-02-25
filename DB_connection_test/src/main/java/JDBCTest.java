import java.sql.*;

// yeah i did it <33 it works

public class JDBCTest {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/jdbctest";
        String username = "root";
        String password = "44943967@aA";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url, username, password);

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("select * from student");

            while (resultSet.next()) {
                System.out.println(resultSet.getInt(1) + " " + resultSet.getString(2) + " " + resultSet.getString(3));
            }



            connection.close();

        } catch (Exception e){
            System.out.println(e);
        }
    }
}
