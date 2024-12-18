package JDBC;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.io.FileInputStream;
public class ImageInsert {
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
            String imgPath="D:\\JDBCImage\\myimage.jpg";
            FileInputStream ft=new FileInputStream(imgPath);
            byte[] imageArr=new byte[ft.available()];
            ft.read(imageArr);
            String query="INSERT INTO images(image) values(?);";
            PreparedStatement pt=con.prepareStatement(query);
            pt.setBytes(1,imageArr);
            int rs=pt.executeUpdate();
            if(rs>0){
                System.out.println("Image Inserted Successfully!!!");
                System.out.println(rs+" Row (s) affected!!!");
            }
            else{
                System.out.println("Insertion Failed!!!");
            }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
