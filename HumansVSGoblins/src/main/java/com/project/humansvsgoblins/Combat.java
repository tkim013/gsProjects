package com.project.humansvsgoblins;

public class Combat {

    //d6 for combat
    private final static int max = 6;
    private final static int min = 1;
    private final static int range = max - min + 1;

    public static Creature resolveCombat(Creature att, Creature def) {

        int attDamage;
        int defDamage;

        while (true) {

            //attacker damage = attacker strength + d6
            attDamage = (int) ((Math.random() * range) + min) + att.getStrength();
            System.out.println(att + " attacks " + def + " for " + attDamage + " damage.");
            def.setHealth(def.getHealth() - attDamage);

            if (def.getHealth() <= 0) {
                System.out.println(att + " is winner.");
                System.out.println("The real friends were the Gs we slew along the way.  What's a little murder between friends?");
                return att;
            }

            //defender damage = defender strength + d6
            defDamage = (int) ((Math.random() * range) + min) + def.getStrength();
            System.out.println(def + " attacks " + att + " for " + defDamage + " damage.");
            att.setHealth(att.getHealth() - defDamage);

            if (att.getHealth() <= 0) {
                System.out.println(def + " is winner.");
                return def;
            }

            System.out.println(att + " health: " + att.getHealth() + "\n" + def + " health: " + def.getHealth());
        }
    }
}
