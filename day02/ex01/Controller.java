package day02.ex01;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Controller {
    private Model model;

    public Controller(Model model) {
        this.model = model;
    }

    public void processFilesAction(String file1, String file2) throws IOException {
        try {
            List<String> wordsFile1 = model.readFile(file1);
            List<String> wordsFile2 = model.readFile(file2);

            Set<String> uniqueWords = new HashSet<>();
            uniqueWords.addAll(wordsFile1);
            uniqueWords.addAll(wordsFile2);

            for (String word : uniqueWords) {
                model.addToDictionary(word);
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public double calculateSimilarityAction(String file1, String file2) throws IOException {

        return model.calculateSimilarity(file1, file2);
    }

    public void writeDictionaryToFileAction() throws IOException {
        try {
            model.writeDictionaryToFile();
        } catch (Exception e) {
            throw e;
        }
    }
}