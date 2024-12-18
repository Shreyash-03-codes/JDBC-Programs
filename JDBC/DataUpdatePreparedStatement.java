package JDBC;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
public class DataUpdatePreparedStatement {
    public static void main(String[] args){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Drivers Loaded Successfully!!!");
        }
        catch(ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
        String url="jdbc:mysql://localhost:3306/jdbc";
        String user="root";
        String pass="shreyash@2005";

        try(Connection con=DriverManager.getConnection(url,user,pass)){
            String query="UPDATE students SET name=?,mark=? WHERE id=?;";
            PreparedStatement pstmt=con.prepareStatement(query);
            pstmt.setString(1,"Shreyash Bhagwan Gurav");
            pstmt.setInt(2,95);
            pstmt.setInt(3,1);
            int rs=pstmt.executeUpdate();

            if(rs>0){
                System.out.println("Updated Successfully!!!");
                System.out.println(rs+" Row(s) affected!!!!");
            }
            else{
                System.out.println("Updation Failed!!!!");
            }
            pstmt.close();
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
