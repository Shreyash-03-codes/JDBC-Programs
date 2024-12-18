package JDBC;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class DataDeletePreparedStatement {
    public static void main(String[] args){

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Drivers Loaded Successfully!!!!");
        }
        catch(ClassNotFoundException e){
            System.out.println(e.getMessage());
        }

        String url="jdbc:mysql://localhost:3306/jdbc";
        String user="root";
        String pass="shreyash@2005";

        try(Connection con=DriverManager.getConnection(url,user,pass)){
            System.out.println("Connection Established Successfully!!!");
            String query="DELETE FROM students WHERE id=?";
            PreparedStatement pstmt=con.prepareStatement(query);
            pstmt.setInt(1,5);
            int rs=pstmt.executeUpdate();

            if(rs>0){
                System.out.println("Data Deleted Successfully!!!");
                System.out.println(rs+" Row(s) affected!!!!");
            }
            else{
                System.out.println("Data Deletion Failed!!!!");
            }
            pstmt.close();
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
