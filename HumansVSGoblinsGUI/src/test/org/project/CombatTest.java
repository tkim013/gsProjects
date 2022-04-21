package org.project;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CombatTest {
    GameWorld gw = new GameWorld();

    @Test
    void resolveCombatHumanVHuman() {
        Creature winner = Combat.resolveCombat(null, new Human(), new Human());
        assertTrue(winner instanceof Human, "Human vs Human failed.");
    }

    @Test
    void resolveCombatGoblinVGoblin() {
        Creature winner = Combat.resolveCombat(null, new Goblin(), new Goblin());
        assertTrue(winner instanceof Goblin, "Goblin vs Goblin failed.");
    }

    @Test
    void resolveCombatAttWin() {
        Human h = new Human(null, 5, 20, new int[] {5, 5});
        assertEquals(h, Combat.resolveCombat(null, h, new Goblin()), "Attacker win failed.");
    }

    @Test
    void resolveCombatDefWin() {
        Human h = new Human(null, 1, 0, new int[] {5, 5});
        Goblin g = new Goblin();
        assertEquals(g, Combat.resolveCombat(null, h, g), "Defender win failed.");
    }
}