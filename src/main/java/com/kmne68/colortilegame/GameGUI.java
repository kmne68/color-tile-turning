/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kmne68.colortilegame;

import com.kmne68.utils.Constants;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author kmne6
 */
public class GameGUI extends JFrame {

  private Game game;
  private JPanel gamePanel;

  public GameGUI(Game game) {

    this.game = game;

    int padding = Constants.PADDING;
    setTitle("Color Tile Game");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    gamePanel = new GamePanel(game);
    gamePanel.setPreferredSize(new Dimension(game.getGrid().getNumCols() * Constants.CELL_SIZE + 2 * padding,
            game.getGrid().getNumRows() * Constants.CELL_SIZE + 2 * padding));
    add(gamePanel);
    
    System.out.println("Panel size: " + gamePanel.getSize());

    pack();
    setVisible(true);
  }

  public void updateGUI() {
    gamePanel.repaint();
  }
}
