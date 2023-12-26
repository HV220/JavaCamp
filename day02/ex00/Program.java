package day02.ex00;

public class Program {
    public static void main(String[] args) {

        CreateFiles.CreatePackTestFiles();

        Model model = new Model();
        Controller controller = new Controller(model);
        Menu consoleView = new Menu(controller);
        consoleView.showMenu();

        DeleteFiles.deleteFiles();
    }
}
