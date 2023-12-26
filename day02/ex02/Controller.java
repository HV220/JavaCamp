package day02.ex02;

public class Controller {
    private Model model;

    public Controller(Model model) {
        this.model = model;
    }

    public void listFilesAction() {
        this.model.listFiles();
    }

    public void changeDirectoryAction(String folderName) {
        this.model.changeDirectory(folderName);
    }

    public void moveFileAction(String source, String destination) {
        this.model.moveFile(source, destination);
    }

    public String getCurrentFolderAction() {
        return this.model.getCurrentFolder();
    }
}