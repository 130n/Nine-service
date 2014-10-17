package nine;

import logic.NineSolver;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pojo.Nine;
import pojo.NineDiff;
import pojo.NineResult;
import pojo.WordList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/nine/**")
@RestController
public class NineController {

    private final NineSolver nineSolver = new NineSolver();

    private final Map<String, Integer> cheatCounter = new HashMap<String, Integer>();
    /**
     *
     * @return
     */
    @RequestMapping(value = "/nine/getnine", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Nine getnine() {
        return new Nine();
    }

    @RequestMapping(value = "/nine/wordlist", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<String> wordlist() {
        return WordList.SAOL.getWordList();
    }

    @RequestMapping(value = "/nine/validate", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody ResponseEntity<NineDiff> validate(@RequestBody NineResult input) {
        System.out.println(ReflectionToStringBuilder.toString(input));
        if(input.getNineLetters().length() != 9){
            return new ResponseEntity<NineDiff>(HttpStatus.BAD_REQUEST);
        }
        if (cheatCounter.get(input.getNineLetters()) == null ){
            cheatCounter.put(input.getNineLetters(), 1);
        } else {
            Integer counter = cheatCounter.get(input.getNineLetters());
            cheatCounter.put(input.getNineLetters(), ++counter);
        }

        String mustLetter = "" + input.getNineLetters().charAt(4);
        String possLetters = input.getNineLetters().substring(0,4) + input.getNineLetters().substring(5,9);
        System.out.println(mustLetter);
        System.out.println(possLetters);
        List<String> facit = nineSolver.getFacit(mustLetter, possLetters);
        return new ResponseEntity<NineDiff>(new NineDiff(input, facit),HttpStatus.OK);
    }

}
