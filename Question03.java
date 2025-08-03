import java.util.*;

public class Marks {
    static final int SUBJECTS = 3;
    static final String[] SUBJECT_NAMES = {"Mathematics", "Chemistry", "Physics"};
    static Map<Integer, int[]> studentMarks = new HashMap<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== MENU ===");
            System.out.println("1. Add student marks");
            System.out.println("2. Update student mark");
            System.out.println("3. Get subject average");
            System.out.println("4. Get student average");
            System.out.println("5. Get student total");
            System.out.println("6. Display grades");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> addStudent();
                case 2 -> updateMark();
                case 3 -> subjectAverage();
                case 4 -> studentAverage();
                case 5 -> studentTotal();
                case 6 -> displayGrades(); // New for Q3
                case 7 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

// Add student marks
    static void addStudent() {
        System.out.print("Enter Student ID: ");
        int id = scanner.nextInt();
        int[] marks = new int[SUBJECTS];
        for (int i = 0; i < SUBJECTS; i++) {
            System.out.print("Enter marks for " + SUBJECT_NAMES[i] + ": ");
            marks[i] = scanner.nextInt();
        }
        studentMarks.put(id, marks);
        System.out.println("Marks added successfully.");
    }

// Update student mark
    static void updateMark() {
        System.out.print("Enter Student ID: ");
        int id = scanner.nextInt();
        if (!studentMarks.containsKey(id)) {
            System.out.println("Student not found.");
            return;
        }
        System.out.print("Enter Subject ID (1:Math, 2:Chem, 3:Phys): ");
        int subjectId = scanner.nextInt();
        if (subjectId < 1 || subjectId > SUBJECTS) {
            System.out.println("Invalid subject ID.");
            return;
        }
        System.out.print("Enter new mark: ");
        int newMark = scanner.nextInt();
        studentMarks.get(id)[subjectId - 1] = newMark;
        System.out.println("Mark updated.");
    }

//Get subject average
    static void subjectAverage() {
        System.out.print("Enter Subject ID (1:Math, 2:Chem, 3:Phys): ");
        int subjectId = scanner.nextInt();
        if (subjectId < 1 || subjectId > SUBJECTS) {
            System.out.println("Invalid subject ID.");
            return;
        }
        double total = 0;
        int count = 0;
        for (int[] marks : studentMarks.values()) {
            total += marks[subjectId - 1];
            count++;
        }
        if (count == 0) {
            System.out.println("No students found.");
            return;
        }
        System.out.println("Average for " + SUBJECT_NAMES[subjectId - 1] + ": " + (total / count));
    }

//Get average for a student
    static void studentAverage() {
        System.out.print("Enter Student ID: ");
        int id = scanner.nextInt();
        if (!studentMarks.containsKey(id)) {
            System.out.println("Student not found.");
            return;
        }
        int[] marks = studentMarks.get(id);
        double sum = 0;
        for (int mark : marks) sum += mark;
        System.out.println("Average for student " + id + ": " + (sum / SUBJECTS));
    }

//Get total for a student
    static void studentTotal() {
        System.out.print("Enter Student ID: ");
        int id = scanner.nextInt();
        if (!studentMarks.containsKey(id)) {
            System.out.println("Student not found.");
            return;
        }
        int[] marks = studentMarks.get(id);
        int sum = 0;
        for (int mark : marks) sum += mark;
        System.out.println("Total marks for student " + id + ": " + sum);
    }

//Display Grades
    static void displayGrades() {
        if (studentMarks.isEmpty()) {
            System.out.println("No student records available.");
            return;
        }

        System.out.println("\nStudentID\tMath\tGrade\tChem\tGrade\tPhys\tGrade");
        for (Map.Entry<Integer, int[]> entry : studentMarks.entrySet()) {
            int id = entry.getKey();
            int[] marks = entry.getValue();
            System.out.print(id + "\t\t\t");
            for (int mark : marks) {
                System.out.print(mark + "\t\t" + getGrade(mark) + "\t");
            }
            System.out.println();
        }
    }

    static String getGrade(int score) {
        if (score >= 90) return "Grade A";
        else if (score >= 80) return "Grade B";
        else if (score >= 70) return "Grade C";
        else if (score >= 60) return "Grade D";
        else return "Fail";
    }
}