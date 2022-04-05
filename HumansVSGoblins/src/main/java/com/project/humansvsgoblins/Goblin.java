package com.project.humansvsgoblins;

public class Goblin {
    private int health;
    private int strength;

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
