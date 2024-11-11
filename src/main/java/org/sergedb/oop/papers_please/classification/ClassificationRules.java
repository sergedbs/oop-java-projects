package org.sergedb.oop.papers_please.classification;

import org.sergedb.oop.papers_please.models.Individual;

import java.util.HashSet;
import java.util.Set;

public class ClassificationRules {

    public static boolean isStarWars(Individual individual) {
        return (matchesPlanet(individual.planet(), "Kashyyyk") &&
                matchesIsHumanoid(individual.isHumanoid(), false) &&
                matchesAgeRange(individual.age(), 400) &&
                matchesTraits(individual, Set.of("HAIRY", "TALL"))) ||
                (matchesPlanet(individual.planet(), "Endor") &&
                        matchesIsHumanoid(individual.isHumanoid(), false) &&
                        matchesAgeRange(individual.age(), 60) &&
                        matchesTraits(individual, Set.of("SHORT", "HAIRY")));
    }

    public static boolean isMarvel(Individual individual) {
        return matchesPlanet(individual.planet(), "Asgard") &&
                matchesIsHumanoid(individual.isHumanoid(), true) &&
                matchesAgeRange(individual.age(), 5000) &&
                matchesTraits(individual, Set.of("BLONDE", "TALL"));
    }

    public static boolean isHitchhikers(Individual individual) {
        return (matchesPlanet(individual.planet(), "Betelgeuse") &&
                matchesIsHumanoid(individual.isHumanoid(), true) &&
                matchesAgeRange(individual.age(), 100) &&
                matchesTraits(individual, Set.of("EXTRA_ARMS", "EXTRA_HEAD"))) ||
                (matchesPlanet(individual.planet(), "Vogsphere") &&
                        matchesIsHumanoid(individual.isHumanoid(), false) &&
                        matchesAgeRange(individual.age(), 200) &&
                        matchesTraits(individual, Set.of("GREEN", "BULKY")));
    }

    public static boolean isLordOfTheRings(Individual individual) {
        return (matchesPlanet(individual.planet(), "Earth") &&
                matchesIsHumanoid(individual.isHumanoid(), true) &&
                matchesTraits(individual, Set.of("BLONDE", "POINTY_EARS"))) ||
                (matchesPlanet(individual.planet(), "Earth") &&
                        matchesIsHumanoid(individual.isHumanoid(), true) &&
                        matchesAgeRange(individual.age(), 200) &&
                        matchesTraits(individual, Set.of("SHORT", "BULKY")));
    }

    private static boolean matchesPlanet(String actualPlanet, String expectedPlanet) {
        return actualPlanet == null || actualPlanet.equals(expectedPlanet);
    }

    private static boolean matchesIsHumanoid(Boolean actualIsHumanoid, Boolean expectedIsHumanoid) {
        return actualIsHumanoid == null || actualIsHumanoid.equals(expectedIsHumanoid);
    }

    private static boolean matchesAgeRange(Integer age, int max) {
        return age == null || (age >= 0 && age <= max);
    }

    private static boolean matchesTraits(Individual individual, Set<String> requiredTraits) {
        if (individual.traits() == null) {
            return true;
        }
        return new HashSet<>(individual.traits()).containsAll(requiredTraits);
    }
}