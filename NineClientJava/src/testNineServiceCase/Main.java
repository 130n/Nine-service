package testNineServiceCase;

public class Main {
	Main () {
		NineLetters nineLetters = new NineLetters();
		WordList wordList = new WordList();
		CorrectWords correctWords = new CorrectWords(wordList.getJsonArray(), nineLetters.getLetters());
		System.out.println(nineLetters.getLetters());
		System.out.println("---------------");
		System.out.println(correctWords.getCorrectWords());
		System.out.println("---------------");
		System.out.println(correctWords.validate());
		
	}
	public static void main(String[] args) {
		new Main();
	}

}
