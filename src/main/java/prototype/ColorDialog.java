/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prototype;

import prototype.ColorTileGame;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author kmne6
 */
public class ColorDialog extends JDialog {
  
  private final ColorTileGame mainPanel;
  private final JLabel redLabel, greenLabel, blueLabel;
  private final JSpinner redSpinner, greenSpinner, blueSpinner;
  private final int rowIndex, columnIndex;
  
  public ColorDialog(JFrame parent, Color color, int rowIndex, int columnIndex, ColorTileGame mainPanel) {
    super(parent, "Color Information", ModalityType.MODELESS);
    // super(parent, "Color Information", ModalityType.APPLICATION_MODAL);
    
    this.mainPanel = mainPanel;
    this.rowIndex = rowIndex;
    this.columnIndex = columnIndex;
    
    
    JPanel contentPanel = new JPanel(new GridLayout(5, 2));
    contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
    
    // Create labels and spinners for RGB values
    redLabel = new JLabel("Red: ");
    redLabel.setForeground(Color.RED);
    
    greenLabel = new JLabel("Green: ");
    greenLabel.setForeground(Color.GREEN);
    
    blueLabel = new JLabel("Blue: ");
    blueLabel.setForeground(Color.BLUE);
    
    SpinnerNumberModel redModel = new SpinnerNumberModel(color.getRed(), 0, 255, 1);
    SpinnerNumberModel greenModel = new SpinnerNumberModel(color.getGreen(), 0, 255, 1);
    SpinnerNumberModel blueModel = new SpinnerNumberModel(color.getBlue(), 0, 255, 1);
  
    redSpinner = new JSpinner(redModel);
    greenSpinner = new JSpinner(greenModel);
    blueSpinner = new JSpinner(blueModel);
    
    redSpinner.addChangeListener(e -> updateColor());
    greenSpinner.addChangeListener(e -> updateColor());
    blueSpinner.addChangeListener(e -> updateColor());
        
    // Crate a Cancel button
    JButton cancelButton = new JButton("Cancel");
    cancelButton.addActionListener(e -> {
      setVisible(false);
      dispose();
    });
    
    // Create an OK button to close the dialog
    JButton okButton = new JButton("OK");
    okButton.addActionListener(e -> {
      updateColor();
      setVisible(false);
      dispose();
    });
    
    // Layout components with a simple grid
    setLayout(new GridLayout(3, 2));
    contentPanel.add(redLabel);
    contentPanel.add(redSpinner);
    contentPanel.add(greenLabel);
    contentPanel.add(greenSpinner);
    contentPanel.add(blueLabel);
    contentPanel.add(blueSpinner);
    contentPanel.add(new JPanel());
    contentPanel.add(new JPanel());
    contentPanel.add(cancelButton);
    contentPanel.add(okButton);
    
    // TODO: add color to the labels
    
    add(contentPanel);
    
    pack();
    setLocationRelativeTo(parent);
    setVisible(true);
    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
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
  
  private void updateColor() {
    Color newColor = new Color((int) redSpinner.getValue(), (int) greenSpinner.getValue(), (int) blueSpinner.getValue());
    mainPanel.updateGridColor(rowIndex, columnIndex, newColor);
  }
  
}
