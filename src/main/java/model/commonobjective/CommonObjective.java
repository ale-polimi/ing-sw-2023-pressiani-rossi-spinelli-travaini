package model.commonobjective;

import model.library.Library;

import java.io.Serializable;

public abstract class CommonObjective implements Serializable {
    ObjectiveNumeral objectiveNumeral;
    String description;

    /**
     * This method will be overridden by the more specific common objectives.
     * @param library is the library of a player.
     * @param x is the starting X coordinate. Usually it is 0.
     * @param y is the starting Y coordinate. Usually it is 0.
     * @return {@code true} if the library satisfies the rules of the objective, {@code false} otherwise.
     */
    public abstract boolean applyObjectiveRules(Library library, int x, int y);

    /**
     * Getter method for the numeral of the objective.
     * @return the ordinal position of the numeral in the {@link ObjectiveNumeral enumeration}.
     */
    public int getObjectiveNumeral() {
        return objectiveNumeral.ordinal();
    }

    /**
     * Setter method for the numeral of the objective.
     * @param objectiveNumeral is the numeral for this objective.
     */
    public void setObjectiveNumeral(ObjectiveNumeral objectiveNumeral) {
        this.objectiveNumeral = objectiveNumeral;
    }

    public abstract String getDescription();
}
