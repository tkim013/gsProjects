import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;

public class Hangman {
    public static void main(String[] args) {

        SortedSet<Character> guessSet = new TreeSet<>();
        Scanner in = new Scanner(System.in);
        RandomWord rWord = new RandomWord();

        String secretWord;
        String guessString = "";

        int guessState; //hangman attempts
        int guessResult;

        while (true) {
            rWord.generate();
            guessState = 0;
            secretWord = rWord.getWord();
            System.out.println(secretWord);

            while (true) {
                //display here

                display1(guessState);

                if (guessState == 7) {
                    System.out.println("you died");
                    break;
                }

                guessSet.add('a');
                guessSet.add('e');
                guessSet.add('b');
                wordBox(secretWord, guessSet);

                //guess input
                while (true) {

                    System.out.println("Guess a letter.\n");

                    try {
                        guessString = in.nextLine();
                    } catch (Exception e) {
                        System.out.println("Exception guess input");
                    }

                    guessResult = evaluateGuess(guessSet, guessString);

                    if (guessResult == 1) {
                        guessSet.add(Character.toLowerCase(guessString.charAt(0)));
                        break;
                    }
                }

                guessState++;
                //resultDisplay
            }
            break;
        }
    }

    public static void display1(int guessState) {

        System.out.println("H A N G M A N");
        System.out.println(" +---+");
        System.out.println(guessState == 0 ? "     |" : " O   |");
        System.out.println(guessState < 2 ? "     |" : guessState < 4 ? " |   |" : guessState < 5 ? "\\|   |" : "\\|/  |");
        System.out.println(guessState < 3 ? "     |" : " |   |");
        System.out.println(guessState < 6 ? "     |" : guessState < 7 ? "/    |" : "/ \\  |");
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

        System.out.println();
    }

    private static int evaluateGuess(SortedSet<Character> set, String guess) {
            if (!guess.isEmpty() && set.contains(guess.charAt(0)) && Character.isLetter(guess.charAt(0)) && guess.length() == 1) {

                System.out.println("You have already guessed that letter. Choose again.\n");
                return 0;
            } else if (!guess.isEmpty() && Character.isLetter(guess.charAt(0)) && guess.length() == 1) {
                return 1;
            } else {
                System.out.println("Invalid input. Choose again.\n");
            }
        return 2;
    }
}