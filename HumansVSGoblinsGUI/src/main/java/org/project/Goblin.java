package org.project;

public class Goblin extends Creature {

    private String id = "\"Goblin\" totally not a sheep";
    private int health = 20;

    private int maxHealth = 20;
    private int strength = 3;

    public Goblin() {
    }

    public Goblin(int h, int s) {
        this.health = h;
        this.maxHealth = h;
        this.strength = s;
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

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public void setHealth(int health) {
        this.health = health;
    }

    @Override
    public int getMaxHealth() {
        return maxHealth;
    }

    @Override
    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }
}