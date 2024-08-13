/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kmne68.colortilegame;

import java.awt.Color;
// import java.awt.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

/**
 *
 * @author kmne6
 */
public class Game {
  
  private List<Player> players;
  private Player currentPlayer;
  private Grid grid;
  private int maxTurns = 10;
  private int currentTurn = 0;
    
  public Game(int numRows, int numCols) {
    
    // Create players
    players = new ArrayList<>();
    players.add(new Player("Player1", Color.BLACK));
    players.add(new Player("Player2", Color.WHITE));
    
    // Randomly determine starting player
    currentPlayer = players.get(new Random().nextInt(players.size()));
    
    // Create grid
    grid = new Grid(numRows, numCols);
  }
  
  
  public void changeTileColor(int row, int col, Color newColor) {
    if(currentPlayer.getRemainingPoints() >= 
            Math.abs(newColor.getRed() - grid.getTileColor(row, col).getRed()) +
            Math.abs(newColor.getGreen() - grid.getTileColor(row, col).getGreen()) +
            Math.abs(newColor.getBlue()) - grid.getTileColor(row, col).getBlue())
    {
      int colorDifference = 
              Math.abs(newColor.getRed() - grid.getTileColor(row, col).getRed()) +
              Math.abs(newColor.getGreen() - grid.getTileColor(row, col).getGreen()) +
              Math.abs(newColor.getBlue() - grid.getTileColor(row, col).getBlue());
      currentPlayer.subtractPoints(colorDifference);
      grid.setTileColor(row, col, newColor);
      
      // Check if tile is locked after color change
      if(newColor.equals(currentPlayer.getTargetColor())) {
        grid.getTile(row, col).setLocked(true);
        currentPlayer.addPoints(10);
      }
    }       
  }
            
  // Methods for switching players, checking win conditions, updating scores, etc.
  public void switchPlayer() {
    int currentIndex = players.indexOf(currentPlayer);
    currentPlayer = players.get((currentIndex + 1) % players.size());
  }
  
  public boolean checkWinCondition() {
    currentTurn++;
    if(currentTurn >= maxTurns) {
      return true;
    }
    for(Player player : players) {
      boolean hasWon = true;
      for(int row = 0; row < grid.getNumRows(); row++) {
        for(int col = 0; col < grid.getNumCols(); col++) {
          if(!grid.getTile(row, col).getColor().equals(player.getTargetColor())) {
            hasWon = false;
            break;
          }
        }
        if (hasWon) {
          return true;
        }
      }
      return false;
    }
    return false;
  }
  
  
  public String endGame() {
    // Determine the winner based on scores
    Player winner = players.stream().max(Comparator.comparingInt(Player::getScore)).get();
  
    return winner.getName();
    // Display a message indicating the winner
    // System.out.println("Game over! The winner is: " + winner.getName());
  }
  
  
  public void printGrid() {
    for (int row = 0; row < grid.getNumCols(); row++) {
      for (int col = 0; col < grid.getNumCols(); col++) {
      //  System.out.print(grid.getTile(row, col).getColor().getRed() + " ");
      }
      System.out.println();
    }
  }
  
  public Player getCurrentPlayer() {
    return currentPlayer;
  }
  
  
  public Grid getGrid() {
    return grid;
  }

}
