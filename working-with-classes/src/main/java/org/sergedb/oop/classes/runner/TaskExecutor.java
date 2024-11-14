package org.sergedb.oop.classes.runner;

import org.sergedb.oop.classes.utils.FileHelper;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class TaskExecutor {

    public void executeTask(String taskFlag, Map<String, List<String>> taskArgsMap, boolean noArgs,
                            Consumer<String> taskRunner, String defaultFilePath) {
        if (taskArgsMap.containsKey(taskFlag) || noArgs) {
            String filePath = FileHelper.getFilePath(taskArgsMap.get(taskFlag), defaultFilePath);
            taskRunner.accept(filePath);
        }
    }
}
