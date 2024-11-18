package org.sergedb.oop.intro;

import org.sergedb.oop.intro.classification.Classifier;
import org.sergedb.oop.intro.models.Individual;
import org.sergedb.oop.intro.models.Universe;
import org.sergedb.oop.intro.utils.JsonReader;
import org.sergedb.oop.intro.utils.JsonWriter;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Main {
    private static final String INPUT_FILE_PATH = "intro-classification/src/main/resources/input.json";
    private static final String OUTPUT_FOLDER_PATH = "intro-classification/src/main/resources/output";

    public static void main(String[] args) {
        JsonReader jsonReader = new JsonReader();
        JsonWriter jsonWriter = new JsonWriter();
        Classifier classifier = new Classifier();

        try {
            List<Individual> individuals = jsonReader.readIndividuals(INPUT_FILE_PATH);

            classifier.classify(individuals);

            Map<Universe, List<Individual>> classifiedIndividuals = classifier.getUniverseClassificationMap();
            jsonWriter.writeClassifiedIndividuals(classifiedIndividuals, OUTPUT_FOLDER_PATH);

            System.out.println("Classification completed. Check the output folder for results.");

        } catch (IOException e) {
            System.err.println("Error processing JSON files: " + e.getMessage());
        }
    }
}
