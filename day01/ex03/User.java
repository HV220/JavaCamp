package day01.ex03;

public class User {

    public User(String name, double balance) {
        this.id_ = UserIdsGenerator.getInstance().generateId();
        this.name_ = name;
        this.balance_ = balance;
    }

    public Double getBalance() {
        return balance_;
    }

    public Integer getIdentifier() {

        return id_;
    }

    public String getName() {
        return name_;
    }

    public void setBalance(Double balance) {
        balance_ = balance;
    }

    public void setName(String name) {
        name_ = name;
    }

    public TransactionsList getTransactions() {
        return transactions_;
    }

    private Integer id_;
    private String name_;
    private Double balance_;
    private TransactionsList transactions_;
}
