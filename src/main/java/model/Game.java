package model;

import model.board.Board;
import model.objects.ObjectsDeck;
import model.player.Player;

import java.util.ArrayList;

public class Game {
    private Board board;
    private ArrayList<Player> players;
    private ObjectsDeck objectsDeck;
    private Turn turn;
}
