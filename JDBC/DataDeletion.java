package JDBC;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;
public class DataDeletion {
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
            Connection con=DriverManager.getConnection(url,user,pass);
            System.out.println("Connection Established Successfully!!!!");
            Statement stmt=con.createStatement();
            int rs=stmt.executeUpdate("DELETE FROM employees where id=7;");
            if(rs>0){
                System.out.println("Deletion Successful !!!!");
                System.out.println(rs+"Rows(s) affected...!");
            }
            else{
                System.out.println("Deletion Failed..!!!");
            }
            stmt.close();
            con.close();
            System.out.println("Connection Closed Successfully !!!");
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
