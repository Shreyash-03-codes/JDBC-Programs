package JDBC;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
public class PreparedStatementOperation {
    public static void main(String[] args){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Drivers Loaded Successfully!!!!");
        }
        catch(ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
        String url="jdbc:mysql://localhost:3306/jdbc";
        String user="root";
        String pass="shreyash@2005";
        try{
            Connection connection=DriverManager.getConnection(url,user,pass);
            System.out.println("Connection Established Successfully!!!");
            String query="INSERT INTO employees(id,name,position,salary) values (?,?,?,?),(?,?,?,?);";
//            String query="UPDATE employees SET name=?,salary=? where id=?;";
            PreparedStatement preparedStatement=connection.prepareStatement(query);


            preparedStatement.setInt(1,2);
            preparedStatement.setString(2,"Abhijeet Bhagwan Gurav");
            preparedStatement.setString(3,"AIML Engineer");
            preparedStatement.setInt(4,250000);

            preparedStatement.setInt(5,3);
            preparedStatement.setString(6,"Rohan Mane");
            preparedStatement.setString(7,"DevOps Engineer");
            preparedStatement.setInt(8,150000);

            int rs=preparedStatement.executeUpdate();
            if(rs>0){
                System.out.println("Updated Successfully!!!");
                System.out.println(rs+" Rows(s) affected...!");
            }
            else{
                System.out.println("Updated Failed!!!");
            }
            preparedStatement.close();
            connection.close();
            System.out.println("Connection Closed Successfully!!!");
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }

    }
}
