package cpsc2150.extendedTicTacToe.models;

/**
 * This class is used to store the position of the game board.
 *
 * @author Yash Patel
 * @version 1.0
 *
 * @invariant 0 <= row < 5 AND 0 <= col < 8 AND row = #row AND col = #col
 */
public class BoardPosition {
    private int row;
    private int col;

    /**
     * This constructor takes in an int for the Row position and an int for the Column position.
     *
     * @param row The Row position on the board.
     * @param col The Column position on the board.
     *
     * @pre NONE
     * @post this.row = row AND this.col = col
     */
    public BoardPosition(int row, int col){
        this.row = row;
        this.col = col;
    }

    /**
     * This method returns the row of this BoardPosition object.
     *
     * @return returns row
     *
     * @pre NONE
     * @post getRow = row AND row = #row
     */
    public int getRow(){
        return row;
    }

    /**
     * This method returns the column of this BoardPosition object.
     *
     * @return returns col
     *
     * @pre NONE
     * @post getColumn() = col AND col = #col
     */
    public int getColumn(){
        return col;
    }

    /**
     * This method returns a true if two BoardPositions are equal,they are equal if they
     * have the same row and column.
     *
     * @param boardComparison another BoardPosition object compared to current position
     *
     *
     * @pre NONE
     * @post equals() = TRUE if row = BoardPosition.getRow() AND col = BoardPosition.getColumn()()
     */
    @Override
    public boolean equals(Object boardComparison) {
        if(boardComparison instanceof BoardPosition){
            return (row == (((BoardPosition) boardComparison).getRow()) && col == (((BoardPosition) boardComparison).getColumn()));
        }
        return false;
    }

    /**
     * This method will create a string in the following format "<row>,<column>."
     *
     *
     * @return returns the string in the following format "<row>,<column>"
     *
     * @pre NONE
     * @post toString = [<row>,<col>]
     *
     */
    @Override
    public String toString() {
        return row + "," + col;
    }

}

