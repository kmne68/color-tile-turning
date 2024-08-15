/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kmne68.colortilegame;

import java.awt.Color;
import java.util.Scanner;

/**
 *
 * @author kmne6
 */
public class GameTest {

  public static void main(String[] args) {

    Game game = new Game(10, 10);
    GameGUI gui = new GameGUI(game);
    
    gui.setVisible(true);

    while (!game.checkWinCondition()) {
    //  System.out.println("Current player: " + game.getCurrentPlayer().getName());

      game.printGrid();
      game.switchPlayer();
      
      System.out.println("Enter new color (RGB): ");
      Scanner scanner = new Scanner(System.in);
      int red = scanner.nextInt();
      int green = scanner.nextInt();
      int blue = scanner.nextInt();
      Color newColor = new Color(red, green, blue);
      
      game.changeTileColor(game.getSelectedRow(), game.getSelectedCol(), newColor);
    }

    System.out.println("Game over! Winner: " + game.endGame());
  }
}
