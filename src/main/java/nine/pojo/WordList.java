package nine.pojo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by LeoHen on 2014-10-17.
 */
public enum WordList {
    SAOL;
    private List<String> wordList = new ArrayList<String>();

    WordList() {
        File saol = new File("src/main/resources/saol_2.txt");
        if (!saol.exists() && !saol.canRead()){
            try {
                throw new FileNotFoundException("bad file path");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        BufferedReader bufferedReader;
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(saol), "UTF-8");
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
