package com.project.humansvsgoblins;

import java.util.ArrayList;

public class Human {
    ArrayList<ArrayList<Land>> gw = GameWorld.getLandList();

    private int health = 20;
    private int strength = 5;
    private int[] currentPos = {0, 0}; //element0 - n/s, element1 - e/w

    public Human() {
        gw.get(currentPos[0]).get(currentPos[1]).setHasCreature(this);
    }

    public Human(int health, int strength, int[] currentPos) {
        this.health = health;
        this.strength = strength;
        this.currentPos = currentPos;

        gw.get(currentPos[0]).get(currentPos[1]).setHasCreature(this);
    }

    @Override
    public String toString() {
        return "@";
    }

    public int move(String s) {

        //check valid moves
        switch (s) {
            case "n" :

                if (this.currentPos[0] - 1 < 0) {
                    System.out.println("Invalid move.");
                    return 0;
                }

                gw.get(currentPos[0]).get(currentPos[1]).setHasCreature(null);
                this.currentPos[0]--;
                gw.get(currentPos[0]).get(currentPos[1]).setHasCreature(this);

                return 1;

            case "s" :

                if (this.currentPos[0] + 1 > GameWorld.getLandList().size() - 1) {
                    System.out.println("Invalid move.");
                    return 0;
                }

                gw.get(currentPos[0]).get(currentPos[1]).setHasCreature(null);
                this.currentPos[0]++;
                gw.get(currentPos[0]).get(currentPos[1]).setHasCreature(this);

                return 1;

            case "e" :

                if (this.currentPos[1] + 1 > GameWorld.getLandList().get(0).size() - 1) {
                    System.out.println("Invalid move.");
                    return 0;
                }

                gw.get(currentPos[0]).get(currentPos[1]).setHasCreature(null);
                this.currentPos[1]++;
                gw.get(currentPos[0]).get(currentPos[1]).setHasCreature(this);

                return 1;

            case "w" :

                if (this.currentPos[1] - 1 < 0) {
                    System.out.println("Invalid move.");
                    return 0;
                }

                gw.get(currentPos[0]).get(currentPos[1]).setHasCreature(null);
                this.currentPos[1]--;
                gw.get(currentPos[0]).get(currentPos[1]).setHasCreature(this);

                return 1;

            default :

                System.out.println("Invalid move.");
                return 0;
        }
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int[] getCurrentPos() {
        return currentPos;
    }

    public void setCurrentPos(int[] currentPos) {
        this.currentPos = currentPos;
    }
}
