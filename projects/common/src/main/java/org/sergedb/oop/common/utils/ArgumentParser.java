package org.sergedb.oop.common.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Utility class for parsing command-line arguments into a structured map.
 */
public class ArgumentParser {

    /**
     * Parses command-line arguments into a map of flags and their corresponding values.
     *
     * @param args Array of command-line arguments.
     * @return Map where each key is a flag and the value is a list of associated arguments.
     */
    public static Map<String, List<String>> parseArguments(String[] args) {
        Map<String, List<String>> parsedArgs = new HashMap<>();
        for (int i = 0; i < args.length; i++) {
            if (args[i].startsWith("--")) {
                parsedArgs.putIfAbsent(args[i], new ArrayList<>());
                List<String> values = parsedArgs.get(args[i]);
                while (i + 1 < args.length && !args[i + 1].startsWith("--")) {
                    values.add(args[++i]);
                }
            }
        }
        return parsedArgs;
    }

    /**
     * Retrieves a specific argument's value from the parsed arguments map, with an option for default values.
     *
     * @param parsedArgs    The map of parsed arguments.
     * @param flag          The flag to retrieve values for.
     * @param defaultValues Default values to return if the flag is not present.
     * @return List of values for the provided flag or default values if flag is absent.
     */
    public static List<String> getArgument(Map<String, List<String>> parsedArgs, String flag, List<String> defaultValues) {
        return parsedArgs.getOrDefault(flag, defaultValues);
    }
}
