import java.util.Scanner;

public class DragonCave {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int choice = 0;

        System.out.println("You are in a land full of dragons. In front of you, \n" +
                "you see two caves.  In one cave, the dragon is friendly \n" +
                "and will share his treasure with you.  The other dragon \n" +
                "is greedy and hungry and will eat you on sight.\n" +
                "Which cave will you go into?  (1 or 2)\n");
        try {
            choice = scan.nextInt();
        } catch (Exception e) {
            System.out.println("Exception");
        }
            switch (choice) {
                case 1:
                    System.out.println("You approach the cave...\n" +
                            "It is dark and spooky...\n" +
                            "A large dragon jumps out in front of you!  He opens his jaws and...\n" +
                            "Gobbles you down in one bite!\n");
                    break;
                case 2:
                    System.out.println("You approach the cave...\n" +
                            "The entrance seems inviting enough as you cautiously peak inside...\n" +
                            "Inside the cave you find glittering gold and sparkling treasures!\n" +
                            "A golden nimbus of light appears suddenly and coalesces into the form of a giant dragon!\n" +
                            "The dragon generously shares his wealth with you!\n" +
                            "At dinner time, it gobbles you up because... dragon!\n");
                    break;
                default:
                    System.out.println("Not a valid choice.\n");
            }
        
    }
}
