package org.sergedb.oop.classes.runner;

import org.sergedb.oop.classes.display.DisplayComparator;
import org.sergedb.oop.classes.display.Display;
import org.sergedb.oop.classes.textprocessing.TextData;
import org.sergedb.oop.classes.textprocessing.TextParser;
import org.sergedb.oop.classes.utils.ArgumentParser;
import org.sergedb.oop.classes.utils.FileHelper;
import org.sergedb.oop.classes.utils.JsonLoader;
import org.sergedb.oop.classes.utils.TextLoader;

import java.util.List;
import java.util.Map;

public class TasksRunner {

    private static final String DEFAULT_FILE_PATH = "working-with-classes/src/main/resources/input.txt";
    private static final String DISPLAY_JSON_PATH = "working-with-classes/src/main/resources/displays.json";

    private final ArgumentParser argumentParser = new ArgumentParser();
    private final TextLoader textLoader = new TextLoader();
    private final JsonLoader jsonLoader = new JsonLoader();
    private final TaskExecutor taskExecutor = new TaskExecutor();

    public void run(String[] args) {
        Map<String, List<String>> taskArgsMap = argumentParser.parseArguments(args);
        boolean noArgs = args.length == 0;

        taskExecutor.executeTask("--first", taskArgsMap, noArgs, this::runDisplayTask, DISPLAY_JSON_PATH);
        taskExecutor.executeTask("--second", taskArgsMap, noArgs, this::runTextTask, DEFAULT_FILE_PATH);
    }

    private void runDisplayTask(String filePath) {
        System.out.println("\n----------- [Task 1] -----------");

        List<Display> displays = jsonLoader.loadFromJson(filePath, Display.class);
        if (displays == null) return;

        DisplayComparator displayComparator = new DisplayComparator();
        String result = displayComparator.resultsToString(displays);
        System.out.println(result);
    }

    private void runTextTask(String filePath) {
        System.out.println("\n----------- [Task 2] -----------");

        String text = textLoader.loadText(filePath);
        if (text == null) return;

        TextData textData = new TextParser().parseText(FileHelper.getFileName(filePath), text);
        System.out.println(textData);
    }
}
