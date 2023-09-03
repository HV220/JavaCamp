package day02.ex01;

import java.util.Scanner;

public class Menu {
    private Controller controller;
    private Scanner scanner = new Scanner(System.in);

    public Menu(Controller controller) {
        this.controller = controller;
    }

    public void showMenu() {
        try {
            System.out.print("Enter the path to the first file: ");
            String file1 = scanner.nextLine();
            System.out.print("Enter the path to the second file: ");
            String file2 = scanner.nextLine();

            controller.processFilesAction(file1, file2);
            double similarity = controller.calculateSimilarityAction(file1, file2);
            String formattedSimilarity = String.format("%.2f", similarity);
            System.out.println("Similarity = " + formattedSimilarity);
            controller.writeDictionaryToFileAction();
        } catch (Exception e) {
            System.out.println("Error. Try again. " + e.toString());
        }

    }
}