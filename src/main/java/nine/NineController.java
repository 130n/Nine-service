package nine;

import nine.logic.NineSolver;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import nine.pojo.Nine;
import nine.pojo.NineDiff;
import nine.pojo.NineResult;
import nine.pojo.WordList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/nine/**")
@RestController
public class NineController {

    private final NineSolver nineSolver = new NineSolver();

    private final Map<String, Integer> cheatCounter = new HashMap<String, Integer>();
    /**
     * Generate nine random letters
     * @return JSON in format {"letters": "abcdefghi"}
     */
    @RequestMapping(value = "/nine/getnine.json", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Nine getnine() {
        return new Nine();
    }

    @RequestMapping(value = "/nine/getnine.xml", method = RequestMethod.GET, produces = {MediaType.APPLICATION_XML_VALUE})
    public Nine getnine_xml() {
        return new Nine();
    }

    /**
     *
     * @return
     */
    @RequestMapping(value = "/nine/wordlist", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public WordList wordlist() {
        return WordList.SAOL;
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

    @RequestMapping(value = "/error")
    public @ResponseBody String error(){
        return "error";
    }
}
