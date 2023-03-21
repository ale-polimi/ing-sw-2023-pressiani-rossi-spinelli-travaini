package model.objects;

import enumerations.ObjectColour;

import java.util.Random;

public class ObjectCard {
    private final ObjectColour objectColour;
    public ObjectCard(){
        Random random = new Random();
        objectColour = ObjectColour.values()[random.nextInt(ObjectColour.values().length)];
    }

    public ObjectCard(ObjectColour objectColour){
        this.objectColour = objectColour;
    }

    public ObjectColour getObjectColour() {
        return objectColour;
    }
}
