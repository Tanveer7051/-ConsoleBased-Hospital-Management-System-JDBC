package HospitalManagementSystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class HospitalMangeSystem {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        final String url = "jdbc:mysql://localhost:3306/Hospital";
        final String username = "root";
        final String password = "Mummy@7835";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        try {
            Connection conn = DriverManager.getConnection(url, username, password);

            Doctors d = new Doctors(conn, sc);
            Patients p = new Patients(conn, sc);
            Appointment a = new Appointment(conn, sc);   // Appointment object

            while (true) {

                System.out.println("\n========= MAIN MENU =========");
                System.out.println(" 1. Patient");
                System.out.println(" 2. Doctor");
                System.out.println(" 3. Appointments");
                System.out.println(" 4. Exit");
                System.out.println("Enter your choice:");

                int val = sc.nextInt();

                switch (val) {

                    // ---------------- PATIENT MENU ----------------
                    case 1:
                        while (true) {
                            System.out.println("\n---- PATIENT MENU ----");
                            System.out.println("  1. Add Patient");
                            System.out.println("  2. Delete Patient By Id");
                            System.out.println("  3. View Patients");
                            System.out.println("  4. See Patient Detail By Id");
                            System.out.println("  5. Go To Main Menu");

                            int value = sc.nextInt();

                            switch (value) {
                                case 1:
                                    p.addpatient();
                                    break;
                                case 2:
                                    p.deletePatientById();
                                    break;
                                case 3:
                                    p.viewPatientsLists();
                                    break;
                                case 4:
                                    p.getPatientById();
                                    break;
                                case 5:
                                    System.out.println("Returning to Main Menu...");
                                    break;
                                default:
                                    System.out.println("Enter valid option!");
                            }

                            if (value == 5) break;
                        }
                        break;

                    // ---------------- DOCTOR MENU ----------------
                    case 2:
                        while (true) {
                            System.out.println("\n---- DOCTOR MENU ----");
                            System.out.println("  1. View Doctors");
                            System.out.println("  2. Check Doctor By Department");
                            System.out.println("  3. Go To Main Menu");

                            int value = sc.nextInt();

                            switch (value) {
                                case 1:
                                    d.viewDoctors();
                                    break;
                                case 2:
                                    d.checkDoctorByDeptt();
                                    break;
                                case 3:
                                    System.out.println("Returning to Main Menu...");
                                    break;
                                default:
                                    System.out.println("Enter valid option!");
                            }

                            if (value == 3) break;
                        }
                        break;

                    // ---------------- APPOINTMENT MENU ----------------
                    case 3:
                        while (true) {
                            System.out.println("\n---- APPOINTMENT MENU ---- ");
                            System.out.println("  1. Book Appointment");
                            System.out.println("  2. View Appointments");
                            System.out.println("  3. Cancel Appointment");
                            System.out.println("  4. Go To Main Menu");

                            int value = sc.nextInt();

                            switch (value) {
                                case 1:
                                    a.bookAppointment();
                                    break;
                                case 2:
                                    a.viewAppointments();
                                    break;
                                case 3:
                                    a.cancelAppointment();
                                    break;
                                case 4:
                                    System.out.println("Returning to Main Menu...");
                                    break;
                                default:
                                    System.out.println("Enter valid option!");
                            }

                            if (value == 4) break;
                        }
                        break;

                    // ---------------- EXIT ----------------
                    case 4:
                        System.out.println("Thank You for using Hospital Management System!");
                        conn.close();
                        sc.close();
                        return;

                    default:
                        System.out.println("Please enter valid option!");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        sc.close();
    }
}
