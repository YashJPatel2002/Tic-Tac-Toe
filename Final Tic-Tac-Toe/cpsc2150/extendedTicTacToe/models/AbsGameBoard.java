package cpsc2150.extendedTicTacToe.models;

/**
 * This class contains the structure of the GameBoard.
 *
 * @author Yash Patel
 * @version 1.0
 *
 * @invariant 0 <= row < MAXROW AND 0 <= col < MAXCOL AND row = #row AND col = #col
 */

public abstract class AbsGameBoard implements IGameBoard {

    /**
     * This method creates a string representation of the game board.
     *
     * @return returns a string representation of the game board.
     * @pre NONE
     * @post toString = [string representation of the game board] AND self = #self
     */
    @Override
    public String toString() {
        String s = "   ";
        for (int i = 0; i < getNumColumns(); i++) {
            if(i < 10)
                s += " " + i + "|";
            else
                s += i + "|";

        }
        s += "\n";
        for (int i = 0; i < getNumRows(); i++) {
            if(i < 10)
                s += " " + i + "|";
            else
                s += i + "|";

            for (int j = 0; j < getNumColumns(); j++) {
                s += whatsAtPos(new BoardPosition(i,j))+" |";
            }
            s += "\n";
        }
        return s;
    }
}
