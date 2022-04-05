package com.project.humansvsgoblins;

import java.util.Arrays;
import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        GameWorld gw = new GameWorld();
        Human h = new Human(50, 5, new int[]{5, 5});
        Goblin g1 = new Goblin();
        Goblin g2 = new Goblin();
        Goblin g3 = new Goblin();
        Goblin g4 = new Goblin();
        String m = "";

//        System.out.println(Combat.resolveCombat(h, g1));
        gw.getLandList().get(4).get(5).setHasCreature(g1);
        gw.getLandList().get(6).get(5).setHasCreature(g2);
        gw.getLandList().get(5).get(6).setHasCreature(g3);
        gw.getLandList().get(5).get(4).setHasCreature(g4);


        while (true) {

            System.out.println(gw);
            System.out.println(h.getHealth());
            System.out.println(Arrays.toString(h.getCurrentPos()));
            System.out.println("Move human. (n/s/e/w) or \"q\" to quit");

            try {
                m = in.nextLine().toLowerCase();
            } catch (Throwable e) {
                e.printStackTrace();
            }

            if (m.equals("q")) {
                in.close();
                break;
            }

            h.move(m);
        }
    }
}
