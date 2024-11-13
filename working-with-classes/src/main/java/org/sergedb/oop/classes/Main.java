package org.sergedb.oop.classes;

import org.sergedb.oop.classes.display.ComparisonService;
import org.sergedb.oop.classes.display.Display;
import org.sergedb.oop.classes.textanalyzer.FileReader;
import org.sergedb.oop.classes.textanalyzer.TextData;
import org.sergedb.oop.classes.textanalyzer.TextParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<String, List<String>> taskArgsMap = new HashMap<>();
        boolean noArgs = args.length == 0;

        if (!noArgs) {
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
        }

        // Task 1: Objects & Object Interaction
        if (taskArgsMap.containsKey("--first") || noArgs) {
            System.out.println("\n----------- [Task 1] -----------");
            Display lgDisplay = new Display(2560, 1440, 127.7f, "LG 1440p 23 inch");
            Display samsungDisplay = new Display(3840, 2160, 163.18f, "Samsung 4K 27 inch");
            Display dellDisplay = new Display(1920, 1080, 157.35f, "Dell FHD 14 inch");

            ComparisonService comparisonService = new ComparisonService();

            System.out.println(comparisonService.compareDisplays(lgDisplay, samsungDisplay));
            System.out.println(comparisonService.compareDisplays(dellDisplay, lgDisplay));
            System.out.println(comparisonService.compareDisplays(samsungDisplay, dellDisplay));
        }

        // Task 2: Program arguments and Text parsing
        if (taskArgsMap.containsKey("--second") || noArgs) {
            System.out.println("\n----------- [Task 2] -----------");

            String defaultFilePath = "working-with-classes/src/main/resources/input.txt";
            String filePath = taskArgsMap.getOrDefault("--second", List.of(defaultFilePath)).getFirst();

            if (filePath.equals(defaultFilePath)) {
                System.out.println("No file path provided. Using default file: " + defaultFilePath);
            }

            FileReader fileReader = new FileReader();
            String text = fileReader.readFileAsString(filePath);

            if (text.isEmpty()) {
                System.out.println("File not found or empty.");
            } else {
                String fileName = filePath.substring(filePath.lastIndexOf('/') + 1);
                TextParser textParser = new TextParser();
                TextData textData = textParser.parseText(fileName, text);

                System.out.println(textData);
                // System.out.println("Content: " + textData.getText());
            }
        }
    }
}
