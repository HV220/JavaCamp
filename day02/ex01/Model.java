package day02.ex01;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Model {
    private Set<String> dictionary;

    public Model() {
        dictionary = new HashSet<>();
    }

    public void addToDictionary(String word) {
        dictionary.add(word);
    }

    public Set<String> getDictionary() {
        return dictionary;
    }

    public double calculateSimilarity(String file1, String file2) throws IOException {
        int[] vector1 = createVector(dictionary, file1);
        int[] vector2 = createVector(dictionary, file2);

        return calculateSimilarity(vector1, vector2);
    }

    public void writeDictionaryToFile() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("dictionary.txt", true))) {
            for (String word : dictionary) {
                writer.write(word);
                writer.newLine();
            }
            System.out.println("Dictionary file created/updated successfully.");
        } catch (IOException e) {
            throw e;
        }
    }

    private double calculateSimilarity(int[] vector1, int[] vector2) {
        int numerator = 0;
        int magnitude1 = 0;
        int magnitude2 = 0;

        for (int i = 0; i < vector1.length; i++) {
            numerator += vector1[i] * vector2[i];
            magnitude1 += vector1[i] * vector1[i];
            magnitude2 += vector2[i] * vector2[i];
        }

        double denominator = Math.sqrt(magnitude1) * Math.sqrt(magnitude2);

        if (denominator == 0) {
            return 0;
        }

        double similarity = numerator / denominator;
        double roundedSimilarity = Math.round(similarity * 100000000.0) / 100000000.0;
        return roundedSimilarity;
    }

    private int[] createVector(Set<String> dictionary, String file) throws IOException {
        int[] vector = new int[dictionary.size()];
        List<String> words = readFile(file);
        int index = 0;
        for (String word : dictionary) {
            int frequency = countFrequency(word, words);
            vector[index] = frequency;
            index++;
        }
        return vector;
    }

    private int countFrequency(String word, List<String> words) {
        int frequency = 0;
        for (String w : words) {
            if (w.equals(word)) {
                frequency++;
            }
        }
        return frequency;
    }

    public List<String> readFile(String file) throws IOException {
        List<String> words = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] lineWords = line.split("\\s");
                for (String word : lineWords) {
                    words.add(word);
                }
            }
        } catch (IOException e) {
            throw e;
        }
        return words;
    }
}