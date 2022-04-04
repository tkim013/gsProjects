import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomWord {

    private List<String> dict = new ArrayList<>();
    private Random rand = new Random();

    private String word; //holds random word

    public RandomWord() {

        try {
            //reads txt file of words
            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/hangmanWords.txt"));
            while (br.ready()) {
                //adds word to List<String>
                dict.add(br.readLine());
            }
        } catch (IOException e) {
            System.out.println("Exception");
        }

        //generate random word
        this.generate();
    }

    public String getWord() {
        return this.word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public void generate() {

        //random word generation
        this.word = this.dict.get(this.rand.nextInt(this.dict.size() - 1));
    }
}
