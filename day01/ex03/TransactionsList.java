package day01.ex03;

public interface TransactionsList {
    void addTransaction(Transaction transaction) throws TransactionNotFoundException;

    void removeTransaction(String id) throws TransactionNotFoundException;

    Transaction[] toArray();
}
