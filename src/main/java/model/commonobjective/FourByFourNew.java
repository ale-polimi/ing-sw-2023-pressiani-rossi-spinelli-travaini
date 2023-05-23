package model.commonobjective;

import enumerations.ObjectColour;
import model.library.Library;
import view.cli.Colours;

import java.util.ArrayList;
import java.util.List;

public class FourByFourNew extends CommonObjective{
    private Library library ;
    private static final int MIN_GROUP_SIZE = 4;
    private static final int MIN_GROUP_COUNT = 5;
    int groupCount;
    int count = 1;

    private final String description = " " + Colours.UNDERLINED + " " + Colours.RESET + "\n" +
            "|" + Colours.UNDERLINED + "■" + Colours.RESET + "|  Four groups each containing at least\n" +
            "|" + Colours.UNDERLINED + "■" + Colours.RESET + "|  4 tiles of the same type (not necessarily\n" +
            "|" + Colours.UNDERLINED + "■" + Colours.RESET + "|  in the depicted shape).\n" +
            "|" + Colours.UNDERLINED + "■" + Colours.RESET + "|  The tiles of one group can be different\n" +
            " x4  from those of another group.";
    @Override
    public boolean applyObjectiveRules(Library library, int x, int y) {
        this.library= library;
       return hasValidGroups();
    }

    @Override
    public String getDescription() {
        return description;
    }


    public boolean hasValidGroups() {
             groupCount = 0;
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 5; j++) {
                    ObjectColour colour = library.getLibrarySpace(i,j).getObject().getObjectColour();
                    if (colour != null) {

                        List<int[]> group = getAdjacentCells(i, j, colour);
                        if (group.size() >= MIN_GROUP_SIZE) {

                            if (isUniqueGroup(group)) {

                                 groupCount = countGroup(group);

                            }
                        }
                    }
                }
            }

            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 5; j++) {
                    library.getLibrarySpace(i,j).setGroup(0);
                    library.getLibrarySpace(i,j).setVisited(false);
                }

            }

            if (groupCount >= MIN_GROUP_COUNT) {
                return true;
            }
            return false;
        }

        private List<int[]> getAdjacentCells(int i, int j, ObjectColour colour) {
            List<int[]> group = new ArrayList<>();
            if (i >= 0 && i < 6 && j >= 0 && j < 5 && !library.getLibrarySpace(i, j).isVisited()) {
                ObjectColour currentColour = library.getLibrarySpace(i,j).getObject().getObjectColour();
                if (currentColour != null && currentColour.isEquals(colour)) {
                    library.getLibrarySpace(i,j).setVisited(true);
                    group.add(new int[] {i, j});
                    group.addAll(getAdjacentCells(i-1, j, colour));
                    group.addAll(getAdjacentCells(i+1, j, colour));
                    group.addAll(getAdjacentCells(i, j-1, colour));
                    group.addAll(getAdjacentCells(i, j+1, colour));
                }
            }
            return group;
        }


        private boolean isUniqueGroup(List<int[]> group) {
            int row = group.get(0)[0];
            int col = group.get(0)[1];
            return library.getLibrarySpace(row,col).getGroup() == 0;
        }


        private int countGroup(List<int[]> group) {

            for (int i = 0; i < group.size(); i++) {
                int[] cell = group.get(i);
                int row = cell[0];
                int col = cell[1];
                library.getLibrarySpace(row, col).setGroup(count);
            }
            return ++count; //
        }




}
