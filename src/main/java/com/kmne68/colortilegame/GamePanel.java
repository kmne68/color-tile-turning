/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kmne68.colortilegame;

import com.kmne68.colortilegame.ColorDialog;
import com.kmne68.colortilegame.Game;
import com.kmne68.utils.Constants;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author kmne6
 */
public class GamePanel extends JPanel {

  private Game game;
  private int selectedRow = -1;
  private int selectedCol = -1;

  public GamePanel(Game game) {
    this.game = game;
    
    System.out.println("TOP OF GAME PANEL");

    addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        int cellSize = Constants.CELL_SIZE;
        int row = e.getY() / cellSize;
        int col = e.getX() / cellSize;

        if (row >= 0 && row < game.getGrid().getNumRows() && col >= 0 && col < game.getGrid().getNumCols()) {
          selectedRow = row;
          selectedCol = col;
          repaint();

          Frame topFrame = (Frame) SwingUtilities.getAncestorOfClass(Frame.class, GamePanel.this);
          if (topFrame != null) {

            ColorDialog colorDialog = new ColorDialog((JFrame) topFrame, game, selectedRow, selectedCol, GamePanel.this);
            colorDialog.setVisible(true);
            
            Color newColor = colorDialog.getColor();
            if(newColor != null) {
              game.changeTileColor(selectedRow, selectedCol, newColor);
              repaint();
            }
          }
        }

        System.out.println("Row: " + row + ", " + "Col: " + col);
        System.out.println("selRow: " + selectedRow + ", " + "selCol: " + selectedCol);
      }
    });
    repaint();
  }
  

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    int cellSize = Constants.CELL_SIZE;

    for (int row = 0; row < game.getGrid().getNumRows(); row++) {
      for (int col = 0; col < game.getGrid().getNumCols(); col++) {
        Color color = game.getGrid().getTile(row, col).getColor();
        g.setColor(color);
        g.fillRect(col * cellSize, row * cellSize, cellSize, cellSize);
        g.setColor(Color.BLACK);
        g.drawRect(col * cellSize, row * cellSize, cellSize, cellSize);

        // Highlight the selected tile
        if (selectedRow >= 0 && selectedCol >= 0) {
      //    Frame topFrame = (Frame) SwingUtilities.getAncestorOfClass(Frame.class, GamePanel.this);
     //     ColorDialog colorDialog = new ColorDialog((JFrame) topFrame, game, selectedRow, selectedCol);

        //  JDialog colorDialog = new JDialog(this, "Choose Color", true, colorChooser, null, null);
          g.setColor(Color.YELLOW);
          g.drawRect(selectedCol * cellSize, selectedRow * cellSize, cellSize, cellSize);

        }
      }
    }
  }
}
