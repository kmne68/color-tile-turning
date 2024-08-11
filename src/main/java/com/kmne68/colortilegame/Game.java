/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kmne68.colortilegame;

import java.awt.Color;
// import java.awt.List;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author kmne6
 */
public class Game {
  
  private List<Player> players;
  private Player currentPlayer;
  Grid grid;
    
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
            
  // Methods for switching players, checking win conditions, updating scores, etc.
}
