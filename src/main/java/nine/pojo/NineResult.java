package nine.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by LeoHen on 2014-10-17.
 */
public class NineResult implements Serializable {

    private String nineLetters;
    private List<String> result;

    public String getNineLetters() {
        return nineLetters;
    }

    public void setNineLetters(String nineLetters) {
        this.nineLetters = nineLetters;
    }

    public List<String> getResult() {
        return result;
    }

    public void setResult(List<String> result) {
        this.result = result;
    }
}
