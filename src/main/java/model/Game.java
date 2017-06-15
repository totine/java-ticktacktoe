package model;


import exceptions.OccupiedCellException;
import ui.Printer;

public class Game {
    private Board board;
    private GameState currentState;
    private Seed currentPlayer;

    public void initGame(int gameSize) {
        board = new Board(gameSize);
        board.init();
        currentPlayer = randomPlayer();
        currentState = GameState.PLAYING;
        
    }

    private Seed randomPlayer() {
        int random = (int) (Math.random()*2);
        return random == 0 ? Seed.CROSS : Seed.NOUGHT;
    }

    public Board getBoard() {
        return board;
    }

    public GameState getCurrentState() {
        return currentState;
    }

    public Seed getCurrentPlayer() {
        return currentPlayer;
    }

    public void updateGameState(Seed seed, int row, int col) throws OccupiedCellException {
        Printer printer = new Printer();

        board.update(seed, row, col);
        if (board.hasWon(seed, row, col)) {
            currentState = seed==Seed.CROSS ? GameState.CROSS_WON : GameState.NOUGHT_WON;
            printer.winnerDisplay(seed);
        }
        else if (board.isDraw()) {
            currentState = GameState.DRAW;
            printer.winnerDisplay();
        }
        currentPlayer = seed == Seed.CROSS ? Seed.NOUGHT : Seed.CROSS;
    }
}
