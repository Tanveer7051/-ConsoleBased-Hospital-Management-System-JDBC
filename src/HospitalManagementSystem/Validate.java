package HospitalManagementSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Validate {

    private Connection conn;
    private Scanner sc;

    Validate(Connection conn,Scanner sc) {
        this.sc = sc;
        this.conn=conn;
    }

    public int validateAge() {
        int age;
        while (true) {
            try {
                System.out.println("Enter the age");
                 age = sc.nextInt();
                sc.nextLine();
                if (age <= 0) {
                    System.out.println("Please enter the correct age");
                } else {
                    break;
                }

            } catch (InputMismatchException e) {
                System.out.println("Please Enter the age in numerical vlaue");
                sc.nextLine();
            }
        }
        return age;
    }

    public String validateName() {

        String name;
        while (true) {
            System.out.println("Enter the name:");
             name = sc.nextLine();

            if (name.matches("[a-zA-Z ]+")) {
                break;
            } else {
                System.out.println("Please enter a valid name (letters and spaces only).");
            }
        }

        return name.trim().toUpperCase();
    }

    public int validateId() {
        int Id;
        while (true) {
            try {
                System.out.println("Enter the Id.");
                Id = sc.nextInt();
                sc.nextLine();
                if (Id <= 0) {
                    System.out.println("Please enter the correct Id");
                } else {
                    break;
                }

            } catch (InputMismatchException e) {
                sc.nextLine();
                System.out.println("Please Enter the Id in numerical vlaue");

            }
        }
        return Id;
    }

    String getDate() {

        while (true) {
            try {
                System.out.println("Enter Your appointment (yyyy-mm-dd): ");
                String input = sc.nextLine();

                LocalDate inputDate = LocalDate.parse(input);
                LocalDate today = LocalDate.now();

                if (inputDate.isBefore(today)) {
                    System.out.println("You cannot book appointment for past dates!");
                } else {
                    return input;
                }

            } catch (Exception e) {
                System.out.println("Invalid date! Please enter in yyyy-mm-dd format.");
            }
        }
    }


    public int validatePatientId() {
        int Id;
        while (true) {
            try {
                System.out.println("Enter the Patient Id.");
                Id = sc.nextInt();
                sc.nextLine();
                if (Id <= 0) {
                    System.out.println("Please enter the correct Patient Id ");
                    continue;
                }

            } catch (InputMismatchException e) {
                sc.nextLine();
                System.out.println("Please Enter the Id in numerical vlaue");
                continue;

            }
            if(isPatientIdExists(Id)){
                return Id;
            }else{
                System.out.println("This Patient Id is not Exists \n              OR \n Go to Main Menu and select 1 -> 3> \n   Here You can see the patient Id");
            }
        }

    }

    public int validateDoctorId() {
        int Id;
        while (true) {
            try {
                System.out.println("Enter the Doctor Id.");
                Id = sc.nextInt();
                sc.nextLine();
                if (Id <= 0) {
                    System.out.println("Please enter the correct Doctor Id \n              OR\n go to Main Menu and select 2 -> 1> \n   Here You can see the Doctor Id");
                    continue;
                }

            } catch (InputMismatchException e) {
                sc.nextLine();
                System.out.println("Please Enter the Id in numerical vlaue");
                continue;

            }
            if(isDoctorIdExists(Id)){
                return Id;
            }else{
                System.out.println("This Doctor  Id is not Exists");
            }
        }

    }

    public boolean isPatientIdExists(int id) {

        String query = "SELECT 1 FROM patients WHERE patient_Id = ?";

        try  {

            PreparedStatement ps= conn.prepareStatement(query);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            return rs.next();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isDoctorIdExists(int id) {

        String query = "SELECT 1 FROM doctors WHERE doctor_Id = ?";

        try  {

            PreparedStatement ps= conn.prepareStatement(query);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            return rs.next();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


}
