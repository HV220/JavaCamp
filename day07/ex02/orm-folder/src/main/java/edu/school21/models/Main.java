package edu.school21.models;

public class Main {
    public static void main(String[] args) {
        OrmManager manager = new OrmManager();

        User user = new User();
        user.setId(1L);
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setAge(30);

        User foundUser = manager.findById(1L, User.class);
        if (foundUser != null) {
            System.out.println("Пользователь найден: " + foundUser.getFirstName());
        } else {
            System.out.println("Пользователь с ID 1 не найден.");
        }

        user.setAge(31);
        manager.update(user);

    }
}
