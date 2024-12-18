package BankingSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class AccountManager {
    private Connection connection;
    private Scanner sc;
    AccountManager(Connection connection,Scanner sc){
        this.connection=connection;
        this.sc=sc;
    }

    public void debit(int acNumber){


        System.out.println("Enter The Amount to Debit:");
        int amount=sc.nextInt();
        sc.nextLine();
        System.out.println("Enter The Password:");
        String pass=sc.nextLine();
        try{
            connection.setAutoCommit(false);
            PreparedStatement pt=connection.prepareStatement("SELECT * FROM account WHERE accountNumber=?;");
            pt.setInt(1,acNumber);
            ResultSet rs=pt.executeQuery();
            if(rs.next()){
            if(pass.equals(rs.getString("password"))) {
                if (amount <= rs.getInt("balance")) {
                    PreparedStatement p = connection.prepareStatement("UPDATE account SET balance=balance-? WHERE accountNumber=?;");
                    p.setInt(1, amount);
                    p.setInt(2, acNumber);
                    int r = p.executeUpdate();
                    if (r > 0) {
                        System.out.println(amount + " Rs. Debited Successfully From your Account!!");
                        connection.commit();
                    } else {
                        connection.rollback();
                        connection.commit();
                        System.out.println("Insufficient Balance");
                    }
                }
            }
            }
            else{
                System.out.println("Password Incorrect!!!!");
            }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }

    }
    public void credit(int acNumber){

        System.out.println("Enter The Amount to Credit:");
        int amount=sc.nextInt();
        sc.nextLine();
        System.out.println("Enter The Password:");
        String pass=sc.nextLine();
        try{
            connection.setAutoCommit(false);
            PreparedStatement pt=connection.prepareStatement("SELECT * FROM account WHERE accountNumber=?;");
            pt.setInt(1,acNumber);
            ResultSet rs=pt.executeQuery();
            if(rs.next()){
            if(pass.equals(rs.getString("password"))) {

                PreparedStatement p = connection.prepareStatement("UPDATE account SET balance=balance+? WHERE accountNumber=?;");
                p.setInt(1, amount);
                p.setInt(2, acNumber);
                int r = p.executeUpdate();
                if (r > 0) {
                    System.out.println(amount + " Rs. Credited Successfully In your Account!!");
                    connection.commit();
                } else {
                    connection.rollback();
                    connection.commit();
                    System.out.println("Server Issue Try Again Later!!!");
                }
            }

            }
            else{
                System.out.println("Password Incorrect!!!!");
            }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public void transfer(int acNumber){
        System.out.println("Enter The Amount to Debit:");
        int amount=sc.nextInt();
        System.out.println("Enter The Account Number of Receiver to Transfer:");
        int receiver=sc.nextInt();
        sc.nextLine();
        System.out.println("Enter The Password:");
        String pass=sc.nextLine();
        try {
            connection.setAutoCommit(false);
            PreparedStatement t = connection.prepareStatement("SELECT * FROM account WHERE accountNumber=?;");
            t.setInt(1, receiver);
            ResultSet s = t.executeQuery();
                if (s.next()){
                    PreparedStatement pt = connection.prepareStatement("SELECT * FROM account WHERE accountNumber=?;");
                    pt.setInt(1, acNumber);
                    ResultSet rs = pt.executeQuery();
                    if(rs.next()){
                        if (pass.equals(rs.getString("password"))) {
                            if (amount <= rs.getInt("balance")) {
                                PreparedStatement sender = connection.prepareStatement("UPDATE account SET balance=balance-? WHERE accountNumber=?;");
                                sender.setInt(1, amount);
                                sender.setInt(2, acNumber);
                                int r = sender.executeUpdate();
                                PreparedStatement rec = connection.prepareStatement("UPDATE account SET balance=balance+? WHERE accountNumber=?;");
                                rec.setInt(1, amount);
                                rec.setInt(2, receiver);
                                int re = rec.executeUpdate();
                                if (r > 0 && re>0) {
                                    System.out.println(amount + " Rs. Transfered Successfully From your Account!!");
                                    connection.commit();
                                } else {
                                    connection.rollback();
                                    connection.commit();
                                    System.out.println("Insufficient Balance");
                                }
                            }
                        }
                        else {
                            System.out.println("Password Incorrect!!!!");
                        }
                    }
            }
                else{
                    System.out.println("Invalid Receiver Account Number!!!");
                }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public void checkBalance(int acNumber){
        sc.nextLine();
        System.out.println("Enter The Password:");
        String pass=sc.nextLine();
        try{
            PreparedStatement pt=connection.prepareStatement("SELECT * FROM account WHERE accountNumber=?;");
            pt.setInt(1,acNumber);
            ResultSet rs=pt.executeQuery();
            if(rs.next()) {
                if (pass.equals(rs.getString("password"))) {
                    System.out.println("Current Balance is : " + rs.getInt("balance"));
                } else {
                    System.out.println("Password Incorrect!!!!");
                }
            }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
            System.out.println("Server Issue Try Again Later!!!");
        }
    }

}
