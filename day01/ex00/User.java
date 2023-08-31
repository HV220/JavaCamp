public class User {

    public User(int identifier, String name, double balance) {
        this.id_ = identifier;
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

    public void setIdentifier(Integer identifier) {
        id_ = identifier;
    }

    public void setName(String name) {
        name_ = name;
    }

    private Integer id_;
    private String name_;
    private Double balance_;
}
