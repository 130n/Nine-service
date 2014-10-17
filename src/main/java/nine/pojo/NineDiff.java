package nine.pojo;

import java.util.*;

/**
 * Created by LeoHen on 2014-10-17.
 */
public class NineDiff{
    private List<String> missedWords = new ArrayList<String>();
    private List<String> falseWords = new ArrayList<String>();

    public NineDiff(NineResult input, List<String> facit) {
        super();
        Set<String> inputSet = new HashSet<String>(input.getResult());
        Set<String> facitSet = new HashSet<String>(facit);
        boolean hasNoWrongWords = facitSet.containsAll(inputSet);
        boolean hasAllRightWords = inputSet.containsAll(facitSet);

        if (hasAllRightWords && hasNoWrongWords) {
            //no diff == good
        } else {
            for (String facitWord : facitSet) {
                if (!inputSet.contains(facitWord)) {
                    missedWords.add(facitWord);
                }
            }
            for (String inputWord : inputSet) {
                if (!facitSet.contains(inputWord)) {
                    falseWords.add(inputWord);
                }
            }
        }
    }

    public List<String> getMissedWords() {
        return missedWords;
    }

    public void setMissedWords(List<String> missedWords) {
        this.missedWords = missedWords;
    }

    public List<String> getFalseWords() {
        return falseWords;
    }

    public void setFalseWords(List<String> falseWords) {
        this.falseWords = falseWords;
    }
}
