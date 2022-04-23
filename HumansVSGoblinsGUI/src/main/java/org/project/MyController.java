package org.project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.web.WebView;

import java.io.File;

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
    private Button disableRickButton;

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

    @FXML
    private MediaPlayer mediaPlayer;

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

            //music
            Media media = new Media(new File("src/main/resources/org/project/slow-trap-18565.mp3").toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setAutoPlay(true);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            mediaPlayer.setVolume(0.6);
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
        mediaPlayer.pause();
        webView.getEngine().load("https://www.youtube.com/watch?v=dQw4w9WgXcQ");
        webView.setVisible(true);
        disableRickButton.setVisible(true);
    }

    @FXML
    public void disableRickButtonAction(ActionEvent e) {
        mediaPlayer.play();
        webView.getEngine().load(null);
        webView.setVisible(false);
        disableRickButton.setVisible(false);
    }
}