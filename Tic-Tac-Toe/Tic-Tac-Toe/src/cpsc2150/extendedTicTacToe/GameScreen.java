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
     *
     * @param args command line arguments passed to the program
     * @pre NONE
     * @post NONE
     */
    public static void main(String[] args) {
        // Constant Variables for board size and conditions
        int MAX_ROW = 100;
        int MIN_ROW = 3;
        int MAX_COL = 100;
        int MIN_COL = 3;
        int MAX_WIN = 25;
        int MIN_WIN = 3;
        int MAX_PLAYER = 10;
        int MIN_PLAYER = 2;
        char pChar;
        Scanner input = new Scanner(System.in);

        //Placeholders for user input
        IGameBoard board = new GameBoard(0,0,0);

        //Boolean to check if the game is over
        boolean setup = false;
        //loop to get user input for board size and start the game
        while (!setup) {
            //Ask the user for the number of players
            System.out.println("How many players?");
            int numPlayers = input.nextInt();
            while (numPlayers < MIN_PLAYER || numPlayers > MAX_PLAYER) {
                if (numPlayers < MIN_PLAYER) {
                    System.out.println("Must be at least 2 players");
                    System.out.println("How many players?");
                    numPlayers = input.nextInt();
                }
                else if (numPlayers > MAX_PLAYER) {
                    System.out.println("Must be 10 players or fewer");
                    System.out.println("How many players?");
                    numPlayers = input.nextInt();
                }
            }
            //For loop to create the players
            char[] players = new char[numPlayers];
            for (int i = 0; i < numPlayers; i++) {
                System.out.println("Enter the character to represent player " + (i + 1));
                pChar = input.next().charAt(0);
                pChar = Character.toUpperCase(pChar);
                //Check if the character is already taken
                for(int j = 0; j < i; j++){
                    if(pChar == players[j]){
                        System.out.println(pChar + " is already taken as a player token!");
                        System.out.println("Enter the character to represent player " + (i + 1));
                        pChar = input.next().charAt(0);
                        pChar = Character.toUpperCase(pChar);
                    }
                }
                players[i] = pChar;
            }

            //Ask the user for the number of rows
            System.out.println("How many rows?");
            int numRow = input.nextInt();
            while (numRow < MIN_ROW || numRow > MAX_ROW) {
                System.out.println("Rows must be between 3 and 100");
                System.out.println("How many rows?");
                numRow = input.nextInt();
            }

            //Ask the user for the number of columns
            System.out.println("How many columns?");
            int numCol = input.nextInt();
            while (numCol < MIN_COL || numCol > MAX_COL) {
                System.out.println("Columns must be between 3 and 100");
                System.out.println("How many columns?");
                numCol = input.nextInt();
            }

            //Ask the user for the number of tokens in a row to win
            System.out.println("How many in a row to win?");
            int numWin = input.nextInt();
            while (numWin < MIN_WIN || numWin > MAX_WIN) {
                System.out.println("Must be between 3 and 25");
                System.out.println("How many in a row to win?");
                numWin = input.nextInt();

            }

            //Ask for a Fast or Memory Efficient game
            System.out.println("Would you like a Fast Game (F/f) or a Memory Efficient Game (M/m?");
            char game = input.next().charAt(0);
            while (game != 'F' && game != 'f' && game != 'M' && game != 'm') {
                System.out.println("Please enter F or M");
                System.out.println("Would you like a Fast Game (F/f) or a Memory Efficient Game (M/m?");
                game = input.next().charAt(0);
            }

            //Create the game board based on the user's input
            if (game == 'F' || game == 'f') {
                board = new GameBoard(numRow, numCol, numWin);
                //Create the game
                GameScreen gameScreen = new GameScreen();
                //Start the game
                System.out.println(board.toString());
            }
            else if (game == 'M' || game == 'm') {
                board = new GameBoardMem(numRow, numCol, numWin);
                //Create the game
                GameScreen gameScreen = new GameScreen();
                //Start the game
                System.out.println(board.toString());
            }

            //Game loop
            boolean gameEnd = false;
            while (!gameEnd) {

                //For loop to go through each player
                for (int i = 0; i < numPlayers; i++) {
                    //Ask the user for the row they want to place their token
                    System.out.println("Player " + players[i] + " Please enter your ROW");
                    int rowPos = input.nextInt();
                    //Ask the user for the column they want to place their token
                    System.out.println("Player " + players[i] + " Please enter your COLUMN");
                    int colPos = input.nextInt();
                    while(!board.checkSpace(new BoardPosition(rowPos, colPos))){
                        System.out.println("That space is unavailable, please pick again");
                        System.out.println("Player " + players[i] + " Please enter your ROW");
                        rowPos = input.nextInt();
                        System.out.println("Player " + players[i] + " Please enter your COLUMN");
                        colPos = input.nextInt();
                    }

                    //Place the token
                    board.placeMarker(new BoardPosition(rowPos, colPos), players[i]);
                    //Check if the game is over
                    //check for win
                    if (board.checkForWinner(new BoardPosition(rowPos, colPos))) {
                        System.out.println("Player " + players[i] + " wins!");
                        System.out.println(board.toString());
                        System.out.println("Would you like to play again? (Y/N)");
                        char play = input.next().charAt(0);
                        while (play != 'Y' && play != 'y' && play != 'N' && play != 'n') {
                            System.out.println("Please enter Y or N");
                            System.out.println("Would you like to play again? (Y/N)");
                            play = input.next().charAt(0);
                        }
                        if (play == 'Y' || play == 'y') {
                            gameEnd = true;
                            break;
                        }
                        else if (play == 'N' || play == 'n') {
                            setup = true;
                            System.exit(0);
                        }
                    }
                    //Check if the game is a tie
                    else if (board.checkForDraw()) {
                        System.out.println("It's a tie!");
                        System.out.println(board.toString());
                        System.out.println("Would you like to play again? (Y/N)");
                        char play = input.next().charAt(0);
                        while (play != 'Y' && play != 'y' && play != 'N' && play != 'n') {
                            System.out.println("Please enter Y or N");
                            System.out.println("Would you like to play again? (Y/N)");
                            play = input.next().charAt(0);
                        }
                        if (play == 'Y' || play == 'y') {
                            gameEnd = true;
                            break;
                        }
                        else if (play == 'N' || play == 'n') {
                            setup = true;
                            System.exit(0);
                        }
                    }
                    //Print the board
                    System.out.println(board.toString());
                }
            }
        }
    }
}