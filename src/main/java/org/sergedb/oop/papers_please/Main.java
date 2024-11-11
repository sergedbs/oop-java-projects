package org.sergedb.oop.papers_please;

import org.sergedb.oop.papers_please.classification.Classifier;
import org.sergedb.oop.papers_please.models.Individual;
import org.sergedb.oop.papers_please.models.Universe;
import org.sergedb.oop.papers_please.utils.JsonReader;
import org.sergedb.oop.papers_please.utils.JsonWriter;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        JsonReader jsonReader = new JsonReader();
        JsonWriter jsonWriter = new JsonWriter();
        Classifier classifier = new Classifier();

        try {
            List<Individual> individuals = jsonReader.readIndividuals("src/main/resources/papers_please/input.json");

            classifier.classify(individuals);

            Map<Universe, List<Individual>> classifiedIndividuals = classifier.getUniverseClassificationMap();
            jsonWriter.writeClassifiedIndividuals(classifiedIndividuals, "src/main/resources/papers_please/output");

            System.out.println("Classification completed. Check the output folder for results.");

        } catch (IOException e) {
            System.err.println("Error processing JSON files: " + e.getMessage());
        }
    }
}
