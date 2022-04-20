package org.project;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.util.List;

public class Human extends Creature{
    List<List<Land>> gw = GameWorld.getLandList();

    private int health = 20;
    private int strength = 5;
    private int[] currentPos = {0, 0}; //element0 - n/s, element1 - e/w

    public Human() {
        gw.get(this.currentPos[0]).get(this.currentPos[1]).setHasCreature(this);
    }

    public Human(GridPane gridPane, int health, int strength, int[] pos) {
        this.health = health;
        this.strength = strength;
        this.currentPos = pos;

        gw.get(this.currentPos[0]).get(this.currentPos[1]).setHasCreature(this);

        if (gridPane != null) {
            //adds human image to GUI
            Image image = new Image("file:src/main/resources/org/project/hooded-assassin.png");
            ImageView imageView = new ImageView(image);
            imageView.setFitHeight(60);
            imageView.setFitWidth(60);
            imageView.setId("human");
            gridPane.add(imageView, this.currentPos[0], this.currentPos[1]);
        }
    }

    @Override
    public String toString() {
        return "@";
    }

    public int move(GridPane gridPane, String s) {

        if (getHealth() > 0) {
            //check valid moves, assign null to land object at currentPos, assign this Human to new position
            //check if destination Land.hasCreature not null, Combat.resolveCombat with Object, assigns winner to Land.hasCreature
            switch (s) {
                case "n":

                    if (this.currentPos[0] - 1 < 0) {
                        System.out.println("Invalid move.");
                        return 0;
                    }

                    //valid move, creature in destination
                    if (gw.get(currentPos[0] - 1).get(currentPos[1]).getHasCreature() != null) {

                        gw.get(currentPos[0]).get(currentPos[1]).removeCreature();
                        //remove human image from grid
                        if (gridPane != null) {
                            ImageView h = (ImageView) gridPane.lookup("#human");
                            gridPane.getChildren().remove(h);
                        }
                        this.currentPos[0]--;
                        gw.get(currentPos[0]).get(currentPos[1])
                                .setHasCreature(Combat.resolveCombat(this, (Creature) gw.get(currentPos[0]).get(currentPos[1]).getHasCreature()));
                        if (gridPane != null && gw.get(currentPos[0]).get(currentPos[1]).getHasCreature() instanceof Human) {
                            //adds human image to current pos
                            Image image = new Image("file:src/main/resources/org/project/hooded-assassin.png");
                            ImageView imageView = new ImageView(image);
                            imageView.setFitHeight(60);
                            imageView.setFitWidth(60);
                            imageView.setId("human");
                            gridPane.add(imageView, this.currentPos[1], this.currentPos[0]);

                            //remove goblin image from gridPane
                            ImageView g = (ImageView) gridPane.lookup("#goblin" + this.currentPos[1] + this.currentPos[0]);
                            gridPane.getChildren().remove(g);
                        } else if (gridPane != null) {
                            //remove human image from grid
                            ImageView h = (ImageView) gridPane.lookup("#human");
                            gridPane.getChildren().remove(h);
                        }

                        return 2;

                    } else {

                        //valid move, no creature in destination
                        gw.get(currentPos[0]).get(currentPos[1]).removeCreature();
                        //remove human image from grid
                        if (gridPane != null) {
                            ImageView h = (ImageView) gridPane.lookup("#human");
                            gridPane.getChildren().remove(h);
                        }
                        this.currentPos[0]--;
                        gw.get(currentPos[0]).get(currentPos[1]).setHasCreature(this);
                        if (gridPane != null) {
                            //adds human image to current pos
                            Image image = new Image("file:src/main/resources/org/project/hooded-assassin.png");
                            ImageView imageView = new ImageView(image);
                            imageView.setFitHeight(60);
                            imageView.setFitWidth(60);
                            imageView.setId("human");
                            gridPane.add(imageView, this.currentPos[1], this.currentPos[0]);
                        }

                        return 1;
                    }

                case "s":

                    if (this.currentPos[0] + 1 > GameWorld.getLandList().size() - 1) {
                        System.out.println("Invalid move.");
                        return 0;
                    }

                    //valid move, creature in destination
                    if (gw.get(currentPos[0] + 1).get(currentPos[1]).getHasCreature() != null) {

                        gw.get(currentPos[0]).get(currentPos[1]).removeCreature();
                        //remove human image from grid
                        if (gridPane != null) {
                            ImageView h = (ImageView) gridPane.lookup("#human");
                            gridPane.getChildren().remove(h);
                        }
                        this.currentPos[0]++;
                        gw.get(currentPos[0]).get(currentPos[1])
                                .setHasCreature(Combat.resolveCombat(this, (Creature) gw.get(currentPos[0]).get(currentPos[1]).getHasCreature()));
                        if (gridPane != null && gw.get(currentPos[0]).get(currentPos[1]).getHasCreature() instanceof Human) {
                            //adds human image to current pos
                            Image image = new Image("file:src/main/resources/org/project/hooded-assassin.png");
                            ImageView imageView = new ImageView(image);
                            imageView.setFitHeight(60);
                            imageView.setFitWidth(60);
                            imageView.setId("human");
                            gridPane.add(imageView, this.currentPos[1], this.currentPos[0]);

                            //remove goblin image from gridPane
                            ImageView g = (ImageView) gridPane.lookup("#goblin" + this.currentPos[1] + this.currentPos[0]);
                            gridPane.getChildren().remove(g);
                        } else if (gridPane != null) {
                            //remove human image from grid
                            ImageView h = (ImageView) gridPane.lookup("#human");
                            gridPane.getChildren().remove(h);
                        }

                        return 2;

                    } else {

                        //valid move, no creature in destination
                        gw.get(currentPos[0]).get(currentPos[1]).removeCreature();
                        //remove human image from grid
                        if (gridPane != null) {
                            ImageView h = (ImageView) gridPane.lookup("#human");
                            gridPane.getChildren().remove(h);
                        }
                        this.currentPos[0]++;
                        gw.get(currentPos[0]).get(currentPos[1]).setHasCreature(this);
                        if (gridPane != null) {
                            //adds human image to current pos
                            Image image = new Image("file:src/main/resources/org/project/hooded-assassin.png");
                            ImageView imageView = new ImageView(image);
                            imageView.setFitHeight(60);
                            imageView.setFitWidth(60);
                            imageView.setId("human");
                            gridPane.add(imageView, this.currentPos[1], this.currentPos[0]);
                        }

                        return 1;
                    }

                case "e":

                    if (this.currentPos[1] + 1 > GameWorld.getLandList().get(0).size() - 1) {
                        System.out.println("Invalid move.");
                        return 0;
                    }

                    //valid move, creature in destination
                    if (gw.get(currentPos[0]).get(currentPos[1] + 1).getHasCreature() != null) {

                        gw.get(currentPos[0]).get(currentPos[1]).removeCreature();
                        //remove human image from grid
                        if (gridPane != null) {
                            ImageView h = (ImageView) gridPane.lookup("#human");
                            gridPane.getChildren().remove(h);
                        }
                        this.currentPos[1]++;
                        gw.get(currentPos[0]).get(currentPos[1])
                                .setHasCreature(Combat.resolveCombat(this, (Creature) gw.get(currentPos[0]).get(currentPos[1]).getHasCreature()));

                        if (gridPane != null && gw.get(currentPos[0]).get(currentPos[1]).getHasCreature() instanceof Human) {
                            //adds human image to current pos
                            Image image = new Image("file:src/main/resources/org/project/hooded-assassin.png");
                            ImageView imageView = new ImageView(image);
                            imageView.setFitHeight(60);
                            imageView.setFitWidth(60);
                            imageView.setId("human");
                            gridPane.add(imageView, this.currentPos[1], this.currentPos[0]);

                            //remove goblin image from gridPane
                            ImageView g = (ImageView) gridPane.lookup("#goblin" + this.currentPos[1] + this.currentPos[0]);
                            gridPane.getChildren().remove(g);
                        } else if (gridPane != null) {
                            //remove human image from grid
                            ImageView h = (ImageView) gridPane.lookup("#human");
                            gridPane.getChildren().remove(h);
                        }

                        return 2;

                    } else {

                        //valid move, no creature in destination
                        gw.get(currentPos[0]).get(currentPos[1]).removeCreature();
                        //remove human image from grid
                        if (gridPane != null) {
                            ImageView h = (ImageView) gridPane.lookup("#human");
                            gridPane.getChildren().remove(h);
                        }
                        this.currentPos[1]++;
                        gw.get(currentPos[0]).get(currentPos[1]).setHasCreature(this);
                        if (gridPane != null) {
                            //adds human image to current pos
                            Image image = new Image("file:src/main/resources/org/project/hooded-assassin.png");
                            ImageView imageView = new ImageView(image);
                            imageView.setFitHeight(60);
                            imageView.setFitWidth(60);
                            imageView.setId("human");
                            gridPane.add(imageView, this.currentPos[1], this.currentPos[0]);
                        }

                        return 1;
                    }

                case "w":

                    if (this.currentPos[1] - 1 < 0) {
                        System.out.println("Invalid move.");
                        return 0;
                    }

                    //valid move, creature in destination
                    if (gw.get(currentPos[0]).get(currentPos[1] - 1).getHasCreature() != null) {

                        gw.get(currentPos[0]).get(currentPos[1]).removeCreature();
                        //remove human image from grid
                        if (gridPane != null) {
                            ImageView h = (ImageView) gridPane.lookup("#human");
                            gridPane.getChildren().remove(h);
                        }
                        this.currentPos[1]--;
                        gw.get(currentPos[0]).get(currentPos[1])
                                .setHasCreature(Combat.resolveCombat(this, (Creature) gw.get(currentPos[0]).get(currentPos[1]).getHasCreature()));

                        if (gridPane != null && gw.get(currentPos[0]).get(currentPos[1]).getHasCreature() instanceof Human) {
                            //adds human image to current pos
                            Image image = new Image("file:src/main/resources/org/project/hooded-assassin.png");
                            ImageView imageView = new ImageView(image);
                            imageView.setFitHeight(60);
                            imageView.setFitWidth(60);
                            imageView.setId("human");
                            gridPane.add(imageView, this.currentPos[1], this.currentPos[0]);

                            //remove goblin image from gridPane
                            ImageView g = (ImageView) gridPane.lookup("#goblin" + this.currentPos[1] + this.currentPos[0]);
                            gridPane.getChildren().remove(g);
                        } else if (gridPane != null) {
                            //remove human image from grid
                            ImageView h = (ImageView) gridPane.lookup("#human");
                            gridPane.getChildren().remove(h);
                        }

                        return 2;

                    } else {

                        //valid move, no creature in destination
                        gw.get(currentPos[0]).get(currentPos[1]).removeCreature();
                        //remove human image from grid
                        if (gridPane != null) {
                            ImageView h = (ImageView) gridPane.lookup("#human");
                            gridPane.getChildren().remove(h);
                        }
                        this.currentPos[1]--;
                        gw.get(currentPos[0]).get(currentPos[1]).setHasCreature(this);
                        if (gridPane != null) {
                            //adds human image to current pos
                            Image image = new Image("file:src/main/resources/org/project/hooded-assassin.png");
                            ImageView imageView = new ImageView(image);
                            imageView.setFitHeight(60);
                            imageView.setFitWidth(60);
                            imageView.setId("human");
                            gridPane.add(imageView, this.currentPos[1], this.currentPos[0]);
                        }

                        return 1;
                    }

                default:

                    System.out.println("Invalid move.");
                    return 0;
            }
        }
        return -1;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int[] getCurrentPos() {
        return currentPos;
    }

    public void setCurrentPos(int[] currentPos) {
        this.currentPos = currentPos;
    }
}
