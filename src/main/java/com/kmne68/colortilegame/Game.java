/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kmne68.colortilegame;

import java.awt.Color;
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
  private int red, green, blue = 0;

  public Game(int numRows, int numCols) {

    System.out.println("TOP OF GAME CLASS");

// Create players
    players = new ArrayList<>();
    players.add(new Player("Player1", new Color(0, 0, 0)));
    players.add(new Player("Player2", new Color(255, 255, 255)));

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
    red = grid.getTileColor(row, col).getRed();
    green = grid.getTileColor(row, col).getGreen();
    blue = grid.getTileColor(row, col).getBlue();
    //  int redDiff = Math.abs(grid.getTileColor(row, col).getRed() - newColor.getRed());
    //  int greenDiff = Math.abs(grid.getTileColor(row, col).getGreen() - newColor.getGreen());
    //  int blueDiff = Math.abs(grid.getTileColor(row, col).getBlue() - newColor.getBlue());

    /*
    remainingPoints = currentPlayer.getRemainingPoints() - (redDiff + greenDiff + blueDiff);
    System.out.println("CURRENT PLAYER POINTS 1: " + currentPlayer.getRemainingPoints());
    if (currentPlayer.getRemainingPoints() >= remainingPoints) {
      currentPlayer.subtractPoints(remainingPoints);
    }
    System.out.println("CURRENT PLAYER POINTS 2: " + currentPlayer.getRemainingPoints());
    System.out.println("REMAINING POINTS: " + remainingPoints);
     */
    System.out.println("BEFORE WHILE REMAINING POINTS: " + currentPlayer.getRemainingPoints());

//    while (currentPlayer.getRemainingPoints() > 0) {
      System.out.println("WHILE TILE RED: " + grid.getTileColor(row, col).getRed());
      System.out.println("WHILE TILE GREEN: " + grid.getTileColor(row, col).getGreen());
      System.out.println("WHILE TILE BLUE: " + grid.getTileColor(row, col).getBlue());
      int redDiff = Math.abs(grid.getTileColor(row, col).getRed() - newColor.getRed());
      int greenDiff = Math.abs(grid.getTileColor(row, col).getGreen() - newColor.getGreen());
      int blueDiff = Math.abs(grid.getTileColor(row, col).getBlue() - newColor.getBlue());

      if (currentPlayer.getRemainingPoints() >= redDiff) {
        System.out.println("redDiff: " + redDiff);
        currentPlayer.subtractPoints(redDiff);
        System.out.println("IN RED REMAINING POINTS: " + currentPlayer.getRemainingPoints());
      } else {

        // reduce red by remaining points
        grid.setRed(red - currentPlayer.getRemainingPoints());
        // set remaining points to zero
        currentPlayer.subtractPoints(currentPlayer.getRemainingPoints());

        System.out.println("ELSE RED REMAINING POINTS: " + currentPlayer.getRemainingPoints());
    //    break;
      }
      if (currentPlayer.getRemainingPoints() >= greenDiff) {
        System.out.println("greenDiff: " + greenDiff);
        currentPlayer.subtractPoints(greenDiff);
        System.out.println("IN GREEN REMAINING POINTS: " + currentPlayer.getRemainingPoints());
      } else {
        // reduce green by remaining points
        grid.setGreen(green - currentPlayer.getRemainingPoints());
        // set remaining points to zero
        currentPlayer.subtractPoints(currentPlayer.getRemainingPoints());

        System.out.println("ELSE GREEN REMAINING POINTS: " + currentPlayer.getRemainingPoints());
    //    break;
      }
      if (currentPlayer.getRemainingPoints() >= blueDiff) {
        System.out.println("blueDiff: " + blueDiff);
        currentPlayer.subtractPoints(blueDiff);
        System.out.println("IN BLUE REMAINING POINTS: " + currentPlayer.getRemainingPoints());
      } else {
        // reduce blue by remaining points
        grid.setBlue(blue - currentPlayer.getRemainingPoints());
        // set remaining points to zero
        currentPlayer.subtractPoints(currentPlayer.getRemainingPoints());
        System.out.println("ELSE BLUE REMAINING POINTS: " + currentPlayer.getRemainingPoints());
    //    break;
      }
  //  }
    System.out.println("OUTSIDE WHILE");
    grid.setTileColor(row, col, newColor);

// Check if tile is locked after color change
// TODO: FIGURE OUT HOW TO DEAL WITH ADDITIONAL POINTS
    if (newColor.equals(currentPlayer.getTargetColor())) {
      grid.getTile(row, col).setLocked(true);
      currentPlayer.incrementScore();
      System.out.println("LOCKED?: " + grid.getTile(row, col).isLocked());
      selectedRow = -1;
      selectedCol = -1;
      grid.setRed(255);
      grid.setGreen(255);
      grid.setBlue(255);
      //  currentPlayer.addPoints(10);
      //  switchPlayer();
    }
    /* 
    if(currentPlayer == players.get(0)) {
      currentPlayer = players.get(1);
    } else {
      currentPlayer = players.get(0);
    } */
    // switchPlayer2();
    
//  }
 }    

  public void switchPlayer2() {
    System.out.println("CURRENT PLAYER BEFORE: " + currentPlayer);
    if (currentPlayer == players.get(0)) {
      currentPlayer = players.get(1);
    } else {
      currentPlayer = players.get(0);
    }
    System.out.println("CURRENT PLAYER AFTER: " + currentPlayer);
  }

// Methods for switching players, checking win conditions, updating scores, etc.
  public void switchPlayer() {
    System.out.println("IN SWITCH PLAYER");
    int currentIndex = players.indexOf(currentPlayer);
    currentPlayer = players.get((currentIndex + 1) % players.size());
  }

  public boolean checkWinCondition() {
    currentTurn++;
    if (currentTurn >= maxTurns) {
      return true;
    }
    for (Player player : players) {
      boolean hasWon = true;
      for (int row = 0; row < grid.getNumRows(); row++) {
        for (int col = 0; col < grid.getNumCols(); col++) {
          if (!grid.getTile(row, col).getColor().equals(player.getTargetColor())) {
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

  public int getRed() {
    return red;
  }

  public void setRed(int red) {
    this.red = red;
  }

  public int getGreen() {
    return green;
  }

  public void setGreen(int green) {
    this.green = green;
  }

  public int getBlue() {
    return blue;
  }

  public void setBlue(int blue) {
    this.blue = blue;
  }

  public List<Player> getPlayers() {
    return this.players;
  }

  public void setPlayers(List<Player> players) {
    this.players = players;
  }
  
  

}
