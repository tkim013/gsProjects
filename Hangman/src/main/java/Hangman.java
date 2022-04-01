import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;

public class Hangman {
    public static void main(String[] args) {

        SortedSet<Character> guessSet = new TreeSet<>();
        Scanner in = new Scanner(System.in);
        RandomWord rWord = new RandomWord();
        StringBuilder missString = new StringBuilder();

        String secretWord;
        String guessString = "";

        int guessState; //hangman attempts
        int guessResult;

        while (true) {
            rWord.generate();
            guessState = 0;
            guessSet.clear();
            missString.setLength(0);
            secretWord = rWord.getWord();
            System.out.println(secretWord);

            while (true) {
                //display here

                display1(guessState);

                //check for complete hanged stickman victim and loop exit
                if (guessState == 7) {
                    System.out.println("you died");
                    break;
                }

                wordBox(secretWord, missString.toString(), guessSet);

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
                //if letter guessed is not in secret word, append miss String and increment guessState
                if (!secretWord.contains(String.valueOf(guessString.charAt(0)))) {
                    missString.append(guessString.charAt(0));
                    guessState++;
                }
//                if (secretWord.equals()) {
//
//                }
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

    private static void wordBox(String word,String missed, SortedSet<Character> set) {

        StringBuilder sb = new StringBuilder();

        System.out.print("Missed letters: ");
        System.out.println(missed);
        System.out.println();

        for (int i = 0; i < word.length(); i++) {

            sb.append(set.contains(word.charAt(i)) ? word.charAt(i) : "_");
        }

        System.out.println(sb);
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