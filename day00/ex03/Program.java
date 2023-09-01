import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Program {

    public static void main(String[] args) {

        Map<Integer, Integer> progressMap = new HashMap<Integer, Integer>();

        outputData(inputData(progressMap), progressMap);
    }

    public static int inputData(Map<Integer, Integer> progressMap) {
        Scanner scanner = new Scanner(System.in);
        int week = 1;

        while (true) {
            System.out.println("Week " + week);
            String input = scanner.nextLine();

            if (input.equals("42")) {
                break;
            }

            if (input.isEmpty()) {
                scanner.close();
                errorReport();
            }

            checkNumber(week, input, progressMap);

            week++;
        }
        scanner.close();

        return week;
    }

    public static void outputData(Integer week, Map<Integer, Integer> progressMap) {
        for (int i = 1; i < week; i++) {
            System.out.print("Week " + i + " ");
            for (int j = 1; j <= progressMap.get(i); j++) {
                System.out.print("=");
            }
            System.out.println(">");
        }
    }

    public static void checkNumber(Integer week, String numberString,
            Map<Integer, Integer> progressMap) {
        String[] grades = numberString.split(" ");

        if (grades.length != 5) {
            errorReport();
        }

        try {
            int numGrade = 9;
            int tmp = 9;
            for (String grade : grades) {
                tmp = Integer.parseInt(grade);
                if (tmp < numGrade) {
                    numGrade = tmp;
                }
                if (numGrade > 0 && numGrade < 10) {
                    continue;
                } else {
                    errorReport();
                }

            }

            progressMap.put(week, numGrade);
        } catch (Exception e) {
            errorReport();
        }
    }

    public static void errorReport() {
        System.err.println("IllegalArgument");
        System.exit(-1);
    }
}