package JDBC;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class BatchProccesingPreparedStatement {
    public static void main(String[]args){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Drivers Loaded successfully!!!!");
        }
        catch(ClassNotFoundException e){
            System.out.println(e.getMessage());
        }

        String url="jdbc:mysql://localhost:3306/jdbc";
        String user="root";
        String pass="shreyash@2005";

        try(Connection con=DriverManager.getConnection(url,user,pass)){
            System.out.println("Connection Established Successfully!!!");
            String query="INSERT INTO employees (name,position,salary) values(?,?,?);";
            PreparedStatement pt=con.prepareStatement(query);

            Scanner sc=new Scanner(System.in);

            while(true){
                System.out.println("Enter The Name:");
                pt.setString(1,sc.nextLine());
                System.out.println("Enter The Position:");
                pt.setString(2,sc.nextLine());
                System.out.println("Enter the Salary:");
                pt.setInt(3,sc.nextInt());
                sc.nextLine();
                pt.addBatch();

                System.out.println();
                int[] rs=pt.executeBatch();
                System.out.println("Batch Inserted Successfully!!!");
                System.out.println();
                System.out.println("You want to Insert data again Y/N:");
                if(sc.nextLine().toUpperCase().equals("N")){
                    break;
                }



            }
            pt.close();
            con.close();
            System.out.println("Connection is Closed Successfully!!!");
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
