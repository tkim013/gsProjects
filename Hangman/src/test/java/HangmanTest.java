import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.SortedSet;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.*;

class HangmanTest {

    @Test
    void displayState0() {

        ByteArrayOutputStream outS = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outS);
        System.setOut(printStream);

        Hangman.display1(0);

        String[] lines = outS.toString().split(System.lineSeparator());
        String actual = lines[lines.length - 5];

        assertEquals("     |", actual, "displayState0 failed.");
    }

    @Test
    void displayState1() {

        ByteArrayOutputStream outS = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outS);
        System.setOut(printStream);

        Hangman.display1(1);

        String[] lines = outS.toString().split(System.lineSeparator());
        String actual = lines[lines.length - 5];

        assertEquals(" O   |", actual, "displayState1 failed.");
    }

    @Test
    void displayState2() {

        ByteArrayOutputStream outS = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outS);
        System.setOut(printStream);

        Hangman.display1(2);

        String[] lines = outS.toString().split(System.lineSeparator());
        String actual = lines[lines.length - 4];

        assertEquals(" |   |", actual, "displayState2 failed.");
    }

    @Test
    void displayState3() {

        ByteArrayOutputStream outS = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outS);
        System.setOut(printStream);

        Hangman.display1(3);

        String[] lines = outS.toString().split(System.lineSeparator());
        String actual = lines[lines.length - 3];

        assertEquals(" |   |", actual, "displayState3 failed.");
    }

    @Test
    void displayState4() {

        ByteArrayOutputStream outS = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outS);
        System.setOut(printStream);

        Hangman.display1(4);

        String[] lines = outS.toString().split(System.lineSeparator());
        String actual = lines[lines.length - 4];

        assertEquals("\\|   |", actual, "displayState4 failed.");
    }

    @Test
    void displayState5() {

        ByteArrayOutputStream outS = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outS);
        System.setOut(printStream);

        Hangman.display1(5);

        String[] lines = outS.toString().split(System.lineSeparator());
        String actual = lines[lines.length - 4];

        assertEquals("\\|/  |", actual, "displayState5 failed.");
    }

    @Test
    void displayState6() {

        ByteArrayOutputStream outS = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outS);
        System.setOut(printStream);

        Hangman.display1(6);

        String[] lines = outS.toString().split(System.lineSeparator());
        String actual = lines[lines.length - 2];

        assertEquals("/    |", actual, "displayState6 failed.");
    }

    @Test
    void displayState7() {

        ByteArrayOutputStream outS = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outS);
        System.setOut(printStream);

        Hangman.display1(7);

        String[] lines = outS.toString().split(System.lineSeparator());
        String actual = lines[lines.length - 2];

        assertEquals("/ \\  |", actual, "displayState7 failed.");
    }

    @Test
    void evaluatePlayAgainY() {
        assertTrue(Hangman.evaluatePlayAgain("yes"), "\"yes\" not entered.");
    }

    @Test
    void evaluatePlayAgainN() {
        assertTrue(Hangman.evaluatePlayAgain("no"), "\"no\" not entered.");
    }

    @Test
    void evaluatePlayAgainInvalid() {
        assertFalse(Hangman.evaluatePlayAgain("1"));
        assertFalse(Hangman.evaluatePlayAgain("#?!"));
        assertFalse(Hangman.evaluatePlayAgain("   "));
        assertFalse(Hangman.evaluatePlayAgain("a"));
        assertFalse(Hangman.evaluatePlayAgain("aaa"));
    }

    @Test
    void wordBoxMissed() {
        String word = "def";
        String missed = "abc";
        SortedSet<Character> set = new TreeSet<>();
        set.add('a');
        set.add('b');
        set.add('c');

        ByteArrayOutputStream outS = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outS);
        System.setOut(printStream);

        Hangman.wordBox(word, missed, set);

        String[] lines = outS.toString().split(System.lineSeparator());
        String actual = lines[lines.length - 3];

        assertEquals("Missed letters: abc", actual, "missed string failed.");
    }

    @Test
    void wordBoxWordString() {
        String word = "abcdef";
        String missed = "";
        SortedSet<Character> set = new TreeSet<>();
        set.add('a');
        set.add('c');
        set.add('f');

        ByteArrayOutputStream outS = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outS);
        System.setOut(printStream);

        Hangman.wordBox(word, missed, set);

        String[] lines = outS.toString().split(System.lineSeparator());
        String actual = lines[lines.length - 1];

        assertEquals("a_c__f", actual, "guessing display failed.");
    }

    @Test
    void wordBoxNoWin() {

        SortedSet<Character> set = new TreeSet<>();
        set.add('a');
        set.add('b');
        set.add('c');

        assertFalse(Hangman.wordBox("abcd", "", set), "no win condition failed.");
    }

    @Test
    void wordBoxWin() {

        SortedSet<Character> set = new TreeSet<>();
        set.add('a');
        set.add('b');
        set.add('c');
        set.add('d');

        assertTrue(Hangman.wordBox("abcd", "", set), "win condition failed.");
    }

    @Test
    void evaluateAlreadyGuessed() {

        SortedSet<Character> set = new TreeSet<>();
        set.add('a');
        set.add('b');
        set.add('c');

        assertEquals(0,  Hangman.evaluateGuess(set, "a"));
        assertEquals(0,  Hangman.evaluateGuess(set, "b"));
        assertEquals(0,  Hangman.evaluateGuess(set, "c"));
    }

    @Test
    void evaluateGuess() {

        SortedSet<Character> set = new TreeSet<>();
        set.add('a');

        assertEquals(1,  Hangman.evaluateGuess(set, "b"));
        assertEquals(1,  Hangman.evaluateGuess(set, "c"));
        assertEquals(1,  Hangman.evaluateGuess(set, "d"));
    }

    @Test
    void evaluateInvalidGuess() {

        SortedSet<Character> set = new TreeSet<>();

        assertEquals(2,  Hangman.evaluateGuess(set, "1"));
        assertEquals(2,  Hangman.evaluateGuess(set, ""));
        assertEquals(2,  Hangman.evaluateGuess(set, "aA"));
    }
}