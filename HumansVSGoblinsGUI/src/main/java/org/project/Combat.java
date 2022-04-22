package org.project;

import javafx.scene.control.ProgressBar;

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
                uiState.getTextArea().appendText(att.getId() + " attacks " + def.getId() + " for " + attDamage + " damage.\n");
            }
            def.setHealth(def.getHealth() - attDamage);

            if (def.getHealth() <= 0) {
                System.out.println(att + " is winner.");
                if (uiState != null && att instanceof Human) {
                    uiState.getTextArea().appendText("You slaughter an innocent " + def.getId() + ".\n");
                } else if (uiState != null) {
                    uiState.getTextArea().appendText(def.getId() + " dies.\n");
                }
                System.out.println("The real friends were the Gs we slew along the way.  What's a little murder between friends?");
                return att;
            }

            //defender damage = defender strength + d6
            defDamage = (int) ((Math.random() * range) + min) + def.getStrength();
            System.out.println(def + " attacks " + att + " for " + defDamage + " damage.");
            if (uiState != null) {
                uiState.getTextArea().appendText(def.getId() + " attacks " + att.getId() + " for " + defDamage + " damage.\n");
            }
            att.setHealth(att.getHealth() - defDamage);
            if (uiState != null && att instanceof Human) {
                uiState.getProgressBar().setProgress((double)att.getHealth()/att.getMaxHealth());
                uiState.getHp().setText(String.valueOf(att.getHealth()));
            }

            if (att.getHealth() <= 0) {
                System.out.println(def + " is winner.");
                if (uiState != null) {
                    uiState.getTextArea().appendText(def.getId() + " wins.  You die.  Game Over.\n");
                    uiState.getProgressBar().setProgress(ProgressBar.INDETERMINATE_PROGRESS);
                }
                return def;
            }

            System.out.println(att + " health: " + att.getHealth() + "\n" + def + " health: " + def.getHealth());
        }
    }
}
