package org.sergedb.oop.papers_please.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.sergedb.oop.papers_please.models.Individual;
import org.sergedb.oop.papers_please.models.Universe;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class JsonWriter {
    private final ObjectMapper mapper;

    public JsonWriter() {
        this.mapper = new ObjectMapper();
        this.mapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    public void writeClassifiedIndividuals(Map<Universe, List<Individual>> classifiedIndividuals, String outputDirPath) throws IOException {
        Path outputDir = Path.of(outputDirPath);
        Files.createDirectories(outputDir);

        for (Map.Entry<Universe, List<Individual>> entry : classifiedIndividuals.entrySet()) {
            String universeName = String.valueOf(entry.getKey());
            List<Individual> individuals = entry.getValue();
            Path outputFile = outputDir.resolve(universeName + ".json");
            try (var writer = Files.newBufferedWriter(outputFile)) {
                mapper.writeValue(writer, individuals);
            }
        }
    }
}