public class Transaction {

    public Transaction(Integer identifier, User recipient, User sender, String transferCategory,
            double transferAmount) {
        this.id_ = identifier;
        this.recipient_ = recipient;
        this.sender_ = sender;
        this.transferCategory_ = transferCategory;
        this.amount_ = transferAmount;
    }

    public Double getTransferAmount() {
        return amount_;
    }

    public Integer getId() {
        return id_;
    }

    public User getRecipient() {
        return recipient_;
    }

    public User getSender() {
        return sender_;
    }

    public String getTransferCategory() {
        return transferCategory_;
    }

    public void setTransferAmount(Double amount_) {
        this.amount_ = amount_;
    }

    public void setId(Integer id_) {
        this.id_ = id_;
    }

    public void setRecipient(User recipient_) {
        this.recipient_ = recipient_;
    }

    public void setSender(User sender_) {
        this.sender_ = sender_;
    }

    public void setTransferCategory(String transferCategory_) {
        this.transferCategory_ = transferCategory_;
    }

    private Integer id_;
    private User recipient_;
    private User sender_;
    private String transferCategory_;
    private Double amount_;
}
