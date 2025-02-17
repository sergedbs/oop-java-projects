package org.sergedb.oop.classification.classification;

import org.sergedb.oop.classification.models.Individual;
import org.sergedb.oop.classification.models.Universe;
import org.sergedb.oop.common.utils.JsonUtils;
import org.sergedb.oop.common.utils.ObjectClassifier;
import java.util.List;
import java.util.Map;

public class Classifier {

    private final ObjectClassifier<Individual, Universe> classifier;

    public Classifier() {
        classifier = new ObjectClassifier<>(List.of(Universe.values()));
    }

    public void classifyFromJson(String inputFilePath) {
        List<Individual> individuals = JsonUtils.readFromJson(inputFilePath, Individual.class, "data");
        classifier.classify(individuals, ClassificationRules::determineUniverse);
    }

    public void writeClassifiedIndividuals(String outputDirPath) {
        Map<Universe, List<Individual>> classifiedMap = classifier.getClassificationMap();
        JsonUtils.writeClassifiedToJson(classifiedMap, outputDirPath);
    }

    public Map<Universe, List<Individual>> getClassifiedMap() {
        return classifier.getClassificationMap();
    }
}
