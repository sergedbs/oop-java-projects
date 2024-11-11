package org.sergedb.oop.papers_please.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.sergedb.oop.papers_please.models.Individual;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonReader {
    public List<Individual> readIndividuals(String filePath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode data = mapper.readTree(new File(filePath)).get("data");

        List<Individual> individuals = new ArrayList<>();
        for (JsonNode node : data) {
            Individual individual = mapper.treeToValue(node, Individual.class);
            individuals.add(individual);
        }
        return individuals;
    }
}
