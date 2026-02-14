package HospitalManagementSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Doctors {
    private Connection conn;
    private Scanner sc;

    public Doctors(Connection conn, Scanner sc){
        this.conn=conn;
        this.sc=sc;
    }

    public void viewDoctors() {

        String viewQuery = "SELECT * FROM doctors";

        try {
            PreparedStatement preStmt = conn.prepareStatement(viewQuery);
            ResultSet res = preStmt.executeQuery();

            System.out.println("\nDoctors Table");
            System.out.println("+------------+----------------------+----------------------+");
            System.out.println("| Doctor ID  | Name                 | Department           |");
            System.out.println("+------------+----------------------+----------------------+");

            while (res.next()) {

                int id = res.getInt("doctor_Id");
                String name = res.getString("doctor_Name");
                String dept = res.getString("doctor_Deptt");

                System.out.printf("| %-10d | %-20s | %-20s |\n",
                        id, name, dept);
            }

            System.out.println("+------------+----------------------+----------------------+");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void checkDoctorByDeptt() {

        String viewQuery = "SELECT * FROM doctors WHERE doctor_Deptt = ?";

        System.out.print("Enter the Department Name (e.g., Orthopedics): ");
        sc.nextLine();
        String deptt = sc.nextLine().trim().toUpperCase();

        try {
            PreparedStatement preStmt = conn.prepareStatement(viewQuery);
            preStmt.setString(1, deptt);

            ResultSet res = preStmt.executeQuery();

            boolean found = false;

            System.out.println("\nDoctors in Department: " + deptt);
            System.out.println("+------------+----------------------+----------------------+");
            System.out.println("| Doctor ID  | Name                 | Department           |");
            System.out.println("+------------+----------------------+----------------------+");

            while (res.next()) {

                found = true;

                int id = res.getInt("doctor_Id");
                String name = res.getString("doctor_Name");
                String dept = res.getString("doctor_Deptt");

                System.out.printf("| %-10d | %-20s | %-20s |\n",
                        id, name, dept);
            }

            if (!found) {
                System.out.println("| No doctors found in this department.               |");
            }

            System.out.println("+------------+----------------------+----------------------+");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
