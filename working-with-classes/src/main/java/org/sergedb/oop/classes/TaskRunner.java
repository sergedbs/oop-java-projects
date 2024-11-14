package org.sergedb.oop.classes;

import org.sergedb.oop.classes.display.DisplayComparator;
import org.sergedb.oop.classes.display.Display;
import org.sergedb.oop.classes.textanalyzer.FileReader;
import org.sergedb.oop.classes.textanalyzer.TextData;
import org.sergedb.oop.classes.textanalyzer.TextParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskRunner {

    private static final String DEFAULT_FILE_PATH = "working-with-classes/src/main/resources/input.txt";

    public void run(String[] args) {
        boolean noArgs = args.length == 0;
        Map<String, List<String>> taskArgsMap = parseArguments(args);

        if (shouldExecuteTask(taskArgsMap, "--first", noArgs)) {
            runDisplayTask();
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

    private void runDisplayTask() {
        System.out.println("\n----------- [Task 1] -----------");
        Display lgDisplay = new Display(2560, 1440, 127.7f, "LG 1440p 23 inch");
        Display samsungDisplay = new Display(3840, 2160, 163.18f, "Samsung 4K 27 inch");
        Display dellDisplay = new Display(1920, 1080, 157.35f, "Dell FHD 14 inch");

        DisplayComparator displayComparator = new DisplayComparator();

        System.out.println(displayComparator.compareDisplays(lgDisplay, samsungDisplay));
        System.out.println(displayComparator.compareDisplays(dellDisplay, lgDisplay));
        System.out.println(displayComparator.compareDisplays(samsungDisplay, dellDisplay));
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

    private String getFilePath(List<String> args) {
        if (args == null || args.isEmpty()) {
            System.out.println("No file path provided. Using default file: " + DEFAULT_FILE_PATH);
            return DEFAULT_FILE_PATH;
        }
        return args.getFirst();
    }
}
