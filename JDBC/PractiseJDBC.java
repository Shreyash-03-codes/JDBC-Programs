package JDBC;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.io.*;
public class PractiseJDBC {
    public static void main(String[] args){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Drivers Loaded Successfully!!!!!");
        }
        catch(ClassNotFoundException e){
            System.out.println(e.getMessage());
        }

        String url="jdbc:mysql://localhost:3306/jdbc";
        String user="root";
        String pass="shreyash@2005";

        try(Connection con=DriverManager.getConnection(url,user,pass)){
            System.out.println("Connection Established Successfully!!!");
            String query="SELECT * FROM images WHERE id=?;";
            PreparedStatement pt=con.prepareStatement(query);
            pt.setInt(1,1);
            ResultSet rs=pt.executeQuery();
            String folderPath="D:\\JDBCImage\\";
            String imgPath=folderPath+"certificateimage.jpg";
            FileOutputStream ft=new FileOutputStream(imgPath);

            if(rs.next()){

                ft.write(rs.getBytes("image"));
                System.out.println("Image Retrieval Successful!!");
            }
            else{
                System.out.println("Image Ritrieval Unsuccessful!!!");
            }
            rs.close();
            con.close();
            pt.close();
            ft.close();
            System.out.println("Connection Closed Successfully!!!");

        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
