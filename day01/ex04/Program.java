package day01.ex04;

import java.util.ArrayList;

public class Program {
    public static void main(String[] args) {

        TransactionsService transactionsService = new TransactionsService();

        User user1 = new User("John Doe", 100.0);
        User user2 = new User("Jane Smith", 200.0);

        transactionsService.addUser(user1);
        transactionsService.addUser(user2);
        try {

            double balance1 = transactionsService.getUserBalance(0);
            double balance2 = transactionsService.getUserBalance(1);

            System.out.println("User 1 balance before transaction: " + balance1);
            System.out.println("User 2 balance before transaction: " + balance2);

            transactionsService.performTransaction(0, 1, balance1);

            double balance3 = transactionsService.getUserBalance(0);
            double balance4 = transactionsService.getUserBalance(1);
            System.out.println("User 1 balance after transaction: " + balance3);
            System.out.println("User 2 balance after transaction: " + balance4);

            TransactionsList user1Transactions = transactionsService.getUserTransactions(0);
            System.out.println("User 1 transactions: " + user1Transactions.toArray().length);

            TransactionsList user2Transactions = transactionsService.getUserTransactions(1);
            System.out.println("User 2 transactions: " + user2Transactions.toArray().length);

            transactionsService.removeTransaction(1, 1);

            user1Transactions = transactionsService.getUserTransactions(0);
            System.out.println("User 1 transactions: " + user1Transactions.toArray().length);

            user2Transactions = transactionsService.getUserTransactions(1);
            System.out.println("User 2 transactions after remove transaction: " + user2Transactions.toArray().length);

            ArrayList<Transaction> unpairedTransactions = transactionsService.getUnpairedTransactions();

            System.out.println("Unpaired transactions: " + unpairedTransactions.size());

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}