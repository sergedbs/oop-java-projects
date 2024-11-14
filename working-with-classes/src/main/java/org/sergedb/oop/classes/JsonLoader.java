package org.sergedb.oop.classes;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.sergedb.oop.classes.display.Display;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonLoader {

    public List<Display> loadDisplaysFromJson(String jsonFilePath) {
        List<Display> loadedDisplays = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();

        try {
            JsonNode rootNode = mapper.readTree(new File(jsonFilePath));
            for (JsonNode node : rootNode) {
                Display display = mapper.treeToValue(node, Display.class);
                loadedDisplays.add(display);
            }
        } catch (IOException e) {
            System.out.println("Error loading displays from JSON: " + e.getMessage());
        }

        return loadedDisplays;
    }
}
