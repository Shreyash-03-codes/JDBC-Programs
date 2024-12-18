package JDBC;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DataInsert {
    public static void main(String[] args){
        String url="jdbc:mysql://localhost:3306/jdbc";
        String user="root";
        String pass="shreyash@2005";
        String query="insert into employees(id,name,position,salary) values (4,'Abhijeet Gurav','AIML Engineer',102000);";
        String qu="select * from employees";
        try{
            Class.forName("com.jdbc.mysql.Driver");
            System.out.println("Drivers Loaded Successfully!!!");
        }
        catch(ClassNotFoundException e){
            System.out.println(e.getMessage());
        }

        try{
            Connection con=DriverManager.getConnection(url,user,pass);
            System.out.println("Connection Established Successfully!!!");
            Statement stmt=con.createStatement();
            int result=stmt.executeUpdate(query);
            if(result>0){
                System.out.println(result+" row(s) effected...!");
            }
            else{
                System.out.println("Insertion Failed!!!!");
            }




            stmt.close();
            con.close();
            System.out.println("Connection Closed Successfully!!!");
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }


    }
}
