import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        RandomWord rw = new RandomWord();
        Scanner in = new Scanner(System.in);
        Set<Character> guessSet = new HashSet<>(); //holds guessed chararacters
        List<Character> secretWord = new ArrayList<>();; //word to be guessed
        StringBuilder missString = new StringBuilder(); //miss string for wordbox display

        String guessString = ""; //stores character guess
        String playAgain = ""; //input for main loop continuation
        String wordOut = ""; //String of secretWord

        int state; //hangman state
        int result; //input validity

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
}