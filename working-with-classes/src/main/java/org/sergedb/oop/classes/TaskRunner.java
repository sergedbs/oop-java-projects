package org.sergedb.oop.classes;

import org.sergedb.oop.classes.display.Comparison;
import org.sergedb.oop.classes.display.Display;
import org.sergedb.oop.classes.textanalyzer.FileReader;
import org.sergedb.oop.classes.textanalyzer.TextData;
import org.sergedb.oop.classes.textanalyzer.TextParser;

import java.util.*;

public class TaskRunner {

    private static final String DEFAULT_FILE_PATH = "working-with-classes/src/main/resources/input.txt";
    private static final String DISPLAY_JSON_PATH = "working-with-classes/src/main/resources/displays.json";

    private final JsonLoader jsonLoader = new JsonLoader();
    private List<Display> displays;
    private Comparison comparison;

    public void run(String[] args) {
        boolean noArgs = args.length == 0;
        Map<String, List<String>> taskArgsMap = parseArguments(args);

        if (shouldExecuteTask(taskArgsMap, "--first", noArgs)) {
            runDisplayTask(getFile(taskArgsMap.get("--first"), DISPLAY_JSON_PATH));
        }
        if (shouldExecuteTask(taskArgsMap, "--second", noArgs)) {
            runTextTask(getFile(taskArgsMap.get("--second"), DEFAULT_FILE_PATH));
        }
    }

    private Map<String, List<String>> parseArguments(String[] args) {
        Map<String, List<String>> taskArgsMap = new HashMap<>();
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("--first") || args[i].equals("--second")) {
                taskArgsMap.putIfAbsent(args[i], new ArrayList<>());
                List<String> taskArgs = taskArgsMap.get(args[i]);
                while (i + 1 < args.length && !args[i + 1].startsWith("--")) {
                    taskArgs.add(args[++i]);
                }
            }
        }
        return taskArgsMap;
    }

    private boolean shouldExecuteTask(Map<String, List<String>> taskArgsMap, String taskFlag, boolean noArgs) {
        return taskArgsMap.containsKey(taskFlag) || noArgs;
    }

    private void runDisplayTask(String filePath) {
        System.out.println("\n----------- [Task 1] -----------");
        loadDisplays(filePath);
        getComparison().compareDisplays(displays);
    }

    private void runTextTask(String filePath) {
        System.out.println("\n----------- [Task 2] -----------");

        String text = loadFileContent(filePath);
        if (text == null) return;

        TextData textData = new TextParser().parseText(getFileName(filePath), text);
        System.out.println(textData);
    }

    private void loadDisplays(String filePath) {
        if (displays == null) {
            displays = jsonLoader.loadDisplaysFromJson(filePath);
        }
    }

    private Comparison getComparison() {
        if (comparison == null) {
            comparison = new Comparison();
        }
        return comparison;
    }

    private String loadFileContent(String filePath) {
        String content = new FileReader().readFileAsString(filePath);
        if (content == null || content.isEmpty()) {
            System.out.println("File not found or empty.");
            return null;
        }
        return content;
    }

    private String getFile(List<String> args, String defaultPath) {
        if (args == null || args.isEmpty()) {
            System.out.println("No file path provided. Using default file: " + defaultPath);
            return defaultPath;
        }
        return args.getFirst();
    }

    private String getFileName(String filePath) {
        return filePath.substring(filePath.lastIndexOf('/') + 1);
    }
}
