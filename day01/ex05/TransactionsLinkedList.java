package day01.ex05;

public class TransactionsLinkedList implements TransactionsList {
    private TransactionNode head;

    private class TransactionNode {
        Transaction transaction;
        TransactionNode next;

        public TransactionNode(Transaction transaction) {
            this.transaction = transaction;
            this.next = null;
        }
    }

    @Override
    public void addTransaction(Transaction transaction) {
        TransactionNode newNode = new TransactionNode(transaction);
        if (head == null) {
            head = newNode;
        } else {
            TransactionNode current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }

    @Override
    public void removeTransaction(Integer id) throws TransactionNotFoundException {
        if (head == null) {
            throw new TransactionNotFoundException("Transaction not found");
        }
        if (head.transaction.getId().equals(id)) {
            head = head.next;
            return;
        }
        TransactionNode current = head;
        while (current.next != null && !current.next.transaction.getId().equals(id)) {
            current = current.next;
        }
        if (current.next == null) {
            throw new TransactionNotFoundException("Transaction not found");
        }
        current.next = current.next.next;
    }

    @Override
    public Transaction[] toArray() {
        int size = 0;
        TransactionNode current = head;
        while (current != null) {
            size++;
            current = current.next;
        }
        Transaction[] array = new Transaction[size];
        current = head;
        for (int i = 0; i < size; i++) {
            array[i] = current.transaction;
            current = current.next;
        }
        return array;
    }
}
