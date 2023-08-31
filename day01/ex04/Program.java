package day01.ex04;

import java.util.ArrayList;

public class Program {
    public static void main(String[] args) {

        TransactionsService transactionsService = new TransactionsService();

        // Создание и добавление пользователей
        User user1 = new User("John Doe", 100.0);
        User user2 = new User("Jane Smith", 200.0);

        transactionsService.addUser(user1);
        transactionsService.addUser(user2);
        try {

            // Проверка баланса пользователя
            double balance1 = transactionsService.getUserBalance(0);
            System.out.println("User 1 balance: " + balance1); // Ожидаемый результат: 100.0

            // Выполнение транзакции
            transactionsService.performTransaction(0, 1, balance1);

            // Проверка баланса после транзакции
            double balance2 = transactionsService.getUserBalance(0);
            double balance3 = transactionsService.getUserBalance(1);
            System.out.println("User 1 balance after transaction: " + balance2); // Ожидаемый результат: 50.0
            System.out.println("User 2 balance after transaction: " + balance3); // Ожидаемый результат: 250.0

            // Получение списка транзакций пользователя
            TransactionsList user1Transactions = transactionsService.getUserTransactions(0);
            System.out.println("User 1 transactions: " + user1Transactions);

            // Удаление транзакции
            transactionsService.removeTransaction(1, 1);

            // Получение списка непарных транзакций
            ArrayList<Transaction> unpairedTransactions = transactionsService.getUnpairedTransactions();

            System.out.println("Unpaired transactions: " + unpairedTransactions.size());

        } catch (Exception e) {
            System.out.println("TransactionNotFoundException");
        }
    }
}