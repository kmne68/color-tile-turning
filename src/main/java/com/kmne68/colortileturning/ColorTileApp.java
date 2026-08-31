package com.kmne68.colortileturning;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.scene.paint.Color;

public class ColorTileApp extends Application {
    private GameController controller;
    private Game game;
    private Label statusBar;

    @Override
    public void start(Stage primaryStage) {
        game = new Game(5, 5, 10);  // Your model init - adjust size
        statusBar = new Label("...");
        controller = new GameController(game, this, statusBar);

        BorderPane root = new BorderPane();

        // Top
        MenuBar menuBar = createMenuBar();
        HBox toolbar = createToolbar();
        VBox top = new VBox(menuBar, toolbar);
        top.setStyle("-fx-background-color: #222;");
        root.setTop(top);

        // Center Board
        GridPane boardGrid = controller.createBoardGrid();
        boardGrid.setPadding(new Insets(20));
        root.setCenter(boardGrid);

        // Left Player 1
        PlayerPanel p1Panel = new PlayerPanel(game.getPlayer1(), true, controller);
        p1Panel.setMinWidth(500);
        p1Panel.setPrefWidth(500);
        p1Panel.setStyle("-fx-background-color: #1e3a5f; -fx-border-color: #4a90e2; -fx-border-width: 3;");
        root.setLeft(p1Panel);

        // Right Player 2
        PlayerPanel p2Panel = new PlayerPanel(game.getPlayer2(), false, controller);
        p2Panel.setMinWidth(500);
        p2Panel.setPrefWidth(500);
        p2Panel.setStyle("-fx-background-color: #5f1e1e; -fx-border-color: #e24a4a; -fx-border-width: 3;");
        root.setRight(p2Panel);

        // Bottom Status
        // TO DO: make the label content dyamic
        Label status = new Label("");
     //   status.setText("Player: " + game.getCurrentPlayer().getName());
        status.setPadding(new Insets(10));
        status.setStyle("-fx-background-color: #333; -fx-text-fill: white;");
        root.setBottom(statusBar);

        controller.setPanels(p1Panel, p2Panel);

        Scene scene = new Scene(root, 1550, 850);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Color Tile Turning");
        primaryStage.show();

        // controller.startNewGame(5, 5, 10);
        controller.refreshStatus();
        controller.refreshUI();  // Force update
        controller.beginFirstTurn();
    }

    // (Keep your createMenuBar and createToolbar methods here - unchanged)
    private MenuBar createMenuBar() { /* your code */ return new MenuBar(); }
    private HBox createToolbar() { /* your code */ return new HBox(); }

    public static void main(String[] args) { launch(args); }
}