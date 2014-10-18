package nine.pojo;

import java.util.Random;

/**
 * Created by LeoHen on 2014-10-17.
 */
public class Nine {
    private static final Random random = new Random();
    private String letters;

    public Nine() {
        String alphabet = "abcdefghijklmnopqrstuvwxyzåäö";
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            stringBuilder.append(alphabet.charAt(random.nextInt(alphabet.length())));
        }
        this.letters = stringBuilder.toString();
    }

    public String getLetters() {
        return letters;
    }
}
