import java.util.Random;
import java.util.Scanner;

public class Guess {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        Random rand = new Random();

        String cont = "y"; //variable used for looping program
        String name; //user input name string

        int rNumber; //stores random number
        int guess; //user input guess
        int count; //stores number of attempts
        boolean guessedResult;

        while(cont.equals("y")) {
            rNumber = rand.nextInt(20);
            rNumber++;

            System.out.println("Hello!  What is your name?\n");

            //name input
            name = nameInput(in);

            System.out.println("Well " + name + ", I am thinking of a number between 1 and 20.\n" +
                    "Take a guess.\n");

            //first guess
            guess = guessInput(in);

            //initialize count with guess attempt #1
            count = 1;

            //loop until 6 attempts
            while (count < 6) {

                //evaluate guess comparison to random number
                guessedResult = evaluateGuess(rNumber, guess);

                //if guessedResult is true, break loop
                if (guessedResult) {
                    break;
                }

                //guess input if result is incorrect
                guess = guessInput(in);

                //increment number of attempt
                count++;
            }

            //output result
            resultString(count, name);

            //user input to play again
            cont = getCont(in);
        }
        in.close();
    }

    public static boolean evaluateGuess(int rNumber, int guess) {

        if (rNumber < guess) {
            System.out.println("Your guess is too high.\n" +
                    "Take a guess.\n");
        } else if (rNumber > guess) {
            System.out.println("Your guess is too low.\n" +
                    "Take a guess.\n");
        } else {
            return true;
        }

        return false;
    }

    public static void resultString(int count, String name) {

        switch (count) {
            case 6 : {
                System.out.println("Sorry, you could not guess the number in 6 tries.\n");
                break;
            }

            case 1: {
                System.out.println("Good job, " + name + "!  You guessed my number on your first guess!\n");
                break;
            }

            default: {
                System.out.println("Good job, " + name + "!  You guessed my number in " + count + " guesses!\n");
                break;
            }
        }

        System.out.println("Would you like to play again? (y or n)\n");
    }

    public static int guessInput(Scanner in) {
        int guess = -1;

        try {
            guess = Integer.parseInt(in.nextLine());
        } catch (Exception e) {
            System.out.println("Exception: guessInput");
        }

        return guess;
    }

    public static String nameInput(Scanner in) {
        String name = "";
        try {
            name = in.nextLine();
        } catch(Exception e) {
            System.out.println("Exception: nameInput");
        }

        //Capitalized name if applicable
        return name.substring(0,1).toUpperCase() + name.substring(1);
    }

    public static String getCont(Scanner in) {
        String cont = "";
        try {
            cont = in.nextLine().toLowerCase();
            //if answer is "y" or "n" return answer
            if (cont.equals("y") || cont.equals("n")) {
                return cont;
            }

            //message for input not "y" or "n"
            System.out.println("Invalid Input");
        } catch (Exception e) {
            System.out.println("Exception");
        }
        return cont;
    }
}
