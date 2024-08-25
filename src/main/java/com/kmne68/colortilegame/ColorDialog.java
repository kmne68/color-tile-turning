/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kmne68.colortilegame;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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
  
  private JTextField redField, greenField, blueField;
  private JButton okButton, cancelButton;
  private Color newColor;
  private GamePanel gamePanel;
  Game game;
  private int selectedRow, selectedCol;
  private String tileRed, tileGreen, tileBlue;
  private String playerName;
  
  public ColorDialog(JFrame parent, Game game, int selectedRow, int selectedCol, GamePanel gamePanel) {
    super(parent, "Choose Color", ModalityType.APPLICATION_MODAL);
    
    System.out.println("TOP OF COLOR DIALOG");
    
    this.game = game;
    this.selectedRow = selectedRow;
    this.selectedCol = selectedCol;
    this.gamePanel = gamePanel;
    
    // Create components
    redField = new JTextField(3);
    greenField = new JTextField(3);
    blueField = new JTextField(3);
    okButton = new JButton("OK");
    cancelButton = new JButton("CANCEL");
    tileRed = Integer.toString(game.getGrid().getTileColor(selectedRow, selectedCol).getRed());
    tileGreen = Integer.toString(game.getGrid().getTileColor(selectedRow, selectedCol).getGreen());
    tileBlue = Integer.toString(game.getGrid().getTileColor(selectedRow, selectedCol).getBlue());
    playerName = game.getCurrentPlayer().getName();
    
    System.out.println("From color dialog");
    System.out.println("RGB: " + tileRed + ", " + tileGreen + ", " + tileBlue);
    
    // Create panel and layout
    
    JPanel contentPanel = new JPanel(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    
    gbc.gridx = 0;
    gbc.gridy = 0;
    contentPanel.add(new JLabel("Current player:"), gbc);
    
    gbc.gridx = 1;
    gbc.gridy = 0;
    contentPanel.add(new JLabel(""), gbc);
    
    gbc.gridx = 2;
    gbc.gridy = 0;
    contentPanel.add(new JLabel(playerName), gbc);
    
    gbc.gridx = 0;
    gbc.gridy = 1;
    contentPanel.add(new JLabel("Red"), gbc);
    
    gbc.gridx = 1;
    gbc.gridy = 1;
    contentPanel.add(redField, gbc);
    
    gbc.gridx = 2;
    gbc.gridy = 1;
    contentPanel.add(new JLabel(tileRed), gbc);

    gbc.gridx = 0;
    gbc.gridy = 2;
    contentPanel.add(new JLabel("Green"), gbc);
    
    gbc.gridx = 1;
    gbc.gridy = 2;
    contentPanel.add(greenField, gbc);
    
    gbc.gridx = 2;
    gbc.gridy = 2;
    contentPanel.add(new JLabel(tileGreen), gbc);
    
    gbc.gridx = 0;
    gbc.gridy = 3;
    contentPanel.add(new JLabel("Blue"), gbc);
    
    gbc.gridx = 1;
    gbc.gridy = 3;
    gbc.gridwidth = 1;
    contentPanel.add(blueField, gbc);
    
    gbc.gridx = 2;
    gbc.gridy = 3;
    gbc.gridwidth = 1;
    contentPanel.add(new JLabel(tileBlue), gbc);  
    
    gbc.gridx = 0;
    gbc.gridy = 4;
    gbc.gridwidth = 1;  // Span two columns
    contentPanel.add(okButton, gbc);
    
    gbc.gridx = 1;
    gbc.gridy = 4;
    gbc.gridwidth = 1;
    contentPanel.add(new JLabel(""), gbc);
    
    gbc.gridx = 2;
    gbc.gridy = 4;
    gbc.gridwidth = 1;  // Span two columns
    contentPanel.add(cancelButton, gbc);
    
    /*
    contentPanel.add(new JLabel("Red"));
    contentPanel.add(redField);
    contentPanel.add(new JLabel("Green"));
    contentPanel.add(greenField);
    contentPanel.add(new JLabel("Blue"));
    contentPanel.add(blueField);
    contentPanel.add(new JLabel()); // Spacer
    contentPanel.add(okButton);
    contentPanel.add(cancelButton);
*/
    
    // Add listeners
    okButton.addActionListener(e -> {
      try {
        int red = Integer.parseInt(redField.getText());
        int green = Integer.parseInt(greenField.getText());
        int blue = Integer.parseInt(blueField.getText());
        
        // Input validation
        if (red < 0 || red > 255 || green < 0 || green > 255 || blue < 0 || blue > 255) {
          JOptionPane.showMessageDialog(this, "Invalid color values. Please enter values between 0 and 255");
          return;
      }
        System.out.println("RED: " + red + " Green: " + green + " Blue: " + blue);
        newColor = new Color(red, green, blue);
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
