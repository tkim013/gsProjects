import java.util.SortedSet;
import java.util.TreeSet;

public class Hangman {
    public static void main(String[] args) {

        SortedSet<Character> guessSet = new TreeSet<>();
        RandomWord rWord = new RandomWord();

        String cont = "yes";
        String secretWord;

        int guessState; //hangman attempts

        while (cont.equals("yes")) {
            rWord.generate();
            guessState = 0;
            secretWord = rWord.getWord();
            System.out.println(secretWord);

            System.out.println("H A N G M A N");

            //display here

            display1(6);

            guessSet.add('a');
            guessSet.add('e');
            guessSet.add('b');
            wordBox(secretWord, guessSet);
            //guess input

            //resultDisplay

            cont = "no";
        }
    }

    public static void display1(int guessState) {

        String display;

        System.out.println(" +---+");
        display = guessState == 0 ? "     |" : " O   |";
        System.out.println(display);
        display = guessState < 2 ? "     |" : guessState < 4 ? " |   |" : guessState < 5 ? "\\|   |" : "\\|/  |";
        System.out.println(display);
        display = guessState < 3 ? "     |" : " |   |";
        System.out.println(display);
        display = guessState < 6 ? "     |" : guessState < 7 ? "/    |" : "/ \\  |";
        System.out.println(display);
        System.out.println("    ===");
    }

    private static void wordBox(String word, SortedSet<Character> set) {

        System.out.print("Missed letters: ");
        for (Character c : set) {
            if (!word.contains(c.toString())) {
                System.out.print(c);
            }
        }
        System.out.println();

        for (int i = 0; i < word.length(); i++) {

            System.out.print(set.contains(word.charAt(i)) ? word.charAt(i) : "_");
        }

        System.out.println("\nGuess a letter.\n");
    }
}