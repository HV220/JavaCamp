package day01.ex05;

public class User {

    public User(String name, double balance) {
        this.id_ = UserIdsGenerator.getInstance().generateId();
        this.name_ = name;
        this.balance_ = balance;
    }

    public void setBalance(Double balance) {
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

    public TransactionsList getTransactions() {
        return transactions_;
    }

    public void addTransaction(Transaction transaction) throws Exception {
        try {
            this.transactions_.addTransaction(transaction);
        } catch (Exception e) {
            throw e;
        }
    }

    public void removeTransaction(Integer idTransaction) throws Exception {
        try {
            this.transactions_.removeTransaction(idTransaction);
        } catch (Exception e) {
            throw e;
        }
    }

    private Integer id_;
    private String name_;
    private Double balance_;
    private TransactionsList transactions_ = new TransactionsLinkedList();
}
