import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;

public class Hangman {
    public static void main(String[] args) {

        SortedSet<Character> guessSet = new TreeSet<>(); //holds guessed characters
        Scanner in = new Scanner(System.in);
        RandomWord rWord = new RandomWord();
        StringBuilder missString = new StringBuilder(); //miss string for wordbox display

        String secretWord; //word to be guessed
        String guessString = ""; //stores character guess

        int guessState; //hangman missed letter attempts
        int guessResult; //used to check input validity

        while (true) {
            rWord.generate(); //generate random word
            guessState = 0; //reset guessState
            guessSet.clear(); //clear guessSet
            missString.setLength(0); //clear missString
            secretWord = rWord.getWord(); //set secret word

            while (true) {

                //display hangman by missed guesses
                display1(guessState);

                //check for complete hanged stickman victim, loop exit
                if (guessState == 7) {
                    System.out.println("Everyone reaches their eventual demise. It just came for you a bit sooner than " +
                            "expected. At least you finished life before anyone else you know right? W-I-N-N-E-R!!! but not.");
                    break;
                }

                //check for win, loop exit, wordbox display
                if (wordBox(secretWord, missString.toString(), guessSet)) {
                    System.out.println("Yes! The secret word is \"" + secretWord + "\"! You have won!\n");
                    break;
                }

                //guess input
                while (true) {

                    System.out.println("Guess a letter.\n");

                    try {
                        guessString = in.nextLine();
                    } catch (Exception e) {
                        System.out.println("Exception guess input");
                    }

                    //evaluates validity of guess
                    guessResult = evaluateGuess(guessSet, guessString);

                    //breaks loop on valid input
                    if (guessResult == 1) {
                        guessSet.add(Character.toLowerCase(guessString.charAt(0)));
                        break;
                    }
                }

                //if letter guessed is not in secret word, append missString and increment guessState
                if (!secretWord.contains(String.valueOf(guessString.charAt(0)))) {
                    missString.append(guessString.charAt(0));
                    guessState++;
                }
            }
            //repeat logic
            System.out.println("Do you want to play again? (yes or no)");
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

    private static boolean wordBox(String word, String missed, SortedSet<Character> set) {

        StringBuilder sb = new StringBuilder();

        System.out.print("Missed letters: ");
        System.out.println(missed);
        System.out.println();

        for (int i = 0; i < word.length(); i++) {

            sb.append(set.contains(word.charAt(i)) ? word.charAt(i) : "_");
        }

        System.out.println(sb);
        System.out.println();

        return word.equals(sb.toString());
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