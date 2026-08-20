package com.kmne68.colortileturning;

/**
 * Main game orchestrator. Manages turns, players, and win conditions.
 */
public class Game {
    private final Board board;
    private final Player player1;
    private final Player player2;
    private Player currentPlayer;
    private int roundsLeft;

    public Game(int rows, int cols, int totalRounds) {
        this.board = new Board(rows, cols);
        this.player1 = new Player(true);
        this.player2 = new Player(false);
        player1.setAsPlayer1(true);
        player2.setAsPlayer1(false);
        this.currentPlayer = player1;
        this.roundsLeft = totalRounds;
        this.currentPlayer.startTurn(board);
    }

    public void nextTurn() {
        // Switch players and start their turn
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
        currentPlayer.startTurn(board);
        roundsLeft--;
    }

    /**
     * Make a move for the current player.
     */
    public void makeMove(Tile tile, int rDelta, int gDelta, int bDelta) {
        currentPlayer.spendPoints(tile, rDelta, gDelta, bDelta);
    }

    public boolean isGameOver() {
        return roundsLeft <= 0 || allTilesCaptured();
    }

    private boolean allTilesCaptured() {
        // TODO: Implement check for all tiles locked
        return false; // Placeholder
    }

    public Player getWinner() {
        // TODO: Return player with more captured tiles
        return player1.getCapturedTiles() > player2.getCapturedTiles() ? player1 : player2;
    }

    public Player getCurrentPlayer() { return currentPlayer; }
    public Board getBoard() { return board; }
    public int getRoundsLeft() { return roundsLeft; }

    public Player getPlayer1() { return player1; }
    public Player getPlayer2() { return player2; }
    public boolean isPlayerTurn(Player p) { return currentPlayer == p; }
    public void switchTurn() { currentPlayer = (currentPlayer == player1) ? player2 : player1; }
    public int calculateContiguousBonus(Player p) { return 0; } // Implement later
    public void checkCapture(Tile t, Player p) { /* logic */ }
//    public boolean isGameOver() { return false; } // Implement later
}



/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
// package com.kmne68.colortileturning;

/**
 *
 * @author kmne6
 */


/*
public class Game {
  private Board board;
  private Player player1, player2;
  private Player currentPlayer;
  private int roundsLeft;
  
  public Game(int rows, int cols, int rounds) {
    board = new Board(rows, cols);
    player1 = new Player(true);
    player2 = new Player(false);
    currentPlayer = player1;
    roundsLeft = rounds;
  }
  
  public void nextTurn() {
    // Switch players and update points
    currentPlayer = (currentPlayer == player1) ? player2 : player1;
    currentPlayer.startTurn(board);
    roundsLeft--;
  }
  
  public boolean isGameOver() { return roundsLeft <= 0; }
  public Player getCurrentPlayer() { return currentPlayer; }
  public Board getBoard() { return board; }
  public int getPlayerScore(Player player) { return player.getCapturedTiles(); }
}

*/