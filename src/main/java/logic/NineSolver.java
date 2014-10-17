package logic;

import pojo.WordList;

import java.util.ArrayList;
import java.util.List;

public class NineSolver {

    public List<String> getFacit(String mustLetter, String possLetters) {
        return getFacit(mustLetter, possLetters, WordList.SAOL.getWordList());
    }

    public List<String> getFacit(String mustLetter, String possLetters, List<String> wordList) {
        List<String> facit = new ArrayList<String>();
        final String allLetters = mustLetter + possLetters;

        for (String word : wordList) {//loop over wordlist
            if (word.length() > 2 && word.contains(mustLetter)) {
                StringBuilder sb = new StringBuilder(allLetters);

                for (int i = 0; i < word.length(); i++) {//loop over word
                    char ch = word.charAt(i);

                    for (int e = 0; e < sb.length(); e++) {//loop over the nine per character
                        if (sb.charAt(e) == ch) {
                            sb.deleteCharAt(e);
                            break;
                        }
                    }
                }
                if (9 - sb.length() == word.length()) {
                    facit.add(word);
                }
            }
        }
        return facit;
    }
}