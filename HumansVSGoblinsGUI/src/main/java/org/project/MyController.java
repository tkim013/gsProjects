package org.project;

import javafx.animation.PauseTransition;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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
    private Group group;
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
    private MediaPlayer mediaPlayer;
    @FXML
    private Pane pane;
    private double layoutX = 200;
    private double layoutY = 200;
    private double newPosX;
    private double newPosY;
    private double xOffset;
    private double yOffset;
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
    @FXML
    private TextField gSpawn;
    @FXML
    private TextField gHealth;
    @FXML
    private TextField gStrength;
    @FXML
    private Label gameOverLabel;
    @FXML
    private Label youWinLabel;
    @FXML
    private Button restart;
    @FXML
    private Group gameOverGroup;

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

        //gSpawn textField input validation
        gSpawn.textProperty().addListener(
                ((observableValue, s, t1) -> {
                    start.setDisable(t1.equals("")); //disables start button on empty field, enabled when valid
                    if (t1.equals("")) {
                        gSpawn.clear();
                    } else if (isNumeric(t1) && Integer.parseInt(t1) >= 1 && Integer.parseInt(t1) <= 50) {
                        gSpawn.setText(t1);
                    } else ((StringProperty)observableValue).setValue(s);
                })
        );

        //gHealth textField input validation
        gHealth.textProperty().addListener(
                ((observableValue, s, t1) -> {
                    start.setDisable(t1.equals("")); //disables start button on empty field, enabled when valid
                    if (t1.equals("")) {
                        gHealth.clear();
                    } else if (isNumeric(t1) && Integer.parseInt(t1) >= 1 && Integer.parseInt(t1) <= 200) {
                        gHealth.setText(t1);
                    } else ((StringProperty)observableValue).setValue(s);
                })
        );

        //gStrength textField input validation
        gStrength.textProperty().addListener(
                ((observableValue, s, t1) -> {
                    start.setDisable(t1.equals("")); //disables start button on empty field, enabled when valid
                    if (t1.equals("")) {
                        gStrength.clear();
                    } else if (isNumeric(t1) && Integer.parseInt(t1) >= 0 && Integer.parseInt(t1) <= 30) {
                        gStrength.setText(t1);
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
        //check for input validation int datatype
        if (str == null) {
            return false;
        }
        try {
            int i = Integer.parseInt(str);
        } catch (NumberFormatException e) {
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
        group.setDisable(true);
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
        group.setDisable(false);
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
        group.setDisable(false);
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
        uiState = new UIState(gridPane, progressBar, textArea, hp, pos, gameOverGroup, gameOverLabel, youWinLabel);
        h = new Human(uiState,
                Integer.parseInt(hHealth.getText()),
                Integer.parseInt(hStrength.getText()),
                randomToggle.isSelected() ?
                        new int[] {(int) (Math.random() * 10),(int) (Math.random() * 10)}
                        : new int[] {Integer.parseInt(hPosY.getText()), Integer.parseInt(hPosX.getText())});

        gw.populateGoblins(gridPane,
                Integer.parseInt(gSpawn.getText()),
                Integer.parseInt(gHealth.getText()),
                Integer.parseInt(gStrength.getText())
        );
    }

    @FXML
    public void getOffset(MouseEvent e) {
        //mouse offset
        xOffset = e.getSceneX();
        yOffset = e.getSceneY();
    }

    @FXML
    public void setPanePos(MouseEvent e) {
        //new position of pane into layout variables for drag calculation
        layoutX = newPosX;
        layoutY = newPosY;
    }
    @FXML
    public void dragPane(MouseEvent e) {
        pane.setTranslateX(pane.getTranslateX()
                + e.getX()
                - xOffset
                + layoutX);
        newPosX = pane.getLayoutX() + pane.getTranslateX();
        pane.setTranslateY(pane.getTranslateY()
                + e.getY()
                - yOffset
                + layoutY);
        newPosY = pane.getLayoutY() + pane.getTranslateY();

        //alternate code for initialize()
//        pane.setOnMouseDragged(event -> {
//            pane.setTranslateX(pane.getTranslateX()
//                    + event.getX()
//                    - xOffset
//                    + layoutX);
//            newPosX = pane.getLayoutX() + pane.getTranslateX();
//            pane.setTranslateY(pane.getTranslateY()
//                    + event.getY()
//                    - yOffset
//                    + layoutY);
//            newPosY = pane.getLayoutY() + pane.getTranslateY();
//        });
    }

    @FXML
    private void restart() {
        gameOverLabel.setVisible(false);
        youWinLabel.setVisible(false);
        gameOverGroup.setVisible(false);
        pane.setVisible(true);
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