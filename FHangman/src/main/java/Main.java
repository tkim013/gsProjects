import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {

        RandomWord rw = new RandomWord();
        Scanner in = new Scanner(System.in);
        Set<Character> guessSet = new HashSet<>(); //holds guessed chararacters
        List<Character> secretWord; //word to be guessed
        StringBuilder missString = new StringBuilder(); //miss string for wordbox display

        String guessString = ""; //stores character guess
        String playAgain = ""; //input for main loop continuation
        String playerName;

        int state; //hangman state
        int score; //game score

        //The One Loop to rule them all
        while (true) {
            rw.generate(); //generate random word
            state = 0; //reset guessState
            guessSet.clear(); //clear guessSet
            missString.setLength(0); //clear missString
            score = 0; //reset score
            playerName = "error"; //reset player name

            //set secret word
            secretWord = rw.getWord()
                    .chars()
                    .mapToObj(e -> (char) e)
                    .collect(Collectors.toList());

            //name input
            playerName = nameInput(in, playerName);

            //the game
            score = game(in, guessSet, secretWord, missString, guessString, state, score);
            //high score
            evaluateScore(in, playerName, score);
            //play again?
            if (repeatInput(in, playAgain).equals("no")) {
                break;
            }
        }
        in.close();
    }

    public static int game(Scanner in, Set<Character> guessSet, List<Character> secretWord,
                           StringBuilder missString, String guessString, int state, int score) {

        //hangman display by state
        display(state);

        String wordOut = secretWord.toString()
                .substring(1, 3 * secretWord.size() - 1)
                .replaceAll(", ", "");

        //check for complete hanged stickman victim, return score - end condition
        if (state == 7) {
            System.out.println("The word is: " + wordOut);
            System.out.println("Everyone reaches their eventual demise. It just came for you a bit sooner than " +
                    "expected. At least you finished life before anyone else you know right? W-I-N-N-E-R!!! but not.");
            return score;
        }

        //check for win, wordbox display, return score - end condition
        if (wordBox(secretWord, missString.toString(), guessSet)) {

            System.out.println("Yes! The secret word is \"" + wordOut + "\"! You have won!\n");
            return score;
        }

        //input
        guessString = guessInput(in, guessString, guessSet);

        //if letter guessed is not in secret word, append missString and increment guessState
        if (!secretWord.contains(guessString.charAt(0))) {
            missString.append(guessString.charAt(0));
            state++;
            score -= 2 * guessString.length(); //-(2 * length of word) pts for missed guess
        } else {
            score += 10 * guessString.length(); //10 * length of word pts for correct guess
        }
        return game(in, guessSet, secretWord, missString, guessString, state, score);
    }

    public static void display(int state) {
        try {

            //read from display.txt
            Files.readAllLines(Path.of("src/main/resources/display.txt")).stream()
                    //filter lines starting with state
                    .filter(e -> e.startsWith(Integer.toString(state)))
                    //replace state with ' '
                    .map(s -> s.replace(Character.forDigit(state, 10), ' '))
                    //print
                    .forEach(System.out::println);

        } catch (Exception e) {
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

    public static String guessInput(Scanner in, String guessString, Set<Character> guessSet) {

        System.out.println("Guess a letter.\n");

        //char guess input
        try {

            guessString = in.nextLine().toLowerCase();

        } catch (Exception e) {
            e.printStackTrace();
        }
        //evaluates validity of guess
        int guessResult = evaluateGuess(guessSet, guessString);

        //end condition
        if (guessResult == 1) {
            //adds valid char to guessSet
            guessSet.add(guessString.charAt(0));
            return guessString;
        } else {
            return guessInput(in, guessString, guessSet);
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

    public static String repeatInput(Scanner in, String playAgain) {
        System.out.println("Do you want to play again? (yes or no)");

        //input for continuation of main loop
        try {
            playAgain = in.nextLine().toLowerCase();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //return string if valid input, end condition
        return evaluatePlayAgain(playAgain) ? playAgain : repeatInput(in, playAgain);
    }

    public static boolean evaluatePlayAgain(String s) {

        //check string for "yes" or "no", returns true if valid
        if (s.equals("yes") || s.equals("no")) {
            return true;
        }
        //returns false if unacceptable value
        System.out.println("Invalid input.");
        return false;
    }

    public static void evaluateScore(Scanner in, String pName, int score) {

        List<List<String>> scoreList = new ArrayList<>();
        final String fName = "src/main/resources/score.txt"; //file path

        int max = -1;

        //create file if none
        try {
            File file = new File(fName);
            if (!file.exists()) { //check if file exists
                file.createNewFile(); //create file
            }
        } catch (IOException e) {
            System.out.println("IOException create File");
        }

        try {
            //read file into List<List<String>>
           scoreList = Files.readAllLines(Path.of(fName)).stream()
                   .map(e -> Arrays.asList(e.split(" ")))
                   .collect(Collectors.toList());

        } catch (IOException e) {
            System.out.println("IOException read File");
        }

        //find high score
        if (!scoreList.isEmpty()) {
            max = scoreList.stream()
                    //map over return index 1 score
                    .map(e -> e.get(1))
                    //convert to intStream
                    .mapToInt(Integer::parseInt)
                    //find max
                    .max()
                    //return as int
                    .getAsInt();
        }

        //high score if score list empty or score is > current high score
        if (scoreList.isEmpty() || score > max) {
            System.out.println(pName + ", you have the highest score of " + score + ".");
        } else {
            System.out.println(pName + ", your score is: " + score + ".");

            final int m = max;

            //List string of max scorer
            String h = scoreList.stream()
                    //filter max score
                    .filter(e -> e.get(1).contains(Integer.toString(m)))
                    .collect(Collectors.toList()).get(0).get(0);

            System.out.println("High score is " + h + " with score of " + max + ".");
        }

        try {
            //write score to file
            BufferedWriter bw = new BufferedWriter(new FileWriter(fName, true));
            bw.write(pName + " " + score + System.getProperty("line.separator"));
            bw.close();
        } catch (IOException e) {
            System.out.println("IOException writeToAFile");
        }
    }

    public static String nameInput(Scanner in, String name) {
        System.out.println("Enter your name.");

        //player name input
        try {
            name = in.nextLine();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //return string if valid input, end condition
        return evaluateName(name) ? name : nameInput(in, name);
    }

    public static boolean evaluateName(String s) {

        //check string for spaces, returns false if detected
        if (s.contains(" ")) {
            System.out.println("Name cannot have space(s). Try again.");
            return false;
        }
        return true;
    }
}