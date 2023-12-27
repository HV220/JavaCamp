package edu.school21.printer.app;

import edu.school21.printer.logic.ImageToCharConverter;

public class Main {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.err.println("Usage: java Main <white_character> <black_character> <path_to_image>");
            System.exit(1);
        }

        char whiteChar = args[0].charAt(0);
        char blackChar = args[1].charAt(0);
        String imagePath = args[2];

        ImageToCharConverter converter = new ImageToCharConverter(whiteChar, blackChar);
        converter.printImage(imagePath);
    }
}