package com.project.humansvsgoblins;

public class Land {

    private Object hasCreature; //stores Human or Goblin

    public Land() {
    }

    @Override
    public String toString() {
        //if hasCreature not null, return toString() of hasCreature
        return getHasCreature() == null ? "_" : hasCreature.toString();
    }

    public Object getHasCreature() {
        return hasCreature;
    }

    public void setHasCreature(Object hasCreature) {
        this.hasCreature = hasCreature;
    }

    public void removeCreature() {
        this.hasCreature = null;
    }

}
