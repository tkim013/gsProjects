import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Random;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class GuessTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void evaluateCorrectGuess() {

        assertTrue(Guess.evaluateGuess(10, 10), "Correct guess failed.");
    }

    @Test
    void evaluateWrongGuess() {

        assertFalse(Guess.evaluateGuess(10, 5), "Wrong guess failed.");
    }

    @Test
    void testHighGuess() {

        String expected = "Your guess is too high.\n" +
                "Take a guess.\n";

        ByteArrayOutputStream outS = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outS);
        System.setOut(printStream);

        Guess.evaluateGuess(10, 11);

        String[] lines = outS.toString().split(System.lineSeparator());
        String actual = lines[lines.length - 1];

        assertEquals(expected, actual, "High guess failed.");
    }

    @Test
    void testLowGuess() {

        String expected = "Your guess is too low.\n" +
                "Take a guess.\n";

        ByteArrayOutputStream outS = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outS);
        System.setOut(printStream);

        Guess.evaluateGuess(10, 9);

        String[] lines = outS.toString().split(System.lineSeparator());
        String actual = lines[lines.length - 1];

        assertEquals(expected, actual, "Low guess failed.");
    }

    @Test
    void resultString6tries() {
        String expected = "Sorry, you could not guess the number in 6 tries.\n";

        ByteArrayOutputStream outS = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outS);
        System.setOut(printStream);

        Guess.resultString(6, "<name>");

        String[] lines = outS.toString().split(System.lineSeparator());
        String actual = lines[lines.length - 2];

        assertEquals(expected, actual, "resultString 6 tries failed.");
    }

    @Test
    void resultStringFirstTry() {

        String name = "<name>";
        String expected = "Good job, " + name + "!  You guessed my number on your first guess!\n";

        ByteArrayOutputStream outS = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outS);
        System.setOut(printStream);

        Guess.resultString(1, name);

        String[] lines = outS.toString().split(System.lineSeparator());
        String actual = lines[lines.length - 2];

        assertEquals(expected, actual, "resultString first try failed.");
    }

    @Test
    void resultStringRetrySuccess() {

        Random rand = new Random();
        int count = rand.nextInt(5);
        count++;

        String name = "<name>";
        String expected = "Good job, " + name + "!  You guessed my number in " + count + " guesses!\n";

        ByteArrayOutputStream outS = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outS);
        System.setOut(printStream);

        Guess.resultString(count, name);

        String[] lines = outS.toString().split(System.lineSeparator());
        String actual = lines[lines.length - 2];

        assertEquals(expected, actual, "resultString retry success failed");
    }

    @Test
    void guessInput10() {

        String userInput = "10";
        Scanner in = new Scanner(userInput);

        assertEquals(10, Guess.guessInput(in), "guess input failed.");
        in.close();
    }

    @Test
    void guessInputNotInt() {

        String userInput = "not Integer";
        Scanner in = new Scanner(userInput);

        assertEquals(-1, Guess.guessInput(in), "guess non int input failed.");
        in.close();
    }



    @Test
    void nameInput() {

        String userInput = "bobbert";
        Scanner in = new Scanner(userInput);

        assertEquals("Bobbert", Guess.nameInput(in), "name input failed.");
        in.close();
    }

    @Test
    void getContY() {
        String userInput = "Y";
        Scanner in = new Scanner(userInput);

        assertEquals("y", Guess.getCont(in), "cont input y failed.");
        in.close();
    }

    @Test
    void getContN() {
        String userInput = "N";
        Scanner in = new Scanner(userInput);

        assertEquals("n", Guess.getCont(in), "cont input n failed.");
        in.close();
    }

    @Test
    void getContInvalid() {
        String userInput = "11yidt";
        String expected = "Invalid Input";

        Scanner in = new Scanner(userInput);
        ByteArrayOutputStream outS = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outS);
        System.setOut(printStream);

        Guess.getCont(in);

        String[] lines = outS.toString().split(System.lineSeparator());
        String actual = lines[lines.length - 1];

        assertEquals(expected, actual, "cont invalid input failed.");
        in.close();
    }
}