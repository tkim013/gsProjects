import java.util.Scanner;

public class DragonCave {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int choice;

        System.out.println("You are in a land full of dragons. In front of you, \n" +
                "you see two caves.  In one cave, the dragon is friendly \n" +
                "and will share his treasure with you.  The other dragon \n" +
                "is greedy and hungry and will eat you on sight.\n" +
                "Which cave will you go into?  (1 or 2)\n");
        choice = scan.nextInt();

        switch (choice) {
            case 1:
                System.out.println("You approach the cave...\n" +
                        "It is dark and spooky...\n" +
                        "A large dragon jumps out in front of you!  He opens his jaws and...\n" +
                        "Gobbles you down in one bite!\n");
                break;
            case 2:
                System.out.println("Everything is cool\n" +
                        "yep");
                break;
        }
    }
}
