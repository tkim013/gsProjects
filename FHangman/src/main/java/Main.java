import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        RandomWord rw = new RandomWord();
        Scanner in = new Scanner(System.in);
        Set<Character> guessSet = new HashSet<>(); //holds guessed chararacters
        List<Character> secretWord; //word to be guessed
        StringBuilder missString = new StringBuilder(); //miss string for wordbox display

        String guessString = "c"; //stores character guess
        String playAgain = ""; //input for main loop continuation
        String wordOut = ""; //String of secretWord

        int state; //hangman state

        //The One Loop to rule them all
        while (true) {
            rw.generate(); //generate random word
            state = 0; //reset guessState
            guessSet.clear(); //clear guessSet
            missString.setLength(0); //clear missString
            //set secret word
            secretWord = rw.getWord()
                    .chars()
                    .mapToObj(e -> (char) e)
                    .collect(Collectors.toList());

            //hangman display by state
            display(state);

//            secretWord.add('p');
//            secretWord.add('o');
//            secretWord.add('p');
//            guessSet.add('p');
//            guessSet.add('o');

            //check for complete hanged stickman victim, loop exit
            wordOut = secretWord.toString()
                    .substring(1, 3 * secretWord.size() - 1)
                    .replaceAll(", ", "");
            if (state == 7) {
                System.out.println("The word is: " + wordOut);
                System.out.println("Everyone reaches their eventual demise. It just came for you a bit sooner than " +
                        "expected. At least you finished life before anyone else you know right? W-I-N-N-E-R!!! but not.");
            }

            if (wordBox(secretWord, missString.toString(), guessSet)) {

                System.out.println("Yes! The secret word is \"" + wordOut + "\"! You have won!\n");
                break;
            }

            //input
            guessString = guessInput(in, guessString, guessSet, 0);

            //if letter guessed is not in secret word, append missString and increment guessState
            if (!secretWord.contains(guessString.charAt(0))) {
                missString.append(guessString.charAt(0));
                state++;
            }
            break;
        }
        //high score
    }

    public static void display(int state) {
        try {

            //read from display.txt
            Files.readAllLines(Path.of("src/main/resources/display.txt")).stream()
                    //filter lines starting with state
                    .filter(e -> e.startsWith(Integer.toString(state)))
                    //replace state with '_'
                    .map(s -> s.replace(Character.forDigit(state, 10), ' '))
                    //print
                    .forEach(System.out::println);

        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public static boolean wordBox(List<Character> word, String missed, Set<Character> set) {

        String guessed; //holds guessed line out
        String wordOut = word.toString()  //string for guess comparison to secret word
                .substring(1, 3 * word.size() - 1)
                .replaceAll(", ", "");

        //missed letters console output
        System.out.print("Missed letters: ");
        System.out.println(missed);
        System.out.println();

        //string of "_" and correctly guessed letters
        guessed = word.stream()
                .map(e -> e = set.contains(e) ? e : '_') //map over ternary operation if char not in set, replace with '_'
                .map(Object::toString).collect(Collectors.joining()); //to String format

        System.out.println(guessed);
        System.out.println();

        //check for correct guess, returns boolean
        return wordOut.equals(guessed);
    }

    public static String guessInput(Scanner in, String guessString, Set<Character> guessSet, int guessResult) {

        System.out.println("Guess a letter.\n");

        //char guess input
        try {

            guessString = in.nextLine().toLowerCase();

        } catch (Exception e) {
                System.out.println("Exception guess input");
        }
        //evaluates validity of guess
        guessResult = evaluateGuess(guessSet, guessString);

        //end condition
        if (guessResult == 1) {
            guessSet.add(guessString.charAt(0));
            return guessString;
        } else {
            return guessInput(in, guessString, guessSet, guessResult);
        }
    }

    public static int evaluateGuess(Set<Character> set, String guess) {
        //check not empty, previous guess, is a char, length check - char has already been guessed
        if (!guess.isEmpty() && set.contains(guess.charAt(0)) && Character.isLetter(guess.charAt(0)) && guess.length() == 1) {
            System.out.println("You have already guessed that letter. Choose again.\n");
            return 0;
            //check not empty, is a char, length check - valid input
        } else if (!guess.isEmpty() && Character.isLetter(guess.charAt(0)) && guess.length() == 1) {
            return 1;
        } else {
            System.out.println("Invalid input. Choose again.\n");
        }
        return 2;
    }
}