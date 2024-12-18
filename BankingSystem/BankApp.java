package BankingSystem;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class BankApp {

    private static String url="jdbc:mysql://localhost:3306/banksystem" ;
    private static String user="root";
    private static String pass="shreyash@2005";

    public static void main(String[] args){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch(ClassNotFoundException e){
            System.out.println(e.getMessage());
        }

        try(Connection connection =DriverManager.getConnection(url,user,pass)){
            boolean flag=true;
            Scanner sc=new Scanner(System.in);
            User user=new User(connection,sc);
            Account account=new Account(connection,sc);
            AccountManager accountManager=new AccountManager(connection,sc);
            while(flag){
                System.out.println("1.Sign Up");
                System.out.println("2.Log In");
                System.out.println("3.Exit");

                int op=sc.nextInt();
                sc.nextLine();

                switch (op){
                    case 1:user.signUp();

                        break;
                    case 2:
                        String email=user.logIn();
                        if(email!=null){
                            System.out.println("User Logged In...!");
                            boolean enter=true;

                                System.out.println("1.Add Bank Account !!");
                                System.out.println("2.Go Back !!");
                                if(sc.nextInt()==1){
                                    if(account.accountExists(email)){
                                        System.out.println("User Account Exists Already!!!!");
                                    }
                                    else{
                                        int acNumber=account.addAccount(email);
                                        System.out.println("Account Opened Successfully!!");
                                        System.out.println("Your Account Number is "+acNumber);
                                    }
                                }

                                int acNumber=account.getAccountNumber(email);

                                while(enter){
                                    System.out.println("1.Debit Money:");
                                    System.out.println("2.Credit Money:");
                                    System.out.println("3.Transfer Money:");
                                    System.out.println("4.Check Balance:");
                                    System.out.println("5.Exit:");
                                    int o=sc.nextInt();

                                    switch (o){
                                        case 1: accountManager.debit(acNumber);
                                        break;
                                        case 2:accountManager.credit(acNumber);
                                        break;
                                        case 3:accountManager.transfer(acNumber);
                                        break;
                                        case 4:accountManager.checkBalance(acNumber);
                                        break;
                                        case 5:
                                            enter=false;
                                            break;
                                        default:
                                            System.out.println("Invalid Choice!!!");
                                    }
                                }

                        }
                        else{
                            System.out.println("Incorrect Email or Password!!!!");
                        }
                        break;
                    case 3:
                        System.out.println("Thank You For Using Bank!!!!");
                        System.out.println("Exiting Bank!!!");
                        flag=false;
                        break;
                    default:
                        System.out.println("Invalid Operation!!!");
                }
            }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
