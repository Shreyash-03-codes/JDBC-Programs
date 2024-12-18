package JDBC;
import java.sql.*;

public class DataRetreival {
    public static void main(String[] args){
        String url="jdbc:mysql://localhost:3306/jdbc";
        String user="root";
        String pass="shreyash@2005";
        String querry="select * from employees;";
        try{
            Class.forName("com.jdbc.mysql.Driver");
            System.out.println("Driver Loaded Successfully!!!");
        }
        catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }

        try{
            Connection con=DriverManager.getConnection(url,user,pass);
            System.out.println("Connection Established Successfully!!");
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery(querry);
            System.out.println("---------------------------------------------");
            System.out.println("| id |\tname\t\t\t| position\t\t\t| salary |");
            System.out.println("----------------------------------------------");

            while(rs.next()){

                System.out.println("| "+rs.getInt("id")+" | "+rs.getString("name")+" | "+rs.getString("position")+" | "+rs.getInt("salary")+" |");
                System.out.println("----------------------------------------- ");
            }
            rs.close();
            stmt.close();
            con.close();
            System.out.println("Connection Closed Successfully!!!");

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
