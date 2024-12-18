package JDBC;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class InsertDataPreparedStatement {
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
        try(Connection connection =DriverManager.getConnection(url,user,pass)
        ){
            String query= """
                    INSERT INTO students (name,class,mark) values(?,?,?);
                    """;
            PreparedStatement pstmt=connection.prepareStatement(query);
            pstmt.setString(1,"Sham Mane");
            pstmt.setString(2,"Third Year");
            pstmt.setInt(3,81);



            int rs=pstmt.executeUpdate();
            if(rs>0){
                System.out.println("Data Inserted Successfully!!!");
                System.out.println(rs+" Row (s) affected!!!");
            }
            else{
                System.out.println("Data Insertion Failed!!!!");
            }

            pstmt.close();
            connection.close();
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
