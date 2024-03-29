package model.objects;

import enumerations.ObjectColour;

import java.io.Serializable;
import java.util.Random;

/**
 * This class represents the single object card. Each card has a {@link ObjectColour type}.
 */
public class ObjectCard implements Serializable {
    private final ObjectColour objectColour;

    /**
     * Constructor of the ObjectCard class, using a random colour
     */
    public ObjectCard(){
        Random random = new Random();
        objectColour = ObjectColour.values()[random.nextInt(ObjectColour.values().length)];
    }

    /**
     * Constructor of the ObjectCard class, given a String
     * @param colour is a String corresponding to the colour of the card
     */
    public ObjectCard(String colour){ objectColour = ObjectColour.valueOf(colour);
    }


    /**
     * Constructor of the ObjectCard class, given an objectColour
     * @param objectColour is the colour of the card
     */
    public ObjectCard(ObjectColour objectColour){
        this.objectColour = objectColour;
    }

    /**
     *
     * @return the colour of the card
     */
    public ObjectColour getObjectColour() {
        return objectColour;
    }

}
