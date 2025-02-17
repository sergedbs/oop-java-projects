package org.sergedb.oop.classification;

import org.sergedb.oop.classification.classification.Classifier;

public class Main {
    private static final String INPUT_FILE_PATH = "projects/01-intro-classification/src/main/resources/input.json";
    private static final String OUTPUT_FOLDER_PATH = "projects/01-intro-classification/src/main/resources/output";

    public static void main(String[] args) {
        Classifier classifier = new Classifier();

        classifier.classifyFromJson(INPUT_FILE_PATH);

        classifier.writeClassifiedIndividuals(OUTPUT_FOLDER_PATH);

        System.out.println("Classification completed. Check the output folder for results.");
    }
}
