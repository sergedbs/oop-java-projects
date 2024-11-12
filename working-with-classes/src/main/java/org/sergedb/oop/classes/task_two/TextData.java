package org.sergedb.oop.classes.task_two;

import java.util.Arrays;
import java.util.Comparator;

public class TextData {
    private static final String REGEX_URL = "\\b(?:https?://)?(?:www\\.)?(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}(?::\\d{1,5})?(/\\S*/?)?\\b/?";
    private static final String REGEX_WORD = "\\b\\w+(?:'\\w+)?(?:\\.\\d+)?\\b";
    private static final String REGEX_VOWEL = "(?i)[aeiou]";
    private static final String REGEX_CONSONANT = "(?i)[b-df-hj-np-tv-z]";
    private static final String REGEX_LETTER = "[a-zA-Z]";
    private static final String REGEX_SENTENCE = "\\b([A-Z][^.!?\\n]*?(?:\"[^\"]*\"|\\([^)]*\\)|\\b\\w+\\.\\w+\\b|etc\\.(?!\\s*[A-Z])|\\d+\\.\\d+|[^.!?])*?(?:[.!?](?=\\s|\\n|$)|\\n|$))";

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
        this.text = removeUrls(text);
        analyzeText();
    }

    private String removeUrls(String text) {
        return text.replaceAll(REGEX_URL, "");
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

    public int getVowelCount() {
        return vowelCount;
    }

    public int getConsonantCount() {
        return consonantCount;
    }

    public int getLetterCount() {
        return letterCount;
    }

    public int getSentenceCount() {
        return sentenceCount;
    }

    public String getLongestWord() {
        return longestWord;
    }

    private int countWords() {
        return text.replaceAll(REGEX_WORD, "*").replaceAll("[^*]", "").length();
    }

    private int countVowels() {
        return text.replaceAll(REGEX_VOWEL, "").length();
    }

    private int countConsonants() {
        return text.replaceAll(REGEX_CONSONANT, "").length();
    }

    private int countLetters() {
        return text.replaceAll(REGEX_LETTER, "").length();
    }

    private int countSentences() {
        return text.replaceAll(REGEX_SENTENCE, "*").replaceAll("[^*]", "").length();
    }

    // The old classic implementation.
    /*
        private String findLongestWord() {
        String[] words = text.split("\\s+");
        String longestWord = "";

        for (String word : words) {
            if (word.length() > longestWord.length()) {
                longestWord = word;
            }
        }

        return longestWord;
    } */

    // This approach (according to some "sources") should be more efficient (but idk honestly).
    private String findLongestWord() {
        return Arrays.stream(text.split("\\s+"))
                     .max(Comparator.comparingInt(String::length))
                     .orElse("");
    }

    public void analyzeText() {
        wordCount = countWords();
        vowelCount = countVowels();
        consonantCount = countConsonants();
        letterCount = countLetters();
        sentenceCount = countSentences();
        longestWord = findLongestWord();
    }
}