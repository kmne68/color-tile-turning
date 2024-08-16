/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kmne68.colortilegame;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
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
  
  public ColorDialog(JFrame parent, Game game, int selectedRow, int selectedCol, GamePanel gamePanel) {
    super(parent, "Choose Color", ModalityType.APPLICATION_MODAL);
    
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
    
    // Create panel and layout
    
    System.out.println("BEFORE JPANEL");
    
//    JPanel contentPanel = new JPanel(new GridLayout(4, 2));
    JPanel contentPanel = new JPanel(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    
    gbc.gridx = 0;
    gbc.gridy = 0;
    contentPanel.add(new JLabel("Red"), gbc);
    gbc.gridx = 1;
    gbc.gridy = 0;
    contentPanel.add(redField, gbc);
    
    gbc.gridx = 0;
    gbc.gridy = 1;
    contentPanel.add(new JLabel("Green"), gbc);
    gbc.gridx = 1;
    gbc.gridy = 1;
    contentPanel.add(greenField, gbc);

    gbc.gridx = 0;
    gbc.gridy = 2;
    contentPanel.add(new JLabel("Blue"), gbc);
    gbc.gridx = 1;
    gbc.gridy = 2;
    contentPanel.add(blueField, gbc);  
    
    gbc.gridx = 0;
    gbc.gridy = 3;
    gbc.gridwidth = 1;  // Span two columns
    contentPanel.add(okButton, gbc);
    
    gbc.gridx = 1;
    gbc.gridy = 3;
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
        newColor = new Color(red, green, blue);
        game.changeTileColor(selectedRow, selectedCol, newColor);
        gamePanel.repaint();
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
