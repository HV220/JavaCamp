package edu.school21.numbers;

public class NumberWorker {
    public boolean isPrime(int number) {
        if (number <= 1) {
            throw new IllegalNumberException("not equals 0 and be positive!");
        }
        boolean b = true;
        for (int i = 2; i < number && b; i++) {
            if (number % i == 0 && number != i) {
                b = false;
            }
        }
        return b;
    }

    public int digitsSum(int number) {
        int a = number;
        int sum = 0;
        while (a != 0) {
            sum += a % 10;
            a = a / 10;
        }
        return sum;
    }


}
