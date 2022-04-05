package com.project.humansvsgoblins;

public class Land {
    private boolean hasHuman;
    private boolean hasGoblin;
    private Object hasCreature;

    public Land() {
        setHasHuman(false);
        setHasGoblin(false);
    }

    @Override
    public String toString() {
        return "_";
    }

    public Object getHasCreature() {
        return hasCreature;
    }

    public void setHasCreature(Object hasCreature) {
        this.hasCreature = hasCreature;
    }

    public void enterCreature(Object o) {
        this.hasCreature = o;
    }

    public void exitCreature() {
        this.hasCreature = null;
    }

    public boolean isHasHuman() {
        return hasHuman;
    }

    public void setHasHuman(boolean hasHuman) {
        this.hasHuman = hasHuman;
    }

    public boolean isHasGoblin() {
        return hasGoblin;
    }

    public void setHasGoblin(boolean hasGoblin) {
        this.hasGoblin = hasGoblin;
    }
}
