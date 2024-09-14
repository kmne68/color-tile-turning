/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kmne68.colortilegame;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author kmne6
 */
public class ColorDialog extends JDialog {

  private JTextField playerInputRed, playerInputGreen, playerInputBlue;
  private JButton okButton, cancelButton;
  private Color newColor;
  private GamePanel gamePanel;
  private Game game;
  private int selectedRow, selectedCol;
  private String tileRed, tileGreen, tileBlue;
  private String playerName;
  private String player1Score;
  private String player2Score;
  private Player player;

  public ColorDialog(JFrame parent, Game game, int selectedRow, int selectedCol, GamePanel gamePanel) {
    super(parent, "Choose Color", ModalityType.APPLICATION_MODAL);

    player = game.getCurrentPlayer();

    System.out.println("TOP OF COLOR DIALOG");

    this.game = game;
    this.selectedRow = selectedRow;
    this.selectedCol = selectedCol;
    this.gamePanel = gamePanel;

    List<Player> players = game.getPlayers();
    player1Score = Integer.toString(players.get(0).getScore());
    player2Score = Integer.toString(players.get(1).getScore());
    // Create components
    playerInputRed = new JTextField(3);
    playerInputGreen = new JTextField(3);
    playerInputBlue = new JTextField(3);
    okButton = new JButton("OK");
    cancelButton = new JButton("CANCEL");
    tileRed = Integer.toString(game.getGrid().getTileColor(selectedRow, selectedCol).getRed());
    tileGreen = Integer.toString(game.getGrid().getTileColor(selectedRow, selectedCol).getGreen());
    tileBlue = Integer.toString(game.getGrid().getTileColor(selectedRow, selectedCol).getBlue());
    playerName = game.getCurrentPlayer().getName();

    System.out.println("From color dialog");
    System.out.println("COLOR DIALOG RGB: " + tileRed + ", " + tileGreen + ", " + tileBlue);

    // Create panel and layout
    JPanel contentPanel = new JPanel(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();

    gbc.gridx = 0;
    gbc.gridy = 0;
    contentPanel.add(new JLabel("Player 1"), gbc);
    //   contentPanel.add(leftPanel);

    gbc.gridx = 1;
    gbc.gridy = 0;
    contentPanel.add(new JLabel("Current player:"), gbc);

    gbc.gridx = 2;
    gbc.gridy = 0;
    contentPanel.add(new JLabel(""), gbc);

    gbc.gridx = 3;
    gbc.gridy = 0;
    contentPanel.add(new JLabel(playerName), gbc);

    gbc.gridx = 4;
    gbc.gridy = 0;
    //  contentPanel.add(rightPanel);
    contentPanel.add(new JLabel("Player 2"), gbc);

    gbc.gridx = 0;
    gbc.gridy = 1;
    contentPanel.add(new JLabel(player1Score), gbc);

    gbc.gridx = 1;
    gbc.gridy = 1;
    contentPanel.add(new JLabel("Red"), gbc);

    gbc.gridx = 2;
    gbc.gridy = 1;
    contentPanel.add(playerInputRed, gbc);

    gbc.gridx = 3;
    gbc.gridy = 1;
    contentPanel.add(new JLabel(tileRed), gbc);

    gbc.gridx = 4;
    gbc.gridy = 1;
    contentPanel.add(new JLabel(player2Score), gbc);

    gbc.gridx = 0;
    gbc.gridy = 2;
    contentPanel.add(new JLabel(""), gbc);

    gbc.gridx = 1;
    gbc.gridy = 2;
    contentPanel.add(new JLabel("Green"), gbc);

    gbc.gridx = 2;
    gbc.gridy = 2;
    contentPanel.add(playerInputGreen, gbc);

    gbc.gridx = 3;
    gbc.gridy = 2;
    contentPanel.add(new JLabel(tileGreen), gbc);

    gbc.gridx = 4;
    gbc.gridy = 2;
    contentPanel.add(new JLabel(""), gbc);

    gbc.gridx = 0;
    gbc.gridy = 3;
    contentPanel.add(new JLabel(""), gbc);

    gbc.gridx = 1;
    gbc.gridy = 3;
    contentPanel.add(new JLabel("Blue"), gbc);

    gbc.gridx = 2;
    gbc.gridy = 3;
    gbc.gridwidth = 1;
    contentPanel.add(playerInputBlue, gbc);

    gbc.gridx = 3;
    gbc.gridy = 3;
    gbc.gridwidth = 1;
    contentPanel.add(new JLabel(tileBlue), gbc);

    gbc.gridx = 4;
    gbc.gridy = 3;
    contentPanel.add(new JLabel(""), gbc);

    gbc.gridx = 0;
    gbc.gridy = 4;
    contentPanel.add(new JLabel(""), gbc);

    gbc.gridx = 1;
    gbc.gridy = 4;
    gbc.gridwidth = 1;  // Span two columns
    contentPanel.add(okButton, gbc);

    gbc.gridx = 2;
    gbc.gridy = 4;
    gbc.gridwidth = 1;
    contentPanel.add(new JLabel(""), gbc);

    gbc.gridx = 3;
    gbc.gridy = 4;
    gbc.gridwidth = 1;  // Span two columns
    contentPanel.add(cancelButton, gbc);

    gbc.gridx = 4;
    gbc.gridy = 4;
    contentPanel.add(new JLabel(""), gbc);

    // Add listeners
    okButton.addActionListener(e -> {
      try {
        int userRedInput = Integer.parseInt(playerInputRed.getText());
        int userGreenInput = Integer.parseInt(playerInputGreen.getText());
        int userBlueInput = Integer.parseInt(playerInputBlue.getText());

        player.setPlayerInputRed(userRedInput);
        player.setPlayerInputGreen(userGreenInput);
        player.setPlayerInputBlue(userBlueInput);
        
        
        
        // Input validation
        if (userRedInput < 0 || userRedInput > 255 || userGreenInput < 0 || userGreenInput > 255 || userBlueInput < 0 || userBlueInput > 255) {
          JOptionPane.showMessageDialog(this, "Invalid color values. Please enter values between 0 and 255");
          return;
        }
        System.out.println("RED: " + userRedInput + " Green: " + userGreenInput + " Blue: " + userBlueInput);
        newColor = new Color(userRedInput, userGreenInput, userBlueInput);
        System.out.println("NEW COLOR: " + newColor);
        game.changeTileColor(selectedRow, selectedCol, newColor);
        gamePanel.repaint();
        game.switchPlayer();

        setVisible(false);
      } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(this, "Invalid input. Please enter numbers.");;
      }
    });

    cancelButton.addActionListener(e -> setVisible(false));

    setContentPane(contentPanel);
    pack();
    setLocationRelativeTo(parent);
  }

  public Color getColor() {
    return newColor;
  }
}
