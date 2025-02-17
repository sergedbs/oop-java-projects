package org.sergedb.oop.classes.utils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;

public class TextLoader {

    public String loadText(String filePath) {
        if (FileHelper.fileExists(filePath) || FileHelper.isFileEmpty(filePath)) {
            System.out.println("File not found or is empty: " + filePath);
            return null;
        }

        try {
            Path path = Paths.get(filePath);
            return Files.readString(path);
        } catch (IOException e) {
            System.out.println("Error reading text file: " + e.getMessage());
            return null;
        }
    }
}
