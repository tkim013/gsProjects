package org.project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class MyController {

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
    private TextField textField;

    public MyController() {
        System.out.println("first");

    }

    public void initialize() {

        System.out.println("testing");

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

        GameWorld gw = new GameWorld();
        Human h = new Human(gridPane,50, 5, new int[] {5,5});

        gw.populateGoblins(gridPane, 5);
    }

    @FXML
    public void theButtonAction(ActionEvent e) {
        System.out.println("buttonaction");

    }
}