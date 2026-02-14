package HospitalManagementSystem;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Validate {

    private Scanner sc;

    Validate(Scanner sc) {
        this.sc = sc;
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

}
