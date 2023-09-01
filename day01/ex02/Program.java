package day01.ex02;

public class Program {
    public static void main(String[] args) {
        UsersList usersList = new UsersArrayList();

        User user1 = new User("Live", 100);
        User user2 = new User("This", 200);
        User user3 = new User("Sidr", 300);
        User user4 = new User("And", 400);
        User user5 = new User("Pivo", 500);
        usersList.addUser(user1);
        usersList.addUser(user2);
        usersList.addUser(user3);
        usersList.addUser(user4);
        usersList.addUser(user5);

        try {
            System.out.println("getUserByIndex:");

            System.out.println(usersList.getUserByIndex(0).getName());
            System.out.println(usersList.getUserByIndex(1).getName());
            System.out.println(usersList.getUserByIndex(2).getName());
            System.out.println(usersList.getUserByIndex(3).getName());
            System.out.println(usersList.getUserByIndex(4).getName());

            System.out.println("getUserById:");

            System.out.println(usersList.getUserById(0).getName());
            System.out.println(usersList.getUserById(1).getName());
            System.out.println(usersList.getUserById(2).getName());
            System.out.println(usersList.getUserById(3).getName());
            System.out.println(usersList.getUserById(4).getName());

            System.out.println("Количество юзеров: " + usersList.getNumberOfUsers());

            System.out.println("Проверка UserNotFoundException:");
            System.out.println(usersList.getUserByIndex(10).getName());
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}