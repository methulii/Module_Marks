package task;

import java.io.*;
import java.util.*;

public class Task01 {
    private static final int MAX_CAPACITY = 100;
    private static Task01.Student[] students = new Task01.Student[MAX_CAPACITY];
    private static int studentCount = 0;

    public static void main(String[] args) {
        loadStudentDetailsFromFile();
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            printMenu();//Menu
            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        checkAvailableSeats();
                        break;
                    case 2:
                        registerStudent(scanner);
                        break;
                    case 3:
                        deleteStudent(scanner);
                        break;
                    case 4:
                        findStudent(scanner);
                        break;
                    case 5:
                        storeStudentDetailsToFile();
                        break;
                    case 6:
                        loadStudentDetailsFromFile();
                        break;
                    case 7:
                        viewStudentsByName();
                        break;
                    case 8:
                        Task02.manageModules(scanner, students, studentCount);
                        break;
                    case 9:
                        Task03.generateSummary(students, studentCount);
                        break;
                    case 10:
                        Task03.generateCompleteReport(students, studentCount);
                        break;
                    case 11:
                        exit = true;
                        System.out.println("Exiting");
                        break;
                    default:
                        System.out.println("Invalid choice.");// invalid integer
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine();
            }
        }
        scanner.close();
    }

    //load menu including exit
    private static void printMenu() {
        System.out.println("\nMenu:");
        System.out.println("1. Check available seats");
        System.out.println("2. Enroll student");
        System.out.println("3. Delete student");
        System.out.println("4. Find student (by ID)");
        System.out.println("5. Store student details to file");
        System.out.println("6. Load student details from file");
        System.out.println("7. View students by name");
        System.out.println("8. Enter module marks");
        System.out.println("9. Generate summary");
        System.out.println("10. Generate complete report");
        System.out.println("11. Exit");
        System.out.print("Enter your choice: ");
    }
    //check accessible seats
    private static void checkAvailableSeats() {
        System.out.println("Available seats: " + (MAX_CAPACITY - studentCount));
    }

    private static void registerStudent(Scanner scanner) {
        if (studentCount < MAX_CAPACITY) {
            System.out.println("Enter Student ID:");
            String id = scanner.nextLine();
            System.out.println("Enter Student Name:");
            String name = scanner.nextLine();
            students[studentCount++] = new Task01.Student(id, name);
            System.out.println("Student registered successfully.");

            storeStudentDetailsToFile();
        } else {
            System.out.println("No available seats.");//if there's no sufficient space
        }
    }

    private static void deleteStudent(Scanner scanner) {
        System.out.println("Enter Student ID:");
        String delId = scanner.nextLine();
        boolean found = false;

        for (int i = 0; i < studentCount; i++) {
            if (students[i].getId().equals(delId)) {
                students[i] = students[--studentCount];
                students[studentCount] = null;
                System.out.println("Student deleted successfully.");
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Student ID not found.");// if ID is not in the file
        }
    }

    private static void findStudent(Scanner scanner) {
        System.out.println("Enter Student ID to find:");// Entering the ID
        String findId = scanner.nextLine();
        boolean found = false;

        for (Task01.Student student : students) {
            if (student != null && student.getId().equals(findId)) {
                System.out.println("Student found: " + student);//If ID matches withe records, print output.
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Student ID not found.");//if ID is not in the file
        }
    }

    private static void storeStudentDetailsToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("students.txt"))) {
            oos.writeObject(students);
            oos.writeInt(studentCount);
            System.out.println("Student details saved to file.");// store student records to student.txt file
        } catch (IOException e) {
            System.out.println("Error saving student details: " + e.getMessage());
        }
    }

    //get back records from student.txt file
    private static void loadStudentDetailsFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("students.txt"))) {
            students = (Task01.Student[]) ois.readObject();
            studentCount = ois.readInt();
            System.out.println("Student details loaded from file:");
            for (int i = 0; i < studentCount; i++) {
                System.out.println(students[i]);
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading student details: " + e.getMessage());//If an error occurred, Display this
        }
    }

    //according to the Student ID
    private static void viewStudentsByName() {
        Arrays.sort(students, 0, studentCount, Comparator.comparing(Task01.Student::getName));
        for (int i = 0; i < studentCount; i++) {
            System.out.println(students[i]);
        }
    }

    static class Student implements Serializable {
        private String id;
        private String name;
        private int[] moduleMarks;

        public Student(String id, String name) {
            this.id = id;
            this.name = name;
            this.moduleMarks = new int[3];
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public int[] getModuleMarks() {
            return moduleMarks;
        }

        public void setModuleMarks(int[] moduleMarks) {
            this.moduleMarks = moduleMarks;
        }

        @Override
        public String toString() {
            return "Student ID: " + id + ", Name: " + name;
        }
    }
}

