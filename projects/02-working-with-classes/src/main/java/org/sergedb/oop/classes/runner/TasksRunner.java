package org.sergedb.oop.classes.runner;

import org.sergedb.oop.classes.tasks.display.DisplayComparator;
import org.sergedb.oop.classes.tasks.display.Display;
import org.sergedb.oop.classes.tasks.displayassistant.AssistantManager;
import org.sergedb.oop.classes.tasks.textprocessing.TextData;
import org.sergedb.oop.classes.tasks.textprocessing.TextParser;
import org.sergedb.oop.classes.utils.ArgumentParser;
import org.sergedb.oop.classes.utils.FileHelper;
import org.sergedb.oop.classes.utils.JsonLoader;
import org.sergedb.oop.classes.utils.TextLoader;

import java.util.Collections;
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
        taskExecutor.executeTask("--third", taskArgsMap, noArgs, this::runAssistantTask, DISPLAY_JSON_PATH);
    }

    private void runDisplayTask(String filePath) {
        System.out.println("\n----------- [Task 1] -----------");

        List<Display> displays = jsonLoader.loadFromJson(filePath, Display.class);
        if (displays == null) return;

        DisplayComparator displayComparator = new DisplayComparator();

        for (int i = 0; i < 3; i++) {
            Collections.shuffle(displays);
            Display display1 = displays.get(0);
            Display display2 = displays.get(1);
            System.out.println(displayComparator.compareTwoDisplays(display1, display2));
        }
    }

    private void runTextTask(String filePath) {
        System.out.println("\n----------- [Task 2] -----------");

        List<String> filePaths = List.of(filePath.split(","));
        TextParser textParser = new TextParser();

        for (String path : filePaths) {
            String text = textLoader.loadText(path);
            if (text == null) continue;

            TextData textData = textParser.parseText(FileHelper.getFileName(path), text);
            System.out.println(textData);
        }
    }

    private void runAssistantTask(String filePath) {
        System.out.println("\n----------- [Task 3] -----------");

        List<Display> displays = jsonLoader.loadFromJson(filePath, Display.class);
        if (displays == null) return;

        AssistantManager assistantManager = new AssistantManager(displays, "Jora");
        assistantManager.showMenu();
    }
}
