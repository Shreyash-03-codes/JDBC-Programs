package HospitalManagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Patient {
    Connection connection;
    Scanner scanner;

    Patient(Connection connection,Scanner sc){
        this.connection=connection;
        this.scanner=sc;
    }

    public void addPatient(){
        try{
            System.out.println("Enter The Patient Name:");
            String name= scanner.nextLine();
            scanner.nextLine();
            System.out.println("Enter The Patient Age:");
            int age=scanner.nextInt();
            System.out.println("Enter the Gender ('M','F','O') :");
            String gender=scanner.next();

            String query="INSERT INTO patient (name,age,gender) values(?,?,?);";

            PreparedStatement pt=connection.prepareStatement(query);
            pt.setString(1,name);
            pt.setInt(2,age);
            pt.setString(3,gender);
            int rs=pt.executeUpdate();
            if(rs>0){
                System.out.println("Patient Added Successfully!!!");
            }
            else{
                System.out.println("Patient Added Unsuccessful!!!");
            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void viewPatients(){

        try{

            String query="SELECT * FROM patient;";

            PreparedStatement pt=connection.prepareStatement(query);
            ResultSet rs=pt.executeQuery();
            System.out.println("Patient Data:");
            System.out.println("+----+----------------------+---------+-----------+");
            System.out.println("| ID | Name                 | Age     | Gender    |");
            System.out.println("+----+----------------------+---------+-----------+");
            while(rs.next()){
                System.out.printf("| %-2d | %-20s | %-7d | %-9s |\n",rs.getInt("id"),rs.getString("name"),rs.getInt("age"), rs.getString("gender"));
                System.out.println("+----+----------------------+---------+-----------+");
            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }

    public boolean checkPatient(int id){
        try{

            String query="SELECT * FROM patient WHERE id=?;";

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
