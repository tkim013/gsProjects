import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomWord {

    private List<String> dict = new ArrayList<>();
    private Random rand = new Random();

    private String word;

    public RandomWord() {

        try {
            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/usa.txt"));
            while (br.ready()) {
                dict.add(br.readLine());
            }
        } catch (IOException e) {
            System.out.println("Exception");
        }

        this.generate();
    }

    public String getWord() {
        return this.word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public void generate() {

        this.word = this.dict.get(this.rand.nextInt(this.dict.size() - 1));
    }
}
