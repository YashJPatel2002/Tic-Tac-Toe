package cpsc2150.extendedTicTacToe.models;


import cpsc2150.extendedTicTacToe.models.*;
import org.junit.Test;
import junit.framework.TestCase;
import static org.junit.Assert.*;


/**
 * This class is to test the GameBoard class.
 * @author Yash Patel
 * @version 1.0
 */



public class TestGameBoard {

    private IGameBoard sample(int row, int col, int win){
        return new GameBoard(row, col, win);
    }

    private String printBoard(char[][] board){
        String tempBoard = "";

        for (int i = 0; i <= board.length - 1; i++) {
            for (int j = 0; j <= board[0].length - 1; j++) {
                if (i == 0 && j == 0) {
                    tempBoard = "    ";
                    for (int c = 0; c <= board[0].length - 1; c++) {
                        if (c == 0) {
                            tempBoard += String.valueOf(c) + "| ";
                        }
                        if (c < 9 && c > 0 && c != board.length - 1) {
                            tempBoard += String.valueOf(c) + "| ";
                        }
                        if (c >= 9 || c == board.length - 1) {
                            tempBoard += String.valueOf(c) + "|";
                        }
                    }
                    tempBoard += "\n";
                }
                if (j == 0) {
                    if (i < 10) {
                        tempBoard += " ";
                    }
                    tempBoard += String.valueOf(i) + "|";
                }
                char tempChar = board[i][j];
                tempBoard += tempChar + " |";
                if (j == board[0].length - 1) {
                    tempBoard += "\n";
                }
            }
        }
        return tempBoard;
    }

    /**
    * This method tests the minimum input of the gameBoard constructor.
    */
    @Test
    public void testGameBoard_Min_Input() {
        char[][] expected = new char[3][3];
        for (int i = 0; i < expected.length; i++) {
            for (int j = 0; j < expected[0].length; j++) {
                expected[i][j] = ' ';
            }
        }
        IGameBoard gb = sample(3, 3, 3);
        String sampleString = printBoard(expected);
        assertEquals(sampleString, gb.toString());
    }

    /**
    * This method tests the bounds of valid input of the gameBoard constructor.
    */
   @Test
   public void testGameBoard_Valid_Input(){
       char[][] expected = new char[5][5];
       for (int i = 0; i < expected.length; i++) {
           for (int j = 0; j < expected[0].length; j++) {
               expected[i][j] = ' ';
           }
       }
       IGameBoard gb = sample(5, 5, 5);
       String sampleString = printBoard(expected);
       assertEquals(sampleString, gb.toString());
   }

    /**
    * This method tests the maximum input of the gameBoard constructor.
    */
    @Test
    public void testGameBoard_Max_Input(){
        char[][] expected = new char[100][100];
        for (int i = 0; i < expected.length; i++) {
            for (int j = 0; j < expected[0].length; j++) {
                expected[i][j] = ' ';
            }
        }
        IGameBoard board = sample(100, 100, 25);
        String sampleString = printBoard(expected);
        assertEquals(sampleString, board.toString());
    }

   /**
   * This method tests for checkSpace for an empty board.
   */
   @Test
   public void testCheckSpace_Empty(){
       BoardPosition pos = new BoardPosition(0, 0);
       boolean expected = true;
       IGameBoard board = sample(3, 3, 3);
       boolean actual = board.checkSpace(new BoardPosition(1,1));
       assertEquals(expected, actual);
   }

