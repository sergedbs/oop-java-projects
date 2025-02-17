package org.sergedb.oop.classes.runner;

import org.sergedb.oop.common.utils.FileUtils;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class TaskHandler {

    public void executeTask(String taskFlag, Map<String, List<String>> taskArgsMap, boolean noArgs,
                            Consumer<String> taskRunner, String defaultFilePath) {
        if (taskArgsMap.containsKey(taskFlag) || noArgs) {
            String filePath = FileUtils.getFilePath(taskArgsMap.get(taskFlag), defaultFilePath);
            taskRunner.accept(filePath);
        }
    }
}
