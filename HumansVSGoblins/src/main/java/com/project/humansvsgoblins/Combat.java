package com.project.humansvsgoblins;

public class Combat {

    //d6 for combat
    private final int max = 6;
    private final int min = 1;
    private final int range = max - min + 1;

    public Combat() {
    }

    public Creature resolveCombat(Creature att, Creature def) {

        int attDamage;
        int defDamage;

        System.out.println(att.getHealth());
        System.out.println(att.getStrength());

        while (true) {

            attDamage = (int) ((Math.random() * range) + min) + att.getStrength();
            System.out.println(att + " attacks " + def + " for " + attDamage + " damage.");
            def.setHealth(def.getHealth() - attDamage);

            if (def.getHealth() <= 0) {
                System.out.println(att + " is winner.");
                System.out.println("The real friends were the Gs we slew along the way.  What's a little murder between friends?");
                return att;
            }

            defDamage = (int) ((Math.random() * range) + min) + def.getStrength();
            System.out.println(def + " attacks " + att + " for " + defDamage + " damage.");
            att.setHealth(att.getHealth() - defDamage);

            if (att.getHealth() <= 0) {
                System.out.println(def + " is winner.");
                return def;
            }
        }
    }
}
