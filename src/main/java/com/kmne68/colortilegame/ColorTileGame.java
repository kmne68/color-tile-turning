/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.kmne68.colortilegame;

import com.kmne68.utils.ColorDialog;
import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author kmne6
 */
public class ColorTileGame extends JPanel {

  // public class GridDrawer extends JPanel {
  private static final int numRows = 25;
  private static final int numCols = 25;
  private static final int blockSize = 16; // Assuming upload image icon size is 16x16 pixels
  private Color[][] gridColors;

  public ColorTileGame() {
    
    // Initialize gridColors with random colors
    gridColors = new Color[numRows][numCols];
    for (int i = 0; i < numRows; i++) {
      for (int j = 0; j < numCols; j++) {
        gridColors[i][j] = new Color((int) (Math.random() * 256),
                (int) (Math.random() * 256),
                (int) (Math.random() * 256));
      }
    }
    
    addMouseListener(new MouseAdapter() {

      @Override
      public void mouseClicked(MouseEvent me) {

        System.out.println("IN MOUSE CLICKED!");

        int clickedX = me.getX();
        int clickedY = me.getY();

        // Calculate row and column indices based on the block size
        int rowIndex = clickedY / blockSize;
        int columnIndex = clickedX / blockSize;

        System.out.println("tile coordinates: " + rowIndex + ", " + columnIndex);
        System.out.println("RGB: " + gridColors[rowIndex][columnIndex]);
        System.out.println("Red: " + (gridColors[rowIndex][columnIndex]).getRed());
        System.out.println("Green: " + (gridColors[rowIndex][columnIndex]).getGreen());
        System.out.println("Blue: " + (gridColors[rowIndex][columnIndex]).getBlue());
        
        ColorDialog colorDialog = new ColorDialog(null, gridColors[rowIndex][columnIndex], rowIndex, columnIndex, ColorTileGame.this);
        colorDialog.setVisible(true);
        
        // Get the new color from the dialog
     //   Color newColor = colorDialog.getNewColor();
        
     //   gridColors[rowIndex][columnIndex] = newColor;
        repaint();
      }
    });

  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    // Draw horizontal lines
    for (int i = 0; i <= numRows; i++) {
        g.drawLine(0, i * blockSize, numCols * blockSize, i * blockSize);
    }

    // Draw vertical lines
    for (int j = 0; j <= numCols; j++) {
        g.drawLine(j * blockSize, 0, j * blockSize, numRows * blockSize);
    }

    // Draw filled rectangles with colors from gridColors
    for (int i = 0; i < numRows; i++) {
        for (int j = 0; j < numCols; j++) {
            g.setColor(gridColors[i][j]);
            g.fillRect(j * blockSize, i * blockSize, blockSize, blockSize);
        }
    }

    System.out.println("LEAVING PAINT");
  }
  
  
  public void updateGridColor(int rowIndex, int columnIndex, Color newColor) {
    
    gridColors[rowIndex][columnIndex] = newColor;
    repaint(columnIndex * blockSize, rowIndex * blockSize, blockSize, blockSize);
  }
  

  public static void main(String[] args) {

    SwingUtilities.invokeLater(() -> {

      System.out.println("EDT: " + SwingUtilities.isEventDispatchThread());

      JFrame frame = new JFrame("Grid");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      ColorTileGame grid = new ColorTileGame();
      
      // Calculate the panel size
      int panelWidth = numCols * blockSize;
      int panelHeight = numRows * blockSize;
      grid.setPreferredSize(new Dimension(panelWidth, panelHeight));
      
      frame.add(grid);
      frame.pack();
//      frame.setSize(numCols * blockSize, numRows * blockSize); // Add some padding
      frame.setVisible(true);
    });
  }
}
