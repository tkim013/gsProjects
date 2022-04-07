package com.project.humansvsgoblins;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HumanTest {

    GameWorld gw = new GameWorld();
    Human h;


    @Test
    void moveNInvalid() {
        h = new Human(5, 5, new int[] {0,5});
        assertEquals(0, h.move("n"), "Invalid move north failed.");
    }

    @Test
    void moveSInvalid() {
        h = new Human(5, 5, new int[] {9,5});
        assertEquals(0, h.move("s"), "Invalid move south failed.");
    }

    @Test
    void moveEInvalid() {
        h = new Human(5, 5, new int[] {5,9});
        assertEquals(0, h.move("e"), "Invalid move east failed.");
    }

    @Test
    void moveWInvalid() {
        h = new Human(5, 5, new int[] {5,0});
        assertEquals(0, h.move("w"), "Invalid move west failed.");
    }

    @Test
    void moveInvalidInput() {
        h = new Human(5, 5, new int[] {5,5});
        assertEquals(0, h.move("! 5"), "Invalid input failed.");
    }

    @Test
    void moveN() {
        h = new Human(5, 5, new int[] {5,5});
        assertEquals(1, h.move("n"), "Move north failed.");
    }

    @Test
    void moveS() {
        h = new Human(5, 5, new int[] {5,5});
        assertEquals(1, h.move("s"), "Move south failed.");
    }

    @Test
    void moveE() {
        h = new Human(5, 5, new int[] {5,5});
        assertEquals(1, h.move("e"), "Move east failed.");
    }

    @Test
    void moveW() {
        h = new Human(5, 5, new int[] {5,5});
        assertEquals(1, h.move("w"), "Move west failed.");
    }

    @Test
    void moveNCombat() {
        h = new Human(5, 5, new int[] {5,5});
        gw.getLandList().get(h.getCurrentPos()[0] - 1).get(h.getCurrentPos()[1]).setHasCreature(new Goblin());
        assertEquals(2, h.move("n"), "Move north combat failed.");
    }

    @Test
    void moveSCombat() {
        h = new Human(5, 5, new int[] {5,5});
        gw.getLandList().get(h.getCurrentPos()[0] + 1).get(h.getCurrentPos()[1]).setHasCreature(new Goblin());
        assertEquals(2, h.move("s"), "Move south combat failed.");
    }

    @Test
    void moveECombat() {
        h = new Human(5, 5, new int[] {5,5});
        gw.getLandList().get(h.getCurrentPos()[0]).get(h.getCurrentPos()[1] + 1).setHasCreature(new Goblin());
        assertEquals(2, h.move("e"), "Move east combat failed.");
    }

    @Test
    void moveWCombat() {
        h = new Human(5, 5, new int[] {5,5});
        gw.getLandList().get(h.getCurrentPos()[0]).get(h.getCurrentPos()[1] - 1).setHasCreature(new Goblin());
        assertEquals(2, h.move("w"), "Move west combat failed.");
    }
}