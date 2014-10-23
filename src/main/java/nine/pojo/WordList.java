package nine.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by LeoHen on 2014-10-17.
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum WordList {
    SAOL;
    private List<String> wordList = new ArrayList<String>();

    WordList() {
        /*
        File saol = new File("classpath:saol_2.txt");
        if (!saol.exists() && !saol.canRead()){
            try {
                throw new FileNotFoundException("bad file path");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        */
        BufferedReader bufferedReader;
        try {
            InputStream in = getClass().getResourceAsStream("/saol_2.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(
                    //new FileInputStream("saol_2.txt"),
                    in,
                    "UTF-8");
            bufferedReader = new BufferedReader(inputStreamReader);
            String s = bufferedReader.readLine();

            while (s != null) {
                wordList.add(s);
                s = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> getWordList() {
        return wordList;
    }
}
