package day02.ex02;

public class Program {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Please provide the absolute path to the current folder.");
            return;
        }

        String currentFolder = args[0];
        Model model = new Model(currentFolder);
        Controller controller = new Controller(model);
        Menu menu = new Menu(controller);
        menu.showMenu();
    }
}
