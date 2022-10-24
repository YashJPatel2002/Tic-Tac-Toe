package cpsc2150.extendedTicTacToe.models;


/**
 * This interface contains the methods that will be implemented in the GameBoard class.
 *
 * @author Yash Patel
 * @version 1.0
 *
 * @invariant 0 <= row < 5 AND 0 <= col < 8 AND row = #row AND col = #col
 */
public interface IGameBoard {
    public static final int MAX_ROW = 5;
    public static final int MAX_COL = 8;
    public static final int numToWin = 5;
    public static final int MIN_ROW = 0;
    public static final int MIN_COL = 0;


    /**
     * This method returns the number of rows on the board.
     *
     * @return returns MAX_ROW
     *
     * @pre NONE
     * @post getNumRows = MAX_ROW
     */
    public int getNumRows();

    /**
     * This method returns the number of columns on the board.
     *
     * @return returns MAX_COL
     *
     * @pre NONE
     * @post getNumColumns = MAX_COL
     */
    public int getNumColumns();

    /**
     * This method returns the number of tokens in a row needed to win.
     *
     * @return returns numToWin
     *
     * @pre NONE
     * @post getNumToWin = numToWin
     */
    public int getNumToWin();

    /**
     * This method checks to see if space on the board is available.
     *
     * @param pos The position on the board.
     *
     * @return returns true if space is available, false otherwise.
     *
     * @pre 0 <= pos.getRow() < MAX_ROW AND 0 <= pos.getColumn() < MAX_COL
     * @post checkSpace = true AND FALSE otherwise
     */
    default public boolean checkSpace(BoardPosition pos){
        //returns true if the position specified in pos is available;
        //false otherwise. If a space is not in bounds, then it is not available
        if (whatsAtPos(pos) == ' ')
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * This method places a marker on the board.
     *
     * @param pos The position on the board.
     * @param player The player's marker.
     *
     * @pre checkSpace(pos) = true
     * @post board[pos.getRow()][pos.getColumn()] = player
     */
    public void placeMarker(BoardPosition pos, char player);

    /**
     * This method checks to see if the game is won.
     *
     * @param lastPos The last position on the board.
     *
     * @return returns true if the game is won
     *
     * @pre checkSpace(lastPos) = false AND lastPos.getRow() < MAX_ROW AND lastPos.getColumn() < MAX_COL
     * @post checkForWinner = true if checkForVerticalWin OR checkForHorizontalWin
     * OR checkForDiagonalWin = true AND false otherwise
     */
    default public boolean checkForWinner(BoardPosition lastPos){
        return checkVerticalWin(lastPos,whatsAtPos(lastPos)) || checkHorizontalWin(lastPos,whatsAtPos(lastPos)) || checkDiagonalWin(lastPos,whatsAtPos(lastPos));
    }

    /**
     * This method checks to see if the game is won.
     *
     * @param lastPos The last position on the board.
     *
     * @return returns true if the game is won
     *
     * @pre 0 <= lastPos.getRow() < MAX_ROW AND 0 <= lastPos.getColumn() < MAX_COL
     * @post checkVerticalWin = true if [numToWin of same char marker in a vertical row] AND checkVerticalWin
     * = false if [numTOWIN of same char marker not in a vertical row]
     */
    default public boolean checkVerticalWin(BoardPosition lastPos, char player){

        int count = 0;
        int row = lastPos.getRow();
        int col = lastPos.getColumn();
        //check up
        for (int i = row; i >= 0; i--)
        {
            if (whatsAtPos(new BoardPosition(i,col)) == player)
            {
                count++;
            }
            else
            {
                break;
            }
        }
        //check down
        for (int i = row + 1; i < MAX_ROW; i++)
        {
            if (whatsAtPos(new BoardPosition(i,col)) == player)
            {
                count++;
            }
            else
            {
                break;
            }
        }
        if (count >= numToWin)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * This method checks to see if the game is won.
     *
     * @param lastPos The last position on the board.
     *
     * @return returns true if the game is won
     *
     * @pre 0 <= lastPos.getRow() < MAX_ROW AND 0 <= lastPos.getColumn() < MAX_COL
     * @post checkHorizontalWin = true if [numToWin of same char marker in a horizontal row] AND checkHorizontalWin
     * = false if [numTOWIN of same char marker not in a horizontal row]
     */
    default public boolean checkHorizontalWin(BoardPosition lastPos, char player){
        int count = 0;
        int row = lastPos.getRow();
        int col = lastPos.getColumn();
        //check left
        for (int i = col; i >= 0; i--)
        {
            if (whatsAtPos(new BoardPosition(row,i)) == player)
            {
                count++;
            }
            else
            {
                break;
            }
        }
        //check right
        for (int i = col + 1; i < MAX_COL; i++)
        {
            if (whatsAtPos(new BoardPosition(row,i)) == player)
            {
                count++;
            }
            else
            {
                break;
            }
        }
        return count >= numToWin;
    }

    /**
     * This method checks to see if the game is won.
     *
     * @param lastPos The last position on the board.
     *
     * @return returns true if the game is won
     *
     * @pre 0 <= lastPos.getRow() < MAX_ROW AND 0 <= lastPos.getColumn() < MAX_COL
     * @post checkDiagonalWin = true if [numToWin of same char marker in a diagonal row] AND checkDiagonalWin
     * = false if [numTOWIN of same char marker not in a diagonal row]
     */
    default public boolean checkDiagonalWin(BoardPosition lastPos, char player){
        int count = 0;
        int row = lastPos.getRow();
        int col = lastPos.getColumn();
        //check up left
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--)
        {
            if (whatsAtPos(new BoardPosition(i,j)) == player)
            {
                count++;
            }
            else
            {
                break;
            }
        }
        //check down right
        for (int i = row + 1, j = col + 1; i < MAX_ROW && j < MAX_COL; i++, j++)
        {
            if (whatsAtPos(new BoardPosition(i,j)) == player)
            {
                count++;
            }
            else
            {
                break;
            }
        }
        if (count >= numToWin)
        {
            return true;
        }
        count = 0;
        //check up right
        for (int i = row, j = col; i >= 0 && j < MAX_COL; i--, j++)
        {
            if (whatsAtPos(new BoardPosition(i,j)) == player)
            {
                count++;
            }
            else
            {
                break;
            }
        }
        //check down left
        for (int i = row + 1, j = col - 1; i < MAX_ROW && j >= 0; i++, j--)
        {
            if (whatsAtPos(new BoardPosition(i,j)) == player)
            {
                count++;
            }
            else
            {
                break;
            }
        }
        return count >= numToWin;
    }

    /**
     * This method checks to see if the game is tied.
     *
     *
     * @return returns true if the game is tied
     *
     * @pre 0 <= lastPos.getRow() < MAX_ROW AND 0 <= lastPos.getColumn() < MAX_COL
     * @post checkForDraw = true if [board is full AND checkForWinner = false] AND
     * checkForDraw = false if [board is not full OR checkForWinner = true]
     */
    default public boolean checkForDraw(){
        //returns true if the board is full and there is no winner
        //false otherwise
        BoardPosition lastPos = new BoardPosition(0,0);
        if (checkForWinner(lastPos) == false)
        {
            for (int i = 0; i < MAX_ROW; i++)
            {
                for (int j = 0; j < MAX_COL; j++)
                {
                    if (whatsAtPos(new BoardPosition(i,j)) == ' ')
                    {
                        return false;
                    }
                }
            }
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * This method returns what is in the board at a specific position.
     *
     * @param pos The position on the board.
     *
     * @return returns what is in the board at a specific position
     *
     * @pre 0 <= pos.getRow() < MAX_ROW AND 0 <= pos.getColumn() < MAX_COL
     * @post getBoardPosition = board[pos.getRow()][pos.getColumn()]
     */
    public char whatsAtPos(BoardPosition pos);

    /**
     * This method returns the player shows the players position.
     *
     * @return returns the player shows the players position
     *
     * @param pos The position on the board.
     * @param playerChar The player's marker.
     *
     * @pre 0 <= pos.getRow() < MAX_ROW AND 0 <= pos.getColumn() < MAX_COL
     * @post playerAtPos = board[pos.getRow()][pos.getColumn()] == playerChar
     */
    default public boolean isPlayerAtPos(BoardPosition pos, char playerChar){
        return whatsAtPos(pos) == playerChar;
    }

}


