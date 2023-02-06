package cpsc2150.extendedTicTacToe.controllers;

import cpsc2150.extendedTicTacToe.models.*;
import cpsc2150.extendedTicTacToe.views.*;

/**
 * <p>
 * The {@link TicTacToeController} class will handle communication between our {@link TicTacToeView}
 * and our model ({@link IGameBoard} and {@link BoardPosition})
 * </p>
 *
 * <p>
 * This is where you will write code
 * <p>
 *
 * You will need to include your {@link BoardPosition} class, the {@link IGameBoard} interface
 * and both of the {@link IGameBoard} implementations from Project 4.
 * If your code was correct you will not need to make any changes to your {@link IGameBoard} implementation class.
 *
 * @version 2.0
 */
public class TicTacToeController {

    /**
     * <p>
     * The current game that is being played
     * </p>
     */
    private IGameBoard curGame;

    /**
     * <p>
     * The screen that provides our view
     * </p>
     */
    private TicTacToeView screen;

    /**
     * <p>
     * Constant for the maximum number of players.
     * </p>
     */
    public static final int MAX_PLAYERS = 10;

    /**
     * <p>
     * The number of players for this game. Note that our player tokens are hard coded.
     * </p>
     */
    private int numPlayers;

    //private variables for the players
    private char[] players = {'X', 'O', 'Y', 'Z', 'A', 'B', 'C', 'D', 'E', 'F'};

    //private variable for the current player
    private int curPlayer = 0;

    //boolean to check if the game is over
    private boolean gameOver = false;

    /**
     * <p>
     * This creates a controller for running the Extended TicTacToe game
     * </p>
     *
     * @param model
     *      The board implementation
     * @param view
     *      The screen that is shown
     * @param np
     *      The number of players for this game.
     *
     * @post [ the controller will respond to actions on the view using the model. ]
     */
    public TicTacToeController(IGameBoard model, TicTacToeView view, int np) {
        this.curGame = model;
        this.screen = view;
        this.numPlayers = np;



        // Register this controller as an observer of the view
        screen.registerObserver(this);

    }

    /**
     * <p>
     * This processes a button click from the view.
     * </p>
     *
     * @param row
     *      The row of the activated button
     * @param col
     *      The column of the activated button
     *
     * @post [ will allow the player to place a marker in the position if it is a valid space, otherwise it will display an error
     * and allow them to pick again. Will check for a win as well. If a player wins it will allow for them to play another
     * game hitting any button ]
     */
    public void processButtonClick(int row, int col) {
        //if the game is over, allow the user to play again
        if(gameOver){
            newGame();
        }
            //create a new board position
            BoardPosition pos = new BoardPosition(row, col);
            //check if the space is available
            if(curGame.checkSpace(pos)){
                //place the marker
                curGame.placeMarker(pos, players[curPlayer]);
                screen.setMarker(row, col, players[curPlayer]);
                //check for a winner
                if(curGame.checkForWinner(pos)){
                    //display the winner
                    screen.setMessage("Player " + players[curPlayer] + " wins! Click anywhere to play again.");
                    gameOver = true;
                }
                //check for a tie
                else if(curGame.checkForDraw()){
                    //display the tie
                    screen.setMessage("It's a tie! Click anywhere to play again.");
                    //set the game over to true
                    gameOver = true;
                }
                //if the game is not over
                else{
                    //increment the current player
                    curPlayer++;
                    //if the current player is greater than the number of players
                    if(curPlayer >= numPlayers){
                        //set the current player to 0
                        curPlayer = 0;
                    }
                    //display the current player
                    screen.setMessage("Player " + players[curPlayer] + "'s turn");
                }
            }
            //if the space is not available
            else{
                //display the error
                screen.setMessage("Space is not available");
            }

    }

    /**
     * <p>
     * This method will start a new game by returning to the setup screen and controller
     * </p>
     *
     * @post [ a new game gets started ]
     */
    private void newGame() {
        //close the current screen
        screen.dispose();

        //start back at the set up menu
        GameSetupScreen screen = new GameSetupScreen();
        GameSetupController controller = new GameSetupController(screen);
        screen.registerObserver(controller);
    }
}