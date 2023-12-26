package ex02;

public class Program {
    private static final int MAX_ARRAY_SIZE = 2_000_000;
    private static final int MAX_ELEMENT_VALUE = 1_000;

    public static void main(String[] args) {
        int arraySize = 0;
        int threadsCount = 0;

        for (String arg : args) {
            String[] splitArg = arg.split("=");
            try {
                if (splitArg[0].equals("--arraySize") && splitArg.length > 1) {
                    arraySize = Integer.parseInt(splitArg[1]);
                } else if (splitArg[0].equals("--threadsCount") && splitArg.length > 1) {
                    threadsCount = Integer.parseInt(splitArg[1]);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.exit(-1);
            }

        }

        if (!validateInputData(arraySize, threadsCount)) {
            System.err.println("Common error. Please try again.");
            System.exit(-1);
        } else {
            processArrayWithThreads(createAndFillArray(arraySize), threadsCount, arraySize);
        }
    }

    private static boolean validateInputData(int arraySize, int threadsCount) {

        boolean status = true;

        if (arraySize <= MAX_ARRAY_SIZE) {
            if (threadsCount <= arraySize) {
                status = true;
            } else {
                status = false;
            }
            if (arraySize <= 0 || threadsCount <= 0) {
                status = false;
            }
        } else {
            status = false;
        }

        return status;
    }

    private static int[] createAndFillArray(int size) {
        int[] array = new int[size];

        for (int i = 0; i < size; i++) {
            int tmp = (int) (Math.random() * (2 * MAX_ELEMENT_VALUE + 1)) - MAX_ELEMENT_VALUE;
            if (tmp < 0)
                tmp *= -1;
            array[i] = tmp;
        }

        return array;
    }

    private static void outputData(Summation[] summations, int totalSum, int threadsCount) {
        System.out.println("Sum: " + totalSum);
        for (int i = 0; i < threadsCount; i++) {
            System.out.println(summations[i].getResult());
        }
        System.out.println("Sum by threads: " + totalSum);
    }

    private static void processArrayWithThreads(int[] array, int threadsCount, int arraySize) {

        int chunkSize = arraySize / threadsCount;
        int remainder = arraySize % threadsCount;
        Thread[] threads = new Thread[threadsCount];
        Summation[] summations = new Summation[threadsCount];

        int start = 0;
        for (int i = 0; i < threadsCount; i++) {
            int end = start + chunkSize + (i < remainder ? 1 : 0) - 1;
            summations[i] = new Summation(array, start, end);
            threads[i] = new Thread(summations[i], "Thread " + (i + 1));
            threads[i].start();
            start = end + 1;
        }

        int totalSum = 0;

        try {
            for (int i = 0; i < threadsCount; i++) {
                threads[i].join();
                totalSum += summations[i].getPartialSum();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        outputData(summations, totalSum, threadsCount);
    }
}
