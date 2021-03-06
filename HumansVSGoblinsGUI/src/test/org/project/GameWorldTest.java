package org.project;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GameWorldTest {
    GameWorld gw = new GameWorld();


    @Test
    void populateGoblins() {
        int numberOfGoblins = 7;
        int actualGoblins = 0;

        //random spawn goblins in GameWorld
        GameWorld.populateGoblins(null, numberOfGoblins, 1, 0);

        //count Goblin in GameWorld
        for (List<Land> list : gw.getLandList()) {
            for (Land l : list) {
                if (l.getHasCreature() instanceof Goblin) {
                    actualGoblins++;
                }
            }
        }

        assertEquals(numberOfGoblins, actualGoblins, "Goblin spawn error.");
    }
}