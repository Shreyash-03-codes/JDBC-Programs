package JDBC;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;
public class BatchProccessingStatement {
    public static void main(String[] args){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver Loaded Successfully!!!");
        }
        catch(ClassNotFoundException e){
            System.out.println(e.getMessage());
        }

        String url="jdbc:mysql://localhost:3306/jdbc";
        String user="root";
        String pass="shreyash@2005";

        try(Connection con=DriverManager.getConnection(url,user,pass)){
            System.out.println("Connection Established Successfully!!!!");

            Statement st=con.createStatement();
            String query1="INSERT INTO employees(name,position,salary) values('Shreyash Gurav','Full Stack Developer',102000);";
            String query2="INSERT INTO employees(name,position,salary) values('Rohan Mane','AIML Engineer',115000);";
            String query3="INSERT INTO employees(name,position,salary) values('Pratik Patil','Web-Developer',119000);";
            st.addBatch(query1);
            st.addBatch(query2);
            st.addBatch(query3);

            int[] rs=st.executeBatch();
            System.out.println("Batch Inserted Successfully!!!");
            st.close();
            con.close();
            System.out.println("Connection Closed Successfully");
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
