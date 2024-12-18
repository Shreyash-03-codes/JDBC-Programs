package JDBC;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;

public class DataUpdate {
    public static void main(String[] args){

        String url="jdbc:mysql://localhost:3306/jdbc";
        String user="root";
        String pass="shreyash@2005";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Drivers Loaded Successfully!!!");
        }
        catch(ClassNotFoundException e){
            System.out.println(e.getMessage());
        }

        try{
            Connection connection=DriverManager.getConnection(url,user,pass);
            System.out.println("Connection Established Successfully!!!!");
            Statement stmt=connection.createStatement();
            int rs=stmt.executeUpdate("UPDATE employees SET position='Java Fullstack Developer',salary=180000 WHERE id=1;");
            if(rs>0){
                System.out.println("Update Successful !!!");
                System.out.println(rs+" Row(s) affected...!!!");
            }
            else{
                System.out.println("Update Failed!!!!");
            }
            stmt.close();
            connection.close();
            System.out.println("Connection Closed Successfully!!!");
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
