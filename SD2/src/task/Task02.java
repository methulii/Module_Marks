package task;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Task02 {
    public static void manageModules(Scanner scanner, Task01.Student[] students, int studentCount) {
        System.out.println("Enter Student ID to add module marks:");//ID entering field
        String id = scanner.nextLine();
        boolean found = false;

        for (Task01.Student student : students) {//Enter marks to the modules
            if (student != null && student.getId().equals(id)) {
                try {
                    System.out.println("Enter Module 1 marks:");//module 01
                    int m1 = scanner.nextInt();
                    System.out.println("Enter Module 2 marks:");//module 02
                    int m2 = scanner.nextInt();
                    System.out.println("Enter Module 3 marks:");//module 03
                    int m3 = scanner.nextInt();
                    scanner.nextLine(); // Consume newline after reading integers

                    if (m1 < 0 || m1 > 100 || m2 < 0 || m2 > 100 || m3 < 0 || m3 > 100) {
                        System.out.println("Marks should be between 0 and 100.");
                        return;
                    }

                    student.setModuleMarks(new int[]{m1, m2, m3});
                    System.out.println("Module marks added successfully.");
                    found = true;
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter valid marks.");
                    scanner.nextLine(); // Clear input buffer
                }
            }
        }

        if (!found) {
            System.out.println("Student ID not found.");// raise an error if the input ID in not on the records.
        }
    }

    public static String calculateGrade(int average) {
        if (average >= 80) return "Distinction";
        else if (average >= 70) return "Merit";
        else if (average >= 40) return "Pass";
        else return "Fail";
    }
}









