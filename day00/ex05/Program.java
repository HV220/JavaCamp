import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Program {
    private static final int MAX_CLASSES_PER_WEEK = 10;
    private static final int MAX_STUDENTS = 10;
    private static final int MAX_STUDENT_NAME_LENGTH = 10;

    public static void main(String[] args) {
        List<String> students = createStudentList();
        List<Class> timetable = createTimetable();

        recordAttendance(students, timetable);
        displayTimetable(students, timetable);
    }

    private static List<String> createStudentList() {
        List<String> students = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Creating a list of students (maximum 10 students):");
        for (int i = 1; i <= MAX_STUDENTS; i++) {
            System.out.print("Enter student " + i + " name: ");
            String name = scanner.nextLine();
            students.add(name);
        }

        return students;
    }

    private static List<Class> createTimetable() {
        List<Class> timetable = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Populating the timetable (maximum 10 classes per week):");
        int classes = 0;
        while (classes < MAX_CLASSES_PER_WEEK) {
            System.out.print("Enter class day (1-7): ");
            int day = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            System.out.print("Enter class time (13-18): ");
            int time = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            // Validate class time and day
            if (day < 1 || day > 7 || time < 13 || time > 18) {
                System.out.println("Invalid class day or time. Please try again.");
                continue;
            }

            timetable.add(new Class(day, time));
            classes++;

            System.out.print("Do you want to add another class? (Y/N): ");
            String choice = scanner.nextLine();
            if (!choice.equalsIgnoreCase("Y")) {
                break;
            }
        }

        return timetable;
    }

    private static void recordAttendance(List<String> students, List<Class> timetable) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Recording attendance:");
        for (String student : students) {
            System.out.println("Attendance for " + student + ":");

            for (Class cls : timetable) {
                System.out
                        .print("Enter attendance for " + cls.getDay() + " at " + cls.getTime() + " (HERE/NOT_HERE): ");
                String attendance = scanner.nextLine();

                if (!attendance.equalsIgnoreCase("HERE") && !attendance.equalsIgnoreCase("NOT_HERE")) {
                    System.out.println("Invalid attendance status. Please try again.");
                    continue;
                }

                cls.addAttendance(student, attendance);
            }

            System.out.println();
        }
    }

    private static void displayTimetable(List<String> students, List<Class> timetable) {
        System.out.println("Timetable with attendance:");

        System.out.print("Student Name\t");
        for (Class cls : timetable) {
            System.out.print("Day " + cls.getDay() + " at " + cls.getTime() + "\t");
        }
        System.out.println();

        for (String student : students) {
            System.out.print(student + "\t\t");
            for (Class cls : timetable) {
                String attendance = cls.getAttendance(student);
                System.out.print(attendance + "\t\t");
            }
            System.out.println();
        }
    }
}

class Class {
    private int day;
    private int time;
    private List<Attendance> attendanceList;

    public Class(int day, int time) {
        this.day = day;
        this.time = time;
        this.attendanceList = new ArrayList<>();
    }

    public int getDay() {
        return day;
    }

    public int getTime() {
        return time;
    }

    public void addAttendance(String student, String status) {
        attendanceList.add(new Attendance(student, status));
    }

    public String getAttendance(String student) {
        for (Attendance attendance : attendanceList) {
            if (attendance.getStudent().equals(student)) {
                return attendance.getStatus();
            }
        }
        return "";
    }
}

class Attendance {
    private String student;
    private String status;

    public Attendance(String student, String status) {
        this.student = student;
        this.status = status;
    }

    public String getStudent() {
        return student;
    }

    public String getStatus() {
        return status;
    }
}