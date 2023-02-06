package cpsc2150.extendedTicTacToe.models;


/**
 * This interface contains the methods that will be implemented in the GameBoard class.
 *
 * @author Yash Patel
 * @version 1.0
 *
 */
public interface IGameBoard {
    public static final int MAX_ROW = 100;
    public static final int MAX_COL = 100;
    public static final int numToWin = 25;
    public static final int MIN_ROW = 3;
    public static final int MIN_COL = 3;

    public static final int MIN_WIN = 3;



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
        if(pos.getRow() >= getNumRows() || pos.getRow() < 0 || pos.getColumn() >= getNumColumns() || pos.getColumn() < 0 || whatsAtPos(pos) != ' '){
            return false;
        }
        else {
            return true;
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
     * @pre checkSpace(lastPos) = false AND lastPos.getRow() < getNumRow AND lastPos.getColumn() < getNumColumn
     * @post checkForWinner = true if checkForVerticalWin OR checkForHorizontalWin
     * OR checkForDiagonalWin = true AND false otherwise
     */
    default public boolean checkForWinner(BoardPosition lastPos){
        if(whatsAtPos(lastPos) == '\0'){
            return false;
        }
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
     * @post checkVerticalWin = true if [getNumRow of same char marker in a vertical row] AND checkVerticalWin
     * = false if [getNumColumn of same char marker not in a vertical row]
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
        for (int i = row + 1; i < getNumRows(); i++)
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
        if (count >= getNumToWin())
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
     * @pre 0 <= lastPos.getRow() < getNumRow AND 0 <= lastPos.getColumn() < getNumColumn
     * @post checkHorizontalWin = true if [getNumToWin of same char marker in a horizontal row] AND checkHorizontalWin
     * = false if [getNumToWin of same char marker not in a horizontal row]
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
        for (int i = col + 1; i < getNumColumns(); i++)
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
        return count >= getNumToWin();
    }

    /**
     * This method checks to see if the game is won.
     *
     * @param lastPos The last position on the board.
     *
     * @return returns true if the game is won
     *
     * @pre 0 <= lastPos.getRow() < getNumRows AND 0 <= lastPos.getColumn() < getNumColumns
     * @post checkDiagonalWin = true if [getNumToWin of same char marker in a diagonal row] AND checkDiagonalWin
     * = false if [getNumToWin of same char marker not in a diagonal row]
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
        for (int i = row + 1, j = col + 1; i < getNumRows() && j < getNumColumns(); i++, j++)
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
        if (count >= getNumToWin())
        {
            return true;
        }
        count = 0;
        //check up right
        for (int i = row, j = col; i >= 0 && j < getNumColumns(); i--, j++)
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
        for (int i = row + 1, j = col - 1; i < getNumRows() && j >= 0; i++, j--)
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
        return count >= getNumToWin();
    }

    /**
     * This method checks to see if the game is tied.
     *
     *
     * @return returns true if the game is tied
     *
     * @pre 0 <= lastPos.getRow() < getNumRow AND 0 <= lastPos.getColumn() < getNumColumn
     * @post checkForDraw = true if [board is full AND checkForWinner = false] AND
     * checkForDraw = false if [board is not full OR checkForWinner = true]
     */
    default public boolean checkForDraw(){
        //returns true if the board is full and there is no winner
        //false otherwise
        BoardPosition lastPos = new BoardPosition(0,0);
        if (checkForWinner(lastPos) == false)
        {
            for (int i = 0; i < getNumRows(); i++)
            {
                for (int j = 0; j < getNumColumns(); j++)
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
     * @pre 0 <= pos.getRow() < getNumRow AND 0 <= pos.getColumn() < getNumColumn
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
     * @pre 0 <= pos.getRow() < getNumRow AND 0 <= pos.getColumn() < getNumColumn
     * @post playerAtPos = board[pos.getRow()][pos.getColumn()] == playerChar
     */
    default public boolean isPlayerAtPos(BoardPosition pos, char playerChar){
        return whatsAtPos(pos) == playerChar;
    }

}


