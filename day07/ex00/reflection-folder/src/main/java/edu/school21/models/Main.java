package edu.school21.models;

public class Main {
    public static void main(String[] args) {
        ReflectionApp reflectionApp = new ReflectionApp();
        try {
            reflectionApp.consoleClassViewer();
        } catch (Exception e) {
            System.err.println("Error is: " + e.toString());
        }
    }
}