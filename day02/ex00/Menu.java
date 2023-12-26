package day02.ex00;

import java.util.Scanner;

public class Menu {
    private Controller controller;

    public Menu(Controller controller) {
        this.controller = controller;
    }

    public void showMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter the file path (or '42' to quit):");
            String filePath = scanner.nextLine();
            if (filePath.equalsIgnoreCase("42")) {
                break;
            }
            String fileType = controller.checkFileType(filePath);
            System.out.println(fileType);
        }
        scanner.close();
    }
}