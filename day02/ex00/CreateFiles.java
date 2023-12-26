package day02.ex00;

import java.io.FileOutputStream;
import java.io.IOException;

public class CreateFiles {
        public static void CreatePackTestFiles() {
                createFile("day02/ex00/test.png", new byte[] { (byte) 0x89, 0x50, 0x4E, 0x47, 0x0D, 0x0A, 0x1A, 0x0A });
                createFile("day02/ex00/test.gif", new byte[] { 0x47, 0x49, 0x46, 0x38, 0x39, 0x61 });
                createFile("day02/ex00/test.jpeg",
                                new byte[] { (byte) 0xFF, (byte) 0xD8, (byte) 0xFF, (byte) 0xE0, 0x00, 0x10, 0x4A, 0x46,
                                                0x49, 0x46, 0x00, 0x01 });
                createFile("day02/ex00/test.bmp", new byte[] { 0x42, 0x4D });
                createFile("day02/ex00/test.pdf", new byte[] { 0x25, 0x50, 0x44, 0x46 });
                createFile("day02/ex00/test.zip", new byte[] { 0x50, 0x4B, 0x03, 0x04 });
                createFile("day02/ex00/test.gzip", new byte[] { 0x1F, (byte) 0x8B, 0x08, 0x00 });
                createFile("day02/ex00/test.tar", new byte[] { 0x75, 0x73, 0x74, 0x61, 0x72 });
                createFile("day02/ex00/test.exe", new byte[] { 0x4D, 0x5A });
                createFile("day02/ex00/test.docx", new byte[] { 0x50, 0x4B, 0x03, 0x04, 0x14, 0x00, 0x06, 0x00 });
                createFile("day02/ex00/test.txt", new byte[] { 0x54, 0x65, 0x78, 0x74, 0x20, 0x46, 0x69, 0x6C, 0x65 });
                createFile("day02/ex00/test.csv",
                                new byte[] { 0x43, 0x6F, 0x6D, 0x6D, 0x61, 0x20, 0x53, 0x65, 0x70, 0x61, 0x72, 0x61,
                                                0x74, 0x65, 0x64, 0x20, 0x56, 0x61, 0x6C, 0x75, 0x65, 0x73 });
                createFile("day02/ex00/test.xml",
                                new byte[] { 0x3C, 0x3F, 0x78, 0x6D, 0x6C, 0x20, 0x76, 0x65, 0x72, 0x73, 0x69, 0x6F,
                                                0x6E, 0x3D, 0x22,
                                                0x31, 0x2E, 0x30, 0x22, 0x20, 0x65, 0x6E, 0x63, 0x6F, 0x64, 0x69, 0x6E,
                                                0x67, 0x3D, 0x22, 0x55,
                                                0x54, 0x46, 0x2D, 0x38, 0x22, 0x3F, 0x3E });
                createFile("day02/ex00/test.json",
                                new byte[] { 0x7B, 0x22, 0x6E, 0x61, 0x6D, 0x65, 0x22, 0x3A, 0x20, 0x22, 0x4A, 0x6F,
                                                0x68, 0x6E, 0x22,
                                                0x2C, 0x20, 0x22, 0x61, 0x67, 0x65, 0x22, 0x3A, 0x20, 0x32, 0x35, 0x2C,
                                                0x20, 0x22, 0x63, 0x6F,
                                                0x75, 0x6E, 0x74, 0x72, 0x79, 0x22, 0x3A, 0x20, 0x7B, 0x22, 0x63, 0x61,
                                                0x72, 0x73, 0x22, 0x3A,
                                                0x20, 0x7B, 0x22, 0x72, 0x65, 0x64, 0x22, 0x3A, 0x20, 0x31, 0x30, 0x2C,
                                                0x20, 0x22, 0x67, 0x72,
                                                0x65, 0x65, 0x6E, 0x22, 0x3A, 0x20, 0x32, 0x2C, 0x20, 0x22, 0x62, 0x6C,
                                                0x75, 0x65, 0x22, 0x3A,
                                                0x20, 0x33, 0x7D, 0x7D, 0x7D });
                createFile("day02/ex00/test.html",
                                new byte[] { 0x3C, 0x68, 0x74, 0x6D, 0x6C, 0x3E, 0x3C, 0x68, 0x65, 0x61, 0x64, 0x3E });
                createFile("day02/ex00/test.css",
                                new byte[] { 0x62, 0x6F, 0x64, 0x79, 0x20, 0x7B, 0x20, 0x62, 0x61, 0x63, 0x6B, 0x67,
                                                0x72, 0x6F, 0x75, 0x6E, 0x64, 0x3A, 0x20, 0x62, 0x6C, 0x61, 0x63, 0x6B,
                                                0x3B, 0x20, 0x7D });
                createFile("day02/ex00/test.java",
                                new byte[] { 0x70, 0x75, 0x62, 0x6C, 0x69, 0x63, 0x20, 0x63, 0x6C, 0x61, 0x73, 0x73,
                                                0x20, 0x4D, 0x6F, 0x64, 0x65, 0x6C, 0x20, 0x7B });
        }

        private static void createFile(String fileName, byte[] signature) {
                try (FileOutputStream fos = new FileOutputStream(fileName)) {
                        fos.write(signature);
                } catch (IOException e) {
                        e.printStackTrace();
                }
        }
}
