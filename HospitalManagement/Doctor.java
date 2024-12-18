package HospitalManagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Doctor {
    Connection connection;
    Scanner scanner;

    Doctor(Connection connection,Scanner sc){
        this.connection=connection;
        this.scanner=sc;
    }

    public void viewDoctors(){

        try{

            String query="SELECT * FROM doctor;";

            PreparedStatement pt=connection.prepareStatement(query);
            ResultSet rs=pt.executeQuery();
            System.out.println("Doctor Data:");
            System.out.println("+----+----------------------+--------------------+");
            System.out.println("| ID | Name                 | Specialization     |");
            System.out.println("+----+----------------------+--------------------+");
            while(rs.next()){
                System.out.printf("| %-4d | %-20s | %-19s |",rs.getInt("id"),rs.getString("name"),rs.getString("specialization"));
                System.out.println("+----+----------------------+--------------------+");
            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }

    public boolean checkDoctors(int id){
        try{

            String query="SELECT * FROM doctor WHERE id=?;";

            PreparedStatement pt=connection.prepareStatement(query);
            pt.setInt(1,id);
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

}
