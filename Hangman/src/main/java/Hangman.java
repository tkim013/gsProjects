import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Hangman {
    public static void main(String[] args) {

        Random rand = new Random();

        List<String> dict = new ArrayList<>();
        String cont;

        try {
            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/usa.txt"));
            while (br.ready()) {
                dict.add(br.readLine());
            }
        } catch (IOException e) {
            System.out.println("Exception");
        }

//        randomWord(dict);
        System.out.println(randomWord(dict, rand));

    }

    public static String randomWord(List<String> dict, Random rand) {
        int rNumber = rand.nextInt(dict.size() - 1);
        return dict.get(rNumber);
    }
}