package network;

import model.objects.ObjectCard;

import java.util.ArrayList;

public class ShowObjectsInHandMessage extends Message{
    private final ArrayList<ObjectCard> objectsInHand;

    public ShowObjectsInHandMessage(String sender, ArrayList<ObjectCard> objectsInHand) {
        super(sender, MessageType.SHOW_OBJECTS_IN_HAND);
        this.objectsInHand = objectsInHand;
    }

    public ArrayList<ObjectCard> getObjectsInHand() {
        return objectsInHand;
    }
}
