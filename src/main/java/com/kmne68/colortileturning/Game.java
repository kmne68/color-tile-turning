/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kmne68.colortileturning;

/**
 *
 * @author kmne6
 */
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
}
