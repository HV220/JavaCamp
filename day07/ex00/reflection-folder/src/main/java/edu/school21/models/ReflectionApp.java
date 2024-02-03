package edu.school21.models;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Scanner;
import java.lang.reflect.*;

public class ReflectionApp {
    public void consoleClassViewer() throws Exception {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Classes:");
        System.out.println("  - User");
        System.out.println("  - Car");
        System.out.println("---------------------");
        System.out.print("Enter class name:\n-> ");
        String className = scanner.nextLine();

        // Загрузка класса по имени
        Class<?> clazz = Class.forName("edu.school21.models." + className);
        System.out.println("---------------------");
        System.out.println("fields:");
        for (Field field : clazz.getDeclaredFields()) {
            System.out.println("  " + field.getType().getSimpleName() + " " + field.getName());
        }
        System.out.println("methods:");
        for (Method method : clazz.getDeclaredMethods()) {
            System.out.println("  " + method.getReturnType().getSimpleName() + " " + method.getName()
                    + Arrays.toString(method.getParameterTypes()));
        }

        // Создание экземпляра класса
        System.out.println("---------------------");
        System.out.println("Let’s create an object.");
        Object obj = clazz.getDeclaredConstructor().newInstance();
        for (Field field : clazz.getDeclaredFields()) {
            System.out.println(field.getName() + ":");
            System.out.print("-> ");
            String value = scanner.nextLine();
            field.setAccessible(true);
            if (field.getType().equals(int.class)) {
                field.setInt(obj, Integer.parseInt(value));
            } else if (field.getType().equals(String.class)) {
                field.set(obj, value);
            }
        }
        System.out.println("Object created: " + obj.toString());

        // Изменение значения поля объекта
        System.out.println("---------------------");
        System.out.print("Enter name of the field for changing:\n-> ");
        String fieldName = scanner.nextLine();
        Field field = clazz.getDeclaredField(fieldName);
        field.setAccessible(true);
        System.out.print("Enter " + field.getType().getSimpleName() + " value:\n-> ");
        String newValue = scanner.nextLine();
        if (field.getType().equals(int.class)) {
            field.setInt(obj, Integer.parseInt(newValue));
        } else if (field.getType().equals(String.class)) {
            field.set(obj, newValue);
        }
        System.out.println("Object updated: " + obj.toString());

        // Вызов метода объекта
        System.out.println("---------------------");
        System.out.print("Enter name of the method for call:\n-> ");
        String methodName = scanner.nextLine();
        Method method = clazz.getDeclaredMethod(methodName, int.class);
        method.setAccessible(true);
        System.out.print("Enter int value:\n-> ");
        int methodValue = Integer.parseInt(scanner.nextLine());
        Object result = method.invoke(obj, methodValue);
        if (method.getReturnType() != void.class) {
            System.out.println("Method returned:");
            System.out.println(result);
        }
        scanner.close();
    }
}
