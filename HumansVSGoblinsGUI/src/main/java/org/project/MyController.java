package org.project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class MyController {

    Human h;
    GameWorld gw;

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
    private TextArea textArea;

    public MyController() {
    }

    public void initialize() {

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
        h = new Human(gridPane,50, 5, new int[] {(int) (Math.random() * 10),(int) (Math.random() * 10)});

        gw.populateGoblins(gridPane, 5);
    }

    @FXML
    public void northButtonAction(ActionEvent e) {
        h.move(gridPane, textArea, "n");
    }

    @FXML
    public void southButtonAction(ActionEvent e) {
        h.move(gridPane, textArea, "s");
    }

    @FXML
    public void eastButtonAction(ActionEvent e) {
        h.move(gridPane, textArea, "e");
    }

    @FXML
    public void westButtonAction(ActionEvent e) {
        h.move(gridPane, textArea, "w");
    }
}