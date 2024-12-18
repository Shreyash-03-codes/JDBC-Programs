package BankingSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import java.sql.ResultSet;

public class User {
    private Connection connection;
    private Scanner sc;
    User(Connection connection,Scanner sc){
        this.connection=connection;
        this.sc=sc;
    }
    public void signUp(){

        System.out.println("Enter the Name:");
        String name=sc.nextLine();
        System.out.println("Enter the Email:");
        String email=sc.nextLine();
        System.out.println("Enter The Password:");
        String pass=sc.nextLine();
        if(userExists(email)){
            System.out.println("User Already Exists !!!!");
        }
        else{
            try {
                String query = "INSERT INTO user(email,name,password) values(?,?,?);";
                PreparedStatement pt = connection.prepareStatement(query);
                pt.setString(1, email);
                pt.setString(2, name);
                pt.setString(3, pass);
                int rs=pt.executeUpdate();
                if(rs>0){
                    System.out.println("User Registered Successfully!!!!");
                }
                else{
                    System.out.println("User Registered Failed!!!!");
                }
                pt.close();
            }
            catch(SQLException e){
                System.out.println(e.getMessage());
                System.out.println("User Registered Failed!!!!");
            }
        }
    }

    public String logIn(){
        System.out.println("Enter the Email:");
        String email=sc.nextLine();
        System.out.println("Enter The Password:");
        String pass=sc.nextLine();
        String query="SELECT * FROM user WHERE email=? AND password=?;";
        try{
            PreparedStatement pt=connection.prepareStatement(query);
            pt.setString(1,email);
            pt.setString(2,pass);
            ResultSet rs=pt.executeQuery();
            if(rs.next()){
                return email;
            }
            else{
                return null;
            }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public boolean userExists(String email){
        String query="SELECT * FROM user WHERE email=?;";
        try{
            PreparedStatement pt=connection.prepareStatement(query);
            pt.setString(1,email);
            ResultSet rs=pt.executeQuery();
            if(rs.next()){
                return true;

            }
            return false;
        }
        catch (SQLException e){
            System.out.println(e.getMessage());

        }
        return false;
    }
}
