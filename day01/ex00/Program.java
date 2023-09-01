public class Program {
    public static void main(String[] args) {
        User user1 = new User(1, "John", 1000);
        User user2 = new User(2, "Jane", 500);

        Transaction transaction = new Transaction(1, user1, user2, "дебеты", 200);

        System.out.println("Информация о пользователе:");
        System.out.println("Идентификатор: " + user1.getIdentifier());
        System.out.println("Имя: " + user1.getName());
        System.out.println("Баланс: " + user1.getBalance());

        System.out.println("\nИнформация о транзакции:");
        System.out.println("Идентификатор: " + transaction.getId());
        System.out.println("Получатель: " + transaction.getRecipient().getName());
        System.out.println("Отправитель: " + transaction.getSender().getName());
        System.out.println("Категория перевода: " + transaction.getTransferCategory());
        System.out.println("Сумма перевода: " + transaction.getTransferAmount());

    }
}