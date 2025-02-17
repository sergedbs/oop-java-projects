package org.sergedb.oop.classes.tasks.text;

public class TextData {
    private final String fileName;
    private final int wordCount;
    private final int vowelCount;
    private final int consonantCount;
    private final int letterCount;
    private final int sentenceCount;
    private final String longestWord;

    public TextData(String fileName, int wordCount, int vowelCount, int consonantCount, int letterCount, int sentenceCount, String longestWord) {
        this.fileName = fileName;
        this.wordCount = wordCount;
        this.vowelCount = vowelCount;
        this.consonantCount = consonantCount;
        this.letterCount = letterCount;
        this.sentenceCount = sentenceCount;
        this.longestWord = longestWord;
    }

    @Override
    public String toString() {
        return "File name: " + fileName + "\n" +
               "Word count: " + wordCount + "\n" +
               "Vowel count: " + vowelCount + "\n" +
               "Consonant count: " + consonantCount + "\n" +
               "Letters count: " + letterCount + "\n" +
               "Sentence count: " + sentenceCount + "\n" +
               "Longest word: " + longestWord;
    }
}
