package org.project;

import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.util.List;

public class Human extends Creature{
    List<List<Land>> gw = GameWorld.getLandList();

    private String id = "Human";
    private int health = 20;
    private int maxHealth = 20;
    private int strength = 5;
    private int[] currentPos = {0, 0}; //element0 - n/s, element1 - e/w

    public Human() {
        gw.get(this.currentPos[0]).get(this.currentPos[1]).setHasCreature(this);
    }

    public Human(UIState uiState, int health, int strength, int[] pos) {
        this.health = health;
        this.maxHealth = health;
        this.strength = strength;
        this.currentPos = pos;

        gw.get(this.currentPos[0]).get(this.currentPos[1]).setHasCreature(this);

        if (uiState.getGridPane() != null) {
            //adds human image to GUI
            addHumanImage(uiState.getGridPane());
            //set health bar
            uiState.getProgressBar().setProgress((double)getHealth()/getMaxHealth());
        }
    }

    @Override
    public String toString() {
        return "@";
    }

    public int move(UIState uiState, String s) {

        if (getHealth() > 0) {
            //check valid moves, assign null to land object at currentPos, assign this Human to new position
            //check if destination Land.hasCreature not null, Combat.resolveCombat with Object, assigns winner to Land.hasCreature
            switch (s) {
                case "n":

                    //check for move out of bounds
                    if (this.currentPos[0] - 1 < 0) {
                        System.out.println("Invalid move.");
                        if (uiState.getTextArea() != null) {
                            uiState.getTextArea().appendText("Invalid move.\n");
                        }
                        return 0;
                    }

                    //valid move, creature in destination
                    if (gw.get(currentPos[0] - 1).get(currentPos[1]).getHasCreature() != null) {

                        //remove creature from current position
                        gw.get(currentPos[0]).get(currentPos[1]).removeCreature();
                        //remove human image from grid
                        if (uiState.getGridPane() != null) {
                            ImageView h = (ImageView) uiState.getGridPane().lookup("#human");
                            uiState.getGridPane().getChildren().remove(h);
                        }
                        //adjust position
                        this.currentPos[0]--;
                        if (uiState.getTextArea() != null) {
                            uiState.getTextArea().appendText("You move north and encounter a creature.\n");
                        }
                        //invoke combat, set winner
                        startCombat(uiState.getTextArea(), uiState.getProgressBar());
                        if (uiState.getGridPane() != null && gw.get(currentPos[0]).get(currentPos[1]).getHasCreature() instanceof Human) {
                            //adds human image to current pos
                            addHumanImage(uiState.getGridPane());

                            //remove goblin image from gridPane
                            ImageView g = (ImageView) uiState.getGridPane().lookup("#goblin" + this.currentPos[1] + this.currentPos[0]);
                            uiState.getGridPane().getChildren().remove(g);
                        } else if (uiState.getGridPane() != null) {
                            //remove human image from grid
                            ImageView h = (ImageView) uiState.getGridPane().lookup("#human");
                            uiState.getGridPane().getChildren().remove(h);
                        }

                        return 2;

                    } else {

                        //valid move, no creature in destination
                        gw.get(currentPos[0]).get(currentPos[1]).removeCreature();
                        //remove human image from grid
                        if (uiState.getGridPane() != null) {
                            ImageView h = (ImageView) uiState.getGridPane().lookup("#human");
                            uiState.getGridPane().getChildren().remove(h);
                        }
                        //adjust position
                        this.currentPos[0]--;

                        if (uiState.getTextArea() != null) {
                            uiState.getTextArea().appendText("You move north.\n");
                        }

                        //add human to current position
                        gw.get(currentPos[0]).get(currentPos[1]).setHasCreature(this);
                        if (uiState.getGridPane() != null) {
                            //adds human image to current pos
                            addHumanImage(uiState.getGridPane());
                        }

                        return 1;
                    }

                case "s":

                    //check for move out of bounds
                    if (this.currentPos[0] + 1 > GameWorld.getLandList().size() - 1) {
                        System.out.println("Invalid move.");
                        if (uiState.getTextArea() != null) {
                            uiState.getTextArea().appendText("Invalid move.\n");
                        }
                        return 0;
                    }

                    //valid move, creature in destination
                    if (gw.get(currentPos[0] + 1).get(currentPos[1]).getHasCreature() != null) {

                        //remove creature from current position
                        gw.get(currentPos[0]).get(currentPos[1]).removeCreature();
                        //remove human image from grid
                        if (uiState.getGridPane() != null) {
                            ImageView h = (ImageView) uiState.getGridPane().lookup("#human");
                            uiState.getGridPane().getChildren().remove(h);
                        }
                        //adjust position
                        this.currentPos[0]++;
                        if (uiState.getTextArea() != null) {
                            uiState.getTextArea().appendText("You move south and encounter a creature.\n");
                        }
                        //invoke combat, set winner
                        startCombat(uiState.getTextArea(), uiState.getProgressBar());
                        if (uiState.getGridPane() != null && gw.get(currentPos[0]).get(currentPos[1]).getHasCreature() instanceof Human) {
                            //adds human image to current pos
                            addHumanImage(uiState.getGridPane());

                            //remove goblin image from gridPane
                            ImageView g = (ImageView) uiState.getGridPane().lookup("#goblin" + this.currentPos[1] + this.currentPos[0]);
                            uiState.getGridPane().getChildren().remove(g);
                        } else if (uiState.getGridPane() != null) {
                            //remove human image from grid
                            ImageView h = (ImageView) uiState.getGridPane().lookup("#human");
                            uiState.getGridPane().getChildren().remove(h);
                        }

                        return 2;

                    } else {

                        //valid move, no creature in destination
                        gw.get(currentPos[0]).get(currentPos[1]).removeCreature();
                        //remove human image from grid
                        if (uiState.getGridPane() != null) {
                            ImageView h = (ImageView) uiState.getGridPane().lookup("#human");
                            uiState.getGridPane().getChildren().remove(h);
                        }
                        //adjust position
                        this.currentPos[0]++;

                        if (uiState.getTextArea() != null) {
                            uiState.getTextArea().appendText("You move south.\n");
                        }

                        //add human to current position
                        gw.get(currentPos[0]).get(currentPos[1]).setHasCreature(this);
                        if (uiState.getGridPane() != null) {
                            //adds human image to current pos
                            addHumanImage(uiState.getGridPane());
                        }

                        return 1;
                    }

                case "e":

                    //check for move out of bounds
                    if (this.currentPos[1] + 1 > GameWorld.getLandList().get(0).size() - 1) {
                        System.out.println("Invalid move.");
                        if (uiState.getTextArea() != null) {
                            uiState.getTextArea().appendText("Invalid move.\n");
                        }
                        return 0;
                    }

                    //valid move, creature in destination
                    if (gw.get(currentPos[0]).get(currentPos[1] + 1).getHasCreature() != null) {

                        //remove creature from current position
                        gw.get(currentPos[0]).get(currentPos[1]).removeCreature();
                        //remove human image from grid
                        if (uiState.getGridPane() != null) {
                            ImageView h = (ImageView) uiState.getGridPane().lookup("#human");
                            uiState.getGridPane().getChildren().remove(h);
                        }
                        //adjust position
                        this.currentPos[1]++;
                        if (uiState.getTextArea() != null) {
                            uiState.getTextArea().appendText("You move east and encounter a creature.\n");
                        }
                        //invoke combat, set winner
                        startCombat(uiState.getTextArea(), uiState.getProgressBar());
                        if (uiState.getGridPane() != null && gw.get(currentPos[0]).get(currentPos[1]).getHasCreature() instanceof Human) {
                            //adds human image to current pos
                            addHumanImage(uiState.getGridPane());

                            //remove goblin image from gridPane
                            ImageView g = (ImageView) uiState.getGridPane().lookup("#goblin" + this.currentPos[1] + this.currentPos[0]);
                            uiState.getGridPane().getChildren().remove(g);
                        } else if (uiState.getGridPane() != null) {
                            //remove human image from grid
                            ImageView h = (ImageView) uiState.getGridPane().lookup("#human");
                            uiState.getGridPane().getChildren().remove(h);
                        }

                        return 2;

                    } else {

                        //valid move, no creature in destination
                        gw.get(currentPos[0]).get(currentPos[1]).removeCreature();
                        //remove human image from grid
                        if (uiState.getGridPane() != null) {
                            ImageView h = (ImageView) uiState.getGridPane().lookup("#human");
                            uiState.getGridPane().getChildren().remove(h);
                        }
                        //adjust position
                        this.currentPos[1]++;

                        if (uiState.getTextArea() != null) {
                            uiState.getTextArea().appendText("You move east.\n");
                        }

                        //add human to current position
                        gw.get(currentPos[0]).get(currentPos[1]).setHasCreature(this);
                        if (uiState.getGridPane() != null) {
                            //adds human image to current pos
                            addHumanImage(uiState.getGridPane());
                        }

                        return 1;
                    }

                case "w":

                    //check for move out of bounds
                    if (this.currentPos[1] - 1 < 0) {
                        System.out.println("Invalid move.");
                        if (uiState.getTextArea() != null) {
                            uiState.getTextArea().appendText("Invalid move.\n");
                        }
                        return 0;
                    }

                    //valid move, creature in destination
                    if (gw.get(currentPos[0]).get(currentPos[1] - 1).getHasCreature() != null) {

                        //remove creature from current position
                        gw.get(currentPos[0]).get(currentPos[1]).removeCreature();
                        //remove human image from grid
                        if (uiState.getGridPane() != null) {
                            ImageView h = (ImageView) uiState.getGridPane().lookup("#human");
                            uiState.getGridPane().getChildren().remove(h);
                        }
                        //adjust position
                        this.currentPos[1]--;
                        if (uiState.getTextArea() != null) {
                            uiState.getTextArea().appendText("You move west and encounter a creature.\n");
                        }
                        //invoke combat, set winner
                        startCombat(uiState.getTextArea(), uiState.getProgressBar());
                        if (uiState.getGridPane() != null && gw.get(currentPos[0]).get(currentPos[1]).getHasCreature() instanceof Human) {
                            //adds human image to current pos
                            addHumanImage(uiState.getGridPane());

                            //remove goblin image from gridPane
                            ImageView g = (ImageView) uiState.getGridPane().lookup("#goblin" + this.currentPos[1] + this.currentPos[0]);
                            uiState.getGridPane().getChildren().remove(g);
                        } else if (uiState.getGridPane() != null) {
                            //remove human image from grid
                            ImageView h = (ImageView) uiState.getGridPane().lookup("#human");
                            uiState.getGridPane().getChildren().remove(h);
                        }

                        return 2;

                    } else {

                        //valid move, no creature in destination
                        gw.get(currentPos[0]).get(currentPos[1]).removeCreature();
                        //remove human image from grid
                        if (uiState.getGridPane() != null) {
                            ImageView h = (ImageView) uiState.getGridPane().lookup("#human");
                            uiState.getGridPane().getChildren().remove(h);
                        }
                        //adjust position
                        this.currentPos[1]--;

                        if (uiState.getTextArea() != null) {
                            uiState.getTextArea().appendText("You move west.\n");
                        }

                        //add human to current position
                        gw.get(currentPos[0]).get(currentPos[1]).setHasCreature(this);
                        if (uiState.getGridPane() != null) {
                            //adds human image to current pos
                            addHumanImage(uiState.getGridPane());
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

    private void addHumanImage(GridPane gridPane) {

        try {
            ImageView imageView = new ImageView(new Image("file:src/main/resources/org/project/hooded-assassin.png"));
            imageView.setFitHeight(60);
            imageView.setFitWidth(60);
            imageView.setId("human");
            gridPane.add(imageView, this.currentPos[1], this.currentPos[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void startCombat(TextArea textArea, ProgressBar progressBar) {
        gw.get(currentPos[0]).get(currentPos[1])
                .setHasCreature(Combat.resolveCombat(textArea, progressBar, this, (Creature) gw.get(currentPos[0]).get(currentPos[1]).getHasCreature()));
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
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
