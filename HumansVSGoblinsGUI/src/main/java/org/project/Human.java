package org.project;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import java.util.Arrays;
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

        if (uiState != null) {
            //adds human image to GUI
            addHumanImage(uiState.getGridPane());
            //set health bar
            uiState.getProgressBar().setProgress((double)getHealth()/getMaxHealth());
            //set hp label
            uiState.getHp().setText(String.valueOf(getHealth()));
            //set current pos
            uiState.getPos().setText(Arrays.toString(this.getCurrentPos()));
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
                        if (uiState != null) {
                            uiState.getTextArea().appendText("\nInvalid move.");
                        }
                        return 0;
                    }

                    //valid move, creature in destination
                    if (gw.get(currentPos[0] - 1).get(currentPos[1]).getHasCreature() != null) {

                        //remove creature from current position
                        gw.get(currentPos[0]).get(currentPos[1]).removeCreature();
                        //remove human image from grid
                        if (uiState != null) {
                            ImageView h = (ImageView) uiState.getGridPane().lookup("#human");
                            uiState.getGridPane().getChildren().remove(h);
                        }
                        //adjust position
                        this.currentPos[0]--;
                        if (uiState != null) {
                            uiState.getTextArea().appendText("\nYou move north and encounter a creature.");

                        }
                        //invoke combat, set winner
                        startCombat(uiState);
                        if (uiState != null && gw.get(currentPos[0]).get(currentPos[1]).getHasCreature() instanceof Human) {
                            //adds human image to current pos
                            addHumanImage(uiState.getGridPane());
                            //update position in ui
                            uiState.getPos().setText(Arrays.toString(this.getCurrentPos()));
                            //remove goblin image from gridPane
                            ImageView g = (ImageView) uiState.getGridPane().lookup("#goblin" + this.currentPos[1] + this.currentPos[0]);
                            uiState.getGridPane().getChildren().remove(g);
                        } else if (uiState != null) {
                            //remove human image from grid
                            ImageView h = (ImageView) uiState.getGridPane().lookup("#human");
                            uiState.getGridPane().getChildren().remove(h);
                            uiState.getPos().setText("Dead!");
                        }

                        return 2;

                    } else {

                        //valid move, no creature in destination
                        gw.get(currentPos[0]).get(currentPos[1]).removeCreature();
                        //remove human image from grid
                        if (uiState != null) {
                            ImageView h = (ImageView) uiState.getGridPane().lookup("#human");
                            uiState.getGridPane().getChildren().remove(h);
                        }
                        //adjust position
                        this.currentPos[0]--;

                        if (uiState != null) {
                            uiState.getTextArea().appendText("\nYou move north.");
                        }

                        //add human to current position
                        gw.get(currentPos[0]).get(currentPos[1]).setHasCreature(this);
                        if (uiState != null) {
                            //adds human image to current pos
                            addHumanImage(uiState.getGridPane());
                            uiState.getPos().setText(Arrays.toString(this.getCurrentPos()));
                        }

                        return 1;
                    }

                case "s":

                    //check for move out of bounds
                    if (this.currentPos[0] + 1 > GameWorld.getLandList().size() - 1) {
                        System.out.println("Invalid move.");
                        if (uiState != null) {
                            uiState.getTextArea().appendText("\nInvalid move.");
                        }
                        return 0;
                    }

                    //valid move, creature in destination
                    if (gw.get(currentPos[0] + 1).get(currentPos[1]).getHasCreature() != null) {

                        //remove creature from current position
                        gw.get(currentPos[0]).get(currentPos[1]).removeCreature();
                        //remove human image from grid
                        if (uiState != null) {
                            ImageView h = (ImageView) uiState.getGridPane().lookup("#human");
                            uiState.getGridPane().getChildren().remove(h);
                        }
                        //adjust position
                        this.currentPos[0]++;
                        if (uiState != null) {
                            uiState.getTextArea().appendText("\nYou move south and encounter a creature.");
                        }
                        //invoke combat, set winner
                        startCombat(uiState);
                        if (uiState != null && gw.get(currentPos[0]).get(currentPos[1]).getHasCreature() instanceof Human) {
                            //adds human image to current pos
                            addHumanImage(uiState.getGridPane());
                            //update position in ui
                            uiState.getPos().setText(Arrays.toString(this.getCurrentPos()));
                            //remove goblin image from gridPane
                            ImageView g = (ImageView) uiState.getGridPane().lookup("#goblin" + this.currentPos[1] + this.currentPos[0]);
                            uiState.getGridPane().getChildren().remove(g);
                        } else if (uiState != null) {
                            //remove human image from grid
                            ImageView h = (ImageView) uiState.getGridPane().lookup("#human");
                            uiState.getGridPane().getChildren().remove(h);
                            uiState.getPos().setText("Dead!");
                        }

                        return 2;

                    } else {

                        //valid move, no creature in destination
                        gw.get(currentPos[0]).get(currentPos[1]).removeCreature();
                        //remove human image from grid
                        if (uiState != null) {
                            ImageView h = (ImageView) uiState.getGridPane().lookup("#human");
                            uiState.getGridPane().getChildren().remove(h);
                        }
                        //adjust position
                        this.currentPos[0]++;

                        if (uiState != null) {
                            uiState.getTextArea().appendText("\nYou move south.");
                        }

                        //add human to current position
                        gw.get(currentPos[0]).get(currentPos[1]).setHasCreature(this);
                        if (uiState != null) {
                            //adds human image to current pos
                            addHumanImage(uiState.getGridPane());
                            uiState.getPos().setText(Arrays.toString(this.getCurrentPos()));
                        }

                        return 1;
                    }

                case "e":

                    //check for move out of bounds
                    if (this.currentPos[1] + 1 > GameWorld.getLandList().get(0).size() - 1) {
                        System.out.println("Invalid move.");
                        if (uiState != null) {
                            uiState.getTextArea().appendText("\nInvalid move.");
                        }
                        return 0;
                    }

                    //valid move, creature in destination
                    if (gw.get(currentPos[0]).get(currentPos[1] + 1).getHasCreature() != null) {

                        //remove creature from current position
                        gw.get(currentPos[0]).get(currentPos[1]).removeCreature();
                        //remove human image from grid
                        if (uiState != null) {
                            ImageView h = (ImageView) uiState.getGridPane().lookup("#human");
                            uiState.getGridPane().getChildren().remove(h);
                        }
                        //adjust position
                        this.currentPos[1]++;
                        if (uiState != null) {
                            uiState.getTextArea().appendText("\nYou move east and encounter a creature.");
                        }
                        //invoke combat, set winner
                        startCombat(uiState);
                        if (uiState != null && gw.get(currentPos[0]).get(currentPos[1]).getHasCreature() instanceof Human) {
                            //adds human image to current pos
                            addHumanImage(uiState.getGridPane());
                            //update position in ui
                            uiState.getPos().setText(Arrays.toString(this.getCurrentPos()));
                            //remove goblin image from gridPane
                            ImageView g = (ImageView) uiState.getGridPane().lookup("#goblin" + this.currentPos[1] + this.currentPos[0]);
                            uiState.getGridPane().getChildren().remove(g);
                        } else if (uiState != null) {
                            //remove human image from grid
                            ImageView h = (ImageView) uiState.getGridPane().lookup("#human");
                            uiState.getGridPane().getChildren().remove(h);
                            uiState.getPos().setText("Dead!");
                        }

                        return 2;

                    } else {

                        //valid move, no creature in destination
                        gw.get(currentPos[0]).get(currentPos[1]).removeCreature();
                        //remove human image from grid
                        if (uiState != null) {
                            ImageView h = (ImageView) uiState.getGridPane().lookup("#human");
                            uiState.getGridPane().getChildren().remove(h);
                        }
                        //adjust position
                        this.currentPos[1]++;

                        if (uiState != null) {
                            uiState.getTextArea().appendText("\nYou move east.");
                        }

                        //add human to current position
                        gw.get(currentPos[0]).get(currentPos[1]).setHasCreature(this);
                        if (uiState != null) {
                            //adds human image to current pos
                            addHumanImage(uiState.getGridPane());
                            uiState.getPos().setText(Arrays.toString(this.getCurrentPos()));
                        }

                        return 1;
                    }

                case "w":

                    //check for move out of bounds
                    if (this.currentPos[1] - 1 < 0) {
                        System.out.println("Invalid move.");
                        if (uiState != null) {
                            uiState.getTextArea().appendText("\nInvalid move.");
                        }
                        return 0;
                    }

                    //valid move, creature in destination
                    if (gw.get(currentPos[0]).get(currentPos[1] - 1).getHasCreature() != null) {

                        //remove creature from current position
                        gw.get(currentPos[0]).get(currentPos[1]).removeCreature();
                        //remove human image from grid
                        if (uiState != null) {
                            ImageView h = (ImageView) uiState.getGridPane().lookup("#human");
                            uiState.getGridPane().getChildren().remove(h);
                        }
                        //adjust position
                        this.currentPos[1]--;
                        if (uiState != null) {
                            uiState.getTextArea().appendText("\nYou move west and encounter a creature.");
                        }
                        //invoke combat, set winner
                        startCombat(uiState);
                        if (uiState != null && gw.get(currentPos[0]).get(currentPos[1]).getHasCreature() instanceof Human) {
                            //adds human image to current pos
                            addHumanImage(uiState.getGridPane());
                            //update position in ui
                            uiState.getPos().setText(Arrays.toString(this.getCurrentPos()));
                            //remove goblin image from gridPane
                            ImageView g = (ImageView) uiState.getGridPane().lookup("#goblin" + this.currentPos[1] + this.currentPos[0]);
                            uiState.getGridPane().getChildren().remove(g);
                        } else if (uiState != null) {
                            //remove human image from grid
                            ImageView h = (ImageView) uiState.getGridPane().lookup("#human");
                            uiState.getGridPane().getChildren().remove(h);
                            uiState.getPos().setText("Dead!");
                        }

                        return 2;

                    } else {

                        //valid move, no creature in destination
                        gw.get(currentPos[0]).get(currentPos[1]).removeCreature();
                        //remove human image from grid
                        if (uiState != null) {
                            ImageView h = (ImageView) uiState.getGridPane().lookup("#human");
                            uiState.getGridPane().getChildren().remove(h);
                        }
                        //adjust position
                        this.currentPos[1]--;

                        if (uiState != null) {
                            uiState.getTextArea().appendText("\nYou move west.");
                        }

                        //add human to current position
                        gw.get(currentPos[0]).get(currentPos[1]).setHasCreature(this);
                        if (uiState != null) {
                            //adds human image to current pos
                            addHumanImage(uiState.getGridPane());
                            uiState.getPos().setText(Arrays.toString(this.getCurrentPos()));
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
            ImageView imageView = new ImageView(new Image(String.valueOf(getClass().getResource("image/Orcbolg.png"))));
            imageView.setFitHeight(60);
            imageView.setFitWidth(35);
            imageView.setId("human");
            gridPane.add(imageView, this.currentPos[1], this.currentPos[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void startCombat(UIState uiState) {
        gw.get(currentPos[0]).get(currentPos[1])
                .setHasCreature(Combat.resolveCombat(uiState, this, (Creature) gw.get(currentPos[0]).get(currentPos[1]).getHasCreature()));
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
