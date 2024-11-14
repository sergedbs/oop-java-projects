package org.sergedb.oop.classes.utils;

import java.util.*;

public class ArgumentParser {

    public Map<String, List<String>> parseArguments(String[] args) {
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
}
