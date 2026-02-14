package HospitalManagementSystem;


import java.sql.*;
import java.util.Scanner;

public class Appointment {
    private Connection conn;
    private Scanner sc;
    private Validate v;


    Appointment(Connection conn, Scanner sc) {
        this.conn = conn;
        this.sc = sc;
        this.v = new Validate(conn,sc);
    }

    void bookAppointment() {
        String isExistPatient="SELECT 1 FROM patients WHERE patient_Id = ?\n";

        int patientId = v.validatePatientId();
        int doctorId = v.validateDoctorId();
        String date = v.getDate();
        Date newDate = Date.valueOf(date);

        String appointmentQuery = "INSERT INTO APPOINTMENTS (patient_Id,doctor_Id,appointment_Date) VALUES (?,?,?);";

        try {
            PreparedStatement preStmt = conn.prepareStatement(appointmentQuery);
            preStmt.setInt(1, patientId);
            preStmt.setInt(2, doctorId);
            preStmt.setDate(3, newDate);

            int rowsEffected = preStmt.executeUpdate();
            if (rowsEffected > 0) {
                System.out.println("Successfully Appointment. You got this Date" + newDate);
            } else {
                System.out.println("Something went wrong!  Please Try Again Later");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void viewAppointments() {

        String viewQuery = "SELECT * FROM APPOINTMENTS WHERE appointment_Id = ?";

        int id = v.validateId();

        try (PreparedStatement preStmt = conn.prepareStatement(viewQuery)) {

            preStmt.setInt(1, id);

            ResultSet res = preStmt.executeQuery();

            if (res.next()) {

                System.out.println("Appointment Details:");
                System.out.println("-----------------------------");
                System.out.println("Appointment ID : " + res.getInt("appointment_Id"));
                System.out.println("Patient ID     : " + res.getInt("patient_Id"));
                System.out.println("Doctor ID      : " + res.getInt("doctor_Id"));
                System.out.println("Date           : " + res.getDate("appointment_Date"));
                System.out.println("-----------------------------");

            } else {
                System.out.println("No appointment found with ID: " + id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void cancelAppointment(){

        String deleteQuery = "DELETE FROM APPOINTMENTS WHERE appointment_Id = ?";

        int id=v.validateId();
        sc.nextLine();

        try {
            PreparedStatement preStmt = conn.prepareStatement(deleteQuery);
            preStmt.setInt(1, id);

            int rowsEffected = preStmt.executeUpdate();

            if (rowsEffected > 0) {
                System.out.println("Appointment Deleted Successfully! ");
            } else {
                System.out.println(" No appointment found with ID: " + id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
