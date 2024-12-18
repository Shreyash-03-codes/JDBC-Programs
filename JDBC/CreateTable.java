package JDBC;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;
public class CreateTable {
    public static void main(String[] args){

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Drivers Loaded Successfully!!!");
        }
        catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }

        String url="jdbc:mysql://localhost:3306/jdbc";
        String user="root";
        String pass="shreyash@2005";
        String query="""
        CREATE TABLE students(
        id INT PRIMARY KEY AUTO_INCREMENT,
        name VARCHAR(255) NOT NULL,
        class VARCHAR(255) DEFAULT 'First Year',
        mark INT NOT NULL
        );
        """;

        try{
            Connection con=DriverManager.getConnection(url,user,pass);
            System.out.println("Connection Established Successfully!!!");
            Statement stmt=con.createStatement();
            stmt.execute(query);

                System.out.println("Table Created Successfully!!!");


            stmt.close();
            con.close();
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
