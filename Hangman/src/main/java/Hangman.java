public class Hangman {
    public static void main(String[] args) {

        RandomWord rWord = new RandomWord();

        String cont = "yes";
        String secretWord;

        int guessState; //hangman attempts

        while (cont.equals("yes")) {
            rWord.generate();
            guessState = 0;
            secretWord = rWord.getWord();
            System.out.println(secretWord);

            System.out.println("H A N G M A N");

            //display here

            display1(5);

            //guess input

            //resultDisplay

            cont = "no";
        }
    }

    public static void display1(int guessState) {

        String display;

        System.out.println(" +---+");
        display = guessState == 0 ? "     |" : " O   |";
        System.out.println(display);
        display = guessState < 2 ? "     |" : guessState < 4 ? " |   |" : guessState == 4 ? "\\|   |" : "\\|/  |";
        System.out.println(display);
        display = guessState < 3 ? "     |" : " |   |";
        System.out.println(display);
        display = guessState < 6 ? "     |" : guessState < 7 ? "/   |" : "/ \\  |";
        System.out.println(display);
        System.out.println("    ===");
    }
}