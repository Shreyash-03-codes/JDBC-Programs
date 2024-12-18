package JDBC;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransactionHandling {
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

            System.out.println("Connection Established Successfully!!!");
            con.setAutoCommit(false);
            String withdraw="UPDATE bank SET balance=balance-? WHERE accountNumber=?;";
            String credit="UPDATE bank SET balance=balance+? WHERE accountNumber=?;";

            PreparedStatement with=con.prepareStatement(withdraw);
            PreparedStatement cred=con.prepareStatement(credit);
            cred.setDouble(1,2000);
            cred.setString(2,"acc1");
            with.setDouble(1,2000);
            with.setString(2,"acc2");

            int w=with.executeUpdate();
            int c=cred.executeUpdate();

            if(w>0 && c>0){
                System.out.println("Transaction Successful!!!");
                con.commit();

            }
            else{
                System.out.println("Transaction Unsuccessful!!!");
                con.rollback();
            }
            with.close();
            cred.close();
            con.close();
            System.out.println("Connection closed successfully!!");
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
