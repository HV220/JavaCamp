package ex02;

class Summation implements Runnable {
    private int[] array;
    private int start, end;
    private int partialSum = 0;
    private String result;

    public Summation(int[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    public void run() {
        for (int i = start; i <= end; i++) {
            partialSum += array[i];
        }
        result = Thread.currentThread().getName() + ": from " + start + " to " + end + " sum is " + partialSum;
    }

    public int getPartialSum() {
        return partialSum;
    }

    public String getResult() {
        return result;
    }
}
