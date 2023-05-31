package network.messages;

import model.commonobjective.CommonObjective;

/**
 * Message which contains the current common objectives for the game.
 */
public class ShowCommonObjectiveMessage extends Message{
    private final CommonObjective commonObjective1;
    private final CommonObjective commonObjective2;

    /**
     * Default constructor.
     * @param sender is the sender of the message.
     * @param commonObjective1 is the first {@link CommonObjective common objective} in use in the {@link model.Game game}.
     * @param commonObjective2 is the second {@link CommonObjective common objective} in use in the {@link model.Game game}.
     */
    public ShowCommonObjectiveMessage(String sender, CommonObjective commonObjective1, CommonObjective commonObjective2) {
        super(sender, MessageType.SHOW_COMMON_OBJECTIVE);
        this.commonObjective1 = commonObjective1;
        this.commonObjective2 = commonObjective2;
    }

    /**
     * Getter method to return the first common objective.
     * @return the first {@link CommonObjective common objective} currently in use in the {@link model.Game game}.
     */
    public CommonObjective getCommonObjective1() {
        return commonObjective1;
    }
    /**
     * Getter method to return the second common objective.
     * @return the second {@link CommonObjective common objective} currently in use in the {@link model.Game game}.
     */
    public CommonObjective getCommonObjective2() {
        return commonObjective2;
    }
}
