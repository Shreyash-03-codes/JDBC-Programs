package JDBC;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class DataRetrivalPreparedStatement {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Drivers Loaded Successfully!!!");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }


    String url = "jdbc:mysql://localhost:3306/jdbc";
    String user = "root";
    String pass = "shreyash@2005";

    try(Connection con = DriverManager.getConnection(url, user, pass)){
        System.out.println("Connection EstablishedSuccessfully!!!!");
        String query="SELECT * FROM students;";
        PreparedStatement pstmt=con.prepareStatement(query);
        ResultSet rs=pstmt.executeQuery();

        while(rs.next()){
            System.out.println(rs.getInt("id")+" "+rs.getString("name")+" "+rs.getString("class")+" "+rs.getInt("mark"));
        }
        pstmt.close();
    }
    catch(SQLException e){
        System.out.println(e.getMessage());
    }


    }


}

