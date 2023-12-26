/**
 * Program
 */
public class Program {

    private static int count = 50;

    public static void main(String[] args) {
        
        if (args.length > 0) {
            String[] splitArg = args[0].split("=");
            if (splitArg[0].equals("--count") && splitArg.length > 1) {
                try {
                    count = Integer.parseInt(splitArg[1]);
                } catch (NumberFormatException e) {
                    System.err.println("Invalid count argument. Using default.");
                }
            }
        }

        new Thread(() -> {
            for (int i = 0; i < count; i++) {
                printMessage("Egg");
            }
        }).start();;
            
        new Thread(() -> {
            for (int i = 0; i < count; i++) {
                 printMessage("Hen");
            } 
        }).start();;

        for (int i = 0; i < count; i++) {
                printMessage("Human");
        }
        
    }

    private static void printMessage(String message) {
            System.out.println(message);
    }
}
