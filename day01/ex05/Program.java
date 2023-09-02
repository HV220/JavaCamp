package day01.ex05;

public class Program {
    public static void main(String[] args) {

        TransactionsService transactionsService = new TransactionsService();
        Controller controller = new Controller(transactionsService, true);
        Menu consoleView = new Menu(controller);
        consoleView.showMenu();
    }
}