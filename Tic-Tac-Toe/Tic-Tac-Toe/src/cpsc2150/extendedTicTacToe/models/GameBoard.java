package cpsc2150.extendedTicTacToe.models;


/**
 * This class contains the structure of the GameBoard.
 *
 * @author Yash Patel
 * @version 1.0
 *
 * @invariant 0 <= row < 5 AND 0 <= col < 8 AND row = #row AND col = #col
 */
public class GameBoard extends AbsGameBoard implements IGameBoard {
    private char playerChar;
    private final char board[][];
    private int winScore;
    private int row;
    private int col;
    public static final int MAX_ROW = 5;
    public static final int MAX_COL = 8;
    public static final int numToWin = 5;
    /**
     * This constructor takes in a char for the player character and a 2D char array for the game board.
     *
     * @pre NONE
     * @post board[0...MAX_ROW][0...MAX_COl] = " " AND MAX_ROW = #ROW AND MAX_COL = #COL AND numToWin = #numToWin AND
     * [board is size MAX_ROW][MAX_COL]]
     */
    public GameBoard() {
        this.playerChar = 'X';
        this.board = new char[MAX_ROW][MAX_COL];
        this.winScore = 0;
        this.row = 0;
        this.col = 0;
        for (int i = 0; i < MAX_ROW; i++) {
            for (int j = 0; j < MAX_COL; j++) {
                board[i][j] = ' ';
            }
        }
    }

    /**
     * This method places a marker on the board.
     *
     * @param pos The position on the board.
     * @param player The player's marker.
     *
     * @pre checkSpace(pos) = true
     * @post board[pos.getRow()][pos.getCol()] = player
     */
    public void placeMarker(BoardPosition pos, char player){
        //places the player's marker at the specified position
        board[pos.getRow()][pos.getColumn()] = player;
    }

    /**
     * This method returns what is in the board at a specific position.
     *
     * @param pos The position on the board.
     *
     * @return returns what is in the board at a specific position
     *
     * @pre 0 <= pos.getRow() < MAX_ROW AND 0 <= pos.getCol() < MAX_COL
     * @post getBoardPosition = board[pos.getRow()][pos.getCol()]
     */
    public char whatsAtPos(BoardPosition pos) {
        return board[pos.getRow()][pos.getColumn()];
    }


    public int getNumRows(){
        return MAX_ROW;
    }


    public int getNumColumns(){
        return MAX_COL;
    }


    public int getNumToWin(){
        return numToWin;
    }
}


