package cpsc2150.extendedTicTacToe;
import cpsc2150.extendedTicTacToe.models.*;

import java.util.Scanner;

/**
 * This class is the main class of the game. It contains the main method
 * @author Yash Patel
 * @version 1.0
 * @invariant NONE
 **/

public class GameScreen {
    /**
     * This method is the main method of the game. It contains the game loop.
     * @param args command line arguments passed to the program
     * @pre NONE
     * @post NONE
     */
    public static void main(String[] args) {
        //Create a scanner to read input from the user
        char player = 'X';
        IGameBoard board = new GameBoard();
        //Create a game board
        System.out.println(board.toString());
        Scanner scan = new Scanner(System.in);
        //Create a loop to play the game
        boolean game = false; //This variable will be used to determine if the game is over
        boolean valid = false; //This variable will be used to determine if the user input is valid
        while (!game) {
            BoardPosition pos = new BoardPosition(69, 69); //69 is a placeholder
            while(!valid){
                System.out.println("Player " + player + " Please enter your ROW"); //Ask the user for a row
                int row = scan.nextInt();
                System.out.println("Player " + player + " Please enter your COLUMN"); //Ask the user for a column
                int col = scan.nextInt();
                pos = new BoardPosition(row, col); //Create a new position
                //Check if the position is valid
                if (row < board.getNumRows() && col < board.getNumColumns() && row >= 0 && col >= 0 && board.checkSpace(pos)) {
                    board.placeMarker(pos, player);

                    valid = true;
                }
                //If the position is not valid, tell the user and ask for a new position
                else {
                    System.out.println(board.toString());
                    System.out.println("That space is unavailable, please pick again");
                }
            }
            //Check if the game is over through a win
            if (board.checkForWinner(pos)) {
                System.out.println("Player " + player + " wins!");
                System.out.println(board.toString());
                //If the game is over, ask the user if they want to play again
                System.out.println("Would you like to play again? Y/N");
                String answer = scan.next();
                //If the user wants to play again, reset the board
                if (answer.equalsIgnoreCase("y")) {
                    board = new GameBoard();
                    System.out.println(board.toString());
                    player = 'O';
                }
                //If the user does not want to play again, end the game
                else {
                    game = true;
                }
            }
            //check if the game is over through a tie
            else if(board.checkForDraw()){
                System.out.println("The game is a draw!");
                System.out.println(board.toString());
                //If the game is over, ask the user if they want to play again
                System.out.println("Would you like to play again? Y/N");
                String answer = scan.next();
                //If the user wants to play again, reset the board
                if (answer.equalsIgnoreCase("y")) {
                    board = new GameBoard();
                    System.out.println(board.toString());
                    player = 'O';
                }
                //If the user does not want to play again, end the game
                else {
                    game = true;
                }

            }

            else{
                System.out.println(board.toString()); //Print the board
            }
            //If the game is not over, switch players
            if (player == 'X') {
                player = 'O';
            }
            else {
                player = 'X';
            }
            valid = false;
        }

    }

}
