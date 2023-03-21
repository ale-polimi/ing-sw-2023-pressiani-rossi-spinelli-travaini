package controller;

import model.library.Library;
import model.library.PersonalObjective;
import model.objects.ObjectCard;
import model.player.Player;

public class PlayerController {
    private Player player;

    public PlayerController(){
        this.player = new Player(username);
    }

    /**
     *
     * @param objectCard
     * @param column
     */
    public boolean addObjectToLibrary(ObjectCard objectCard, int column){
        for(int i = 5; i >= 0; i--){
            if(player.getLibrary().getLibraryGrid()[i][column].getObjectContained().equals(null)){
                player.getLibrary().getLibraryGrid()[i][column].setObjectContained(objectCard);
                return true;
            }
        }
        return false;
    }
}
