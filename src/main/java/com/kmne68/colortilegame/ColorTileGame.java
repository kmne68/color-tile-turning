/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.kmne68.colortilegame;

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

    public ColorTileGame() {
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

            }
        });
    }
// public class GridDrawer extends JPanel {
    private static final int numRows = 25;
    private static final int numCols = 25;
    private static final int blockSize = 16; // Assuming upload image icon size is 16x16 pixels

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw horizontal lines
        for (int i = 0; i <= numRows; i++) {
            g.drawLine(0, i * blockSize, numCols * blockSize, i * blockSize);

            // Draw vertical lines
            for (int j = 0; j <= numCols; j++) {
                Color randomColor = new Color((int) (Math.random() * 256), (int) (Math.random() * 256), (int) (Math.random() * 256));
                g.setColor(randomColor);
                g.drawLine(j * blockSize, 0, j * blockSize, numRows * blockSize);
                g.fillRect(j * blockSize, i * blockSize, blockSize, blockSize);
            }
        }

        System.out.println("LEAVING PAINT");
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            System.out.println("EDT: " + SwingUtilities.isEventDispatchThread());

            JFrame frame = new JFrame("Grid");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            ColorTileGame grid = new ColorTileGame();
            frame.add(grid);
            frame.setSize(numCols * blockSize, numRows * blockSize); // Add some padding
            frame.setVisible(true);
        });

    }
}
