package day02.ex02;

import java.io.File;

public class Model {
    private String currentFolder;

    public Model(String currentFolderMain) {
        this.currentFolder = currentFolderMain;
    }

    public String getCurrentFolder() {
        return currentFolder;
    }

    public void listFiles() {

        File folder = new File(currentFolder);
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                String fileSize = file.isDirectory() ? "" : " " + file.length() + " bytes";
                System.out.println(file.getName() + fileSize);
            }
        }
    }

    public void changeDirectory(String folderName) {
        File newFolder = new File(currentFolder + File.separator + folderName);
        if (newFolder.isDirectory()) {
            currentFolder = newFolder.getAbsolutePath();
        } else {
            System.out.println("Folder does not exist.");
        }
    }

    public void moveFile(String source, String destination) {
        File sourceFile = new File(currentFolder + File.separator + source);
        File destinationFile = new File(currentFolder + File.separator + destination);

        if (destinationFile.exists() && destinationFile.isDirectory()) {
            destinationFile = new File(destinationFile.getAbsolutePath() + File.separator + source);
        }

        if (sourceFile.renameTo(destinationFile)) {
            System.out.println("File moved/renamed successfully.");
        } else {
            System.out.println("Failed to move/rename file.");
        }
    }
}
