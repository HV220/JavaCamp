package ex03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Program {
    private static final String FILES_URLS_TXT = "./ex03/files_urls.txt";

    public static void main(String[] args) {
        int threadsCount = 3;

        for (String arg : args) {
            String[] splitArg = arg.split("=");
            if (splitArg[0].equals("--threadsCount") && splitArg.length > 1) {
                threadsCount = Integer.parseInt(splitArg[1]);
            }
        }

        ExecutorService executorService = Executors.newFixedThreadPool(threadsCount);

        try (BufferedReader reader = new BufferedReader(new FileReader(FILES_URLS_TXT))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");

                if (parts.length == 2) {
                    int fileNumber = Integer.parseInt(parts[0]);
                    String fileUrl = parts[1];
                    executorService.execute(new Downloader(fileUrl, fileNumber));
                }
            }
        } catch (Exception e) {
            System.out.println("Try input correct values: example: Program.java --threadsCount=3 or check your file");
        }

        executorService.shutdown();
    }
}
