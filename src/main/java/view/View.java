package view;

import model.board.Board;
import model.library.Library;

public interface View {
    void askNickname();
    void askPlayersNumber();

    void showBoard(Board gameBoard);

    void showLibrary(Library playerLibrary);
}
