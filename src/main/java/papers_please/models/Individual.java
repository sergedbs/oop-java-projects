package papers_please.models;

import java.util.List;

public record Individual(int id, Boolean isHumanoid, String planet, Integer age, List<String> traits) {

    @Override
    public String toString() {
        return "Individual{" +
                "id=" + id +
                ", isHumanoid=" + isHumanoid +
                ", planet='" + planet + '\'' +
                ", age=" + age +
                ", traits=" + traits +
                '}';
    }
}
