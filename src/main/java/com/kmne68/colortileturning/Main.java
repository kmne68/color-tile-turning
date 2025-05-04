/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kmne68.colortileturning;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author kmne6
 */
public class Main extends Application {
  @Override
  public void start(Stage primaryStage) {
    Game game = new Game(5, 5, 12);
    GridPane gridPane = new GridPane();
    
    // Populate grid with placeholder buttons
    for(int i = 0; i < 5; i++) {
      for(int j = 0; j < 5; j++) {
        gridPane.add(new javafx.scene.control.Button("Tile"), j, i);
      }
    }
    
    Scene scene = new Scene(gridPane, 400, 400);
    primaryStage.setTitle("Color Tile Turning");
    primaryStage.setScene(scene);
    primaryStage.show();
  }
  
  
  public static void main(String[] args) {
    launch(args);
  }
}
