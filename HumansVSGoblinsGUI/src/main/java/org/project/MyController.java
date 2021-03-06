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
import javafx.scene.layout.AnchorPane;
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
    private List<Media> musicList;
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
    private AnchorPane playerPane;
    private boolean mDragged;
    private double mPLayoutX = 621;
    private double mPLayoutY = 324;
    private double playButtonLayoutX = 0;
    private double playButtonLayoutY = 40;
    private double pauseButtonLayoutX = 35;
    private double pauseButtonLayoutY = 40;
    private double prevButtonLayoutX = 70;
    private double prevButtonLayoutY = 40;
    private double nextButtonLayoutX = 105;
    private double nextButtonLayoutY = 40;
    private double newMPPosX;
    private double newMPPosY;
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
//            musicDir = new File(getClass().getResource("audio/music").toURI());
//            files = musicDir.listFiles();
//            if (files != null) {
//                for (File file : files) {
//                    musicList.add(file);
//                }
//            }

            //workaround for deployment
            musicList.add(new Media(getClass().getResource("audio/music/0slow-trap-18565.mp3").toExternalForm()));
            musicList.add(new Media(getClass().getResource("audio/music/1Nightcore - Levitating.mp3").toExternalForm()));
            musicList.add(new Media(getClass().getResource("audio/music/2Nightcore - Darkside - (Alan Walker - Lyrics).mp3").toExternalForm()));
            musicList.add(new Media(getClass().getResource("audio/music/3Black Sun Empire feat. Inne Eysermans - Killing the Light (Official Music Video).mp3").toExternalForm()));
            musicList.add(new Media(getClass().getResource("audio/music/4Current Value - Dark Rain HD.mp3").toExternalForm()));
            musicList.add(new Media(getClass().getResource("audio/music/5Silence - Delirium ft Sarah Mclachlan.mp3").toExternalForm()));
            musicList.add(new Media(getClass().getResource("audio/music/6BT - Dreaming (original version).mp3").toExternalForm()));
            musicList.add(new Media(getClass().getResource("audio/music/7DJ icey - Escape.mp3").toExternalForm()));
            musicList.add(new Media(getClass().getResource("audio/music/8DJ Icey - The One.mp3").toExternalForm()));
            musicList.add(new Media(getClass().getResource("audio/music/9Above & Beyond pres. OceanLab - Clear Blue Water.mp3").toExternalForm()));


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

        //listener for togglebutton that locks Big Red Button
        bRedButtonLock.selectedProperty().addListener((observableValue, aBoolean, t1) -> {
            //flashy text
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
        cancelTimer();
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
        beginTimer();
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
            media = musicList.get(0);
            musicNumber = 0;
            mediaPlayer = new MediaPlayer(media);
            musicLabel.setText(musicList.get(0).getSource().substring(79).replaceAll("%20", " "));
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
        //check for drag
        if (!mDragged) {
            beginTimer();
            mediaPlayer.play();
        }
    }

    @FXML
    private void pauseMedia() {
        //check for drag
        if (!mDragged) {
            cancelTimer();
            mediaPlayer.pause();
        }
    }

    @FXML
    private void nextMedia() {
        //check for drag
        if (!mDragged) {
            //set index
            musicNumber = musicNumber < musicList.size() - 1 ? ++musicNumber : 0;
            mediaPlayer.stop();
            //terminate progress bar if active
            if (running) {
                cancelTimer();
            }
            media = musicList.get(musicNumber);
            mediaPlayer = new MediaPlayer(media);
            musicLabel.setText(musicList.get(musicNumber).getSource().substring(79).replaceAll("%20", " "));
            //set progress bar
            beginTimer();
            mediaPlayer.setAutoPlay(true);
            vSlider.setValue(50);
            mediaPlayer.setVolume(0.5);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        }
    }

    @FXML
    private void previousMedia() {
        //check for drag
        if (!mDragged) {
            //set index
            musicNumber = musicNumber > 0 ? --musicNumber : musicList.size() - 1;
            mediaPlayer.stop();
            //terminate progress bar if active
            if (running) {
                cancelTimer();
            }
            media = musicList.get(musicNumber);
            mediaPlayer = new MediaPlayer(media);
            musicLabel.setText(musicList.get(musicNumber).getSource().substring(79).replaceAll("%20", " "));
            //set progress bar
            beginTimer();
            mediaPlayer.setAutoPlay(true);
            vSlider.setValue(50);
            mediaPlayer.setVolume(0.5);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        }
    }

    private void beginTimer() {

        //progress bar for music player
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

        //terminates progress bar
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
        //drag start option pane
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
    public void setMPPos(MouseEvent e) {
        //new position of media player into layout variables for drag calculation
        mPLayoutX = newMPPosX;
        mPLayoutY = newMPPosY;
        mDragged = false;
    }
    @FXML
    public void dragMediaPlayer(MouseEvent e) {
        //drag media player anchorpane
        playerPane.setTranslateX(playerPane.getTranslateX()
                + e.getX()
                - xOffset
                + mPLayoutX);
        newMPPosX = playerPane.getLayoutX() + playerPane.getTranslateX();
        playerPane.setTranslateY(playerPane.getTranslateY()
                + e.getY()
                - yOffset
                + mPLayoutY);
        newMPPosY = playerPane.getLayoutY() + playerPane.getTranslateY();
    }

    @FXML
    public void dragPlayButton(MouseEvent e) {
        //drag media player anchorpane from play button
        playerPane.setTranslateX(playerPane.getTranslateX()
                + e.getX()
                - xOffset
                + mPLayoutX
                + playButtonLayoutX);
        newMPPosX = playerPane.getLayoutX() + playerPane.getTranslateX();
        playerPane.setTranslateY(playerPane.getTranslateY()
                + e.getY()
                - yOffset
                + mPLayoutY
                + playButtonLayoutY);
        newMPPosY = playerPane.getLayoutY() + playerPane.getTranslateY();
        mDragged = true;
    }
    @FXML
    public void dragPauseButton(MouseEvent e) {
        //drag media player anchorpane from pause button
        playerPane.setTranslateX(playerPane.getTranslateX()
                + e.getX()
                - xOffset
                + mPLayoutX
                + pauseButtonLayoutX);
        newMPPosX = playerPane.getLayoutX() + playerPane.getTranslateX();
        playerPane.setTranslateY(playerPane.getTranslateY()
                + e.getY()
                - yOffset
                + mPLayoutY
                + pauseButtonLayoutY);
        newMPPosY = playerPane.getLayoutY() + playerPane.getTranslateY();
        mDragged = true;
    }
    @FXML
    public void dragPrevButton(MouseEvent e) {
        //drag media player anchorpane from previous button
        playerPane.setTranslateX(playerPane.getTranslateX()
                + e.getX()
                - xOffset
                + mPLayoutX
                + prevButtonLayoutX);
        newMPPosX = playerPane.getLayoutX() + playerPane.getTranslateX();
        playerPane.setTranslateY(playerPane.getTranslateY()
                + e.getY()
                - yOffset
                + mPLayoutY
                + prevButtonLayoutY);
        newMPPosY = playerPane.getLayoutY() + playerPane.getTranslateY();
        mDragged = true;
    }
    @FXML
    public void dragNextButton(MouseEvent e) {
        //drag media player anchorpane from next button
        playerPane.setTranslateX(playerPane.getTranslateX()
                + e.getX()
                - xOffset
                + mPLayoutX
                + nextButtonLayoutX);
        newMPPosX = playerPane.getLayoutX() + playerPane.getTranslateX();
        playerPane.setTranslateY(playerPane.getTranslateY()
                + e.getY()
                - yOffset
                + mPLayoutY
                + nextButtonLayoutY);
        newMPPosY = playerPane.getLayoutY() + playerPane.getTranslateY();
        mDragged = true;
    }
    @FXML
    private void restart() {
        //enables start options pane
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
        //text lock for togglebutton that locks Big Red Button
        bRedButtonLock.setDisable(!bRedTextLock.getText().equals("YES"));
        if(!bRedTextLock.getText().equals("YES")) {
            dnp.setVisible(false);
            bRedButtonLock.setSelected(false);
        }
        loseFocus();
    }

    private void playKlaxon() {
        try {
            audioClip = new AudioClip(getClass().getResource("audio/sound/Klaxon-sound.mp3").toExternalForm());
            audioClip.setCycleCount(AudioClip.INDEFINITE);
            audioClip.setVolume(0.5);
            audioClip.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
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