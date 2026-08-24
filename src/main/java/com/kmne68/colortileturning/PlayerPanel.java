package com.kmne68.colortileturning;

import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

public class PlayerPanel extends VBox {
    private final Player player;
    private final boolean isPlayer1;
    private final GameController controller;

    private Label nameLabel, scoreLabel, capturedLabel, pointsLabel, turnLabel;
    private Rectangle tilePreview;
    private TextField deltaRField, deltaGField, deltaBField;
    private Button applyButton;
    private Label costPreviewLabel;

    public PlayerPanel(Player player, boolean isPlayer1, GameController controller) {
        this.player = player;
        this.isPlayer1 = isPlayer1;
        this.controller = controller;
        setSpacing(10);
        setPadding(new Insets(15));
        setStyle("-fx-background-color: " + (isPlayer1 ? "#1e3a5f" : "#5f1e1e") + 
                 "; -fx-border-color: " + (isPlayer1 ? "#4a90e2" : "#e24a4a") + 
                 "; -fx-border-width: 3; -fx-border-radius: 5;");

        // Header
        HBox header = new HBox(10);

        Label roleLabel = new Label(isPlayer1 ? "Subtractor (P1)" : "Adder (P2)");
        roleLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: yellow");
        nameLabel = new Label(player.getName() != null ? player.getName() : (isPlayer1 ? "Player 1" : "Player 2"));
        nameLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: yellow;");

        header.getChildren().addAll(roleLabel, nameLabel);
        getChildren().add(header);

        // Stats
        scoreLabel = new Label("Score: 0");
        scoreLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: yellow;");
        capturedLabel = new Label("Captured Tiles: 0");
        capturedLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: yellow;");
        pointsLabel = new Label("Points Available: 255");
        pointsLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: yellow;");
        turnLabel = new Label("");
        turnLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: yellow;");
        getChildren().addAll(scoreLabel, capturedLabel, pointsLabel, turnLabel);

        // Selected Tile Section
        VBox selectedBox = new VBox(8);
        selectedBox.setPadding(new Insets(10));
        tilePreview = new Rectangle(120, 80);
        tilePreview.setFill(Color.WHITE);

        Label selectedTileLabel = new Label("Selected Tile");
        selectedTileLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: yellow");
        selectedBox.getChildren().add(selectedTileLabel);
        selectedBox.getChildren().add(tilePreview);

        selectedTileRed();

        // RGB Deltas
        HBox deltas = new HBox(12);
        deltas.setAlignment(Pos.CENTER);

        // Red Column
        VBox redBox = new VBox(4);
        redBox.setAlignment(Pos.CENTER);
        Label redLabel = new Label("R: ");
        redLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: red;");
        deltaRField = new TextField("0");
        deltaRField.setPrefWidth(70);
        redBox.getChildren().addAll(redLabel, deltaRField);

        // Green Column
        VBox greenBox = new VBox(4);
        greenBox.setAlignment(Pos.CENTER);
        Label greenLabel = new Label("G:");
        greenLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: green;");
        deltaGField = new TextField("0");
        deltaGField.setPrefWidth(70);
        greenBox.getChildren().addAll(greenLabel, deltaGField);

        // Blue Column
        VBox blueBox = new VBox(4);
        blueBox.setAlignment(Pos.CENTER);
        Label blueLabel = new Label("B:");
        blueLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: blue");
        deltaBField = new TextField("0");
        deltaBField.setPrefWidth(70);
        blueBox.getChildren().addAll(blueLabel, deltaBField);

        deltas.getChildren().addAll(redBox, greenBox, blueBox);

        selectedBox.getChildren().add(deltas);

        applyButton = new Button("Apply Changes");
        applyButton.setOnAction(e -> applyChanges());
        costPreviewLabel = new Label("Est. Cost: 0");
        costPreviewLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: yellow;");
        selectedBox.getChildren().addAll(applyButton, costPreviewLabel);
        getChildren().add(selectedBox);


    }

    private void applyChanges() {
        try {
            int dr = Integer.parseInt(deltaRField.getText());
            int dg = Integer.parseInt(deltaGField.getText());
            int db = Integer.parseInt(deltaBField.getText());
            controller.applyRGBChanges(player, dr, dg, db);
            System.out.println("Apply called with " + dr + ", " + dg + ", " + db);
        } catch (Exception ex) {
            // Optional: show alert "Invalid input"
        }
    }

    public void updateStats(int score, int captured, int points) {
        scoreLabel.setText("Score: " + score);
        capturedLabel.setText("Captured Tiles: " + captured);
        pointsLabel.setText("Points Available: " + points);
    }

    public void setTurnActive(boolean active) {
        turnLabel.setText(active ? "YOUR TURN" : "Opponent's Turn");
    }

    public Tile selectedTileRed() {
        Tile tile = controller.getSelectedTile();
        System.out.print("OK");
        return tile;
    }

    public void updateTilePreview(int r, int g, int b) {
        tilePreview.setFill(Color.rgb(
            Math.max(0, Math.min(255, r)),
            Math.max(0, Math.min(255, g)),
            Math.max(0, Math.min(255, b))
        ));
    }
}