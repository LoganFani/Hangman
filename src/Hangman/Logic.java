package Hangman;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Logic {
		
	String randomWord = generateWord();
	ArrayList<Character> wordCharList = generateWordArray(randomWord);
	ArrayList<Character> wordProgList = generateWordProgress(randomWord);
	int guesses = 6;
	
	int numWins = 0;
	int numLosses = 0;
	
	
	
	public Logic() {
		
	}
	
	//returns a random word and adds each char from that word to the wordCharArray
	public String generateWord() {
		final String[] WORDS = {"dog", "cat","red","blue","tree","key","ten","key","air","can",
								"art","sand","glass","board","person","plus","equal","line","graph","floor",
								"keyboard","java","computer","algebra","science","coffee","quotient","polygon","equator","statistics"
								};
		
		//creates a random number between 0-the number of the words in the array
		int randomNum = new Random().nextInt(WORDS.length);
		
		//picks the random word from the list at the index of the random number
		String randomWord = WORDS[randomNum];
		
		return randomWord;
	}
	
	/*
	 * returns an array list of characters
	 * uses the random word as a parameter
	 * for each letter in the random word it creates an arraylist of characters with '-'
	 */
	public ArrayList<Character> generateWordProgress(String randomWord) {
		ArrayList<Character> wordProgress = new ArrayList<Character>();
		
		//for the length of the generated word add a char '-' to the array
		for (int i =0;i<randomWord.length();i++) {
			wordProgress.add('-');
		}
		
		return wordProgress;
			
	}
	
	/*
	 * returns an array list of characters of the actual letters in the word
	 * takes the randomWord as a parameter
	 * used later to see if the letter the user had entered matches with the random word
	 */
	public ArrayList<Character> generateWordArray (String randomWord){
		ArrayList<Character> wordCharArray = new ArrayList<Character>();
		
		for(int i = 0;i<randomWord.length();i++) {
			wordCharArray.add(randomWord.charAt(i));
		}
		
		return wordCharArray;
	}
	
	/*
	 * function that returns a certain state of the hang-man
	 * takes an int as a parameter and returns the index of the string array of hang-man
	 */
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
			
			"<html>  +---+<br>"
			+ "  |   |<br>"
			+ "  O   |<br>"
			+ "      |<br>"
			+ "      |<br>"
			+ "      |<br>"
			+ "=========</html>",
			
			"<html>  +---+<br>"
			+ "  |   |<br>"
			+ "  O   |<br>"
			+ "  |   |<br>"
			+ "      |<br>"
			+ "      |<br>"
			+ "=========</html>",
			
			"<html>  +---+<br>"
			+ "  |   |<br>"
			+ "  O   |<br>"
			+ " /|   |<br>"
			+ "      |<br>"
			+ "      |<br>"
			+ "=========</html>",
			
			"<html>  +---+<br>"
			+ "  |   |<br>"
			+ "  O   |<br>"
			+ " /|\\  |<br>"
			+ "      |<br>"
			+ "      |<br>"
			+ "=========</html>",
			
			"<html>  +---+<br>"
			+ "  |   |<br>"
			+ "  O   |<br>"
			+ " /|\\  |<br>"
			+ " /    |<br>"
			+ "      |<br>"
			+ "=========</html>",
			
			"<html>  +---+<br>"
			+ "  |   |<br>"
			+ "  O   |<br>"
			+ " /|\\  |<br>"
			+ " / \\  |<br>"
			+ "      |<br>"
			+ "=========</html>"
			};
		
		return HANGMAN[index];
	}

/*
 * fucntion that checks the character input box in the gui and also checks if that character is in the random word
 * takes the input box, the wordprogress label, the guess counter label, and the hangman label as a parameter
 * 
 */
public void charInput(JTextField input, JLabel progress, JLabel guessCounter, JLabel hangman) {
		
		//inside try/catch statement because it throws an error if the user clicks the button and nothing is in the input box
		try {
			// if the word character array contains the character the user entered
			if (wordCharList.contains(input.getText().charAt(0))) {
				
				//start a for loop from 0-the length of the word char array
				for (int i=0; i<wordCharList.size();i++) {
					
					//and if it iterated through and at the index i both characters match..
					if (wordCharList.get(i) == input.getText().charAt(0)) {
						//set the index of the word progress list to the matched letter
						wordProgList.set(i, wordCharList.get(i)); 
						
					}
			}
			
				
			}
			
			//if they don't match
			else {
				
				//subtract 1 from guesses
				guesses -=1;
				
				//set the guessCounter label in the App class to guesses
				guessCounter.setText(Integer.toString(guesses));
				
				//call the guessChecker function with the input box
				guessChecker(input);
				
				//call the set hangman state with the hangman label
				setHangManState(hangman);
			}
		}
		
		// if the user doesn't enter a character in but presses the button promp them saying they entered nothing
		catch (Exception e){
			JOptionPane.showMessageDialog(null, "You didn't enter anything into the text box!");
		}
		
		// set the progress label in the App class to the updated wordProgList
		progress.setText(String.valueOf(wordProgList));
		
		
	}


