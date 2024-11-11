package papers_please.classification;

import papers_please.models.Individual;
import papers_please.models.Universe;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class Classifier {

    private final Map<Universe, List<Individual>> universeClassificationMap = new EnumMap<>(Universe.class);

    public Classifier() {
        for (Universe universe : Universe.values()) {
            universeClassificationMap.put(universe, new ArrayList<>());
        }
    }

    public void classify(List<Individual> individuals) {
        for (Individual individual : individuals) {
            Universe universe = classifyIndividual(individual);
            universeClassificationMap.get(universe).add(individual);
        }
    }

    private Universe classifyIndividual(Individual individual) {
        if (ClassificationRules.isStarWars(individual)) {
            return Universe.STAR_WARS;
        } else if (ClassificationRules.isMarvel(individual)) {
            return Universe.MARVEL;
        } else if (ClassificationRules.isHitchhikers(individual)) {
            return Universe.HITCHHIKERS;
        } else if (ClassificationRules.isLordOfTheRings(individual)) {
            return Universe.LOTR;
        } else {
            return Universe.UNKNOWN;
        }
    }

    public Map<Universe, List<Individual>> getUniverseClassificationMap() {
        return universeClassificationMap;
    }
}