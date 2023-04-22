package Hangman;

import java.util.ArrayList;
import java.util.Random;

public class Logic {
		
	public Logic() {
		
	}
	
	//returns a random word and adds each char from that word to the wordCharArray
	public String generateWord() {
		final String[] WORDS = {"dog", "cat","red","blue","tree","key","ten","key","air","can",
								"art","sand","glass","board","person","plus","equal","line","graph","floor",
								"keyboard","java","computer","alebra","science","coffee","quotient","polygon","equator","statistics"
								};
		
		int randomNum = new Random().nextInt(30);
		String randomWord = WORDS[randomNum];
		
		return randomWord;
	}
	
	// creates the word progress array (takes the random word as a param)
	public ArrayList<Character> generateWordProgress(String word) {
		ArrayList<Character> wordProgress = new ArrayList<Character>();
		
		//for the length of the generated word add a char '-' to the array
		for (int i =0;i<word.length();i++) {
			wordProgress.add('-');
		}
		
		return wordProgress;
			
	}
	
	public ArrayList<Character> generateWordArray (String randomWord){
		ArrayList<Character> wordCharArray = new ArrayList<Character>();
		
		for(int i = 0;i<randomWord.length();i++) {
			wordCharArray.add(randomWord.charAt(i));
		}
		
		return wordCharArray;
	}
	
	// function that returns hangman ASCII based on the index put into the parameter
	// !! needs work, need to add html tags to the start and end of each ASCII picture and replace \n with a <br> tag
	public String getHangManState(int index) {
		
		final String[] HANGMAN = 
			{
			"<html> +---+<br>"
			+ "  |   |<br>"
			+ "      |<br>"
			+ "      |<br>"
			+ "      |<br>"
			+ "      |<br>"
			+ "=========</html>",
			
			"  +---+\n"
			+ "  |   |\n"
			+ "  O   |\n"
			+ "      |\n"
			+ "      |\n"
			+ "      |\n"
			+ "=========",
			
			"  +---+\n"
			+ "  |   |\n"
			+ "  O   |\n"
			+ "  |   |\n"
			+ "      |\n"
			+ "      |\n"
			+ "=========",
			
			"  +---+\n"
			+ "  |   |\n"
			+ "  O   |\n"
			+ " /|   |\n"
			+ "      |\n"
			+ "      |\n"
			+ "=========",
			
			"  +---+\n"
			+ "  |   |\n"
			+ "  O   |\n"
			+ " /|\\  |\n"
			+ "      |\n"
			+ "      |\n"
			+ "=========",
			
			"  +---+\n"
			+ "  |   |\n"
			+ "  O   |\n"
			+ " /|\\  |\n"
			+ " /    |\n"
			+ "      |\n"
			+ "=========",
			
			"  +---+\n"
			+ "  |   |\n"
			+ "  O   |\n"
			+ " /|\\  |\n"
			+ " / \\  |\n"
			+ "      |\n"
			+ "========="
			};
		
		return HANGMAN[index];
	}
	
}