    /**
    * This method tests for checkSpace for a full board.
    */
    @Test
    public void testCheckSpace_Full() {
        BoardPosition pos = new BoardPosition(0, 0);
        boolean expected = false;
        IGameBoard gb = sample(3, 3, 3);

        int count = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                BoardPosition temp = new BoardPosition(i, j);
                char space = ' ';
                if (count % 2 == 0) {
                    space = 'X';
                }
                else {
                    space = 'O';
                }
                if (i == 2 && j == 2) {
                    space = 'O';
                }
                gb.placeMarker(temp, space);
                count++;
            }
        }
        boolean actual = gb.checkSpace(new BoardPosition(1,1));
        assertEquals(expected, actual);
    }

    /**
    *  This method tests for checkSpace that is out of bounds.
    */
    @Test
    public void testCheckSpace_bounds() {
      BoardPosition pos = new BoardPosition(5, 5);
      boolean expected = false;
      IGameBoard gb = sample(3, 3, 3);

      boolean actual = gb.checkSpace(new BoardPosition(5, 5));
      assertEquals(expected, actual);
    }
    /**
     * This method tests for checkHorizontalWin with the last marker in the middle
     */
    @Test
    public void testCheckHorizontalWin_Middle() {
        IGameBoard gb = sample(5,5,4);
        gb.placeMarker(new BoardPosition(2,0), 'X');
        gb.placeMarker(new BoardPosition(3,0), 'O');
        gb.placeMarker(new BoardPosition(2,1), 'X');
        gb.placeMarker(new BoardPosition(3,1), 'O');
        gb.placeMarker(new BoardPosition(2,3), 'X');
        gb.placeMarker(new BoardPosition(3,2), 'O');
        gb.placeMarker(new BoardPosition(3,3), 'X');
        gb.placeMarker(new BoardPosition(3,4), 'O');
        gb.placeMarker(new BoardPosition(2,2), 'X');
        assertTrue(gb.checkHorizontalWin(new BoardPosition(2,2), 'X'));
    }


    /**
    *  This method tests for checkHorizontalWin with the last marker at the end
    */
    @Test
    public void testCheckHorizontalWin_End(){
      IGameBoard gb = sample(5,5,4);
      gb.placeMarker(new BoardPosition(2,0), 'X');
      gb.placeMarker(new BoardPosition(3,0), 'O');
      gb.placeMarker(new BoardPosition(2,1), 'X');
      gb.placeMarker(new BoardPosition(3,1), 'O');
      gb.placeMarker(new BoardPosition(2,2), 'X');
      gb.placeMarker(new BoardPosition(3,2), 'O');
      gb.placeMarker(new BoardPosition(3,3), 'X');
      gb.placeMarker(new BoardPosition(3,4), 'O');
      gb.placeMarker(new BoardPosition(2,3), 'X');
      assertTrue(gb.checkHorizontalWin(new BoardPosition(2,3), 'X'));
    }

    /**
    * This test case tests for checkHorizontalWin with the last marker not resulting in a win
    */
    @Test
    public void testCheckHorizontalWin_No_Win() {
        IGameBoard gb = sample(5,5,4);
        gb.placeMarker(new BoardPosition(2,0), 'X');
        gb.placeMarker(new BoardPosition(3,0), 'O');
        gb.placeMarker(new BoardPosition(2,1), 'X');
        gb.placeMarker(new BoardPosition(3,1), 'O');
        gb.placeMarker(new BoardPosition(2,2), 'X');
        gb.placeMarker(new BoardPosition(3,2), 'O');
        gb.placeMarker(new BoardPosition(3,3), 'X');
        assertFalse(gb.checkHorizontalWin(new BoardPosition(3,3), 'X'));
    }

    /**
     *  This method tests for checkHorizontalWin with the last marker at the start
     */
    @Test
    public void testCheckHorizontalWin_Start(){
        IGameBoard gb = sample(5,5,4);
        gb.placeMarker(new BoardPosition(2,3), 'X');
        gb.placeMarker(new BoardPosition(3,0), 'O');
        gb.placeMarker(new BoardPosition(2,1), 'X');
        gb.placeMarker(new BoardPosition(3,1), 'O');
        gb.placeMarker(new BoardPosition(2,2), 'X');
        gb.placeMarker(new BoardPosition(3,2), 'O');
        gb.placeMarker(new BoardPosition(3,3), 'X');
        gb.placeMarker(new BoardPosition(3,4), 'O');
        gb.placeMarker(new BoardPosition(2,0), 'X');
        assertTrue(gb.checkHorizontalWin(new BoardPosition(2,0), 'X'));
    }

    /**
     * This test case tests for checkVerticalWin with the last marker at the end
     */
    @Test
       public void testCheckVerticalWin_End(){
        IGameBoard gb = sample(5,5,4);
        gb.placeMarker(new BoardPosition(0,0), 'X');
        gb.placeMarker(new BoardPosition(0,1), 'O');
        gb.placeMarker(new BoardPosition(1,0), 'X');
        gb.placeMarker(new BoardPosition(1,1), 'O');
        gb.placeMarker(new BoardPosition(2,0), 'X');
        gb.placeMarker(new BoardPosition(2,1), 'O');
        gb.placeMarker(new BoardPosition(3,0), 'X');
        assertTrue(gb.checkVerticalWin(new BoardPosition(3,0), 'X'));
    }

    /**
     * This test case tests for checkVerticalWin with the last marker at the start
     */
    @Test
    public void testCheckVerticalWin_Start(){
        IGameBoard gb = sample(5,5,4);
        gb.placeMarker(new BoardPosition(3,0), 'X');
        gb.placeMarker(new BoardPosition(0,1), 'O');
        gb.placeMarker(new BoardPosition(1,0), 'X');
        gb.placeMarker(new BoardPosition(1,1), 'O');
        gb.placeMarker(new BoardPosition(2,0), 'X');
        gb.placeMarker(new BoardPosition(2,1), 'O');
        gb.placeMarker(new BoardPosition(0,0), 'X');
        assertTrue(gb.checkVerticalWin(new BoardPosition(0,0), 'X'));
    }


    /**
     * This test case tests for checkVerticalWin with the last marker at the middle
     */
    @Test
       public void testCheckVerticalWin_Middle(){
        IGameBoard gb = sample(5,5,4);
        gb.placeMarker(new BoardPosition(0,0), 'X');
        gb.placeMarker(new BoardPosition(0,1), 'O');
        gb.placeMarker(new BoardPosition(1,0), 'X');
        gb.placeMarker(new BoardPosition(1,1), 'O');
        gb.placeMarker(new BoardPosition(3,0), 'X');
        gb.placeMarker(new BoardPosition(2,1), 'O');
        gb.placeMarker(new BoardPosition(2,0), 'X');
        assertTrue(gb.checkVerticalWin(new BoardPosition(2,0), 'X'));
    }

    /**
     * This test case tests for checkVerticalWin with the last marker not resulting in a win
     */
    @Test
       public void testCheckVerticalWin_No_Win(){
        IGameBoard gb = sample(5,5,4);
        gb.placeMarker(new BoardPosition(0,0), 'X');
        gb.placeMarker(new BoardPosition(0,1), 'O');
        gb.placeMarker(new BoardPosition(1,0), 'X');
        gb.placeMarker(new BoardPosition(1,1), 'O');
        gb.placeMarker(new BoardPosition(3,0), 'X');
        gb.placeMarker(new BoardPosition(2,1), 'O');
        gb.placeMarker(new BoardPosition(4,0), 'X');
        assertFalse(gb.checkVerticalWin(new BoardPosition(4,0), 'X'));
    }

    /**
    * This test case tests for checkDiagonalWin with the last marker at the start
    */
    @Test
    public void testCheckDiagonalWin_Start(){
        IGameBoard gb = sample(5,5,4);
        gb.placeMarker(new BoardPosition(4,4), 'X');
        gb.placeMarker(new BoardPosition(3,0), 'O');
        gb.placeMarker(new BoardPosition(2,2), 'X');
        gb.placeMarker(new BoardPosition(3,1), 'O');
        gb.placeMarker(new BoardPosition(3,3), 'X');
        gb.placeMarker(new BoardPosition(3,2), 'O');
        gb.placeMarker(new BoardPosition(1,1), 'X');
        assertTrue(gb.checkDiagonalWin(new BoardPosition(1,1), 'X'));
    }

    /**
     * This test case tests for checkDiagonalWin with the last marker at the end
     */
    @Test
    public void testCheckDiagonalWin_End(){
        IGameBoard gb = sample(5,5,4);
        gb.placeMarker(new BoardPosition(1,1), 'X');
        gb.placeMarker(new BoardPosition(3,0), 'O');
        gb.placeMarker(new BoardPosition(2,2), 'X');
        gb.placeMarker(new BoardPosition(3,1), 'O');
        gb.placeMarker(new BoardPosition(3,3), 'X');
        gb.placeMarker(new BoardPosition(3,2), 'O');
        gb.placeMarker(new BoardPosition(4,4), 'X');
        assertTrue(gb.checkDiagonalWin(new BoardPosition(4,4), 'X'));
    }

    /**
     * This test case tests for checkDiagonalWin with the last marker at the middle
     */
    @Test
    public void testCheckDiagonalWin_Middle(){
        IGameBoard gb = sample(5,5,4);
        gb.placeMarker(new BoardPosition(1,1), 'X');
        gb.placeMarker(new BoardPosition(3,0), 'O');
        gb.placeMarker(new BoardPosition(4,4), 'X');
        gb.placeMarker(new BoardPosition(3,1), 'O');
        gb.placeMarker(new BoardPosition(3,3), 'X');
        gb.placeMarker(new BoardPosition(3,2), 'O');
        gb.placeMarker(new BoardPosition(2,2), 'X');
        assertTrue(gb.checkDiagonalWin(new BoardPosition(2,2), 'X'));
    }

    /**
     * This test case tests for checkDiagonalWin with the last marker starting at the opposite side
     */
    @Test
    public void testCheckDiagonalWin_Start_Opposite(){
        IGameBoard gb = sample(5,5,4);
        gb.placeMarker(new BoardPosition(4,0), 'X');
        gb.placeMarker(new BoardPosition(3,3), 'O');
        gb.placeMarker(new BoardPosition(3,1), 'X');
        gb.placeMarker(new BoardPosition(3,4), 'O');
        gb.placeMarker(new BoardPosition(2,2), 'X');
        gb.placeMarker(new BoardPosition(4,3), 'O');
        gb.placeMarker(new BoardPosition(1,3), 'X');
        assertTrue(gb.checkDiagonalWin(new BoardPosition(1,3), 'X'));
    }

    /**
     * This test case tests for checkDiagonalWin with the last marker ending at the opposite side
     */
    @Test
    public void testCheckDiagonalWin_End_Opposite(){
        IGameBoard gb = sample(5,5,4);
        gb.placeMarker(new BoardPosition(1,3), 'X');
        gb.placeMarker(new BoardPosition(3,3), 'O');
        gb.placeMarker(new BoardPosition(3,1), 'X');
        gb.placeMarker(new BoardPosition(3,4), 'O');
        gb.placeMarker(new BoardPosition(2,2), 'X');
        gb.placeMarker(new BoardPosition(4,3), 'O');
        gb.placeMarker(new BoardPosition(4,0), 'X');
        assertTrue(gb.checkDiagonalWin(new BoardPosition(4,0), 'X'));
    }

    /**
     * This test case tests for checkDiagonalWin with the last middle marker at the opposite side
     */
    @Test
    public void testCheckDiagonalWin_Middle_Opposite(){
        IGameBoard gb = sample(5,5,4);
        gb.placeMarker(new BoardPosition(1,3), 'X');
        gb.placeMarker(new BoardPosition(3,3), 'O');
        gb.placeMarker(new BoardPosition(3,1), 'X');
        gb.placeMarker(new BoardPosition(3,4), 'O');
        gb.placeMarker(new BoardPosition(4,0), 'X');
        gb.placeMarker(new BoardPosition(4,3), 'O');
        gb.placeMarker(new BoardPosition(2,2), 'X');
        assertTrue(gb.checkDiagonalWin(new BoardPosition(2,2), 'X'));
    }

    /**
     * This test case tests for checkDiagonalWin with the last marker resulting in no win
     */
    @Test
    public void testCheckDiagonalWin_No_Win(){
        IGameBoard gb = sample(5,5,4);
        gb.placeMarker(new BoardPosition(1,3), 'X');
        gb.placeMarker(new BoardPosition(3,3), 'O');
        gb.placeMarker(new BoardPosition(3,1), 'X');
        gb.placeMarker(new BoardPosition(3,4), 'O');
        gb.placeMarker(new BoardPosition(4,0), 'X');
        gb.placeMarker(new BoardPosition(4,3), 'O');
        gb.placeMarker(new BoardPosition(0,4), 'X');
        assertFalse(gb.checkDiagonalWin(new BoardPosition(0,4), 'X'));
    }

    /**
     * This test case tests for checkForDraw with an empty board
     */
    @Test
    public void testCheckForDraw_Empty(){
        IGameBoard gb = sample(5,5,4);
        assertFalse(gb.checkForDraw());
    }

   /**
    * This test case tests for checkForDraw with a board that is full
    */
    @Test
    public void testCheckForDraw_Full(){
        IGameBoard gb = sample(5,5,4);
        gb.placeMarker(new BoardPosition(1,3), 'X');
        gb.placeMarker(new BoardPosition(3,3), 'O');
        gb.placeMarker(new BoardPosition(3,1), 'X');
        gb.placeMarker(new BoardPosition(3,4), 'O');
        gb.placeMarker(new BoardPosition(4,0), 'X');
        gb.placeMarker(new BoardPosition(4,3), 'O');
        gb.placeMarker(new BoardPosition(0,4), 'X');
        gb.placeMarker(new BoardPosition(0,0), 'O');
        gb.placeMarker(new BoardPosition(0,1), 'X');
        gb.placeMarker(new BoardPosition(0,2), 'O');
        gb.placeMarker(new BoardPosition(0,3), 'X');
        gb.placeMarker(new BoardPosition(1,0), 'O');
        gb.placeMarker(new BoardPosition(1,1), 'X');
        gb.placeMarker(new BoardPosition(1,2), 'O');
        gb.placeMarker(new BoardPosition(1,4), 'X');
        gb.placeMarker(new BoardPosition(2,0), 'O');
        gb.placeMarker(new BoardPosition(2,1), 'X');
        gb.placeMarker(new BoardPosition(2,2), 'O');
        gb.placeMarker(new BoardPosition(2,3), 'X');
        gb.placeMarker(new BoardPosition(2,4), 'O');
        gb.placeMarker(new BoardPosition(3,0), 'X');
        gb.placeMarker(new BoardPosition(3,2), 'O');
        gb.placeMarker(new BoardPosition(4,1), 'X');
        gb.placeMarker(new BoardPosition(4,2), 'O');
        gb.placeMarker(new BoardPosition(4,4), 'X');
        assertTrue(gb.checkForDraw());
    }

    /**
     * This test case tests for checkForDraw with a board that is full but a player won
     */
    @Test
    public void testCheckForDraw_Full_Win(){
        IGameBoard gb = sample(5,5,4);
        gb.placeMarker(new BoardPosition(0,0), 'X');
        gb.placeMarker(new BoardPosition(0,1), 'O');
        gb.placeMarker(new BoardPosition(0,2), 'X');
        gb.placeMarker(new BoardPosition(0,3), 'O');
        gb.placeMarker(new BoardPosition(0,4), 'X');
        gb.placeMarker(new BoardPosition(1,0), 'O');
        gb.placeMarker(new BoardPosition(1,1), 'X');
        gb.placeMarker(new BoardPosition(1,2), 'O');
        gb.placeMarker(new BoardPosition(1,3), 'X');
        gb.placeMarker(new BoardPosition(1,4), 'O');
        gb.placeMarker(new BoardPosition(2,0), 'O');
        gb.placeMarker(new BoardPosition(2,1), 'X');
        gb.placeMarker(new BoardPosition(2,2), 'X');
        gb.placeMarker(new BoardPosition(2,3), 'O');
        gb.placeMarker(new BoardPosition(2,4), 'X');
        gb.placeMarker(new BoardPosition(3,0), 'X');
        gb.placeMarker(new BoardPosition(3,2), 'O');
        gb.placeMarker(new BoardPosition(3,3), 'O');
        gb.placeMarker(new BoardPosition(3,4), 'X');
        gb.placeMarker(new BoardPosition(4,0), 'O');
        gb.placeMarker(new BoardPosition(4,1), 'X');
        gb.placeMarker(new BoardPosition(4,2), 'O');
        gb.placeMarker(new BoardPosition(4,3), 'X');
        gb.placeMarker(new BoardPosition(4,4), 'X');
        gb.placeMarker(new BoardPosition(3,1), 'X');

        boolean expected = false;
        if(gb.checkForWinner(new BoardPosition(3,1))){
            assertFalse(expected);
        }
        else{
            assertEquals(expected, gb.checkForDraw());
        }
    }

    /**
     * This test case tests for checkForDraw with a board that is full except for the first spot
     */
    @Test
    public void testCheckForDraw_Full_Except_First(){
        IGameBoard gb = sample(5,5,4);
        gb.placeMarker(new BoardPosition(1,3), 'X');
        gb.placeMarker(new BoardPosition(3,3), 'O');
        gb.placeMarker(new BoardPosition(3,1), 'X');
        gb.placeMarker(new BoardPosition(3,4), 'O');
        gb.placeMarker(new BoardPosition(4,0), 'X');
        gb.placeMarker(new BoardPosition(4,3), 'O');
        gb.placeMarker(new BoardPosition(0,4), 'X');
        gb.placeMarker(new BoardPosition(0,1), 'O');
        gb.placeMarker(new BoardPosition(0,2), 'O');
        gb.placeMarker(new BoardPosition(0,3), 'X');
        gb.placeMarker(new BoardPosition(1,0), 'O');
        gb.placeMarker(new BoardPosition(1,1), 'X');
        gb.placeMarker(new BoardPosition(1,2), 'O');
        gb.placeMarker(new BoardPosition(1,4), 'X');
        gb.placeMarker(new BoardPosition(2,0), 'O');
        gb.placeMarker(new BoardPosition(2,1), 'X');
        gb.placeMarker(new BoardPosition(2,2), 'O');
        gb.placeMarker(new BoardPosition(2,3), 'X');
        gb.placeMarker(new BoardPosition(2,4), 'O');
        gb.placeMarker(new BoardPosition(3,0), 'X');
        gb.placeMarker(new BoardPosition(3,2), 'O');
        gb.placeMarker(new BoardPosition(4,1), 'X');
        gb.placeMarker(new BoardPosition(4,2), 'O');
        gb.placeMarker(new BoardPosition(4,4), 'X');
        assertFalse(gb.checkForDraw());
    }

    /**
     * This test case tests for whatsAtPos with a position that is on an empty board
     */
    @Test
    public void testWhatsAtPos_Empty(){
        IGameBoard gb = sample(5,5,4);
        assertEquals(' ', gb.whatsAtPos(new BoardPosition(0,0)));
    }

    /**
     * This test case tests for whatsAtPos with a position that is on a board with a marker
     */
    @Test
    public void testWhatsAtPos_Marker(){
        IGameBoard gb = sample(5,5,4);
        gb.placeMarker(new BoardPosition(1,1), 'X');
        assertEquals('X', gb.whatsAtPos(new BoardPosition(1,1)));
    }

    /**
     * This test case tests for whatsAtPos with a filled column
     */
    @Test
    public void testWhatsAtPos_Filled_Column(){
        IGameBoard gb = sample(5,5,4);
        gb.placeMarker(new BoardPosition(0,0), 'X');
        gb.placeMarker(new BoardPosition(1,0), 'O');
        gb.placeMarker(new BoardPosition(2,0), 'X');
        gb.placeMarker(new BoardPosition(3,0), 'O');
        gb.placeMarker(new BoardPosition(4,0), 'X');
        assertEquals(' ', gb.whatsAtPos(new BoardPosition(0,1)));
    }

    /**
     * This test case tests for whatsAtPos with a full board except one spot
     */
    @Test
    public void testWhatsAtPos_Single_Space(){
        IGameBoard gb = sample(5,5,4);
        gb.placeMarker(new BoardPosition(1,3), 'X');
        gb.placeMarker(new BoardPosition(3,3), 'O');
        gb.placeMarker(new BoardPosition(3,1), 'X');
        gb.placeMarker(new BoardPosition(3,4), 'O');
        gb.placeMarker(new BoardPosition(4,0), 'X');
        gb.placeMarker(new BoardPosition(4,3), 'O');
        gb.placeMarker(new BoardPosition(0,4), 'X');
        gb.placeMarker(new BoardPosition(0,1), 'O');
        gb.placeMarker(new BoardPosition(0,2), 'O');
        gb.placeMarker(new BoardPosition(0,3), 'X');
        gb.placeMarker(new BoardPosition(1,0), 'O');
        gb.placeMarker(new BoardPosition(1,1), 'X');
        gb.placeMarker(new BoardPosition(1,2), 'O');
        gb.placeMarker(new BoardPosition(1,4), 'X');
        gb.placeMarker(new BoardPosition(2,0), 'O');
        gb.placeMarker(new BoardPosition(2,1), 'X');
        gb.placeMarker(new BoardPosition(2,2), 'O');
        gb.placeMarker(new BoardPosition(2,3), 'X');
        gb.placeMarker(new BoardPosition(2,4), 'O');
        gb.placeMarker(new BoardPosition(3,0), 'X');
        gb.placeMarker(new BoardPosition(3,2), 'O');
        gb.placeMarker(new BoardPosition(4,1), 'X');
        gb.placeMarker(new BoardPosition(4,2), 'O');
        gb.placeMarker(new BoardPosition(4,4), 'X');
        assertEquals(' ', gb.whatsAtPos(new BoardPosition(0,0)));
    }

    /**
     * This test case tests for whatsAtPos with a full board
     */
    @Test
    public void testWhatsAtPos_Full(){
        IGameBoard gb = sample(5,5,4);
        gb.placeMarker(new BoardPosition(1,3), 'X');
        gb.placeMarker(new BoardPosition(3,3), 'O');
        gb.placeMarker(new BoardPosition(3,1), 'X');
        gb.placeMarker(new BoardPosition(3,4), 'O');
        gb.placeMarker(new BoardPosition(4,0), 'X');
        gb.placeMarker(new BoardPosition(4,3), 'O');
        gb.placeMarker(new BoardPosition(0,4), 'X');
        gb.placeMarker(new BoardPosition(0,1), 'O');
        gb.placeMarker(new BoardPosition(0,2), 'O');
        gb.placeMarker(new BoardPosition(0,3), 'X');
        gb.placeMarker(new BoardPosition(1,0), 'O');
        gb.placeMarker(new BoardPosition(1,1), 'X');
        gb.placeMarker(new BoardPosition(1,2), 'O');
        gb.placeMarker(new BoardPosition(1,4), 'X');
        gb.placeMarker(new BoardPosition(2,0), 'O');
        gb.placeMarker(new BoardPosition(2,1), 'X');
        gb.placeMarker(new BoardPosition(2,2), 'O');
        gb.placeMarker(new BoardPosition(2,3), 'X');
        gb.placeMarker(new BoardPosition(2,4), 'O');
        gb.placeMarker(new BoardPosition(3,0), 'X');
        gb.placeMarker(new BoardPosition(3,2), 'O');
        gb.placeMarker(new BoardPosition(4,1), 'X');
        gb.placeMarker(new BoardPosition(4,2), 'O');
        gb.placeMarker(new BoardPosition(4,4), 'X');
        gb.placeMarker(new BoardPosition(0,0), 'O');
        assertEquals('O', gb.whatsAtPos(new BoardPosition(0,0)));
    }

    /**
     * This test case tests for isPlayerAtPos with a position that is on an empty board
     */
    @Test
    public void testIsPlayerAtPos_Empty(){
        IGameBoard gb = sample(5,5,4);
        assertFalse(gb.isPlayerAtPos(new BoardPosition(0,0), 'X'));
    }

    /**
     * This test case tests for isPlayerAtPos with a position that is on a board with a marker
     */
    @Test
    public void testIsPlayerAtPos_Marker(){
        IGameBoard gb = sample(5,5,4);
        gb.placeMarker(new BoardPosition(1,1), 'X');
        assertTrue(gb.isPlayerAtPos(new BoardPosition(1,1), 'X'));
    }

    /**
     * This test case tests for isPlayerAtPos with a position that is on a board with two markers
     */
    @Test
    public void testIsPlayerAtPos_Two_Markers(){
        IGameBoard gb = sample(5,5,4);
        gb.placeMarker(new BoardPosition(1,1), 'X');
        gb.placeMarker(new BoardPosition(2,3), 'O');
        assertTrue(gb.isPlayerAtPos(new BoardPosition(2,3), 'O'));
    }

    /**
     * This test case tests for isPlayerAtPos with a position that is on a board with two markers but the wrong player
     */
    @Test
    public void testIsPlayerAtPos_Two_Markers_False(){
        IGameBoard gb = sample(5,5,4);
        gb.placeMarker(new BoardPosition(1,1), 'X');
        gb.placeMarker(new BoardPosition(2,3), 'O');
        assertFalse(gb.isPlayerAtPos(new BoardPosition(2,3), 'X'));
    }

    /**
     * This test case tests for isPlayerAtPos with a position that is on a full board
     */
    @Test
    public void testIsPlayerAtPos_Full(){
        GameBoard gb = new GameBoard(5,5,4);
        gb.placeMarker(new BoardPosition(1,3), 'X');
        gb.placeMarker(new BoardPosition(3,3), 'O');
        gb.placeMarker(new BoardPosition(3,1), 'X');
        gb.placeMarker(new BoardPosition(3,4), 'O');
        gb.placeMarker(new BoardPosition(4,0), 'X');
        gb.placeMarker(new BoardPosition(4,3), 'O');
        gb.placeMarker(new BoardPosition(0,4), 'X');
        gb.placeMarker(new BoardPosition(0,1), 'O');
        gb.placeMarker(new BoardPosition(0,2), 'O');
        gb.placeMarker(new BoardPosition(0,3), 'X');
        gb.placeMarker(new BoardPosition(1,0), 'O');
        gb.placeMarker(new BoardPosition(1,1), 'X');
        gb.placeMarker(new BoardPosition(1,2), 'O');
        gb.placeMarker(new BoardPosition(1,4), 'X');
        gb.placeMarker(new BoardPosition(2,0), 'O');
        gb.placeMarker(new BoardPosition(2,1), 'X');
        gb.placeMarker(new BoardPosition(2,2), 'O');
        gb.placeMarker(new BoardPosition(2,3), 'X');
        gb.placeMarker(new BoardPosition(2,4), 'O');
        gb.placeMarker(new BoardPosition(3,0), 'X');
        gb.placeMarker(new BoardPosition(3,2), 'O');
        gb.placeMarker(new BoardPosition(4,1), 'X');
        gb.placeMarker(new BoardPosition(4,2), 'O');
        gb.placeMarker(new BoardPosition(4,4), 'X');
        assertTrue(gb.isPlayerAtPos(new BoardPosition(3,3), 'O'));
    }

    /**
     * This test case tests for placeMarker with a position that is on an empty board
     */
    @Test
    public void testPlaceMarker_Empty(){
        IGameBoard gb = sample(4,4,4);
        char[][] board = new char[4][4];
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                board[i][j] = ' ';
            }
        }
        board[1][1] = 'X';

        gb.placeMarker(new BoardPosition(1,1), 'X');
        String expected = printBoard(board);
        assertEquals(expected, gb.toString());
    }

    /**
     * This test case tests for placeMarker with a position that is on a board with a third marker
     */
    @Test
    public void testPlaceMarker_Third_Marker(){
        IGameBoard gb = sample(4,4,4);
        char[][] board = new char[4][4];
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                board[i][j] = ' ';
            }
        }
        board[2][1] = 'X';
        board[3][0] = 'O';
        board[1][2] = 'A';

        gb.placeMarker(new BoardPosition(2,1), 'X');
        gb.placeMarker(new BoardPosition(3,0), 'O');
        gb.placeMarker(new BoardPosition(1,2), 'A');
        String expected = printBoard(board);
        assertEquals(expected, gb.toString());
    }

    /**
     * This test case tests for placeMarker with a filled board except one space
     */
    @Test
    public void testPlaceMarker_Single_Space(){

        IGameBoard gb = sample(4, 4, 4);
        char[][] board = new char[4][4];
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                board[i][j] = ' ';
            }
        }
        board[0][0] = 'X';
        board[0][2] = 'X';
        board[1][1] = 'X';
        board[1][3] = 'X';
        board[2][0] = 'X';
        board[2][2] = 'X';
        board[3][1] = 'X';
        board[3][2] = 'X';

        board[0][1] = 'O';
        board[0][3] = 'O';
        board[1][0] = 'O';
        board[2][1] = 'O';
        board[2][3] = 'O';
        board[3][0] = 'O';
        board[3][3] = 'O';
        board[1][2] = 'O';

        gb.placeMarker(new BoardPosition(0,0), 'X');
        gb.placeMarker(new BoardPosition(0,2), 'X');
        gb.placeMarker(new BoardPosition(1,1), 'X');
        gb.placeMarker(new BoardPosition(1,3), 'X');
        gb.placeMarker(new BoardPosition(2,0), 'X');
        gb.placeMarker(new BoardPosition(2,2), 'X');
        gb.placeMarker(new BoardPosition(3,1), 'X');
        gb.placeMarker(new BoardPosition(3,2), 'X');

        gb.placeMarker(new BoardPosition(0,1), 'O');
        gb.placeMarker(new BoardPosition(0,3), 'O');
        gb.placeMarker(new BoardPosition(1,0), 'O');
        gb.placeMarker(new BoardPosition(2,1), 'O');
        gb.placeMarker(new BoardPosition(2,3), 'O');
        gb.placeMarker(new BoardPosition(3,0), 'O');
        gb.placeMarker(new BoardPosition(3,3), 'O');
        gb.placeMarker(new BoardPosition(1,2), 'O');

        String expected = printBoard(board);
        assertEquals(expected, gb.toString());
    }

    /**
     * This test case tests for placeMarker in an empty board in the corner
     */
    @Test
    public void testPlaceMarker_Empty_Corner(){
        IGameBoard gb = sample(4,4,4);
        char[][] board = new char[4][4];
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                board[i][j] = ' ';
            }
        }
        board[0][0] = 'X';

        gb.placeMarker(new BoardPosition(0,0), 'X');
        String expected = printBoard(board);
        assertEquals(expected, gb.toString());
    }

    /**
     * This test case tests for placeMarker with the same marker but in a different position
     */
    @Test
    public void testPlaceMarker_Same_Marker(){
        IGameBoard gb = sample(4,4,4);
        char[][] board = new char[4][4];
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                board[i][j] = ' ';
            }
        }
        board[0][0] = 'X';
        board[0][1] = 'X';
        board[3][0] = 'O';


        gb.placeMarker(new BoardPosition(0,0), 'X');
        gb.placeMarker(new BoardPosition(3,0), 'O');
        gb.placeMarker(new BoardPosition(0,1), 'X');
        String expected = printBoard(board);
        assertEquals(expected, gb.toString());
    }
}
