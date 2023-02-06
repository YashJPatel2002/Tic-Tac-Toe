package cpsc2150.extendedTicTacToe.models;

import java.util.*;

/**
 * This class contains the structure of the GameBoard.
 *
 * @author Yash Patel
 * @version 1.0
 *
 * @invariant 0 <= row < 5 AND 0 <= col < 8 AND row = #row AND col = #col AND NUM_TO_WIN = #NUM_TO_WIN
 * @correspondences self = board
 */

public class GameBoardMem extends AbsGameBoard implements IGameBoard {
    private char playerChar;
    private Map<Character, List<BoardPosition>> board;
    private int winScore;
    private int row;
    private int col;


 /**
     * This constructor takes in a char for the player character and a 2D char array for the game board.
     *
     * @pre NONE
     * @post board[0...MAX_ROW][0...MAX_COl] = " " AND MAX_ROW = #ROW AND MAX_COL = #COL AND numToWin = #numToWin AND
     * [board is size MAX_ROW][MAX_COL]]
     */
    public GameBoardMem(int r, int c, int w) {
        row = r;
        col = c;
        winScore = w;
        board = new HashMap<Character,List<BoardPosition>>();
    }

    /**
     * This method returns the number of rows on the board.
     *
     * @return returns MAX_ROW
     *
     * @pre rows >= 3 AND rows <= 20
     * @post getNumRows = MAX_ROW
     */
    public int getNumRows(){
        return row;
    }

    /**
     * This method returns the number of columns on the board.
     *
     * @return returns MAX_COL
     *
     * @pre cols >= 3 AND cols <= 20
     * @post getNumColumns = MAX_COL
     */
    public int getNumColumns() {
        return col;
    }

    /**
     * This method returns the number of tokens in a row needed to win.
     *
     * @return returns numToWin
     *
     * @pre winScore >= 3 AND winScore <= 20
     * @post getNumToWin = numToWin
     */
    public int getNumToWin() {
        return winScore;
    }

    /**
     * This method places a marker on the board.
     *
     * @param pos The position on the board.
     * @param player The player's marker.
     *
     * @pre checkSpace(pos) = true
     * @post
     */
    public void placeMarker(BoardPosition pos, char player){
        //places the player's marker at the specified position
        int row = pos.getRow();
        int col = pos.getColumn();
        if(!board.containsKey(player)){
            board.put(player, new ArrayList<>());
        }
        BoardPosition pos1 = new BoardPosition(row, col);
        board.get(player).add(pos1);
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
    public char whatsAtPos(BoardPosition pos){
        for(Map.Entry<Character,List<BoardPosition>> entry : board.entrySet()){
            if(entry.getValue().contains(pos)){
                return entry.getKey();
            }
        }
        return ' ';
    }

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
    @Override
    public boolean isPlayerAtPos(BoardPosition pos, char playerChar){
        if(board.containsKey(playerChar)){
            if(board.get(playerChar).contains(pos)){
                return true;
            }
        }
        else if(playerChar == ' ') {
            for (Map.Entry<Character, List<BoardPosition>> entry : board.entrySet()) {
                if (board.get(entry.getKey()).contains(pos)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
