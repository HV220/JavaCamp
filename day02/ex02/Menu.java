package day02.ex02;

import java.util.Scanner;

public class Menu {
    private Controller controller;
    private Scanner scanner = new Scanner(System.in);

    public Menu(Controller controller) {
        this.controller = controller;
    }

    public void showMenu() {
        String input;
        do {
            System.out.print(controller.getCurrentFolderAction() + " ");
            input = scanner.nextLine();
            processCommand(input);
        } while (!input.equals("exit"));

        scanner.close();
    }

    private void processCommand(String command) {
        String[] parts = command.trim().split("\\s+");
        String commandName = parts[0].toLowerCase();

        switch (commandName) {
            case "ls":
                controller.listFilesAction();
                break;
            case "cd":
                if (parts.length < 2) {
                    System.out.println("Invalid command. Usage: cd FOLDER_NAME");
                } else {
                    controller.changeDirectoryAction(parts[1]);
                }
                break;
            case "mv":
                if (parts.length < 3) {
                    System.out.println("Invalid command. Usage: mv WHAT WHERE");
                } else {
                    controller.moveFileAction(parts[1], parts[2]);
                }
                break;
            case "exit":
                break;
            default:
                System.out.println("Invalid command.");
        }
    }
}
