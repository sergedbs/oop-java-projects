package org.sergedb.oop.classes.textanalyzer;

import java.util.Arrays;
import java.util.Comparator;

public class TextParser {
    private static final String REGEX_URL = "\\b(?:https?://)?(?:www\\.)?(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}(?::\\d{1,5})?(/\\S*/?)?\\b/?";
    private static final String REGEX_WORD = "\\b\\w+(?:'\\w+)?(?:\\.\\d+)?\\b";
    private static final String REGEX_VOWEL = "(?i)[aeiou]";
    private static final String REGEX_CONSONANT = "(?i)[b-df-hj-np-tv-z]";
    private static final String REGEX_LETTER = "[a-zA-Z]";
    private static final String REGEX_SENTENCE = "\\b([A-Z][^.!?\\n]*?(?:\"[^\"]*\"|\\([^)]*\\)|\\b\\w+\\.\\w+\\b|etc\\.(?!\\s*[A-Z])|\\d+\\.\\d+|[^.!?])*?(?:[.!?](?=\\s|\\n|$)|\\n|$))";

    public TextData parseText(String fileName, String content) {
        String text = content.replaceAll(REGEX_URL, "");
        TextData textData = new TextData(fileName, text);

        textData.setWordCount(countWords(text));
        textData.setVowelCount(countVowels(text));
        textData.setConsonantCount(countConsonants(text));
        textData.setLetterCount(countLetters(text));
        textData.setSentenceCount(countSentences(text));
        textData.setLongestWord(findLongestWord(text));

        return textData;
    }

    private int countWords(String text) {
        return text.replaceAll(REGEX_WORD, "*").replaceAll("[^*]", "").length();
    }

    private int countVowels(String text) {
        return text.replaceAll(REGEX_VOWEL, "").length();
    }

    private int countConsonants(String text) {
        return text.replaceAll(REGEX_CONSONANT, "").length();
    }

    private int countLetters(String text) {
        return text.replaceAll(REGEX_LETTER, "").length();
    }

    private int countSentences(String text) {
        return text.replaceAll(REGEX_SENTENCE, "*").replaceAll("[^*]", "").length();
    }

    // This approach (according to some "sources") should be more efficient (but idk honestly).
    private String findLongestWord(String text) {
        return Arrays.stream(text.split("\\s+"))
                     .max(Comparator.comparingInt(String::length))
                     .orElse("");
    }
}
