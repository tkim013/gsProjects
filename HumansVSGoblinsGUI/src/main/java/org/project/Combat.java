package org.project;

import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.media.AudioClip;

public class Combat {

    //d6 for combat
    private final static int max = 6;
    private final static int min = 1;
    private final static int range = max - min + 1;

    public static Creature resolveCombat(UIState uiState, Creature att, Creature def, int[] currentPos) {

        int attDamage;
        int defDamage;

        while (true) {

            //attacker damage = attacker strength + d6
            attDamage = (int) ((Math.random() * range) + min) + att.getStrength();
            System.out.println(att + " attacks " + def + " for " + attDamage + " damage.");
            if (uiState != null) {
                uiState.getTextArea().appendText("\n" + att.getId() + " attacks " + def.getId() + " for " + attDamage + " damage.");
            }
            def.setHealth(def.getHealth() - attDamage);

            if (def.getHealth() <= 0) {
                System.out.println(att + " is winner.");
                if (uiState != null && att instanceof Human) {
                    uiState.getTextArea().appendText("\nYou slaughter an innocent " + def.getId() + ".");
                    goblinSound();
                    addDeadGoblinImage(uiState.getGridPane(), currentPos);
                    GameWorld.setGoblinCount(GameWorld.getGoblinCount() - 1);
                    if (GameWorld.getGoblinCount() == 0) {
                        uiState.getGameOverGroup().setVisible(true);
                        uiState.getYouWinLabel().setVisible(true);
                        uiState.getMainGroup().setDisable(true);
                    }
                } else if (uiState != null) {
                    uiState.getTextArea().appendText("\n" + def.getId() + " dies.");
                }
                System.out.println("The real friends were the Gs we slew along the way.  What's a little murder between friends?");
                return att;
            }

            //defender damage = defender strength + d6
            defDamage = (int) ((Math.random() * range) + min) + def.getStrength();
            System.out.println(def + " attacks " + att + " for " + defDamage + " damage.");
            if (uiState != null) {
                uiState.getTextArea().appendText("\n" + def.getId() + " attacks " + att.getId() + " for " + defDamage + " damage.");
            }
            att.setHealth(att.getHealth() - defDamage);
            if (uiState != null && att instanceof Human) {
                uiState.getProgressBar().setProgress((double)att.getHealth()/att.getMaxHealth());
                uiState.getHp().setText(String.valueOf(att.getHealth()));
            }

            if (att.getHealth() <= 0) {
                System.out.println(def + " is winner.");
                if (uiState != null && att instanceof Human) {
                    uiState.getTextArea().appendText("\n" + def.getId() + " wins.  You die.  Game Over.");
                    uiState.getProgressBar().setProgress(ProgressBar.INDETERMINATE_PROGRESS);
                    humanSound();
                    uiState.getGameOverGroup().setVisible(true);
                    uiState.getGameOverLabel().setVisible(true);
                    uiState.getMainGroup().setDisable(true);
                } else if (uiState != null) {
                    uiState.getTextArea().appendText("\n" + att.getId() + " dies.");
                }
                return def;
            }

            System.out.println(att + " health: " + att.getHealth() + "\n" + def + " health: " + def.getHealth());
        }
    }
    private static void goblinSound() {
        //random goblins sounds
        try {
            String fName = null;

            int rand = (int) (Math.random() * 3);
            switch (rand) {
                case 0:
                    fName = "audio/sound/sf_sheep_03.mp3";
                    break;
                case 1:
                    fName = "audio/sound/sf_lamb_01.mp3";
                    break;
                case 2:
                    fName = "audio/sound/SF_sheep_bleat_03.mp3";
                    break;
                default:
                    break;
            }

            if (fName != null) {
                AudioClip audioClip = new AudioClip(Combat.class.getResource(fName).toExternalForm());
                audioClip.play();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void humanSound() {
        try {
            AudioClip audioClip = new AudioClip(Combat.class.getResource("audio/sound/Wilhelm_scream.mp3").toExternalForm());
            audioClip.play();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void addDeadGoblinImage(GridPane gridPane, int[] currentPos) {
        if (gridPane != null) {
            //adds goblin image to GUI
            try {
                String fName = null;
                int rand = (int) (Math.random() * 3);
                switch (rand) {
                    case 0:
                        fName = "image/mutton.png";
                        break;
                    case 1:
                        fName = "image/ribrack.png";
                        break;
                    case 2:
                        fName = "image/lamb.png";
                        break;
                    default:
                        break;
                }
                ImageView imageView = new ImageView(new Image(String.valueOf(GameWorld.class.getResource(fName))));
                imageView.setFitHeight(60);
                imageView.setFitWidth(60);
                gridPane.add(imageView, currentPos[1], currentPos[0]);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}