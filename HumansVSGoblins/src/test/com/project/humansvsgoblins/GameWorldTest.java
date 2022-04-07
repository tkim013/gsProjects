package com.project.humansvsgoblins;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameWorldTest {
    GameWorld gw = new GameWorld();


    @Test
    void populateGoblins() {
        int numberOfGoblins = 7;
        int actualGoblins = 0;

        //random spawn goblins in GameWorld
        GameWorld.populateGoblins(numberOfGoblins);

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