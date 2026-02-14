package HospitalManagementSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Patients {

    private Connection conn;
    private Scanner sc;
    Validate v=new Validate(sc);

    public Patients(Connection conn, Scanner sc){
        this.sc=sc;
        this.conn=conn;
    }

    public void addpatient(){
        System.out.println("You have to provide these details to add the patient\n   1. Name \n   2.  Age\n   3.  Gender(Male, Femal, Other");

        System.out.println("Enter the Name of the patients");
        sc.nextLine();
        String name=new Validate(sc).validateName();
        int age= v.validateAge();

        sc.nextLine();
        if(age==0 || age<0){
            System.out.println("You have entered the wrong age of the patients");
            return;
        }
        System.out.println("Enter the gender of the patients");
        String gender=sc.nextLine().trim().toUpperCase();

        try{
            String addQuery="INSERT INTO PATIENTS (patient_Name, patient_Age, patient_Gender) VALUES (?,?,?);";
            PreparedStatement preStmt= conn.prepareStatement(addQuery);
            preStmt.setString(1,name);
            preStmt.setInt(2,age);
            preStmt.setString(3, gender);

            int rowsEffected=preStmt.executeUpdate();
            if(rowsEffected>0){
                System.out.println("Patients Add Successfully! "+ rowsEffected+"row(s) Effected");
            }else{
                System.out.println("Failed due to Some reason. Please Try again later");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void viewPatientsLists() {

        String viewQuery = "SELECT * FROM patients";

        try {
            PreparedStatement preStmt = conn.prepareStatement(viewQuery);
            ResultSet res = preStmt.executeQuery();

            System.out.println("\nPatients Table");
            System.out.println("+------------+----------------------+-----+--------+");
            System.out.println("| Patient ID | Name                 | Age | Gender |");
            System.out.println("+------------+----------------------+-----+--------+");

            while (res.next()) {
                int id = res.getInt("patient_Id");
                String name = res.getString("patient_Name");
                int age = res.getInt("patient_Age");
                String gender = res.getString("patient_Gender");

                System.out.printf("| %-10d | %-20s | %-3d | %-6s |\n",
                        id, name, age, gender);
            }

            System.out.println("+------------+----------------------+-----+--------+");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getPatientById() {

        String getQuery = "SELECT * FROM patients WHERE patient_Id = ?";

        int id=v.validateId();
        try {
            PreparedStatement preStmt = conn.prepareStatement(getQuery);
            preStmt.setInt(1, id);

            ResultSet res = preStmt.executeQuery();

            if (res.next()) {

                System.out.println("\nPatient Details");
                System.out.println("+------------+----------------------+-----+--------+");
                System.out.println("| Patient ID | Name                 | Age | Gender |");
                System.out.println("+------------+----------------------+-----+--------+");

                int patientId = res.getInt("patient_Id");
                String name = res.getString("patient_Name");
                int age = res.getInt("patient_Age");
                String gender = res.getString("patient_Gender");

                System.out.printf("| %-10d | %-20s | %-3d | %-6s |\n",
                        patientId, name, age, gender);

                System.out.println("+------------+----------------------+-----+--------+");

            } else {
                System.out.println(" No patient found with ID: " + id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletePatientById() {

        String deleteQuery = "DELETE FROM patients WHERE patient_Id = ?";

        int id=new Validate(sc).validateId();
        sc.nextLine();

        try {
            PreparedStatement preStmt = conn.prepareStatement(deleteQuery);
            preStmt.setInt(1, id);

            int rowsEffected = preStmt.executeUpdate();

            if (rowsEffected > 0) {
                System.out.println("Patient Deleted Successfully! " + rowsEffected + " row(s) Effected");
            } else {
                System.out.println(" No patient found with ID: " + id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
