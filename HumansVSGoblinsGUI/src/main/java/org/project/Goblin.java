package org.project;

public class Goblin extends Creature {

    private String id = "\"Goblin\" totally not a sheep";
    private int health = 20;

    private int maxHealth = 20;
    private int strength = 3;

    public Goblin() {
    }

    @Override
    public String toString() {
        return "G";
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
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