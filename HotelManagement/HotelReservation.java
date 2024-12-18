package HotelManagement;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;


public class HotelReservation {
    public static void main(String[] args){

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch(ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
        String url="jdbc:mysql://localhost:3306/hotel";
        String user="root";
        String pass="shreyash@2005";

        try{
            Connection connection=DriverManager.getConnection(url,user,pass);
            Statement stmt=connection.createStatement();
            boolean flag=true;
            while(flag){
                System.out.println();
                System.out.println("1.Register New Customer");
                System.out.println("2.Update Existing Customer");
                System.out.println("3.Check All Customer Details");
                System.out.println("4.Get a Customer Details");
                System.out.println("5.Delete Customer Details");
                System.out.println("6.Exit");
                System.out.println();
                Scanner sc=new Scanner(System.in);
                int op=sc.nextInt();
                switch(op){

                    case 1:register(stmt,sc);
                        break;
                    case 2:update(stmt,sc);
                        break;
                    case 3:check(stmt);
                        break;
                    case 4:get(connection,stmt,sc);
                        break;
                    case  5:delete(connection,stmt,sc);
                        break;
                    case 6:exit();
                        stmt.close();
                        connection.close();
                        flag=false;
                        break;
                    default:
                        System.out.println("INVALID CHOICE !!!!!!!");
                }

            }

        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    private static void register(Statement statement,Scanner sc){
        sc.nextLine();
        System.out.println("Enter The Customer Name:");
        String name=sc.nextLine();
        System.out.println("Enter The Customer Room Number:");

        int roomNumber=sc.nextInt();

        System.out.println("Enter The Customer Mobile Number:");
        String phone=sc.next();

        String query="INSERT INTO reservations(custName,roomNumber,mobileNumber) VALUES ('"+name+"','"+roomNumber+"','"+phone+"')";
        try{
            statement.executeUpdate(query);
            System.out.print("Customer Registered Successfully");
            int i=5;
           try{
               while(i!=0){
                   Thread.sleep(450);
                   System.out.print(".");
                   i--;
               }
               System.out.println("!");
           }
           catch (Exception e){
               System.out.println(e.getMessage());
           }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    private static void update(Statement statement,Scanner sc){

            System.out.println("Enter The Customer ID:");
            int id=sc.nextInt();
        if(exists(statement,id)){
            System.out.println("Enter The Customer Name:");
            String name=sc.next();
            System.out.println("Enter The Customer Room Number:");
            int roomNumber=sc.nextInt();
            System.out.println("Enter The Customer Mobile Number:");
            String phone=sc.next();

            String query="UPDATE reservations SET custName='"+name+"',roomNumber='"+roomNumber+"',mobileNumber='"+phone+"' WHERE custId='"+id+"';";
            try{
                statement.executeUpdate(query);
                System.out.print("Customer Updated Successfully");
                int i=5;
                try{
                    while(i!=0){
                        Thread.sleep(450);
                        System.out.print(".");
                        i--;
                    }
                    System.out.println("!");
                }
                catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }
            catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }
        else{
            System.out.println("User Does Not Exists!!!!");
        }
    }
    private static void check(Statement statement){

        String query="SELECT * FROM reservations;";
        try{
            ResultSet rs=statement.executeQuery(query);
            System.out.print("Fetching The Data");
            int i=5;
                try{
                    while(i!=0){
                        Thread.sleep(450);
                        System.out.print(".");
                        i--;
                    }
                    System.out.println("!");
                }
                catch (Exception exception){
                    System.out.println(exception.getMessage());
                }
            System.out.println("ID\tCustomer Name\tRoom Number\tMobile Number\tDate-Time");
            while(rs.next()){
                System.out.println(rs.getString("custId")+"\t"+rs.getString("custName")+"\t"+rs.getInt("roomNumber")+"\t\t\t"+rs.getString("mobileNumber")+"\t\t"+rs.getTimestamp("regDate"));
            }

        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    private static void get(Connection connection,Statement statement,Scanner sc){
        System.out.println("Enter The Customer ID:");
        int id=sc.nextInt();
        if(exists(statement,id)) {
            String query = "SELECT * FROM reservations WHERE custId='" + id + "';";
            try {
                ResultSet rs = statement.executeQuery(query);
                System.out.print("Fetching The Data");
                int i = 5;
                try {
                    while (i != 0) {
                        Thread.sleep(450);
                        System.out.print(".");
                        i--;
                    }
                    System.out.println("!");
                } catch (Exception exception) {
                    System.out.println(exception.getMessage());
                }
                System.out.println("ID\tCustomer Name\tRoom Number\tMobile Number\tDate-Time");
                while (rs.next()) {
                    System.out.println(rs.getString("custId") + "\t" + rs.getString("custName") + "\t\t" + rs.getInt("roomNumber") + "\t\t\t" + rs.getString("mobileNumber") + "\t\t" + rs.getTimestamp("regDate"));
                }

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        else{
            System.out.println("User Does Not Exists!!!!");
        }
    }
    private static void delete(Connection connection,Statement statement,Scanner sc){
        System.out.println("Enter The Customer ID:");
        int id=sc.nextInt();
        if(exists(statement,id)) {
            String query = "DELETE FROM reservations WHERE custId='" + id + "';";
            try {
                statement.executeUpdate(query);
                System.out.print("Deleting The Data of Customer");
                int i = 5;
                try {
                    while (i != 0) {
                        Thread.sleep(450);
                        System.out.print(".");
                        i--;
                    }
                    System.out.println("!");
                    System.out.println("Data Deleted Successfully!!!!");
                } catch (Exception exception) {
                    System.out.println(exception.getMessage());
                }


            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        else{
            System.out.println("User Does Not Exists!!!!");
        }
    }
     private static boolean exists(Statement statement,int id){

        try{
             ResultSet rs=statement.executeQuery("select 1 from reservations where custId='"+id+"';");
             return rs.next();
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
            return false;
        }



    }
    private static void exit(){
        int i = 5;
        System.out.print("Exiting");
        try {
            while (i != 0) {
                Thread.sleep(450);
                System.out.print(".");
                i--;
            }
            System.out.println("!");

        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }
}
