/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kmne68.colortilegame;

import com.kmne68.utils.Constants;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author kmne6
 */
public class GamePanel extends JPanel {
  private Game game;
  
  public GamePanel(Game game) {
    this.game = game;
  }
  
  
  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    
    System.out.println("paintComponent called");
    
    int cellSize = Constants.CELL_SIZE;
    
    for (int row = 0; row < game.getGrid().getNumRows(); row++) {
      for (int col = 0; col < game.getGrid().getNumCols(); col++) {
        Color color = game.getGrid().getTile(row, col).getColor();
        g.setColor(color);
        g.fillRect(col * cellSize, row * cellSize, cellSize, cellSize);
        g.setColor(Color.BLACK);
        g.drawRect(col * cellSize, row * cellSize, cellSize, cellSize);
        
      }
    }
  }
}
