package JDBC;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.sql.ResultSet;
public class ImageRetrieval {
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

        try{
            Connection con=DriverManager.getConnection(url,user,pass);
            System.out.println("Connection Established Successfully!!!");
            String query="select * from images;";
            String folderPath="D:\\JDBCImage\\";
            PreparedStatement pt=con.prepareStatement(query);
            ResultSet rs=pt.executeQuery();
            String imagePath=folderPath+"certificate.jpg";
            FileOutputStream ft=new FileOutputStream(imagePath);
            if(rs.next()) {
                byte[] arr = rs.getBytes("image");
                ft.write(arr);
                System.out.println("Image Retrieval Successful!!!");
            }
            else{
                System.out.println("Image Retrieval Failed!!!");
            }
            rs.next();
            pt.close();
            con.close();
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