/*
 * function that takes in all of the previous functions parameters and more aswell
 * the additional parameters are the wordInput box, Jlabel wins, Jlabel losses, Jlabel numGuesses
 * function checks if the word the user guesses is correct and then asks them if they want to play again
 * if they do call the reset() function which resets the global variables
 */
public void wordInput(JTextField charInput, JTextField input, JLabel progress, JLabel wins, JLabel losses, JLabel lnumGuesses, JLabel hangman) {
	
	// if the input equals the random word..
	if (input.getText().equals(randomWord)) {
		
		// add 1 to wins
		numWins += 1;
		
		//set the label in the App class to the number of wins
		wins.setText(Integer.toString(numWins));
		
		// calls the askContinue function and if the user doesn't press yes, exit the program
		if (askConinue("You Won!\nWould You like to continue?") != true) {
			System.exit(0);
		}
		
		// if they press yes call the reset function and reset all of the global variables for the game
		else {reset(charInput,progress,lnumGuesses,hangman);}
		
	}
	
	// if the word entered doesn't match the random word..
	else {
		
		//add 1 to the losses
		numLosses +=1;
		
		// set the losses label in App to losses 
		losses.setText(Integer.toString(numLosses));
		
		//asks if they want to play again and if they press anything other than yes exit the program
		if (askConinue("You Lost!\nThe word was: "+randomWord+"\nWould You like to continue?") != true) {
			System.exit(0);
		}
		
		//if the user presses yes call the reset function
		else {reset(charInput,progress,lnumGuesses,hangman);}
	}
	
}
	
	/*
	 * this function gets called in the charInput() function
	 * if guesses == 0 set the input restriction to 0 so the user can't enter anymore letters
	 * takes the input from the charInput() function as a parameter
	 */
	private void guessChecker(JTextField input) {
		if (guesses == 0) {
			
			input.setDocument(new LetterInputFilter(0));
			
			JOptionPane.showMessageDialog(null, "You are out of guesses you must guess the word now.");
		}
		
	}
	
	/*
	 * function that gets called in the charInput() function
	 * uses a switch statement that updates the hangman label in App depending on the number of guesses-
	 * the user is on
	 */
	private void setHangManState(JLabel hangman) {
		switch (guesses) {
		case 6:
			hangman.setText(getHangManState(0));
			break;
		case 5:
			hangman.setText(getHangManState(1));
			break;
		case 4:
			hangman.setText(getHangManState(2));
			break;
		case 3:
			hangman.setText(getHangManState(3));
			break;
		case 2:
			hangman.setText(getHangManState(4));
			break;
		case 1:
			hangman.setText(getHangManState(5));
			break;
		case 0:
			hangman.setText(getHangManState(6));
			break;
		}
	}
	
	/*
	 * this function gets called in the wordInput() function
	 * takes in a string for the prompt of the JOptionPane window
	 * returns a boolean
	 */
	private boolean askConinue(String prompt) {
		
		//displays the JOptionpane confirm dialog box with the prompt in it 
		int response = JOptionPane.showConfirmDialog(null, prompt);
		
		//if the user clicks yes return true
		if (response == JOptionPane.YES_OPTION) {
			return true;
		}
		
		//if they click anything else return false
		else {
			return false;
		}
		
	}
	
	
	/*
	 * this function is called in the wordInput() function
	 * takes in the charInput textbox, the wordProgress label, the numGuesses labal, and the hangman label
	 * when this function is called it resets the global variables to new ones and updates the labesl in the App class
	 */
	public void reset(JTextField charInput, JLabel lWordProgress, JLabel lNumGuesses, JLabel hangman) {
		
		randomWord = generateWord(); //generates new word
		
		wordCharList = generateWordArray(randomWord); // generates new wordChar list from the new random word
		
		wordProgList = generateWordProgress(randomWord); // generates new wordProgress list from the new random word
		
		guesses = 6; //sets guesses back to 6
		
		lWordProgress.setText(String.valueOf(generateWordProgress(randomWord))); //sets the word progress on the screen to fit the new word
		
		lNumGuesses.setText(Integer.toString(guesses)); //sets the guesses back on the screen to 6
		
		hangman.setText(getHangManState(0)); //resets the hangman state
		
		charInput.setDocument(new LetterInputFilter(1)); //resets the input filter back to 1 character
	}
		
	
	
	
}

