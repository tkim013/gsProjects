package com.project.humansvsgoblins;

public class Goblin extends Creature {
    private int health = 50;
    private int strength = 5;

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
