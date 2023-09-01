import java.util.Scanner;

class Program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int count = 0;

        while (true) {
            String input = scanner.next();
            if (input.equals("42")) {
                break;
            }
            if (checkSimpleNumber(GetSumNumber(input))) {
                count++;
            }
            scanner.nextLine();
        }

        System.out.println("Count of coffee-request – " + count);

        scanner.close();
    }

    public static int GetSumNumber(String str) {
        int res = 0;
        try {

            for (int i = 0; i < str.length(); i++) {
                char digit = str.charAt(i);
                res += Integer.parseInt(String.valueOf(digit));
            }
        } catch (Exception e) {
            return -1;
        }

        return res;
    }

    public static boolean checkSimpleNumber(int val) {
        if (val <= 1) {
            return false;
        }

        boolean isPrime = true;

        for (int i = 2; i <= Math.sqrt(val); ++i) {
            if (val % i == 0) {
                isPrime = false;
                break;
            }
        }
        return isPrime;
    }
}
