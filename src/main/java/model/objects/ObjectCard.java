package model.objects;

import enumerations.ObjectColour;

public class ObjectCard {
    private ObjectColour objectColour;
    public ObjectCard(){

        objectColour = getObjectColour();
    }

    public ObjectColour getObjectColour() {
        return objectColour;
    }
}
