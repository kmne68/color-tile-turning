/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kmne68.utils;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

/**
 *
 * @author kmne6
 */
public class ColorDialog extends JDialog {
  
  private final JLabel redLabel, greenLabel, blueLabel;
  private final JSpinner redSpinner, greenSpinner, blueSpinner;
  
  public ColorDialog(JFrame parent, Color color) {
    super(parent, "Color Information", ModalityType.APPLICATION_MODAL);
    
    // Create labels and spinners for RGB values
    redLabel = new JLabel("Red: ");
    greenLabel = new JLabel("Green: ");
    blueLabel = new JLabel("Blue: ");
    
    SpinnerNumberModel redModel = new SpinnerNumberModel(color.getRed(), 0, 255, 1);
    SpinnerNumberModel greenModel = new SpinnerNumberModel(color.getGreen(), 0, 255, 1);
    SpinnerNumberModel blueModel = new SpinnerNumberModel(color.getBlue(), 0, 255, 1);
  
    redSpinner = new JSpinner(redModel);
    greenSpinner = new JSpinner(greenModel);
    blueSpinner = new JSpinner(blueModel);
    
    // Layout components with a simple grid
    setLayout(new GridLayout(3, 2));
    add(redLabel);
    add(redSpinner);
    add(greenLabel);
    add(greenSpinner);
    add(blueLabel);
    add(blueSpinner);
    
    pack();
    setLocationRelativeTo(parent);
    setVisible(true);
  }
  
  // Getters for spinner values
  public int getRed() {
    return (int) redSpinner.getValue();
  }
  
  public int getGreen() {
    return (int) greenSpinner.getValue();
  }
  
  public int getBlue() {
    return (int) blueSpinner.getValue();
  }
  
  public Color getNewColor() {
    int red = (int)  redSpinner.getValue();
    int green = (int) greenSpinner.getValue();
    int blue = (int) blueSpinner.getValue();
    return new Color(red, green, blue);
  }
  
}
