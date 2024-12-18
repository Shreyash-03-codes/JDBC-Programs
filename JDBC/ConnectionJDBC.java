package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionJDBC {
    public static void main(String[] args){
        String url="jdbc:mysql://localhost:3306/jdbc";
        String userName="root";
        String passWord="shreyash@2005";

        try(Connection connection=DriverManager.getConnection(url,userName,passWord)){
            System.out.println("Connected to database Successfully");
            System.out.println(connection);
        }catch(SQLException e){
            System.out.println(e);
        }
    }
}
