import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomWord {

    private List<String> dict;
    private final Random rand = new Random();

    private String word; //holds random word

    public RandomWord() {

        try {

            //read hangmanWords.txt to List<String>
            dict = new ArrayList<>(Files.readAllLines(Path.of("src/main/resources/hangmanWords.txt")));

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
