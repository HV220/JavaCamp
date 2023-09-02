package day01.ex05;

public interface TransactionsList {
    void addTransaction(Transaction transaction) throws TransactionNotFoundException;

    void removeTransaction(Integer id) throws TransactionNotFoundException;

    Transaction[] toArray();
}
