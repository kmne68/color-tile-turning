package com.kmne68.colortileturning;

import java.util.Random;

/**
 * Manages the game board grid and spatial calculations.
 */
public class Board {
    private final Tile[][] grid;
    private final int rows;
    private final int cols;

    public Board(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.grid = new Tile[rows][cols];
        initializeBoard();
    }

    private void initializeBoard() {
        Random rand = new Random();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = new Tile(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256), i, j);
            }
        }
    }

    public Tile getTile(int row, int col) {
        if (row < 0 || row >= rows || col < 0 || col >= cols) {
            return null;
        }
        return grid[row][col];
    }

    /**
     * Calculates contiguous bonus for a player (orthogonal shared sides).
     */
public int calculateContiguousBonus(Player player) {
    String owner = player.isPlayer1() ? "P1" : "P2";
    int bonus = 0;

    for (int row = 0; row < rows; row++) {
        for (int col = 0; col < cols; col++) {
            Tile tile = grid[row][col];
            if (tile == null || !owner.equals(tile.getTileOwner())) {
                continue;
            }
            Tile right = getTile(row, col + 1);
            Tile below = getTile(row + 1, col);
            if (right != null && owner.equals(right.getTileOwner())) {
                bonus++;
            }
            if (below != null && owner.equals(below.getTileOwner())) {
                bonus++;
            }
        }
    }
    return bonus;
}

    public int getRows() { return rows; }
    public int getCols() { return cols; }

    public Tile[][] getTiles() { return grid; } // Your 2D array
}
