package com.project.humansvsgoblins;

import java.util.Arrays;
import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        GameWorld gw = new GameWorld();
        Human h = new Human(50, 5, new int[]{5, 5});
        Goblin g1 = new Goblin();
        String m = "";

        Combat combat = new Combat();
        System.out.println(combat.resolveCombat(h, g1));


//        while (true) {
//
//            System.out.println(gw);
//            System.out.println("Move human. (n/s/e/w) or \"q\" to quit");
//
//            try {
//                m = in.nextLine().toLowerCase();
//            } catch (Throwable e) {
//                e.printStackTrace();
//            }
//
//            if (m.equals("q")) {
//                in.close();
//                break;
//            }
//
//            h.move(m);
//        }
    }
}
