package edu.school21.printer.logic;

import com.diogonunes.jcolor.Attribute;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static com.diogonunes.jcolor.Ansi.colorize;

public class ImageToCharConverter {
    private Attribute whiteCharColor;
    private Attribute blackCharColor;

    public ImageToCharConverter(Attribute whiteCharColor, Attribute blackCharColor) {
        this.whiteCharColor = whiteCharColor;
        this.blackCharColor = blackCharColor;
    }

    public void printImage(String path) {
        try {
            BufferedImage image = ImageIO.read(new File(path));
            for (int y = 0; y < image.getHeight(); y++) {
                for (int x = 0; x < image.getWidth(); x++) {
                    int color = image.getRGB(x, y);
                    if (color == -1) {
                        System.out.print(colorize(" ", whiteCharColor));
                    } else {
                        System.out.print(colorize(" ", blackCharColor));
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