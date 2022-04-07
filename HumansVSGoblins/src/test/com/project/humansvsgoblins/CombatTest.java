package com.project.humansvsgoblins;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CombatTest {
    GameWorld gw = new GameWorld();

    @Test
    void resolveCombatHumanVHuman() {
        Creature winner = Combat.resolveCombat(new Human(), new Human());
        assertTrue(winner instanceof Human, "Human vs Human failed.");
    }

    @Test
    void resolveCombatGoblinVGoblin() {
        Creature winner = Combat.resolveCombat(new Goblin(), new Goblin());
        assertTrue(winner instanceof Goblin, "Goblin vs Goblin failed.");
    }

    @Test
    void resolveCombatAttWin() {
        Human h = new Human(5, 20, new int[] {5, 5});
        assertEquals(h, Combat.resolveCombat(h, new Goblin()), "Attacker win failed.");
    }

    @Test
    void resolveCombatDefWin() {
        Human h = new Human(1, 0, new int[] {5, 5});
        Goblin g = new Goblin();
        assertEquals(g, Combat.resolveCombat(h, g), "Defender win failed.");
    }
}