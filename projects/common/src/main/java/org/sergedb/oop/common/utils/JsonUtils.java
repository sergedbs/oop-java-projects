package org.sergedb.oop.common.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Utility class for JSON operations with error handling and reusable methods.
 * Provides functionality to read from and write to JSON files using Jackson.
 */
public class JsonUtils {

    private static final ObjectMapper objectMapper = createObjectMapper();

    private JsonUtils() { }

    /**
     * Creates and configures an ObjectMapper instance with indentation enabled.
     * @return configured ObjectMapper instance.
     */
    private static ObjectMapper createObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        return mapper;
    }

    /**
     * Reads a JSON file from a specified path, extracting objects from the 'data' key.
     * @param filePath path to the JSON file.
     * @param type class type of the objects to map.
     * @return list of objects of the specified type.
     */
    public static <T> List<T> readFromJson(String filePath, Class<T> type) {
        List<T> result = new ArrayList<>();
        if (!FileUtils.fileExists(filePath) || FileUtils.isFileEmpty(filePath)) {
            System.err.println("File not found or is empty: " + filePath);
            return result;
        }
        try {
            JsonNode rootNode = objectMapper.readTree(new File(filePath));
            JsonNode dataNode = rootNode.get("data");
            if (dataNode != null && dataNode.isArray()) {
                for (JsonNode node : dataNode) {
                    result.add(objectMapper.treeToValue(node, type));
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading JSON from " + filePath + ": " + e.getMessage());
        }
        return result;
    }

    /**
     * Writes classified data to individual JSON files in the specified directory.
     * @param classifiedData map of keys to lists of objects.
     * @param outputDirPath path to the output directory.
     */
    public static <T> void writeClassifiedToJson(Map<?, List<T>> classifiedData, String outputDirPath) {
        try {
            FileUtils.createDirectoriesIfNotExist(outputDirPath);
            for (Map.Entry<?, List<T>> entry : classifiedData.entrySet()) {
                Path outputFile = Path.of(outputDirPath, entry.getKey() + ".json");
                objectMapper.writeValue(Files.newBufferedWriter(outputFile), entry.getValue());
            }
        } catch (IOException e) {
            System.err.println("Error writing classified JSON data: " + e.getMessage());
        }
    }

    /**
     * Provides the shared ObjectMapper instance.
     * @return ObjectMapper instance.
     */
    public static ObjectMapper getObjectMapper() {
        return objectMapper;
    }
}
