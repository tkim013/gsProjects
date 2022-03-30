import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Hangman {
    public static void main(String[] args) {

        List<String> dict = new ArrayList<>();
        String cont = "yes";
        String secretWord;

        while (cont.equals("yes")) {
            secretWord = new RandomWord().getWord();
            System.out.println(secretWord);

            System.out.println("H A N G M A N");

            //display here

            //guess input

            //resultDisplay

            cont = "no";
        }



    }
//
//    public String randomWord(List<String> dict, Random rand) {
//        int rNumber = rand.nextInt(dict.size() - 1);
//        return dict.get(rNumber);
//    }

}