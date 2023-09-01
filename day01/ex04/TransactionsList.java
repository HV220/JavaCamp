package day01.ex04;

public interface TransactionsList {
    void addTransaction(Transaction transaction) throws TransactionNotFoundException;

    void removeTransaction(Integer id) throws TransactionNotFoundException;

    Transaction[] toArray();
}
