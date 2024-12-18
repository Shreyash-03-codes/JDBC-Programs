package JDBC;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class UserInputDataInsertionPreparedStatement {
    public static void main(String[] args){

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Drivers Loaded Successfully");
        }
        catch(ClassNotFoundException e){
            System.out.println(e.getMessage());
        }

        String url="jdbc:mysql://localhost:3306/jdbc";
        String user="root";
        String pass="shreyash@2005";
        try(Connection con=DriverManager.getConnection(url,user,pass)){
            System.out.println("Connection Established Successfully!!");
            Scanner sc=new Scanner(System.in);
            System.out.println("Enter Student ID:");
            int id=sc.nextInt();
            sc.nextLine();
            System.out.println("Enter The Student Name:");
            String name=sc.nextLine();
            System.out.println("Enter The Student Class:");
            String studentClass=sc.nextLine();
            System.out.println("Enter Student Mark:");
            int mark=sc.nextInt();
            String query="INSERT INTO students (id,name,class,mark) values(?,?,?,?);";
            PreparedStatement pstmt=con.prepareStatement(query);

            pstmt.setInt(1,id);
            pstmt.setString(2,name);
            pstmt.setString(3,studentClass);
            pstmt.setInt(4,mark);

            int rs=pstmt.executeUpdate();

            if(rs>0){
                System.out.println("Inserted Successfully!!!!");
                System.out.println(rs+" Row(s) affected");
            }
            else{
                System.out.println("Insertion Failed!!!");
            }
            pstmt.close();

        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
