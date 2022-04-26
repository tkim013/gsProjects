package org.project;

import javafx.animation.PauseTransition;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.web.WebView;
import javafx.util.Duration;

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
    @FXML
    private Button resetButton;
    @FXML
    Pane pane;
    @FXML
    private Button start;
    @FXML
    private TextField hHealth;
    @FXML
    private TextField hStrength;
    @FXML
    private TextField hPosX;
    @FXML
    private TextField hPosY;
    @FXML
    private ToggleButton randomToggle;

    public MyController() {
    }

    public void initialize() {

        //set color of progress bar to red
        progressBar.setStyle("-fx-accent: red;");

        //hHealth textField input validation
        hHealth.textProperty().addListener(
                ((observableValue, s, t1) -> {
                    start.setDisable(t1.equals("")); //disables start button on empty field, enabled when valid
                    if (t1.equals("")) {
                        hHealth.clear();
                    } else if (isNumeric(t1) && Integer.parseInt(t1) > 0 && Integer.parseInt(t1) <= 1000) {
                        hHealth.setText(t1);
                    } else ((StringProperty)observableValue).setValue(s);
                })
        );

        //hStrength textField input validation
        hStrength.textProperty().addListener(
                ((observableValue, s, t1) -> {
                    start.setDisable(t1.equals("")); //disables start button on empty field, enabled when valid
                    if (t1.equals("")) {
                        hStrength.clear();
                    } else if (isNumeric(t1) && Integer.parseInt(t1) >= 0 && Integer.parseInt(t1) <= 100) {
                        hStrength.setText(t1);
                    } else ((StringProperty)observableValue).setValue(s);
                })
        );

        //hPosX textField input validation
        hPosX.textProperty().addListener(
                ((observableValue, s, t1) -> {
                    start.setDisable(t1.equals("")); //disables start button on empty field, enabled when valid
                    if (t1.equals("")) {
                        hPosX.clear();
                    } else if (isNumeric(t1) && Integer.parseInt(t1) >= 0 && Integer.parseInt(t1) <= 9) {
                        hPosX.setText(t1);
                    } else ((StringProperty)observableValue).setValue(s);
                })
        );

        //hPosY textField input validation
        hPosY.textProperty().addListener(
                ((observableValue, s, t1) -> {
                    start.setDisable(t1.equals("")); //disables start button on empty field, enabled when valid
                    if (t1.equals("")) {
                        hPosY.clear();
                    } else if (isNumeric(t1) && Integer.parseInt(t1) >= 0 && Integer.parseInt(t1) <= 9) {
                        hPosY.setText(t1);
                    } else ((StringProperty)observableValue).setValue(s);
                })
        );

        //listener to disable human x, y position input textfields on randomtoggle selection
        randomToggle.selectedProperty().addListener((observableValue, aBoolean, t1) -> {
            if (t1.equals(true)) {
                hPosX.setDisable(true);
                hPosY.setDisable(true);
            } else {
                hPosX.setDisable(false);
                hPosY.setDisable(false);
            }
        });
    }

    public static boolean isNumeric(String str) {
        if (str == null) {
            return false;
        }
        try {
            int i = Integer.parseInt(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
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
    private void bigRedButtonAction(ActionEvent e) {
        //rickroll
        mediaPlayer.pause();
        webView.getEngine().load("https://www.youtube.com/watch?v=dQw4w9WgXcQ");
        webView.setVisible(true);
        //delay 5 seconds until disableRickButton is visible
        PauseTransition visiblePause = new PauseTransition(Duration.seconds(5));
        visiblePause.setOnFinished( event -> disableRickButton.setVisible(true));
        visiblePause.play();
    }

    @FXML
    private void disableRickButtonAction(ActionEvent e) {
        //norickroll
        if (mediaPlayer != null) {
            mediaPlayer.play();
        }
        webView.getEngine().load(null);
        webView.setVisible(false);
        disableRickButton.setVisible(false);
    }

    private void loadGrassTiles() {
        //load gridPane with tile_grass image
        try {
            Image image = new Image(String.valueOf(getClass().getResource("image/tile_grass.png")));

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
    }

    private void playMusic() {
        try {
            //music
            Media media = new Media(getClass().getResource("audio/music/slow-trap-18565.mp3").toExternalForm());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setAutoPlay(true);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            mediaPlayer.setVolume(0.5);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void newGameState() {

        //fresh game state
        gridPane.getChildren().clear();
        textArea.clear();
        textArea.setText("Use WASD/arrow keys/buttons to move.");
        loadGrassTiles();
        if (mediaPlayer != null) {
            mediaPlayer.dispose();
        }
        playMusic();

        pane.setVisible(false);
        gw = new GameWorld();
        uiState = new UIState(gridPane, progressBar, textArea, hp, pos);
        h = new Human(uiState,
                Integer.parseInt(hHealth.getText()),
                Integer.parseInt(hStrength.getText()),
                randomToggle.isSelected() ?
                        new int[] {(int) (Math.random() * 10),(int) (Math.random() * 10)}
                        : new int[] {Integer.parseInt(hPosY.getText()), Integer.parseInt(hPosX.getText())});

        gw.populateGoblins(gridPane, 5);
    }

    public Button getButtonNorth() {
        return buttonNorth;
    }

    public Button getButtonSouth() {
        return buttonSouth;
    }

    public Button getButtonEast() {
        return buttonEast;
    }

    public Button getButtonWest() {
        return buttonWest;
    }
}