package edu.school21.printer.logic;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageToCharConverter {
    private char whiteChar;
    private char blackChar;

    public ImageToCharConverter(char whiteChar, char blackChar) {
        this.whiteChar = whiteChar;
        this.blackChar = blackChar;
    }

    public void printImage(String path) {
        try {
            BufferedImage image = ImageIO.read(new File(path));
            for (int y = 0; y < image.getHeight(); y++) {
                for (int x = 0; x < image.getWidth(); x++) {
                    int color = image.getRGB(x, y);
                    if (color == -1) { // -1 corresponds to white
                        System.out.print(whiteChar);
                    } else {
                        System.out.print(blackChar);
                    }
                }
                System.out.println();
            }
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
            System.exit(2);
        }
    }
}
