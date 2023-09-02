package day01.ex05;

import java.util.ArrayList;

public class TransactionsService {
    private ArrayList<User> userList;

    public TransactionsService() {
        this.userList = new ArrayList<>();
    }

    public void addUser(User user) {
        userList.add(user);
    }

    public String getUserName(Integer id) {
        return getUserById(id).getName();
    }

    public Double getUserBalance(int userId) {
        User user = getUserById(userId);
        return user.getBalance();
    }

    public void performTransaction(int senderId, int recipientId, double amount) throws Exception {
        User sender = getUserById(senderId);
        User recipient = getUserById(recipientId);

        if (sender.getBalance() < 0 || recipient.getBalance() < 0 || amount < 0) {
            throw new IllegalTransactionException("amount or balance has negate value");
        }

        if (sender.getBalance() < amount) {
            throw new IllegalTransactionException("Insufficient balance for the transaction");
        }
        Transaction debitTransaction = new Transaction(recipient, sender, "DEBIT", amount);
        Transaction creditTransaction = new Transaction(sender, recipient, "CREDIT", amount);

        try {
            sender.addTransaction(debitTransaction);
            recipient.addTransaction(creditTransaction);

        } catch (Exception e) {
            throw e;
        }

        debitTransaction.setStatusPaired(true);
        creditTransaction.setStatusPaired(true);

        sender.setBalance(sender.getBalance() - amount);
        recipient.setBalance(recipient.getBalance() + amount);
    }

    public TransactionsList getUserTransactions(int userId) throws Exception {
        User user = getUserById(userId);
        return user.getTransactions();
    }

    public void removeTransaction(int userId, int transactionId) throws Exception {
        User user = getUserById(userId);
        user.removeTransaction(transactionId);
    }

    public ArrayList<Transaction> getUnpairedTransactions() {
        ArrayList<Transaction> unpairedTransactions = new ArrayList<>();

        for (User user : userList) {
            Transaction[] transactions = user.getTransactions().toArray();

            for (Transaction transaction : transactions) {
                if (!transaction.isPaired()) {
                    unpairedTransactions.add(transaction);
                }
            }
        }

        return unpairedTransactions;
    }

    private User getUserById(int userId) {
        for (User user : userList) {
            if (user.getIdentifier() == userId) {
                return user;
            }
        }
        throw new IllegalArgumentException("User not found");
    }
}