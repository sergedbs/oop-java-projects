package org.sergedb.oop.intermediate_tasks;

import org.sergedb.oop.intermediate_tasks.task_one.TaskOne;
import org.sergedb.oop.intermediate_tasks.task_two.TaskTwo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<String, List<String>> taskArgsMap = new HashMap<>();
        taskArgsMap.put("--first", new ArrayList<>());
        taskArgsMap.put("--second", new ArrayList<>());

        for (int i = 0; i < args.length; i++) {
            if (taskArgsMap.containsKey(args[i]) && i + 1 < args.length) {
                taskArgsMap.get(args[i]).add(args[++i]);
            }
        }

        System.out.println("----------- [Task 1] -----------");
        TaskOne.main(taskArgsMap.get("--first").toArray(new String[0]));

        System.out.println("\n\n----------- [Task 2] -----------");
        TaskTwo.main(taskArgsMap.get("--second").toArray(new String[0]));
    }
}
