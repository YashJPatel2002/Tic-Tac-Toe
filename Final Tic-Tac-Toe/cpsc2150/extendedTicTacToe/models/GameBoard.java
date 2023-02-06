package cpsc2150.extendedTicTacToe.models;


import java.util.Scanner;

/**
 * This class contains the structure of the GameBoard.
 *
 * @author Yash Patel
 * @version 1.0
 *
 * @invariant 3 <= row < 20 AND 3 <= col < 20 AND row = #row AND col = #col AND NUM_TO_WIN = #NUM_TO_WIN
 * @correspondences self = board[3...MAX_ROW][3...MAX_COL]
 */
public class GameBoard extends AbsGameBoard implements IGameBoard {
    private char playerChar;
    private final char board[][];
    private int winScore;
    private int row;
    private int col;
    public static final int MAX_ROW = 100;
    public static final int MAX_COL = 100;
    public static final int numToWin = 25;

    public static final int MIN_ROW = 3;

    public static final int MIN_COL = 3;

    public static final int MIN_WIN = 3;

    /**
     * This constructor takes in a char for the player character and a 2D char array for the game board.
     *
     * @pre NONE
     * @post board[0...MAX_ROW][0...MAX_COl] = " " AND MAX_ROW = #ROW AND MAX_COL = #COL AND numToWin = #numToWin AND
     * [board is size MAX_ROW][MAX_COL]]
     */
    public GameBoard(int r, int c, int w) {
        row = r;
        col = c;
        winScore = w;
        board = new char[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
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
        return row;
    }


    public int getNumColumns(){
        return col;
    }


    public int getNumToWin(){
        return winScore;
    }
}


