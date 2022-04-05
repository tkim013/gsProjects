package com.project.humansvsgoblins;

public class Human {
    private int health;
    private int strength;

    public Human(int health) {
        this.health = health;
    }

    @Override
    public String toString() {
        return "Human{}";
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
