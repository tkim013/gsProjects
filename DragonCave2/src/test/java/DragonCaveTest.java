import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class DragonCaveTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testoption1() {

        //option 1 to be tested
        String option = "1";
        String expected = "You approach the cave...\n" +
                "It is dark and spooky...\n" +
                "A large dragon jumps out in front of you!  He opens his jaws and...\n" +
                "Gobbles you down in one bite!\n";

        //simulates user input
        String userInput = String.format(option,
                System.lineSeparator(),
                System.lineSeparator());
        ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(bais);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        System.setOut(printStream);

        DragonCave.main(null); // call the main method

        String[] lines = baos.toString().split(System.lineSeparator());
        String actual = lines[lines.length - 1];

        assertEquals(expected,actual);
    }

    @Test
    void testoption2() {

        //option 2 to be tested
        String option = "2";
        String expected = "You approach the cave...\n" +
                "The entrance seems inviting enough as you cautiously peak inside...\n" +
                "Inside the cave you find glittering gold and sparkling treasures!\n" +
                "A golden nimbus of light appears suddenly and coalesces into the form of a giant dragon!\n" +
                "The dragon generously shares his wealth with you!\n" +
                "At dinner time, it gobbles you up because... dragon!\n";

        //simulates user input
        String userInput = String.format(option,
                System.lineSeparator(),
                System.lineSeparator());
        ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(bais);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        System.setOut(printStream);

        DragonCave.main(null); // call the main method

        String[] lines = baos.toString().split(System.lineSeparator());
        String actual = lines[lines.length - 1];

        assertEquals(expected,actual);
    }
}