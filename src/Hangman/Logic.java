package Hangman;

import java.util.ArrayList;
import java.util.Random;

public class Logic {
	
	//word char array that stores each character of a random word chosen
	//will later be compared to the word progress to check if a user has guessed a word
	private ArrayList<Character> wordCharArray = new ArrayList<Character>();
	
	public Logic() {
		
	}
	
	//returns a random word and adds each char from that word to the wordCharArray
	public String generateWord() {
		final String[] WORDS = {"dog", "cat","red","blue","tree","key","ten","key","air","can",
								"art","sand","glass","board","person","plus","equal","line","graph","floor",
								"keyboard","java","computer","alebra","science","coffee","quotient","polygon","equator","statistics"
								};
		
		int randomNum = new Random().nextInt(10);
		String randomWord = WORDS[randomNum];
		
		for (int i =0; i<randomWord.length();i++) {
			wordCharArray.add(randomWord.charAt(i));
		}
		
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
	
	// if the userguess is in the wordCharArray it adds it to the progress array
	public ArrayList<Character> fillWordProgress(ArrayList<Character> wordProgress, char userGuess){
		
		for (int i = 0; i<wordProgress.size();i++) {
			if (userGuess == wordCharArray.get(i)) {
				wordProgress.set(i,userGuess);
			}
		}
		
		return wordProgress;
		
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
