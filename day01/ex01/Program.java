package day01.ex01;

class Program {
    public static void main(String[] args) {
        User user1 = new User("John", 1000);
        User user2 = new User("Jane", 500);

        System.out.println("Информация о пользователе:");
        System.out.println("Идентификатор: " + user1.getIdentifier());
        System.out.println("Имя: " + user1.getName());
        System.out.println("Баланс: " + user1.getBalance());

        System.out.println("Информация о пользователе:");
        System.out.println("Идентификатор: " + user2.getIdentifier());
        System.out.println("Имя: " + user2.getName());
        System.out.println("Баланс: " + user2.getBalance());
    }
}
