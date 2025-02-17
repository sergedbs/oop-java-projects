package org.sergedb.oop.classification.classification;

import org.sergedb.oop.classification.models.Individual;
import org.sergedb.oop.classification.models.Universe;
import java.util.Set;

public class ClassificationRules {

    public static Universe determineUniverse(Individual individual) {
        if (tryMatch(individual, ClassificationRules::isStarWars)) return Universe.STAR_WARS;
        if (tryMatch(individual, ClassificationRules::isMarvel)) return Universe.MARVEL;
        if (tryMatch(individual, ClassificationRules::isHitchhikers)) return Universe.HITCHHIKERS;
        if (tryMatch(individual, ClassificationRules::isLordOfTheRings)) return Universe.LOTR;
        return Universe.UNKNOWN;
    }

    private static boolean tryMatch(Individual individual, MatchFunction matchFunction) {
        return matchFunction.match(individual);
    }

    private static boolean isStarWars(Individual individual) {
        return matches(individual, "KASHYYYK", false, 400, Set.of("HAIRY", "TALL")) ||
               matches(individual, "ENDOR", false, 60, Set.of("SHORT", "HAIRY"));
    }

    private static boolean isMarvel(Individual individual) {
        return matches(individual, "ASGARD", true, 5000, Set.of("BLONDE", "TALL"));
    }

    private static boolean isHitchhikers(Individual individual) {
        return matches(individual, "BETELGEUSE", true, 100, Set.of("EXTRA_ARMS", "EXTRA_HEAD")) ||
               matches(individual, "VOGSPHERE", false, 200, Set.of("GREEN", "BULKY"));
    }

    private static boolean isLordOfTheRings(Individual individual) {
        return matches(individual, "EARTH", true, null, Set.of("BLONDE", "POINTY_EARS")) ||
               matches(individual, "EARTH", true, 200, Set.of("SHORT", "BULKY"));
    }

    private static boolean matches(Individual individual, String planet, Boolean isHumanoid, Integer maxAge, Set<String> traits) {
        if (planet != null && individual.planet() != null && !individual.planet().equalsIgnoreCase(planet)) return false;
        if (isHumanoid != null && individual.isHumanoid() != null && !individual.isHumanoid().equals(isHumanoid)) return false;
        if (maxAge != null && individual.age() != null && individual.age() > maxAge) return false;
        if (traits != null && individual.traits() != null) {
            for (String trait : traits) {
                if (individual.traits().contains(trait)) return true;
            }
            return false;
        }
        return true;
    }

    @FunctionalInterface
    private interface MatchFunction {
        boolean match(Individual individual);
    }
}
