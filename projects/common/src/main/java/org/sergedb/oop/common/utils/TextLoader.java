package org.sergedb.oop.common.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Utility class for loading text from files, with file checks using FileHelper.
 */
public class TextLoader {

    /**
     * Loads text content from the specified file path after validating the file.
     *
     * @param filePath Path to the text file.
     * @return The text content as a string, or null if the file is invalid or an error occurs.
     */
    public static String loadText(String filePath) {
        if (FileUtils.fileExists(filePath) || FileUtils.isFileEmpty(filePath)) {
            System.err.println("File not found or is empty: " + filePath);
            return null;
        }

        try {
            Path path = Paths.get(filePath);
            return Files.readString(path);
        } catch (IOException e) {
            System.err.println("Error reading text file: " + e.getMessage());
            return null;
        }
    }
}
