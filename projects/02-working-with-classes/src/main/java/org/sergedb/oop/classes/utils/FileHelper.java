package org.sergedb.oop.classes.utils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileHelper {

    public static boolean fileExists(String filePath) {
        Path path = Paths.get(filePath);
        return !Files.exists(path);
    }

    public static boolean isFileEmpty(String filePath) {
        try {
            Path path = Paths.get(filePath);
            return Files.size(path) == 0;
        } catch (Exception e) {
            System.out.println("Error checking file size: " + e.getMessage());
            return true;
        }
    }

    public static String getFileName(String filePath) {
        Path path = Paths.get(filePath);
        return path.getFileName().toString();
    }

    public static String getFilePath(List<String> args, String defaultPath) {
        if (args == null || args.isEmpty()) {
            System.out.println("\nNo file path provided. Using default file: " + defaultPath);
            return defaultPath;
        }
        return args.getFirst();
    }
}
