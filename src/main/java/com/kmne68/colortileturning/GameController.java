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
    private Label statusBar;

    public GameController(Game game, ColorTileApp app, Label statusBar) {
        this.game = game;
        this.app = app;
        this.statusBar = statusBar;
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

        // System.out.println("Tile clicked");
        // Optional: add text for RGB values
        return pane;
    }

    public void onTileClicked(Tile tile) {
        this.selectedTile = tile;
        boolean locked = tile.isLocked();
        Player active = game.getCurrentPlayer();

        if (active.isPlayer1() && p1Panel != null) {
            p1Panel.updateTilePreview(tile.getR(), tile.getG(), tile.getB(), locked);
        } else if (p2Panel != null) {
            p2Panel.updateTilePreview(tile.getR(), tile.getG(), tile.getB(), locked);
        }
        System.out.println("Selected tile: " + tile);
        // Update status if needed
    }

    public void applyRGBChanges(Player player, int dr, int dg, int db) {

        System.out.println("Apply RGB changes called with " + dr + ", " + dg + ", " + db);
        
        if (selectedTile == null || !game.isPlayerTurn(player)) {
            System.out.println("selectedTile = " + selectedTile);
            System.out.println("panel player = " + player);
            System.out.println("current player = " + game.getCurrentPlayer());
            System.out.println("isPlayerTurn = " + game.isPlayerTurn(player));
        return;
        }

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

        if ((player.isPlayer1() && newR == 0 && newG == 0 && newB == 0) ||
            (!player.isPlayer1() && newR == 255 && newG == 255 && newB == 255)) {
            selectedTile.setLocked(true);
            player.incrementCapturedTiles(1);
            System.out.println("LOCKED bottom: " + selectedTile.isLocked());
            selectedTile.setTileOwner(player.isPlayer1() ? "P1" : "P2");
        }

        System.out.println("JUST BEFORE player.spendPoints(cost) is called");       

        player.spendPoints(cost);
        // player.spendPoints(selectedTile, dr, dg, db); //  cost);   // THIS HAS TO BE GETTING CALLED BECAUSE THE NEXT LINE EXECUTES

        System.out.println("Points after spend: " + player.getPointsAvailable());

        if (player.getPointsAvailable() <= 0) {
            System.out.println("Calling endTurn()");   // WE'RE MAKING IT TO HERE BUT NOT CALLING TILE.ADJUSTRGB or PLAYER.SPENDPOINTS
            endTurn();
        }

        game.checkCapture(selectedTile, player);  // Implement in Game/Board if not present

        refreshBoard();
        refreshPanels();
        
        /*
        if (player.getPointsAvailable() <= 0) {
            endTurn();
        }
        */
    }

    private int clamp(int val, int min, int max) {
        return Math.max(min, Math.min(max, val));
    }

    public void endTurn() {
        // game.switchTurn();
        game.nextTurn();
        // Player next = game.getCurrentPlayer();
        // int bonus = game.calculateContiguousBonus(next);  // Implement adjacency
        // next.addPoints(255 + next.getCapturedTiles() + bonus);
        refreshPanels();
        refreshStatus();
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

    public void refreshStatus() {
        String name = game.getCurrentPlayer().isPlayer1() ? "Player 1" : "Player 2";
        int points = game.getCurrentPlayer().getPointsAvailable();
        updateStatus(name + "'s Turn | Points: " + game.getCurrentPlayer().getPointsAvailable() + " | Select a tile");
    }

    public void startNewGame(int rows, int columns, int rounds) {
        refreshStatus();    
        game = new Game(rows, columns, rounds);
        // Rebuild UI via app if needed
        refreshUI();
    }

    public void beginFirstTurn() {
        Player p1 = game.getPlayer1();
        int bonus = game.calculateContiguousBonus(p1);
     //   p1.addPoints(p1.getPointsAvailable() + p1.getCapturedTiles() + bonus);
        refreshPanels();
        refreshStatus();
    }

    public Tile getSelectedTile() {
        return this.selectedTile;
    }



    public void updateStatus(String message) {
        if (statusBar != null) {
            statusBar.setText(message);
        }
    }
}