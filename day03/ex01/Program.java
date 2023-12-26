
/**
 * Program
 */
public class Program {
    private static final Object MONITOR = new Object();

    private static final String E = "E";
    private static final String H = "H";
    private static String nextWord = E;
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

            synchronized (MONITOR) {
                for (int i = 0; i < count; i++) {
                try {
                        while (!nextWord.equals(E)) {
                            MONITOR.wait();
                        }
                      
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    printMessage("Egg");
                    
                    nextWord = H;

                    MONITOR.notifyAll();
            }
            }
        }
        ).start();;
            
        new Thread(() -> {

            synchronized (MONITOR) {
                for (int i = 0; i < count; i++) {
                try {
                       while (!nextWord.equals(H)) {
                        MONITOR.wait();
                    }      
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    printMessage("Hen");
                    
                    nextWord = E;

                    MONITOR.notifyAll();
            }
            }
        }
            ).start();;
    }

    private static void printMessage(String message) {
        System.out.println(message);
    }
}
