package day01.ex03;

public class Program {
    public static UsersList creationUsersList() {
        UsersList usersList = new UsersArrayList();

        User user1 = new User("Nikita", 100);
        User user2 = new User("Stas", 200);
        User user3 = new User("Gena", 300);
        User user4 = new User("Turbo", 400);
        User user5 = new User("MishaMetelcin", 500);
        usersList.addUser(user1);
        usersList.addUser(user2);
        usersList.addUser(user3);
        usersList.addUser(user4);
        usersList.addUser(user5);

        return usersList;
    }

    public static TransactionsList creationTransactionsList(UsersList users) {
        TransactionsList transactionsList = new TransactionsLinkedList();

        try {
            for (int i = 0; i < users.getNumberOfUsers(); i++) {
                User user = users.getUserByIndex(i);

                for (int j = i + 1; j < users.getNumberOfUsers();) {
                    User secondUser = users.getUserByIndex(j);

                    Transaction transaction = new Transaction(user.getIdentifier(), user, secondUser, "debit", 10);

                    transactionsList.addTransaction(transaction);
                    break;
                }

            }
        } catch (Exception e) {
            System.out.println("TransactionNotFoundException");
        }

        return transactionsList;
    }

    public static void main(String[] args) {

        UsersList users = creationUsersList();
        TransactionsList transactionsList = creationTransactionsList(users);

        Transaction[] transactionsArray = transactionsList.toArray();
        for (Transaction transaction : transactionsArray) {
            System.out.println("ID: " + transaction.getId() + ", Description: " + transaction.getTransferCategory());
        }
        try {
            transactionsList.removeTransaction("2");
        } catch (Exception e) {
            System.out.println("TransactionNotFoundException");
        }

        transactionsArray = transactionsList.toArray();
        for (Transaction transaction : transactionsArray) {
            System.out.println(
                    "ID: " + transaction.getId() + ", Sender: " + transaction.getSender().getName() + ", Recipient:"
                            + transaction.getRecipient().getName() + ", Description: "
                            + transaction.getTransferCategory() + ", Amount: " + transaction.getTransferAmount());
        }
    }
}