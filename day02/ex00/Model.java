package day02.ex00;

import java.io.*;
import java.util.*;

public class Model {
    private Map<String, List<Byte>> signatures;

    public Model() {
        signatures = new HashMap<>();
        loadSignatures();
    }

    public String checkFileType(String filePath) {
        File file = new File(filePath);
        List<Byte> fileSignature = readSignature(file);
        for (Map.Entry<String, List<Byte>> entry : signatures.entrySet()) {
            if (compareSignature(entry.getValue(), fileSignature)) {
                return entry.getKey();
            }
        }
        return "UNDEFINED";
    }

    private List<Byte> readSignature(File file) {
        List<Byte> fileSignature = new ArrayList<>();

        try (InputStream inputStream = new FileInputStream(file)) {
            int byteValue;
            while ((byteValue = inputStream.read()) != -1) {
                fileSignature.add((byte) byteValue);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fileSignature;
    }

    private void loadSignatures() {
        try (BufferedReader reader = new BufferedReader(
                new FileReader("/home/aladales/JavaCamp/day02/ex00/signatures.txt"))) { // TODO Сделать правильный путь
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String fileType = parts[0].trim();
                String signatureString = parts[1].trim();
                List<Byte> signature = parseSignature(signatureString);
                signatures.put(fileType, signature);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.toString());
        }
    }

    private List<Byte> parseSignature(String signatureString) {
        String[] hexBytes = signatureString.split(" ");
        List<Byte> listBytes = new ArrayList<>();

        for (String hexByte : hexBytes) {
            listBytes.add((byte) Integer.parseInt(hexByte, 16));
        }

        return listBytes;
    }

    private boolean compareSignature(List<Byte> signature1, List<Byte> signature2) {
        if (signature1.size() != signature2.size()) {
            return false;
        }
        for (int i = 0; i < signature1.size(); i++) {
            if (!signature1.get(i).equals(signature2.get(i))) {
                return false;
            }
        }
        return true;
    }
}