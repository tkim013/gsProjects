package org.project;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.List;

public class GameWorld {

    private static List<List<Land>> landList;
    final static int row = 10;
    final static int col = 10;

    public GameWorld() {

        landList = new ArrayList<>();

        for (int i = 0; i < col; i++) {
            landList.add(new ArrayList<>());

            for (int j = 0; j < row; j++) {
                landList.get(i).add(new Land());
            }
        }
    }

    @Override
    public String toString() {
        //builds String representation of game world
        StringBuilder sb = new StringBuilder();
        for (List<Land> list : landList) {
            for (Land l : list) {
                    sb.append(l);
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public static void populateGoblins(GridPane gridPane, int num) {

        Image goblinImage = null;
        try {
            goblinImage = new Image("file:src/main/resources/org/project/goblin.png");
        } catch (Exception e) {
            e.printStackTrace();
        }

        //set limit on num to half map size
        if (num <= (row * col) / 2 && num > 0 && col > 0 && row > 0) {

            List<List<Boolean>> table = new ArrayList<>();
            int r;
            int c;

            //fill table with true
            for (int i = 0; i < col; i++) {
                table.add(new ArrayList<>());
                for (int j = 0; j < row; j++) {
                    table.get(i).add(true);
                }
            }

            for (int i = 0; i < num;) {

                r = (int) (Math.random() * row);
                c = (int) (Math.random() * col);

                //check for valid spawn position in table and null Land.hasCreature, sets position invalid
                if (table.get(c).get(r) && landList.get(c).get(r).getHasCreature() == null) {
                    landList.get(c).get(r).setHasCreature(new Goblin());
                    table.get(c).set(r, false);
                    i++;

                    if (gridPane != null) {
                        //adds goblin image to GUI
                        ImageView imageView = new ImageView(goblinImage);
                        imageView.setFitHeight(60);
                        imageView.setFitWidth(60);
                        imageView.setId("goblin" + r + c);  //goblin id with coordinates for lookup
                        gridPane.add(imageView, r, c);
                    }
                }
            }
        } else {
            System.out.println("Invalid num");
        }
    }

    public static List<List<Land>> getLandList() {
        return landList;
    }

    public static void setLandList(List<List<Land>> list) {
        GameWorld.landList = list;
    }
}