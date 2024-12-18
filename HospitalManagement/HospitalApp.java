package HospitalManagement;

import java.sql.*;
import java.util.Scanner;

public class HospitalApp {

    private static final String url="jdbc:mysql://localhost:3306/hospital";
    private static final String user="root";
    private static final String pass="shreyash@2005";
    public static void main(String[] args){

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }

        try{
            Connection connection= DriverManager.getConnection(url,user,pass);
            Scanner sc=new Scanner(System.in);
            Patient patient=new Patient(connection,sc);
            Doctor doctor=new Doctor(connection,sc);

            while(true){
                System.out.println("1.Add Patient");
                System.out.println("2.View Patient");
                System.out.println("3.View Doctor");
                System.out.println("4.Appointment");
                System.out.println("5.Exit");
                int op=sc.nextInt();

                switch (op){
                    case 1:
                        patient.addPatient();;
                        break;
                    case 2:
                        patient.viewPatients();
                        break;
                    case 3:
                        doctor.viewDoctors();
                        break;
                    case 4:
                        appointment(doctor,patient,connection,sc);
                        break;
                    case 5:
                        System.out.println("Thank You For using Hospital Management System!!!");
                        System.out.println("Exiting.....");
                        return;
                    default:
                        System.out.println("Invalid Choice!!!!");
                }
            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public static void appointment(Doctor doctor,Patient patient,Connection connection, Scanner sc){


            System.out.println("Enter The Patient Id:");
            int ptId=sc.nextInt();
            System.out.println("Enter The Doctor Id:");
            int dcId=sc.nextInt();
            System.out.println("Enter The Date (YYYY-MM-DD)");
            String date=sc.next();

            if(patient.checkPatient(ptId) && doctor.checkDoctors(dcId)){
                if(checkAvailability(connection,dcId,date)){
                    try{
                        String query="INSERT INTO appointment (patientId,doctorId,appointmentDate) values(?,?,?);";
                        PreparedStatement pt=connection.prepareStatement(query);
                        pt.setInt(1,ptId);
                        pt.setInt(2,dcId);
                        pt.setString(3,date);
                        int rs=pt.executeUpdate();
                        if(rs>0){
                            System.out.println("Appointment Added Successfully!!!!");
                        }
                        else{
                            System.out.println("Appointment Added Failed!!!!");
                        }
                    }
                    catch (SQLException e){
                        System.out.println(e.getMessage());
                    }
                }

                else{
                    System.out.println("Doctor is Not Available on "+date);
                }
            }
            else{
                System.out.println("Patient Or Doctor ID Incorrect!!!");
            }

    }

    public static boolean checkAvailability(Connection connection,int dcId,String date){
        try{
            String query="SELECT COUNT(*) FROM appointment WHERE doctorId=? AND appointmentDate=?;";
            PreparedStatement pt= connection.prepareStatement(query);
            pt.setInt(1,dcId);
            pt.setString(2,date);;
            ResultSet rs=pt.executeQuery();
            if(rs.next()){
                int count=rs.getInt(1);
                if(count==0){
                    return true;
                }
                else{
                    return false;
                }
            }

        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return true;
    }
}
