package org.sergedb.oop.common.utils;

import java.util.*;

/**
 * Utility class for classifying objects into categories based on provided classification rules.
 */
public class ObjectClassifier<T, C> {

    private final Map<C, List<T>> classificationMap;

    /**
     * Initializes the classifier with categories.
     * @param categories Collection of categories.
     */
    public ObjectClassifier(Collection<C> categories) {
        classificationMap = new HashMap<>();
        for (C category : categories) {
            classificationMap.put(category, new ArrayList<>());
        }
    }

    /**
     * Classifies a list of objects into categories using the provided classification function.
     * @param objects List of objects to classify.
     * @param classifierFunction Function that maps objects to categories.
     */
    public void classify(List<T> objects, java.util.function.Function<T, C> classifierFunction) {
        for (T object : objects) {
            C category = classifierFunction.apply(object);
            classificationMap.getOrDefault(category, new ArrayList<>()).add(object);
        }
    }

    /**
     * Retrieves the classification map with objects grouped by categories.
     * @return Map of categories to lists of objects.
     */
    public Map<C, List<T>> getClassificationMap() {
        return classificationMap;
    }
}
