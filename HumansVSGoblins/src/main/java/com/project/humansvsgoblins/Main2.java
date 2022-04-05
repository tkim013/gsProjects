package com.project.humansvsgoblins;

import java.util.Arrays;

public class Main2 {
    public static void main(String[] args) {
        GameWorld gw = new GameWorld();
        Human h = new Human(10, 10, new int[]{5, 5});
        System.out.println(GameWorld.getLandList().get(0).get(0).getHasCreature());

        System.out.println(gw);
        System.out.println(Arrays.toString(h.getCurrentPos()));
    }
}
