package edu.school21.models;

public class Car {

    private String make; // Марка автомобиля
    private String model; // Модель автомобиля
    private int year; // Год выпуска
    private String color; // Цвет автомобиля
    private double mileage; // Пробег автомобиля

    public Car() {
        this.make = "Default first make";
        this.model = "Default first model";
        this.year = 2024;
        this.color = "Default first color";
        this.mileage = 0;
    }

    public Car(String make, String model, int year, String color, double mileage) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.color = color;
        this.mileage = mileage;
    }

    public void growMileage(double mileage) {
        this.mileage += mileage;
    }

    @Override
    public String toString() {
        return "Car{" +
                "make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", color='" + color + '\'' +
                ", mileage=" + mileage +
                '}';
    }
}
