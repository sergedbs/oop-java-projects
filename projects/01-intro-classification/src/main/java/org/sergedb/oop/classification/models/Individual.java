package org.sergedb.oop.classification.models;

import java.util.List;

public record Individual(int id, Boolean isHumanoid, String planet, Integer age, List<String> traits) {
}
