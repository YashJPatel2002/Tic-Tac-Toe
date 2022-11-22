default: cpsc2150/extendedTicTacToe/GameScreen.java cpsc2150/extendedTicTacToe/models/IGameBoard.java cpsc2150/extendedTicTacToe/models/GameBoard.java cpsc2150/extendedTicTacToe/models/GameBoardMem.java cpsc2150/extendedTicTacToe/models/AbsGameBoard.java cpsc2150/extendedTicTacToe/models/BoardPosition.java
	javac cpsc2150/extendedTicTacToe/GameScreen.java cpsc2150/extendedTicTacToe/models/IGameBoard.java cpsc2150/extendedTicTacToe/models/GameBoard.java cpsc2150/extendedTicTacToe/models/GameBoardMem.java cpsc2150/extendedTicTacToe/models/AbsGameBoard.java cpsc2150/extendedTicTacToe/models/BoardPosition.java

run: cpsc2150/extendedTicTacToe/GameScreen.class cpsc2150/extendedTicTacToe/models/*.class
	java cpsc2150.extendedTicTacToe.GameScreen

test: cpsc2150/extendedTicTacToe/GameScreen.java cpsc2150/extendedTicTacToe/models/GameBoardMem.java cpsc2150/extendedTicTacToe/models/GameBoard.java cpsc2150/extendedTicTacToe/models/AbsGameBoard.java cpsc2150/extendedTicTacToe/models/BoardPosition.java cpsc2150/extendedTicTacToe/models/TestGameBoard.java cpsc2150/extendedTicTacToe/models/TestGameBoardMem.java
	javac -cp .:/usr/share/java/junit4.jar cpsc2150/extendedTicTacToe/models/GameBoard.java cpsc2150/extendedTicTacToe/models/GameBoardMem.java cpsc2150/extendedTicTacToe/models/AbsGameBoard.java cpsc2150/extendedTicTacToe/models/BoardPosition.java cpsc2150/extendedTicTacToe/models/TestGameBoard.java cpsc2150/extendedTicTacToe/models/TestGameBoardMem.java

testGB: cpsc2150/extendedTicTacToe/models/GameBoard.class cpsc2150/extendedTicTacToe/models/AbsGameBoard.class cpsc2150/extendedTicTacToe/models/BoardPosition.class
	java -cp .:/usr/share/java/junit4.jar org.junit.runner.JUnitCore cpsc2150.extendedTicTacToe.models.TestGameBoard

testGBmem: cpsc2150/extendedTicTacToe/models/GameBoardMem.class cpsc2150/extendedTicTacToe/models/AbsGameBoard.class cpsc2150/extendedTicTacToe/models/BoardPosition.class
	java -cp .:/usr/share/java/junit4.jar org.junit.runner.JUnitCore cpsc2150.extendedTicTacToe.models.TestGameBoardMem

clean:
	rm -f cpsc2150/extendedTicTacToe/*.class cpsc2150/extendedTicTacToe/models/*.class