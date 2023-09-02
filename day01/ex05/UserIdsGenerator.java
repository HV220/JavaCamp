package day01.ex05;

public class UserIdsGenerator {

    private UserIdsGenerator() {
    }

    public static synchronized UserIdsGenerator getInstance() {
        if (instance == null) {
            instance = new UserIdsGenerator();
        }
        return instance;
    }

    private static UserIdsGenerator instance;

    public Integer generateId() {
        if (lastGeneratedId_ == Integer.MAX_VALUE) {
            throw new RuntimeException("Reached maximum ID value");
        }
        lastGeneratedId_++;
        return lastGeneratedId_;
    }

    private Integer lastGeneratedId_ = -1;
}
