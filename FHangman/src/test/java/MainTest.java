import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void gameHangedStateEndCondition() {
        Scanner in = new Scanner(System.in);
        Set<Character> guessSet = new HashSet<>();
        List<Character> secretWord = new ArrayList<>();
        secretWord.add('a');
        StringBuilder missString = new StringBuilder();
        String guessString = "";

        int state = 7;
        int score = 20;
        int actual = Main.game(in, guessSet, secretWord, missString, guessString, state, score);
        in.close();

        assertEquals(20, actual, "hanged state failed.");
    }

    @Test
    void gameWinEndCondition() {
        Scanner in = new Scanner(System.in);
        Set<Character> guessSet = new HashSet<>();
        guessSet.add('w');
        guessSet.add('i');
        guessSet.add('n');
        List<Character> secretWord = new ArrayList<>();
        secretWord.add('w');
        secretWord.add('i');
        secretWord.add('n');
        StringBuilder missString = new StringBuilder();
        String guessString = "";

        int state = 0;
        int score = 20;
        int actual = Main.game(in, guessSet, secretWord, missString, guessString, state, score);
        in.close();

        assertEquals(20, actual, "win state failed.");
    }

    @Test
    void display0() {

        ByteArrayOutputStream outS = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outS);
        System.setOut(printStream);

        Main.display(0);

        String[] lines = outS.toString().split(System.lineSeparator());
        String actual = lines[lines.length - 5];

        assertEquals("      |", actual, "displayState0 failed.");
    }


    @Test
    void display1() {

        ByteArrayOutputStream outS = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outS);
        System.setOut(printStream);

        Main.display(1);

        String[] lines = outS.toString().split(System.lineSeparator());
        String actual = lines[lines.length - 5];

        assertEquals("  O   |", actual, "displayState1 failed.");
    }

    @Test
    void display2() {

        ByteArrayOutputStream outS = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outS);
        System.setOut(printStream);

        Main.display(2);

        String[] lines = outS.toString().split(System.lineSeparator());
        String actual = lines[lines.length - 4];

        assertEquals("  |   |", actual, "displayState2 failed.");
    }

    @Test
    void display3() {

        ByteArrayOutputStream outS = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outS);
        System.setOut(printStream);

        Main.display(3);

        String[] lines = outS.toString().split(System.lineSeparator());
        String actual = lines[lines.length - 3];

        assertEquals("  |   |", actual, "displayState3 failed.");
    }

    @Test
    void display4() {

        ByteArrayOutputStream outS = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outS);
        System.setOut(printStream);

        Main.display(4);

        String[] lines = outS.toString().split(System.lineSeparator());
        String actual = lines[lines.length - 4];

        assertEquals(" \\|   |", actual, "displayState4 failed.");
    }

    @Test
    void display5() {

        ByteArrayOutputStream outS = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outS);
        System.setOut(printStream);

        Main.display(5);

        String[] lines = outS.toString().split(System.lineSeparator());
        String actual = lines[lines.length - 4];

        assertEquals(" \\|/  |", actual, "displayState5 failed.");
    }

    @Test
    void display6() {

        ByteArrayOutputStream outS = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outS);
        System.setOut(printStream);

        Main.display(6);

        String[] lines = outS.toString().split(System.lineSeparator());
        String actual = lines[lines.length - 2];

        assertEquals(" /    |", actual, "displayState6 failed.");
    }

    @Test
    void display7() {
        ByteArrayOutputStream outS = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outS);
        System.setOut(printStream);

        Main.display(7);

        String[] lines = outS.toString().split(System.lineSeparator());
        String actual = lines[lines.length - 2];

        assertEquals(" / \\  |", actual, "displayState7 failed.");
    }

    @Test
    void wordBoxMissed() {
        List<Character> word = new ArrayList<>();
        word.add('d');
        word.add('e');
        word.add('f');
        String missed = "abc";
        Set<Character> set = new HashSet<>();
        set.add('a');
        set.add('b');
        set.add('c');

        ByteArrayOutputStream outS = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outS);
        System.setOut(printStream);

        Main.wordBox(word, missed, set);

        String[] lines = outS.toString().split(System.lineSeparator());
        String actual = lines[lines.length - 3];

        assertEquals("Missed letters: abc", actual, "missed string failed.");
    }

    @Test
    void wordBoxWordString() {
        List<Character> word = new ArrayList<>();
        word.add('a');
        word.add('b');
        word.add('c');
        word.add('d');
        word.add('e');
        word.add('f');
        String missed = "";
        Set<Character> set = new HashSet<>();
        set.add('a');
        set.add('c');
        set.add('f');

        ByteArrayOutputStream outS = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outS);
        System.setOut(printStream);

        Main.wordBox(word, missed, set);

        String[] lines = outS.toString().split(System.lineSeparator());
        String actual = lines[lines.length - 1];

        assertEquals("a_c__f", actual, "guessing display failed.");
    }

    @Test
    void wordBoxNoWin() {

        List<Character> word = new ArrayList<>();
        word.add('a');
        word.add('b');
        word.add('c');
        word.add('d');
        Set<Character> set = new HashSet<>();
        set.add('a');
        set.add('b');
        set.add('c');

        assertFalse(Main.wordBox(word, "", set), "no win condition failed.");
    }

    @Test
    void wordBoxWin() {

        List<Character> word = new ArrayList<>();
        word.add('a');
        word.add('b');
        word.add('c');
        word.add('d');
        Set<Character> set = new HashSet<>();
        set.add('a');
        set.add('b');
        set.add('c');
        set.add('d');

        assertTrue(Main.wordBox(word, "", set), "win condition failed.");
    }

    @Test
    void evaluateAlreadyGuessed() {

        Set<Character> set = new HashSet<>();
        set.add('a');
        set.add('b');
        set.add('c');

        assertEquals(0, Main.evaluateGuess(set, "a"));
        assertEquals(0, Main.evaluateGuess(set, "b"));
        assertEquals(0, Main.evaluateGuess(set, "c"));
    }

    @Test
    void evaluateGuess() {

        Set<Character> set = new HashSet<>();
        set.add('a');

        assertEquals(1, Main.evaluateGuess(set, "b"));
        assertEquals(1, Main.evaluateGuess(set, "c"));
        assertEquals(1, Main.evaluateGuess(set, "d"));
    }

    @Test
    void evaluateInvalidGuess() {

        Set<Character> set = new HashSet<>();

        assertEquals(2, Main.evaluateGuess(set, "1"));
        assertEquals(2, Main.evaluateGuess(set, ""));
        assertEquals(2, Main.evaluateGuess(set, "aA"));
    }

    @Test
    void evaluatePlayAgainY() {
        assertTrue(Main.evaluatePlayAgain("yes"), "\"yes\" not entered.");
    }

    @Test
    void evaluatePlayAgainN() {
        assertTrue(Main.evaluatePlayAgain("no"), "\"no\" not entered.");
    }

    @Test
    void evaluatePlayAgainInvalid() {
        assertFalse(Main.evaluatePlayAgain("1"));
        assertFalse(Main.evaluatePlayAgain("#?!"));
        assertFalse(Main.evaluatePlayAgain(""));
        assertFalse(Main.evaluatePlayAgain("   "));
        assertFalse(Main.evaluatePlayAgain("a"));
        assertFalse(Main.evaluatePlayAgain("aaa"));
    }

    @Test
    void evaluateNameValid() {
        assertTrue(Main.evaluateName("!s;aj%"), "valid name input failed.");
    }

    @Test
    void evaluateNameInvalid() {
        assertFalse(Main.evaluateName("spa ce"), "invalid name input failed.");
    }
}