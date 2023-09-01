import java.util.Scanner;

public class Program {

    public static void main(String[] args) {

        String input = inputData();

        int[] frequencyArray = calculateData(input);

        outputData(frequencyArray);
    }

    public static String inputData() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        scanner.close();
        return input;
    }

    public static int[] calculateData(String input) {
        int[] frequencyArray = new int[52];

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c >= 'a' && c <= 'z') {
                frequencyArray[c - 'a']++;
            } else if (c >= 'A' && c <= 'Z') {
                frequencyArray[c - 'A' + 26]++;
            }
        }
        return frequencyArray;
    }

    public static void outputData(int[] frequencyArray) {
        int maxFrequency = 0;
        for (int frequency : frequencyArray) {
            if (frequency > maxFrequency) {
                maxFrequency = frequency;
            }
        }

        int scale = 999;
        if (maxFrequency > 0) {
            scale = Math.min((maxFrequency + 9) / 10, 999);
        }

        while (true) {
            int maxIndex = -1;
            int maxCount = 0;

            for (int j = 0; j < frequencyArray.length; j++) {
                if (frequencyArray[j] > maxCount) {
                    maxCount = frequencyArray[j];
                    maxIndex = j;
                }
            }

            if (maxIndex == -1) {
                break;
            }

            char c;
            if (maxIndex < 26) {
                c = (char) (maxIndex + 'a');
            } else {
                c = (char) (maxIndex - 26 + 'A');
            }

            int frequency = frequencyArray[maxIndex];

            int scaledFrequency = frequency != 0 ? Math.min(frequency / scale, 999) : 0;

            System.out.printf("%c %3d ", c, frequency);
            for (int j = 0; j < scaledFrequency; j++) {
                System.out.print("#");
            }
            System.out.println();

            frequencyArray[maxIndex] = 0;
        }
    }

}