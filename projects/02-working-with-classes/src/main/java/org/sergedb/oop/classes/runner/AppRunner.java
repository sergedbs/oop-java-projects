package org.sergedb.oop.classes.runner;

import org.sergedb.oop.classes.tasks.display.DisplayComparator;
import org.sergedb.oop.classes.tasks.display.Display;
import org.sergedb.oop.classes.tasks.assistant.AssistantManager;
import org.sergedb.oop.classes.tasks.text.TextData;
import org.sergedb.oop.classes.tasks.text.TextParser;
import org.sergedb.oop.common.utils.ArgumentParser;
import org.sergedb.oop.common.utils.FileUtils;
import org.sergedb.oop.common.utils.JsonUtils;
import org.sergedb.oop.common.utils.TextLoader;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class AppRunner {

    private static final String DEFAULT_FILE_PATH = "projects/02-working-with-classes/src/main/resources/input.txt";
    private static final String DISPLAY_JSON_PATH = "projects/02-working-with-classes/src/main/resources/displays.json";

    private final TaskHandler taskHandler = new TaskHandler();

    public void run(String[] args) {
        Map<String, List<String>> taskArgsMap = ArgumentParser.parseArguments(args);
        boolean noArgs = args.length == 0;

        taskHandler.executeTask("--first", taskArgsMap, noArgs, this::runDisplayTask, DISPLAY_JSON_PATH);
        taskHandler.executeTask("--second", taskArgsMap, noArgs, this::runTextTask, DEFAULT_FILE_PATH);
        taskHandler.executeTask("--third", taskArgsMap, noArgs, this::runAssistantTask, DISPLAY_JSON_PATH);
    }

    private void runDisplayTask(String filePath) {
        System.out.println("\n----------- [Task 1] -----------");
        List<Display> displays = JsonUtils.readFromJson(filePath, Display.class, null);
        if (displays.isEmpty()) return;

        DisplayComparator displayComparator = new DisplayComparator();

        for (int i = 0; i < 3; i++) {
            Collections.shuffle(displays);
            System.out.println(displayComparator.compareTwoDisplays(displays.get(0), displays.get(1)));
        }
    }

    private void runTextTask(String filePath) {
        System.out.println("\n----------- [Task 2] -----------");
        TextParser textParser = new TextParser();

        for (String path : filePath.split(",")) {
            String text = TextLoader.loadText(path);
            if (text != null) {
                TextData textData = textParser.parseText(FileUtils.getFileName(path), text);
                System.out.println(textData);
            }
        }
    }

    private void runAssistantTask(String filePath) {
        System.out.println("\n----------- [Task 3] -----------");
        List<Display> displays = JsonUtils.readFromJson(filePath, Display.class, null);
        if (displays.isEmpty()) return;

        new AssistantManager(displays, "Jora").showMenu();
    }
}
