package network.messages;

import model.library.PersonalObjective;

/**
 * Message which contains the personal objective of the player.
 */
public class ShowPersonalObjectiveMessage extends Message{
    private final PersonalObjective personalObjective;

    /**
     * Default constructor.
     * @param sender is the sender of the message.
     * @param personalObjective is the {@link PersonalObjective personal objective} of the player.
     */
    public ShowPersonalObjectiveMessage(String sender,PersonalObjective personalObjective) {
        super(sender, MessageType.SHOW_PERSONAL_OBJECTIVE);
        this.personalObjective = personalObjective;
    }

    /**
     * Getter method to return the personal objective of the player.
     * @return the {@link PersonalObjective personal objective}.
     */
    public PersonalObjective getPersonalObjective() {
        return personalObjective;
    }
}
