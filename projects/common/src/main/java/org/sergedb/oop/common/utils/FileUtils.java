package org.sergedb.oop.common.utils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.List;

/**
 * Utility class for common file operations such as checking existence, emptiness, and path handling.
 */
public class FileUtils {

    private FileUtils() { }

    /**
     * Checks if the specified file exists.
     * @param filePath Path to the file.
     * @return true if the file exists, false otherwise.
     */
    public static boolean fileExists(String filePath) {
        Path path = Paths.get(filePath);
        return Files.exists(path);
    }

    /**
     * Checks if the specified file is empty.
     * @param filePath Path to the file.
     * @return true if the file is empty or an error occurs, false otherwise.
     */
    public static boolean isFileEmpty(String filePath) {
        try {
            Path path = Paths.get(filePath);
            return Files.size(path) == 0;
        } catch (IOException e) {
            System.err.println("Error checking file size: " + e.getMessage());
            return true;
        }
    }

    /**
     * Gets the file name from the provided file path.
     * @param filePath Path to the file.
     * @return File name as a string.
     */
    public static String getFileName(String filePath) {
        Path path = Paths.get(filePath);
        return path.getFileName().toString();
    }

    /**
     * Gets the file path from provided arguments or defaults to a specified path.
     * @param args List of arguments.
     * @param defaultPath Default path if no argument is provided.
     * @return File path as a string.
     */
    public static String getFilePath(List<String> args, String defaultPath) {
        if (args == null || args.isEmpty()) {
            System.out.println("No file path provided. Using default file: " + defaultPath);
            return defaultPath;
        }
        return args.getFirst();
    }

    /**
     * Creates directories if they do not exist.
     * @param dirPath Path to the directory.
     */
    public static void createDirectoriesIfNotExist(String dirPath) {
        try {
            Path path = Paths.get(dirPath);
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }
        } catch (IOException e) {
            System.err.println("Error creating directories: " + e.getMessage());
        }
    }
}
