package day02.ex01;

import java.io.File;

public class DeleteFiles {
    public static void deleteFiles() {
        deleteFile("day02/ex01//test.png");
        deleteFile("day02/ex01//test.gif");
        deleteFile("day02/ex01//test.jpeg");
        deleteFile("day02/ex01//test.bmp");
        deleteFile("day02/ex01//test.pdf");
        deleteFile("day02/ex01//test.zip");
        deleteFile("day02/ex01//test.gzip");
        deleteFile("day02/ex01//test.tar");
        deleteFile("day02/ex01//test.exe");
        deleteFile("day02/ex01//test.docx");
        deleteFile("day02/ex01//test.txt");
        deleteFile("day02/ex01//test.csv");
        deleteFile("day02/ex01//test.xml");
        deleteFile("day02/ex01//test.json");
        deleteFile("day02/ex01//test.html");
        deleteFile("day02/ex01//test.css");
        deleteFile("day02/ex01//test.java");
    }

    private static void deleteFile(String fileName) {
        File file = new File(fileName);
        if (file.exists()) {
            if (file.delete()) {
                System.out.println(fileName + " has been deleted.");
            } else {
                System.out.println("Failed to delete " + fileName);
            }
        } else {
            System.out.println(fileName + " does not exist.");
        }
    }
}
