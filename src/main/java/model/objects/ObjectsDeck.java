package model.objects;
import java.util.ArrayList;
import model.objects.ObjectCard;
public class ObjectsDeck {
    private ArrayList<ObjectCard> objectsDeck;
    private int availableObjects ;

    public ObjectsDeck (){
        objectsDeck = new ArrayList<ObjectCard>();
        availableObjects = 132;
        int tempCards[] = {22,22,22,22,22,22};
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

    public ObjectCard removeFromDeck(){
        availableObjects -= 1;
        return objectsDeck.remove(0);
    }

    public int getAvailableObjects(){
        return objectsDeck.size();
    }
}
