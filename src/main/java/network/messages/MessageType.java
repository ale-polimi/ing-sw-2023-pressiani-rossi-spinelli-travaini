package network.messages;

import java.io.Serializable;

public enum MessageType implements Serializable {
    MAX_PLAYERS_FOR_GAME,
    USER_INFO,
    PICK_OBJECT,
    PUT_OBJECT,
    BOARD_ERROR,
    LIBRARY_ERROR,
    GENERIC_ERROR,
    SHOW_TURN,
    END_TURN,
    SHOW_LIBRARY,
    SHOW_OTHERS_LIBRARY,
    SHOW_BOARD,
    SHOW_PERSONAL_OBJECTIVE,
    SHOW_COMMON_OBJECTIVE,
    SHOW_OBJECTS_IN_HAND,
    NEXT_TURN,
    GENERIC_MODEL_CHANGE,
    ADDED_PLAYER,
    PING,
    GAME_CLOSED,
    SHOW_LOBBY,
    END_GAME,
    ASK_MAX_PLAYER,
    ASK_BOARD_MOVE,
    ASK_LIBRARY_MOVE,
    ASK_NICKNAME
}