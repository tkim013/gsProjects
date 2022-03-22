import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

public class Guess {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Random rand = new Random();

        String cont = "y";
        int rNumber;
        int guess;
        int count;
        String name;

        while(cont.equals("y")) {
            name = "";
            rNumber = rand.nextInt(20);
            rNumber++;
            //System.out.println(rNumber);

            System.out.println("Hello!  What is your name?\n");
            name = in.nextLine();

            System.out.println("Well " + name + ", I am thinking of a number between 1 and 20.\n" +
                    "Take a guess.\n");
            guess = Integer.parseInt(in.nextLine());
            count = 1;

            while (count < 6) {
                if (rNumber < guess) {
                    System.out.println("Your guess is too high.\n" +
                            "Take a guess.\n");
                } else if (rNumber > guess) {
                    System.out.println("Your guess is too low.\n" +
                            "Take a guess.\n");
                } else {
                    break;
                }
                guess = Integer.parseInt(in.nextLine());
                count++;
            }

            if (count == 6) {
                System.out.println("Sorry, you could not guess the number in 6 tries.\n");
            } else if (count == 1) {
                System.out.println("Good job, " + name + "!  You guessed my number on your first guess!\n");
            } else {
                System.out.println("Good job, " + name + "!  You guessed my number in " + count + " guesses!\n");
            }
            System.out.println("Would you like to play again? (y or n)\n");
            cont = in.nextLine().toLowerCase();
        }
    }
}
