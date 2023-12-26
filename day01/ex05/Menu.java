package day01.ex05;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class Menu {
    private Controller controller_;
    private Scanner scanner_ = new Scanner(System.in);

    public Menu(Controller controller) {
        this.controller_ = controller;
    }

    public void showMenu() {
        int choice;

        do {
            viewMenu();

            choice = scanner_.nextInt();
            scanner_.nextLine();

            switch (choice) {
                case 1:
                    addUser();
                    break;
                case 2:
                    viewUserBalances();
                    break;
                case 3:
                    performTransfer();
                    break;
                case 4:
                    viewTransactionsForUser();
                    break;
                case 5:
                    if (isDevMode()) {
                        removeTransferById();
                    } else {
                        System.out.println("Invalid choice. Please try again.");
                    }
                    break;
                case 6:
                    if (isDevMode()) {
                        checkTransferValidity();
                    } else {
                        System.out.println("Invalid choice. Please try again.");
                    }
                    break;
                case 7:
                    System.out.println("Finishing execution...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }

            System.out.println();
        } while (choice != 7);
    }

    private void addUser() {
        System.out.println("Enter a user name and a balance:");

        try {
            String name = scanner_.nextLine();
            double balance = scanner_.nextDouble();
            Map<Boolean, String> response = controller_.addUserAction(name, balance);

            for (Map.Entry<Boolean, String> entry : response.entrySet()) {
                boolean isSuccess = entry.getKey();
                String message = entry.getValue();

                if (isSuccess) {
                    System.out.println("User added: " + message);
                } else {
                    System.out.println("Failed to add user: " + message);
                }
            }
        } catch (Exception e) {
            System.out.println("Failed to add user. Try again.");
            scanner_.nextLine();
        }
    }

    private void viewUserBalances() {
        System.out.println("Enter a user ID:");

        try {
            int id = scanner_.nextInt();
            Map<Boolean, String> response = controller_.viewUserBalancesAction(id);

            for (Map.Entry<Boolean, String> entry : response.entrySet()) {
                boolean isSuccess = entry.getKey();
                String message = entry.getValue();

                if (isSuccess) {
                    System.out.println("User balance is: " + message);
                } else {
                    System.out.println("Failed to get balance cause: " + message);
                }
            }
        } catch (Exception e) {
            System.out.println("Failed to get user. Try again.");
            scanner_.nextLine();
        }
    }

    private void performTransfer() {
        System.out.println("Enter a sender ID, a recipient ID, and a transfer amount");

        try {
            int senderID = scanner_.nextInt();
            int recipientID = scanner_.nextInt();
            Double transferAmount = scanner_.nextDouble();

            Map<Boolean, String> response = controller_.performTransferAction(senderID, recipientID, transferAmount);

            for (Map.Entry<Boolean, String> entry : response.entrySet()) {
                boolean isSuccess = entry.getKey();
                String message = entry.getValue();

                if (isSuccess) {
                    System.out.println("Trancfer is: " + message);
                } else {
                    System.out.println("Failed to trancfer cause: " + message);
                }
            }
        } catch (Exception e) {
            System.out.println("Failed to trancfer. Try again.");
            scanner_.nextLine();
        }
    }

    private void viewTransactionsForUser() {
        System.out.println("Enter a user ID");

        try {
            int userID = scanner_.nextInt();

            ArrayList<String> response = controller_.viewTransactionsForUserAction(userID);

            if (response.isEmpty()) {
                System.out.println("Failed to get transactions. Try again.");
                return;
            }

            for (String string : response) {
                System.out.println(string);
            }

        } catch (Exception e) {
            System.out.println("Failed to get transactions. Try again.");
            scanner_.nextLine();
        }
    }

    private void removeTransferById() {
        System.out.println("Enter a user ID and a transfer ID");

        try {
            int userID = scanner_.nextInt();
            int transactionID = scanner_.nextInt();

            Map<Boolean, String> response = controller_.removeTransferByIdAction(userID, transactionID);

            for (Map.Entry<Boolean, String> entry : response.entrySet()) {
                boolean isSuccess = entry.getKey();
                String message = entry.getValue();

                if (isSuccess) {
                    System.out.println("Remove is: " + message);
                } else {
                    System.out.println("Failed to remove cause: " + message);
                }
            }
        } catch (Exception e) {
            System.out.println("Failed to read line. Try again.");
            scanner_.nextLine();
        }
    }

    private void checkTransferValidity() {
        try {
            ArrayList<String> response = controller_.checkTransferValidityAction();

            if (response.isEmpty()) {
                System.out.println("Good. No unacknowledged transfer.");
                return;
            }

            for (String string : response) {
                System.out.println(string);
            }

        } catch (Exception e) {
            System.out.println("We have a problem cap. Try again.");
            scanner_.nextLine();
        }
    }

    private boolean isDevMode() {
        return controller_.isDevelopAction();
    }

    private void viewMenu() {
        System.out.println("=== Menu ===");
        System.out.println("1. Add a user");
        System.out.println("2. View user balances");
        System.out.println("3. Perform a transfer");
        System.out.println("4. View all transactions for a specific user");
        System.out.println("5. DEV - Remove a transfer by ID");
        System.out.println("6. DEV - Check transfer validity");
        System.out.println("7. Finish execution");
        System.out.print("Enter your choice: ");
    }
}