package cpsc2150.extendedTicTacToe.models;

/**
 * This class contains the structure of the GameBoard.
 *
 * @author Yash Patel
 * @version 1.0
 *
 * @invariant 0 <= row < 5 AND 0 <= col < 8 AND row = #row AND col = #col
 */

public abstract class AbsGameBoard implements IGameBoard {
    /**
     * This method creates a string representation of the game board.
     *
     * @return returns a string representation of the game board.
     * @pre NONE
     * @post toString = board
     */
    @Override
    public String toString() {
        String s = "  ";
        for (int i = 0; i < getNumColumns(); i++) {
            s += (i + "|");
        }
        s += "\n";
        for (int i = 0; i < getNumRows(); i++) {
            s += i + "|";
            for (int j = 0; j < getNumColumns(); j++) {
                s += whatsAtPos(new BoardPosition(i,j))+"|";
            }
            s += "\n";
        }
        return s;
    }
}
