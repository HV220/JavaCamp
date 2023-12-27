package edu.school21.printer.app;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import edu.school21.printer.logic.ImageToCharConverter;
import com.diogonunes.jcolor.Attribute;

import static com.diogonunes.jcolor.Attribute.*;

public class Main {

    @Parameter(names = "--white", description = "Character to represent white color")
    private String white;

    @Parameter(names = "--black", description = "Character to represent black color")
    private String black;

    @Parameter(description = "Path to image file")
    private String imagePath;

    public static void main(String[] args) {
        Main main = new Main();
        JCommander jCommander = JCommander.newBuilder()
                .addObject(main)
                .build();
        try {
            jCommander.parse(args);
        } catch (ParameterException e) {
            System.err.println(e.getMessage());
            jCommander.usage();
            System.exit(1);
        }

        Attribute whiteCharColor = main.parseColor(main.white);
        Attribute blackCharColor = main.parseColor(main.black);

        ImageToCharConverter converter = new ImageToCharConverter(whiteCharColor, blackCharColor);
        converter.printImage(main.imagePath);
    }

    private Attribute parseColor(String colorName) {
        switch (colorName.toUpperCase()) {
            case "RED":
                return BACK_COLOR(255, 0, 0);
            case "GREEN":
                return BACK_COLOR(0, 255, 0);
            case "BLUE":
                return BACK_COLOR(0, 0, 255);
            default:
                throw new IllegalArgumentException("Invalid color: " + colorName);
        }
    }
}
