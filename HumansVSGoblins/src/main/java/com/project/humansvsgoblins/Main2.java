package com.project.humansvsgoblins;

import java.util.Arrays;

public class Main2 {
    public static void main(String[] args) {
        GameWorld gw = new GameWorld();
        Human h = new Human(10, 10, new int[]{9, 0});
        System.out.println(GameWorld.getLandList().get(9).get(0).getHasCreature());

        h.move("n");

        System.out.println(gw);
        System.out.println(Arrays.toString(h.getCurrentPos()));

        System.out.println(GameWorld.getLandList().get(9).get(0).getHasCreature());

    }
}
