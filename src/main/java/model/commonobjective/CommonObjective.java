package model.commonobjective;

import model.library.Library;

public interface CommonObjective {
    public boolean applyObjectiveRules(Library library, int x, int y);
}
