package ex03;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class Downloader implements Runnable {
    private final String url;
    private final int fileNumber;

    public Downloader(String url, int fileNumber) {
        this.url = url;
        this.fileNumber = fileNumber;
    }

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + " start download file number " + fileNumber);

        try (InputStream in = new BufferedInputStream(new URL(url).openStream());
                FileOutputStream fileOutputStream = new FileOutputStream(
                        "./ex03/file" + fileNumber + url.substring(url.lastIndexOf(".")))) {
            byte dataBuffer[] = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
            System.out.println(threadName + " finish download file number " + fileNumber);
        } catch (Exception e) {
            System.err.println(threadName + " failed to download file number " + fileNumber);
        }
    }
}
