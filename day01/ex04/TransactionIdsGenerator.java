package day01.ex04;

public class TransactionIdsGenerator {
    private TransactionIdsGenerator() {
    }

    public static synchronized TransactionIdsGenerator getInstance() {
        if (instance == null) {
            instance = new TransactionIdsGenerator();
        }
        return instance;
    }

    private static TransactionIdsGenerator instance;

    public Integer generateId() {
        if (lastGeneratedId_ == Integer.MAX_VALUE) {
            throw new RuntimeException("Reached maximum ID value");
        }
        lastGeneratedId_++;
        return lastGeneratedId_;
    }

    private Integer lastGeneratedId_ = -1;
}
