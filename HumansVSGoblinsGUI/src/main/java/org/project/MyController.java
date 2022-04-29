package org.project;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
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
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.web.WebView;
import javafx.util.Duration;

import java.io.File;
import java.util.*;

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
    private Media media;
    @FXML
    private Label musicLabel;
    @FXML
    private ProgressBar musicProgressBar;
    @FXML
    private Button playButton;
    @FXML
    private Button pauseButton;
    @FXML
    private Button nextButton;
    @FXML
    private Button prevButton;
    private File musicDir;
    private File[] files;
    private List<File> musicList;
    private int musicNumber;
    private Timer timer;
    private TimerTask task;
    private boolean running;
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
    @FXML
    private Slider vSlider;
    @FXML
    private ToggleButton bRedButtonLock;
    @FXML
    private TextField bRedTextLock;
    @FXML
    private Label dnp;
    private FadeTransition fadeTransition;
    private AudioClip audioClip;

    public MyController() {
    }

    public void initialize() {

        //set color of progress bar to red
        progressBar.setStyle("-fx-accent: red;");

        //load music player
        try {
            musicList = new ArrayList<>();
//            musicList = Arrays.asList(new File(getClass().getResource("audio/music").toURI()).listFiles());

            musicDir = new File(getClass().getResource("audio/music").toURI());
            files = musicDir.listFiles();
            if (files != null) {
                for (File file : files) {
                    musicList.add(file);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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
                if (hPosX.getText().isEmpty()) {
                    hPosX.setText("0");
                }
                if (hPosY.getText().isEmpty()) {
                    hPosY.setText("0");
                }
                hPosX.setDisable(true);
                hPosY.setDisable(true);
            } else {
                hPosX.setDisable(false);
                hPosY.setDisable(false);
            }
        });

        //listener for togglebutton
        bRedButtonLock.selectedProperty().addListener((observableValue, aBoolean, t1) -> {
            fadeTransition = new FadeTransition(Duration.seconds(0.8), dnp);
            fadeTransition.setFromValue(1.0);
            fadeTransition.setToValue(0.7);
            fadeTransition.setCycleCount(Animation.INDEFINITE);

            if (t1.equals(true)) {
                bigRedButton.setDisable(false);
                dnp.setVisible(true);
                fadeTransition.play();
                playKlaxon();
                bRedButtonLock.setText("UNLOCKED");
                bRedButtonLock.setTextFill(Color.RED);
            } else {
                bigRedButton.setDisable(true);
                dnp.setVisible(false);
                fadeTransition.stop();
                audioClip.stop();
                bRedButtonLock.setText("LOCKED");
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
        fadeTransition.stop();
        group.setDisable(true);
        webView.getEngine().load("https://www.youtube.com/watch?v=dQw4w9WgXcQ");
        webView.setVisible(true);
                audioClip.stop();
        //delay 5 seconds until disableRickButton is visible
        PauseTransition visiblePause = new PauseTransition(Duration.seconds(5));
        visiblePause.setOnFinished( event -> disableRickButton.setVisible(true));
        visiblePause.play();
    }

    @FXML
    private void disableRickButtonAction(ActionEvent e) {
        //norickroll
        mediaPlayer.play();
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
//          Media media = new Media(getClass().getResource("audio/music/slow-trap-18565.mp3").toExternalForm());
            media = new Media(musicList.get(musicNumber).toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            musicLabel.setText(musicList.get(musicNumber).getName().substring(1));
            beginTimer();
            mediaPlayer.setAutoPlay(true);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            mediaPlayer.setVolume(0.5);
            vSlider.setValue(mediaPlayer.getVolume() * 100);
            vSlider.valueProperty().addListener(
                    e -> mediaPlayer.setVolume(vSlider.getValue() / 100));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void playMedia() {
        beginTimer();
        mediaPlayer.play();
    }

    @FXML
    private void pauseMedia() {
        cancelTimer();
        mediaPlayer.pause();
    }

    @FXML
    private void nextMedia() {
        if (musicNumber < musicList.size() - 1) {
            musicNumber++;
        }
        else {
            musicNumber = 0;
        }
        mediaPlayer.stop();
        if (running) {
            cancelTimer();
        }
        media = new Media(musicList.get(musicNumber).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        musicLabel.setText(musicList.get(musicNumber).getName().substring(1));
        beginTimer();
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
    }

    @FXML
    private void previousMedia() {
        if (musicNumber > 0) {
            musicNumber--;
        }
        else {
            musicNumber = musicList.size() - 1;
        }
        mediaPlayer.stop();
        if (running) {
            cancelTimer();
        }
        media = new Media(musicList.get(musicNumber).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        musicLabel.setText(musicList.get(musicNumber).getName().substring(1));
        beginTimer();
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
    }

    private void beginTimer() {

        timer = new Timer();
        task = new TimerTask() {
            @Override
            public void run() {
                running = true;
                double current = mediaPlayer.getCurrentTime().toSeconds();
                double end = media.getDuration().toSeconds();
                musicProgressBar.setProgress(current / end);
                if (current / end == 1) {
                    cancelTimer();
                }
            }
        };

        timer.scheduleAtFixedRate(task, 0, 1000);
    }

    private void cancelTimer() {

        running = false;
        timer.cancel();
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
        bRedButtonLock.setSelected(false);
        bRedTextLock.clear();
        pane.setVisible(false);

        gw = new GameWorld();
        uiState = new UIState(gridPane, progressBar, textArea, hp, pos, gameOverGroup, gameOverLabel, youWinLabel, group);
        h = new Human(uiState,
                Integer.parseInt(hHealth.getText()),
                Integer.parseInt(hStrength.getText()),
                randomToggle.isSelected() ?
                        new int[] {(int) (Math.random() * 10),(int) (Math.random() * 10)}
                        : new int[] {Integer.parseInt(hPosY.getText()), Integer.parseInt(hPosX.getText())});

        //random goblin spawn
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

    @FXML
    private void loseFocus() {
        //required for volume slider to lose focus, interferes with arrow movement
        progressBar.requestFocus();
    }

    @FXML
    private void bRedTextLock() {
        bRedButtonLock.setDisable(!bRedTextLock.getText().equals("YES"));
        if(!bRedTextLock.getText().equals("YES")) {
            dnp.setVisible(false);
            bRedButtonLock.setSelected(false);
        }
        loseFocus();
    }

    private void playKlaxon() {
        audioClip = new AudioClip(getClass().getResource("audio/sound/Klaxon-sound.mp3").toExternalForm());
        audioClip.setCycleCount(AudioClip.INDEFINITE);
        audioClip.setVolume(0.2);
        audioClip.play();
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