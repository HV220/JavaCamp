import java.util.Scanner;

class Program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int number = scanner.nextInt();

        if (number <= 1) {
            System.out.println("IllegalArgument");
            System.exit(-1);
        }

        int steps = 1;
        boolean isPrime = true;

        for (int i = 2; i <= Math.sqrt(number); ++i, ++steps) {
            if (number % i == 0) {
                isPrime = false;
                break;
            }
        }

        System.out.println(isPrime + " " + steps);

        scanner.close();
    }
}