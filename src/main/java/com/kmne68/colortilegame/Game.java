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
 *  *
 *  * @author kmne6  
 */
public class Game {

  private List<Player> players;
  private Player currentPlayer;
  private Grid grid;
  private int maxTurns = 10;
  private int currentTurn = 0;
  private int selectedRow = -1;
  private int selectedCol = -1;
  private int remainingPoints = 0;

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
    System.out.println("GAME newCOLOR: " + newColor);
    System.out.println("CURRENT PLAYER: " + currentPlayer.getName());
    System.out.println("STARTING POINTS: " + currentPlayer.getRemainingPoints());
    System.out.println("TILE RED: " + grid.getTileColor(row, col).getRed());
    System.out.println("TILE GREEN: " + grid.getTileColor(row, col).getGreen());
    System.out.println("TILE BLUE: " + grid.getTileColor(row, col).getBlue());
    int redDiff = Math.abs(grid.getTileColor(row, col).getRed() - newColor.getRed());
    int greenDiff = Math.abs(grid.getTileColor(row, col).getGreen() - newColor.getGreen());
    int blueDiff = Math.abs(grid.getTileColor(row, col).getBlue() - newColor.getBlue());

    /*
    remainingPoints = currentPlayer.getRemainingPoints() - (redDiff + greenDiff + blueDiff);
    System.out.println("CURRENT PLAYER POINTS 1: " + currentPlayer.getRemainingPoints());
    if (currentPlayer.getRemainingPoints() >= remainingPoints) {
      currentPlayer.subtractPoints(remainingPoints);
    }
    System.out.println("CURRENT PLAYER POINTS 2: " + currentPlayer.getRemainingPoints());
    System.out.println("REMAINING POINTS: " + remainingPoints);
     */
    if (currentPlayer.getRemainingPoints() >= redDiff) {
      System.out.println("redDiff: " + redDiff);
      currentPlayer.subtractPoints(redDiff);
      if (currentPlayer.getRemainingPoints() >= greenDiff) {
        System.out.println("greenDiff: " + greenDiff);
        currentPlayer.subtractPoints(greenDiff);
        if (currentPlayer.getRemainingPoints() >= blueDiff) {
          System.out.println("blueDiff: " + blueDiff);
          currentPlayer.subtractPoints(blueDiff);
        } else {
          System.out.println("You have insufficient points for the transaction");
        }
      } else {
        System.out.println("You have insufficient points for the transaction");
      }
    }

    /*  if(currentPlayer.getRemainingPoints() >= 
            Math.abs(newColor.getRed() - grid.getTileColor(row, col).getRed()) +
            Math.abs(newColor.getGreen() - grid.getTileColor(row, col).getGreen()) +
            Math.abs(newColor.getBlue()) - grid.getTileColor(row, col).getBlue())
    { 
      int colorDifference = 
              Math.abs(newColor.getRed() - grid.getTileColor(row, col).getRed()) +
              Math.abs(newColor.getGreen() - grid.getTileColor(row, col).getGreen()) +
              Math.abs(newColor.getBlue() - grid.getTileColor(row, col).getBlue());
      currentPlayer.subtractPoints(colorDifference); */
    grid.setTileColor(row, col, newColor);

// Check if tile is locked after color change
    if (newColor.equals(currentPlayer.getTargetColor())) {
      grid.getTile(row, col).setLocked(true);
      currentPlayer.addPoints(10);
    }
  }
// }

// Methods for switching players, checking win conditions, updating scores, etc.
/*  public void switchPlayer() {
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
  */
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
        //  System.out.print(grid.getTile(row, col).getColor().getRed() + " ");
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

  public void setSelectedTile(int row, int col) {
    selectedRow = row;
    selectedCol = col;
  }

  public int getSelectedRow() {
    return selectedRow;
  }

  public int getSelectedCol() {
    return selectedCol;
  }

}
