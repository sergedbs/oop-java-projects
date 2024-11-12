package org.sergedb.oop.classes.task_two;

public class TaskTwo {
    public static void main(String[] args) {
        String defaultFilePath = "src/main/resources/intermediate_tasks/task_two/input.txt";
        String filePath = (args != null && args.length > 0) ? args[0] : defaultFilePath;

        if (args == null || args.length == 0) {
            System.out.println("No file path provided. Using default file: " + defaultFilePath);
        }

        String fileName = filePath.substring(filePath.lastIndexOf('/') + 1);
        String text;
        try {
            text = FileReader.readFileAsString(filePath);
        } catch (Exception e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
            return;
        }

        TextData textData = new TextData(fileName, text);
        System.out.println("File name: " + textData.getFileName());
        System.out.println("Word count: " + textData.getWordCount());
        System.out.println("Vowel count: " + textData.getVowelCount());
        System.out.println("Consonant count: " + textData.getConsonantCount());
        System.out.println("Letter count: " + textData.getLetterCount());
        System.out.println("Sentence count: " + textData.getSentenceCount());
        System.out.println("Longest word: " + textData.getLongestWord());
        // System.out.println("Text: " + textData.getText());
    }
}
