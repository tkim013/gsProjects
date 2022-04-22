package org.project;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HumanTest {

    GameWorld gw = new GameWorld();
    Human h;


    @Test
    void moveNInvalid() {
        h = new Human(null, 5, 5, new int[] {0,5});
        assertEquals(0, h.move(null, "n"), "Invalid move north failed.");
    }

    @Test
    void moveSInvalid() {
        h = new Human(null, 5, 5, new int[] {9,5});
        assertEquals(0, h.move(null, "s"), "Invalid move south failed.");
    }

    @Test
    void moveEInvalid() {
        h = new Human(null, 5, 5, new int[] {5,9});
        assertEquals(0, h.move(null, "e"), "Invalid move east failed.");
    }

    @Test
    void moveWInvalid() {
        h = new Human(null, 5, 5, new int[] {5,0});
        assertEquals(0, h.move(null, "w"), "Invalid move west failed.");
    }

    @Test
    void moveInvalidInput() {
        h = new Human(null, 5, 5, new int[] {5,5});
        assertEquals(0, h.move(null, "! 5"), "Invalid input failed.");
    }

    @Test
    void moveN() {
        h = new Human(null, 5, 5, new int[] {5,5});
        assertEquals(1, h.move(null, "n"), "Move north failed.");
    }

    @Test
    void moveS() {
        h = new Human(null, 5, 5, new int[] {5,5});
        assertEquals(1, h.move(null, "s"), "Move south failed.");
    }

    @Test
    void moveE() {
        h = new Human(null, 5, 5, new int[] {5,5});
        assertEquals(1, h.move(null, "e"), "Move east failed.");
    }

    @Test
    void moveW() {
        h = new Human(null, 5, 5, new int[] {5,5});
        assertEquals(1, h.move(null, "w"), "Move west failed.");
    }

    @Test
    void moveNCombat() {
        h = new Human(null, 5, 5, new int[] {5,5});
        gw.getLandList().get(h.getCurrentPos()[0] - 1).get(h.getCurrentPos()[1]).setHasCreature(new Goblin());
        assertEquals(2, h.move(null, "n"), "Move north combat failed.");
    }

    @Test
    void moveSCombat() {
        h = new Human(null, 5, 5, new int[] {5,5});
        gw.getLandList().get(h.getCurrentPos()[0] + 1).get(h.getCurrentPos()[1]).setHasCreature(new Goblin());
        assertEquals(2, h.move(null, "s"), "Move south combat failed.");
    }

    @Test
    void moveECombat() {
        h = new Human(null, 5, 5, new int[] {5,5});
        gw.getLandList().get(h.getCurrentPos()[0]).get(h.getCurrentPos()[1] + 1).setHasCreature(new Goblin());
        assertEquals(2, h.move(null, "e"), "Move east combat failed.");
    }

    @Test
    void moveWCombat() {
        h = new Human(null, 5, 5, new int[] {5,5});
        gw.getLandList().get(h.getCurrentPos()[0]).get(h.getCurrentPos()[1] - 1).setHasCreature(new Goblin());
        assertEquals(2, h.move(null, "w"), "Move west combat failed.");
    }
}