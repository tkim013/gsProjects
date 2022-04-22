package org.project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.web.WebView;

public class MyController {

    Human h;
    GameWorld gw;
    UIState uiState;

    @FXML
    private GridPane gridPane;

    @FXML
    private Button buttonNorth;

    @FXML
    private Button buttonSouth;

    @FXML
    private Button buttonEast;

    @FXML
    private Button buttonWest;

    @FXML
    private Button bigRedButton;

    @FXML
    private TextArea textArea;

    @FXML
    private Label hp;

    @FXML
    private Label pos;

    @FXML
    private ProgressBar progressBar;

    @FXML
    private WebView webView;

    public MyController() {
    }

    public void initialize() {

        //set color of progress bar to red
        progressBar.setStyle("-fx-accent: red;");
        //load gridPane with tile_grass image
        try {
            Image image = new Image("file:src/main/resources/org/project/tile_grass.png");

            for (int i = 0; i < GameWorld.col; i++) {
                for (int j = 0; j < GameWorld.row; j++) {
                    ImageView imageView = new ImageView(image);
                    imageView.setFitHeight(60);
                    imageView.setFitWidth(60);
                    gridPane.add(imageView, i, j);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        gw = new GameWorld();
        uiState = new UIState(gridPane, progressBar, textArea, hp, pos);
        h = new Human(uiState, 50, 5, new int[] {(int) (Math.random() * 10),(int) (Math.random() * 10)});

        gw.populateGoblins(gridPane, 5);
    }

    @FXML
    public void northButtonAction(ActionEvent e) {
        h.move(uiState, "n");
    }

    @FXML
    public void southButtonAction(ActionEvent e) {
        h.move(uiState, "s");
    }

    @FXML
    public void eastButtonAction(ActionEvent e) {
        h.move(uiState, "e");
    }

    @FXML
    public void westButtonAction(ActionEvent e) {
        h.move(uiState, "w");
    }

    @FXML
    public void bigRedButtonAction(ActionEvent e) {
        webView.getEngine().load("https://www.youtube.com/watch?v=dQw4w9WgXcQ");
        webView.setVisible(true);
    }
}