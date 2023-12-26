package day02.ex00;

import java.io.File;

public class Controller {
    private Model model;

    public Controller(Model model) {
        this.model = model;
    }

    public String checkFileType(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            return "File does not exist";
        }
        if (!file.canRead()) {
            return "Cannot read file";
        }
        return model.checkFileType(filePath);
    }
}
