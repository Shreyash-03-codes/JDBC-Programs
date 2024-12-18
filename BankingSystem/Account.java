package BankingSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Account {
    private static Connection connection;
    private static Scanner sc;

    Account(Connection connection, Scanner sc){
        this.connection=connection;
        this.sc=sc;
    }

    public int addAccount(String email){
        int acNum=Account.generateAccountNumber();
        try{
            PreparedStatement pt=connection.prepareStatement("INSERT INTO account(accountNumber,name,email,password,balance) values(?,?,?,?,?)");

            pt.setInt(1,acNum);
            System.out.println("Enter Name of Account Owner!!!");
            String name=sc.nextLine();
            pt.setString(2,name);
            pt.setString(3,email);
            sc.nextLine();
            System.out.println("Enter The Strong Password!!!");
            pt.setString(4,sc.nextLine());

            System.out.println("Enter Initial Balance:");
            pt.setInt(5,sc.nextInt());
            int rs=pt.executeUpdate();
            if(rs>0){
                return acNum;
            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return acNum;
    }

    public int getAccountNumber(String email){
        try{
            PreparedStatement pt=connection.prepareStatement("SELECT * FROM account WHERE email=?;");
            pt.setString(1,email);
            ResultSet rs=pt.executeQuery();
            if(rs.next()){
                int acNum=rs.getInt("accountNumber");
                return acNum;
            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return 1000;
    }
    public boolean accountExists(String email){
        try{
            PreparedStatement pt=connection.prepareStatement("SELECT * FROM account WHERE email=?;");
            pt.setString(1,email);
            ResultSet rs=pt.executeQuery();
            if(rs.next()){
                return true;
            }
            else{
                return false;
            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }
    public static  int generateAccountNumber(){
        try{

                PreparedStatement p=connection.prepareStatement("SELECT * FROM account ORDER BY accountNumber DESC LIMIT 1;");
                ResultSet r=p.executeQuery();
                if(r.next()){
                    return r.getInt("accountNumber")+1;

                }
                else{
                    return 1000;
                }


        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return 1000;
    }
}
