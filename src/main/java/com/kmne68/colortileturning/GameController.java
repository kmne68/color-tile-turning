package com.kmne68.colortileturning;

import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.util.*;

public class GameController {
    private Game game;
    private ColorTileApp app;
    private PlayerPanel p1Panel, p2Panel;
    private Tile selectedTile;
    private GridPane boardGrid;

    public GameController(Game game, ColorTileApp app) {
        this.game = game;
        this.app = app;
    }

    public void setPanels(PlayerPanel p1, PlayerPanel p2) {
        this.p1Panel = p1;
        this.p2Panel = p2;
    }

    public GridPane createBoardGrid() {
        GridPane grid = new GridPane();
        grid.setHgap(3);
        grid.setVgap(3);
        Tile[][] tiles = game.getBoard().getTiles();  // Adjust to your Board model
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                StackPane tileView = createTileVisual(tiles[i][j]);
                final Tile tile = tiles[i][j];
                tileView.setOnMouseClicked(e -> onTileClicked(tile));
                grid.add(tileView, j, i);
            }
        }
        this.boardGrid = grid;
        return grid;
    }

    private StackPane createTileVisual(Tile tile) {
        StackPane pane = new StackPane();
        Rectangle rect = new Rectangle(55, 55);
        rect.setFill(Color.rgb(tile.getR(), tile.getG(), tile.getB()));
        pane.getChildren().add(rect);

        System.out.println("Tile clicked");
        // Optional: add text for RGB values
        return pane;
    }

    public void onTileClicked(Tile tile) {
        this.selectedTile = tile;
        Player active = game.getCurrentPlayer();
        if (active.isPlayer1() && p1Panel != null) {
            p1Panel.updateTilePreview(tile.getR(), tile.getG(), tile.getB());
        } else if (p2Panel != null) {
            p2Panel.updateTilePreview(tile.getR(), tile.getG(), tile.getB());
        }
        System.out.println("Selected tile: " + tile);
        // Update status if needed
    }

    public void applyRGBChanges(Player player, int dr, int dg, int db) {

        System.out.println("Apply RGB changes called with " + dr + ", " + dg + ", " + db);

        
        if (selectedTile == null || !game.isPlayerTurn(player)) return;

        System.out.println("Applying RGB changes: " + dr + ", " + dg + ", " + db);

        int cost = Math.abs(dr) + Math.abs(dg) + Math.abs(db);

        System.out.println("Cost: " + cost);

        if (player.getPointsAvailable() < cost) {
            // Show alert "Not enough points"
            System.out.println("Not enough points");
            return;
        }

        // Apply (P1 subtract, P2 add)
        int newR = clamp(selectedTile.getR() + (player.isPlayer1() ? -dr : dr), 0, 255);
        int newG = clamp(selectedTile.getG() + (player.isPlayer1() ? -dg : dg), 0, 255);
        int newB = clamp(selectedTile.getB() + (player.isPlayer1() ? -db : db), 0, 255);

        System.out.println("New RGB: " + newR + ", " + newG + ", " + newB);

        selectedTile.setRGB(newR, newG, newB);
        player.spendPoints(cost);

        game.checkCapture(selectedTile, player);  // Implement in Game/Board if not present

        refreshBoard();
        refreshPanels();
        
        if (player.getPointsAvailable() <= 0) {
            endTurn();
        }
    }

    private int clamp(int val, int min, int max) {
        return Math.max(min, Math.min(max, val));
    }

    public void endTurn() {
        game.switchTurn();
        Player next = game.getCurrentPlayer();
        int bonus = game.calculateContiguousBonus(next);  // Implement adjacency
        next.addPoints(255 + next.getCapturedTiles() + bonus);
        refreshPanels();
        // Check win
    }

private void refreshBoard() {
    if (boardGrid == null) return;
    boardGrid.getChildren().clear();  // Clear old tiles
    // Rebuild with updated tiles
    Tile[][] tiles = game.getBoard().getTiles();
    for (int i = 0; i < tiles.length; i++) {
        for (int j = 0; j < tiles[i].length; j++) {
            StackPane tileView = createTileVisual(tiles[i][j]);
            final Tile tile = tiles[i][j];
            tileView.setOnMouseClicked(e -> onTileClicked(tile));
            boardGrid.add(tileView, j, i);
        }
    }
    System.out.println("Board refreshed with new colors");
}

    public void refreshUI() {
        refreshPanels();
    }

    private void refreshPanels() {
        if (p1Panel != null) p1Panel.updateStats(game.getPlayer1().getScore(), game.getPlayer1().getCapturedTiles(), game.getPlayer1().getPointsAvailable());
        if (p2Panel != null) p2Panel.updateStats(game.getPlayer2().getScore(), game.getPlayer2().getCapturedTiles(), game.getPlayer2().getPointsAvailable());
    }

    public void startNewGame(int rows, int columns, int rounds) {
        game = new Game(rows, columns, rounds);
        // Rebuild UI via app if needed
        refreshUI();
    }

    public Tile getSelectedTile() {
        return this.selectedTile;
    }
}