package org.sergedb.oop.classes.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonLoader {

    private final ObjectMapper mapper = new ObjectMapper();

    public <T> List<T> loadFromJson(String jsonFilePath, Class<T> targetClass) {
        List<T> loadedObjects = new ArrayList<>();

        if (FileHelper.fileExists(jsonFilePath) || FileHelper.isFileEmpty(jsonFilePath)) {
            System.out.println("JSON file not found or is empty: " + jsonFilePath);
            return loadedObjects;
        }

        try {
            JsonNode rootNode = mapper.readTree(new File(jsonFilePath));
            for (JsonNode node : rootNode) {
                T object = mapper.treeToValue(node, targetClass);
                loadedObjects.add(object);
            }
        } catch (IOException e) {
            System.out.println("Error loading objects from JSON: " + e.getMessage());
        }

        return loadedObjects;
    }
}
