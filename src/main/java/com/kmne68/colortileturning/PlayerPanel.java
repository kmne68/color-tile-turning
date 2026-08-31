package com.kmne68.colortileturning;

import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import java.util.*;

public class PlayerPanel extends VBox {
    private final Player player;
    private final boolean isPlayer1;
    private final GameController controller;

    private Label nameLabel, scoreLabel, capturedLabel, pointsLabel, turnLabel;
    private Rectangle tilePreview;
    private TextField deltaRField, deltaGField, deltaBField;
    private Button applyButton;
    private Label costPreviewLabel;
    private TextField currentRField;
    private TextField currentGField;
    private TextField currentBField;
    private TextField rDiffFromTarget;
    private TextField gDiffFromTarget;
    private TextField bDiffFromTarget;
    private TextField tempTotal;

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

        selectedTile();

        // RGB Deltas
        HBox deltas = new HBox(12);
        deltas.setAlignment(Pos.CENTER);

        // Labels Column
        VBox labelsBox = new VBox(4);
        labelsBox.setAlignment(Pos.CENTER_LEFT);

        Label spacer = new Label("");
        Label deltaLabel = new Label("Delta");
        deltaLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: yellow;");
        Label currentLabel = new Label("Selected");
        currentLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: yellow;");
        Label targetLabel = new Label("To Target");
        targetLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: yellow;");

        labelsBox.getChildren().addAll(spacer, deltaLabel, currentLabel, targetLabel);

        // Red Column
        VBox redBox = new VBox(4);
        redBox.setAlignment(Pos.CENTER);
        Label redLabel = new Label("R: ");
        redLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: red;");
        deltaRField = new TextField("0");
        deltaRField.setPrefWidth(70);
        currentRField = new TextField("-");
        currentRField.setPrefWidth(70);
        currentRField.setEditable(false);
        currentRField.setFocusTraversable(false);
        rDiffFromTarget = new TextField("-");
        rDiffFromTarget.setPrefWidth(70);
        rDiffFromTarget.setEditable(false);
        rDiffFromTarget.setFocusTraversable(false);
        redBox.getChildren().addAll(redLabel, deltaRField, currentRField, rDiffFromTarget);

        // Green Column
        VBox greenBox = new VBox(4);
        greenBox.setAlignment(Pos.CENTER);
        Label greenLabel = new Label("G:");
        greenLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: green;");
        deltaGField = new TextField("0");
        deltaGField.setPrefWidth(70);
        currentGField = new TextField("-");
        currentGField.setPrefWidth(70);
        currentGField.setEditable(false);
        currentGField.setFocusTraversable(false);
        gDiffFromTarget = new TextField("-");
        gDiffFromTarget.setPrefWidth(70);
        gDiffFromTarget.setEditable(false);
        gDiffFromTarget.setFocusTraversable(false);        
        greenBox.getChildren().addAll(greenLabel, deltaGField, currentGField, gDiffFromTarget);

        // Blue Column
        VBox blueBox = new VBox(4);
        blueBox.setAlignment(Pos.CENTER);
        Label blueLabel = new Label("B:");
        blueLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: blue");
        deltaBField = new TextField("0");
        deltaBField.setPrefWidth(70);
        currentBField = new TextField("-");
        currentBField.setPrefWidth(70);
        currentBField.setEditable(false);
        currentBField.setFocusTraversable(false);
        bDiffFromTarget = new TextField("-");
        bDiffFromTarget.setPrefWidth(70);
        bDiffFromTarget.setEditable(false);
        bDiffFromTarget.setFocusTraversable(false);
        blueBox.getChildren().addAll(blueLabel, deltaBField, currentBField, bDiffFromTarget);

        VBox totalVBox = new VBox(4);
        totalVBox.setAlignment(Pos.CENTER);
        Label totalLabel = new Label("Total");
        totalLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: yellow");
        tempTotal = new TextField("255");
        tempTotal.setPrefWidth(70);
        tempTotal.setEditable(false);

        totalVBox.getChildren().addAll(totalLabel, tempTotal);

//        HBox totalHBox = new HBox(8, totalLabel, tempTotal);
//        selectedBox.getChildren().add(totalHBox);

        Runnable updateTotal = () -> {
            try {
                int dr = Math.abs(Integer.parseInt(deltaRField.getText()));
                int dg = Math.abs(Integer.parseInt(deltaGField.getText()));
                int db = Math.abs(Integer.parseInt(deltaBField.getText()));
                int cost = dr + dg + db;

                int available = player.getPointsAvailable();
                int remaining = available - cost;

                tempTotal.setText(String.valueOf(remaining));

                if (remaining < 0) {
                    tempTotal.setStyle("-fx-text-fill: red");
                } else {
                    tempTotal.setStyle("-fx-text-fill: black"); 
                }
            } catch (NumberFormatException ex) {
                tempTotal.setText("?");
            }
        };

        deltaRField.textProperty().addListener((obs, oldVal, newVal) -> updateTotal.run());
        deltaGField.textProperty().addListener((obs, oldVal, newVal) -> updateTotal.run());
        deltaBField.textProperty().addListener((obs, oldVal, newVal) -> updateTotal.run());
        deltas.getChildren().addAll(labelsBox, redBox, greenBox, blueBox, totalVBox);

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
            currentRField.setText("-");
            currentGField.setText("-");
            currentBField.setText("-");
            deltaRField.setText(String.valueOf(0));
            deltaGField.setText(String.valueOf(0));
            deltaBField.setText(String.valueOf(0));
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

    public Tile selectedTile() {
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
        // Display current RGB values
        currentRField.setText(String.valueOf(r));
        currentGField.setText(String.valueOf(g));
        currentBField.setText(String.valueOf(b));

        rDiffFromTarget.setText(String.valueOf(Math.abs(player.getTargetRGB()[0] - r)));
        gDiffFromTarget.setText(String.valueOf(Math.abs(player.getTargetRGB()[0] - g)));
        bDiffFromTarget.setText(String.valueOf(Math.abs(player.getTargetRGB()[0] - b)));
    }
}
