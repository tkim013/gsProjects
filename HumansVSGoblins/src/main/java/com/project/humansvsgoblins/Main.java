package com.project.humansvsgoblins;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        GameWorld gw = new GameWorld();
        Human h = new Human(50, 5, new int[]{5, 5});
        String m = "";

        //randomized goblin placement
        gw.populateGoblins(5);

        while (true) {

            System.out.println(gw);
            System.out.println("@ - Human  G - Goblin");

            //exit game when human health 0 or less
            if (h.getHealth() <= 0) {
                System.out.println("You bought the farm, met your maker, and pushing up daisies. GAME OVER.");
                break;
            }
            System.out.println("Health: " + h.getHealth() + "  Position: " + Arrays.toString(h.getCurrentPos()));
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