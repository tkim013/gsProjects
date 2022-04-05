package com.project.humansvsgoblins;

import java.util.ArrayList;

public class Human {
    private int health = 20;
    private int strength = 5;
    private int[] currentPos = {0, 0};

    public Human() {
        ArrayList<ArrayList<Land>> gw = GameWorld.getLandList();
        gw.get(currentPos[0]).get(currentPos[1]).setHasCreature(this);
    }

    public Human(int health, int strength, int[] currentPos) {
        this.health = health;
        this.strength = strength;
        this.currentPos = currentPos;

        ArrayList<ArrayList<Land>> gw = GameWorld.getLandList();
        gw.get(currentPos[0]).get(currentPos[1]).setHasCreature(this);
    }

    @Override
    public String toString() {
        return "@";
    }

    public void move(String s) {

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
