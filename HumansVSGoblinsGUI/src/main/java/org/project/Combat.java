package org.project;

import javafx.scene.control.ProgressBar;
import javafx.scene.media.AudioClip;

import java.io.File;

public class Combat {

    //d6 for combat
    private final static int max = 6;
    private final static int min = 1;
    private final static int range = max - min + 1;

    public static Creature resolveCombat(UIState uiState, Creature att, Creature def) {

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
                } else if (uiState != null) {
                    uiState.getTextArea().appendText("\n" + att.getId() + " dies.");
                }
                return def;
            }

            System.out.println(att + " health: " + att.getHealth() + "\n" + def + " health: " + def.getHealth());
        }
    }
    private static void goblinSound() {
        try {
            AudioClip audioClip = new AudioClip(new File("src/main/resources/org/project/sf_sheep_03.mp3").toURI().toString());
            audioClip.play();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void humanSound() {
        try {
            AudioClip audioClip = new AudioClip(new File("src/main/resources/org/project/Wilhelm_scream.mp3").toURI().toString());
            audioClip.play();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
