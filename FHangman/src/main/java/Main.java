import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) {

        RandomWord rw = new RandomWord();

        int state = 0;

        //one loop to rule them all
        while (true) {

            display(state);
            break;
        }
        //high score
    }

    public static void display(int state) {
        try {

            //read from display.txt
            Files.readAllLines(Path.of("src/main/resources/display.txt")).stream()
                    //filter lines starting with state
                    .filter(e -> e.startsWith(Integer.toString(state)))
                    //replace state with '_'
                    .map(s -> s.replace(Character.forDigit(state, 10), ' '))
                    //print
                    .forEach(System.out::println);

        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}