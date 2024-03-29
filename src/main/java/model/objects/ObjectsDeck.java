package model.objects;
import java.util.ArrayList;

/**
 * This class represents the deck of cards as specified by the rules of the game. It contains 132 {@link ObjectCard cards}.
 */
public class ObjectsDeck {
    private ArrayList<ObjectCard> objectsDeck;
    private int availableObjects ;

    /**
     * Custom constructor of ObjectsDeck class
     */
    public ObjectsDeck (){
        objectsDeck = new ArrayList<>();
        availableObjects = 132;
        int[] tempCards = {7,7,8,7,7,8,7,7,8,7,7,8,7,7,8,7,7,8,0};
        for (int i = 0; i < 132; i++){
            ObjectCard tempCard;
            do {
                tempCard = new ObjectCard();
                if(tempCards[tempCard.getObjectColour().ordinal()]> 0)
                    tempCards[tempCard.getObjectColour().ordinal()]-=1;
                else
                    tempCard = null;
            }while(tempCard==null);
            objectsDeck.add(tempCard);
        }
    }

    /**
     * Returns an object in the deck
     * @return the first objectCard in the ArrayList
     */
    public ObjectCard removeFromDeck(){
        availableObjects -= 1;
        return objectsDeck.remove(0);
    }

    /**
     * Returns the number of cards in the deck
     * @return the size of the objectsDeck ArrayList
     */
    public int getAvailableObjects(){
        return objectsDeck.size();
    }
}
