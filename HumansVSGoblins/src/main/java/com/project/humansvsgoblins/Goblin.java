package com.project.humansvsgoblins;

public class Goblin extends Creature {
    private int health = 20;
    private int strength = 3;

    public Goblin() {
    }

    @Override
    public String toString() {
        return "G";
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
}