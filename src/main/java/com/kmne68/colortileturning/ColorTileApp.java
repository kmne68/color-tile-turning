/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kmne68.colortileturning;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author kmne6
 */
public class Main extends Application {
  
  private Game game;
  private GridPane gridPane;
  private Label statusLabel;
  
  @Override
   public void start(Stage primaryStage) {
        game = new Game(5, 5, 12); // 5x5 grid, 12 rounds
        gridPane = new GridPane();
        statusLabel = new Label("Player " + (game.getCurrentPlayer()
                .getTargetRGB()[0] == 0 ? "1" : "2") + ": " + 
                game.getCurrentPlayer().getPoints() + " points");

        // Create colored buttons
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                Tile tile = game.getBoard().getTile(i, j);
                Button button = createTileButton(i, j, tile);
                gridPane.add(button, j, i);
            }
        }

        VBox root = new VBox(10, statusLabel, gridPane);
        Scene scene = new Scene(root, 400, 450);
        primaryStage.setTitle("Color Tile Turning");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
  
  
  private Button createTileButton(int row, int col, Tile tile) {
    int[] rgb = tile.getRGB();
    Button button = new Button();
    button.setStyle("-fx-background-color: rgb(" + rgb[0] + ", " + rgb[1] + ", " + rgb[2] + ");");
    button.setPrefSize(80, 80);
    if (!tile.isLocked()) {
      button.setOnAction(e -> openRGBDialog(row, col, tile));
    }
    return button;
  }
  
  
  private void openRGBDialog(int row, int col, Tile tile) {
    Player currentPlayer = game.getCurrentPlayer();
    Stage dialog = new Stage();
    dialog.initModality(Modality.APPLICATION_MODAL);
    dialog.setTitle("Adjust RGB");
    
    TextField rField = new TextField("0");
    TextField gField = new TextField("0");
    TextField bField = new TextField("0");
    Button submit = new Button("Apply");
    submit.setOnAction(e -> {
      try {
        int rDelta = Integer.parseInt(rField.getText());
        int gDelta = Integer.parseInt(gField.getText());
        int bDelta = Integer.parseInt(bField.getText());
        currentPlayer.spendPoints(tile, rDelta, gDelta, bDelta);
        updateGrid();
        statusLabel.setText("Player " + (game.getCurrentPlayer().getTargetRGB()[0] == 0 ? "1" : "2") + ": " + currentPlayer.getPoints() + " points");
        game.nextTurn();
        dialog.close();
      } catch (NumberFormatException ex) {
        
      }
    });
  
  
    VBox dialogPane = new VBox(10, new Label("R Delta:"), rField, new Label("G Delta:"), gField, new Label("B Delta:"), bField, submit);
    Scene dialogScene = new Scene(dialogPane, 200, 350);
    dialog.setScene(dialogScene);
    dialog.showAndWait();
  }
  
  
  private void updateGrid() {
    gridPane.getChildren().clear();
    for(int i = 0; i < 5; i++) {
      for(int j = 0; j < 5; j++) {
        Tile tile = game.getBoard().getTile(i, j);
        gridPane.add(createTileButton(i, j, tile), j, i);
      }
    }
  }
  public static void main(String[] args) {
    launch(args);
  }
}
