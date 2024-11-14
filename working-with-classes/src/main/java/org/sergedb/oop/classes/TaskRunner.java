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
            runDisplayTask(taskArgsMap.get("--first"));
        }
        if (shouldExecuteTask(taskArgsMap, "--second", noArgs)) {
            runTextTask(taskArgsMap.get("--second"));
        }
    }

    private Map<String, List<String>> parseArguments(String[] args) {
        Map<String, List<String>> taskArgsMap = new HashMap<>();
        for (int i = 0; i < args.length; i++) {
            String arg = args[i];
            if (arg.equals("--first") || arg.equals("--second")) {
                taskArgsMap.putIfAbsent(arg, new ArrayList<>());
                List<String> taskArgs = taskArgsMap.get(arg);
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

    private void runDisplayTask(List<String> args) {
        System.out.println("\n----------- [Task 1] -----------");

        String filePath = getDisplayJsonPath(args);
        loadDisplays(filePath);

        comparison.compareDisplays(displays);
    }

    private void runTextTask(List<String> args) {
        System.out.println("\n----------- [Task 2] -----------");

        String filePath = getFilePath(args);

        FileReader fileReader = new FileReader();
        String text = fileReader.readFileAsString(filePath);

        if (text == null || text.isEmpty()) {
            System.out.println("File not found or empty.");
            return;
        }

        String fileName = filePath.substring(filePath.lastIndexOf('/') + 1);

        TextParser textParser = new TextParser();
        TextData textData = textParser.parseText(fileName, text);

        System.out.println(textData);
    }

    private void loadDisplays(String filePath) {
        if (displays == null) {
            displays = jsonLoader.loadDisplaysFromJson(filePath);
            comparison = new Comparison();
        }
    }

    private String getFilePath(List<String> args) {
        if (args == null || args.isEmpty()) {
            System.out.println("No file path provided. Using default file: " + DEFAULT_FILE_PATH);
            return DEFAULT_FILE_PATH;
        }
        return args.getFirst();
    }

    private String getDisplayJsonPath(List<String> args) {
        if (args == null || args.isEmpty()) {
            System.out.println("No file path provided. Using default file: " + DISPLAY_JSON_PATH);
            return DISPLAY_JSON_PATH;
        }
        return args.getFirst();
    }
}
