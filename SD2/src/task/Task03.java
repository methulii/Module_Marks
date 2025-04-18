package task;

import java.util.Arrays;

public class Task03 {
    public static void generateSummary(Task01.Student[] students, int studentCount) {
        int totalRegistrations = studentCount;
        long passCount = Arrays.stream(students, 0, studentCount)
                .filter(s -> Arrays.stream(s.getModuleMarks()).allMatch(mark -> mark >= 40))
                .count();
        System.out.println("Total Registrations: " + totalRegistrations);// total  number of student in the student.txt file
        System.out.println("Total Students Scored >= 40 in all Modules: " + passCount);// number of students who pass the modules
    }

    public static void generateCompleteReport(Task01.Student[] students, int studentCount) {
        Arrays.sort(students, 0, studentCount, (s1, s2) -> {
            int avg1 = Arrays.stream(s1.getModuleMarks()).sum() / 3;
            int avg2 = Arrays.stream(s2.getModuleMarks()).sum() / 3;
            return Integer.compare(avg2, avg1); // Sort in descending order of average marks
        });

        for (Task01.Student student : students) {
            if (student != null) {
                int total = Arrays.stream(student.getModuleMarks()).sum();
                int average = total / 3;
                String grade = Task02.calculateGrade(average);
                System.out.printf("ID: %s, Name: %s, Module 1: %d, Module 2: %d, Module 3: %d, Total: %d, Average: %d, Grade: %s%n",
                        student.getId(), student.getName(),
                        student.getModuleMarks()[0], student.getModuleMarks()[1],
                        student.getModuleMarks()[2], total, average, grade);
            }
        }
    }
}




