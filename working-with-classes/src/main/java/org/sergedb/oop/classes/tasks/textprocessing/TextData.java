package org.sergedb.oop.classes.tasks.textprocessing;

public class TextData {
    private final String fileName;
    private final String text;
    private int wordCount;
    private int vowelCount;
    private int consonantCount;
    private int letterCount;
    private int sentenceCount;
    private String longestWord;

    public TextData(String fileName, String text) {
        this.fileName = fileName;
        this.text = text;
    }

    public String getFileName() {
        return fileName;
    }

    public String getText() {
        return text;
    }

    public int getWordCount() {
        return wordCount;
    }

    public void setWordCount(int wordCount) {
        this.wordCount = wordCount;
    }

    public int getVowelCount() {
        return vowelCount;
    }

    public void setVowelCount(int vowelCount) {
        this.vowelCount = vowelCount;
    }

    public int getConsonantCount() {
        return consonantCount;
    }

    public void setConsonantCount(int consonantCount) {
        this.consonantCount = consonantCount;
    }

    public int getLetterCount() {
        return letterCount;
    }

    public void setLetterCount(int letterCount) {
        this.letterCount = letterCount;
    }

    public int getSentenceCount() {
        return sentenceCount;
    }

    public void setSentenceCount(int sentenceCount) {
        this.sentenceCount = sentenceCount;
    }

    public String getLongestWord() {
        return longestWord;
    }

    public void setLongestWord(String longestWord) {
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
