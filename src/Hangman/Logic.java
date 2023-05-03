package Hangman;

import java.awt.Image;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * The Logic class handles all game logic associated with the Hangman gameplay 
 */
public class Logic {
	String randomWord = requestWord();
	ArrayList<Character> wordCharList = generateWordArray(randomWord);
	ArrayList<Character> wordProgList = generateWordProgress(randomWord);
	ArrayList<Character> lettersGuessedList = new ArrayList<Character>();
	
	int guesses = 6;
	int numWins = 0;
	int numLosses = 0;
	
	
	/**
	 * Logic class constructor
	 */
	public Logic(){
		
	}
	
	/**
	 * Selects a random word from the WORDS array based off a random number
	 * @return The random word selected
	 */
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

	/**
	 * Request random word from random word API using HTTP GET request, if request fails, fall back to local word array
	 * @param wordLength The desired length for the random word
	 * @return The random word returned
	 * @throws IOException If reading the HTTP GET response fails
	 */
	public String requestWord() {
        // Generate random number for HTTP request
        int randomNum = new Random().nextInt(6) + 3;
        // Construct HTTP GET request
        String url = "https://random-word-api.herokuapp.com/word?length=" + randomNum;
        
        try {
        	 URL requestURL = new URL(url);
             HttpURLConnection connection = (HttpURLConnection) requestURL.openConnection();
             connection.setRequestMethod("GET");
             // Check response
             int responseCode = connection.getResponseCode();
             System.out.println("Response Code: " + responseCode);
             // If good response, print word
             if (responseCode == HttpURLConnection.HTTP_OK) {
                 BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                 String inputLine;
                 StringBuffer response = new StringBuffer();

                 while ((inputLine = in.readLine()) != null) {
                     response.append(inputLine);
                 }
                 in.close();

                 // strip unwanted characters and return results
     			String correctedWord = response.toString().replace("\"", "").replace("[", "").replace("]", "");
     			System.out.println(correctedWord);
                 return correctedWord;
        }
        
       
        } catch (Exception e) {
        	return generateWord();
        }
        
        return "";
	}

	
	/**
	  * Creates an ArrayList containing the "-" character for each of the letters in the random word
	  * @param randomWord The random word for the current game
	  * @return The ArrayList of dashes
	  */
	public ArrayList<Character> generateWordProgress(String randomWord) {
		ArrayList<Character> wordProgress = new ArrayList<Character>();
		
		//for the length of the generated word add a char '-' to the array
		for (int i =0;i<randomWord.length();i++) {
			wordProgress.add('-');
		}
		
		return wordProgress;
			
	}
	
	/**
	  * Breaks up the random word into individual characters and creates and ArrayList of characters
	  * @param randomWord The random word for the current game
	  * @return The ArrayList containting the characters of the current random word
	  */
	public ArrayList<Character> generateWordArray (String randomWord){
		ArrayList<Character> wordCharArray = new ArrayList<Character>();
		
		for(int i = 0;i<randomWord.length();i++) {
			wordCharArray.add(randomWord.charAt(i));
		}
		
		return wordCharArray;
	}
	
	/**
	  * Outputs the current visual for the current progress in the hangman game
	  * @param index The number of guesses the player has left
	  * @return The current hangman visual
	  */

/**
  * Gets the user's current guess, checks if the letter guessed is in the word, updates current game data, and updates UI to reflect the guess
  * @param input JtextField containing user input
  * @param progress JLabel that outputs the which letters the user has correctly guessed
  * @param guessCounter JLabel that shows how many guesses the user has left
  * @param hangman JLabel that displays the current hangman picture to replect user's progress
  * @param imageIncon passes the imagine icon in App to the method when the setHangmanState method is called
  */
public void charInput(JTextField input, JLabel progress, JLabel guessCounter, JLabel hangman,JLabel lLetters,ImageIcon imageIcon) {
		
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
				
				//call the set hangman state with the hangman label
				setHangManState(hangman,imageIcon);
				//set the guessCounter label in the App class to guesses
				guessCounter.setText(Integer.toString(guesses));
				
				//call the guessChecker function with the input box
				guessChecker(input);
				
				//add letters to the guessed letters pool
				
				addLetterPool(lLetters,input); 
				
				
				
			}
		}
		
		// if the user doesn't enter a character in but presses the button prompt them saying they entered nothing
		catch (Exception e){
			
			//had to put this if statement here to fix a bug where it diplays this message when the user runs out of guesses
			//this happens because when the user is at 0 guesses it changes the text filter by .setDocument which triggers this message box
			if (guesses > 0) {
				JOptionPane.showMessageDialog(null, "You didn't enter anything into the text box!");
			}
			
		}
		
		//reset the letter in the text box
		input.setText("");
		
		// set the progress label in the App class to the updated wordProgList
		progress.setText(String.valueOf(wordProgList));
		
		
	}


/**
  * Gets the full word guessed and checks if it is the correct word for the current game
  * @param charInput JTextField for the character guessing field
  * @param input JTextField containing the word the user is currently guessing
  * @param progress JLabel that outputs the which letters the user has correctly guessed
  * @param wins JLabel that diaplays how many wins the user has
  * @param losses JLabel that diaplays how many losses the user has
  * @param lnumGuesses JLabel that displays how many guesses the user has left
  * @param hangman JLabel that displays the current hangman picture to replect user's progress
  */
public void wordInput(JTextField charInput, JTextField input, JLabel progress, JLabel wins, JLabel losses, JLabel lnumGuesses, JLabel hangman,JLabel lLetters,ImageIcon imageIcon) {

	
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
		else {reset(charInput,progress,lnumGuesses,hangman,lLetters,imageIcon);}
		
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
		else {reset(charInput,progress,lnumGuesses,hangman,lLetters,imageIcon);}
	}
	
	//reset the letter in the text box
		input.setText("");
	
}
	
	/**
	  * Checks if the user is out of gueeses. If out of guesses, if 0 guesses, set letter input restriction to 0 and display out of guess message
	  * @param input JTextField used for guessing letters
	  */
	private void guessChecker(JTextField input) {
		if (guesses == 0) {
			
			input.setDocument(new LetterInputFilter(0));
			
			
			
			JOptionPane.showMessageDialog(null, "You are out of guesses you must guess the word now.");
		}
		
	}
	
	/**
	  * Uses a switch statement to update the hangman progress image to reflect the user's progress in the game
	  * @param hangman JLable being used to display the current hangman image
	  * @param imageIcon ImagineIcon being changed to an image matching the amount of guesses the user has
	  */
	private void setHangManState(JLabel hangman, ImageIcon imageIcon) {
		switch (guesses) {
		case 6:
			imageIcon = new ImageIcon(new ImageIcon(System.getProperty("user.dir") + "/Hangman/Hangman.jpeg").getImage().getScaledInstance(240, 240, Image.SCALE_DEFAULT));
			hangman.setIcon(imageIcon);
			break;
		case 5:
			imageIcon = new ImageIcon(new ImageIcon(System.getProperty("user.dir") + "/Hangman/Hangman2.jpeg").getImage().getScaledInstance(240, 240, Image.SCALE_DEFAULT));
			hangman.setIcon(imageIcon);
			break;
		case 4:
			imageIcon = new ImageIcon(new ImageIcon(System.getProperty("user.dir") + "/Hangman/Hangman3.jpeg").getImage().getScaledInstance(240, 240, Image.SCALE_DEFAULT));
			hangman.setIcon(imageIcon);
			break;
		case 3:
			imageIcon = new ImageIcon(new ImageIcon(System.getProperty("user.dir") + "/Hangman/Hangman4.jpeg").getImage().getScaledInstance(240, 240, Image.SCALE_DEFAULT));
			hangman.setIcon(imageIcon);
			break;
		case 2:
			imageIcon = new ImageIcon(new ImageIcon(System.getProperty("user.dir") + "/Hangman/Hangman5.jpeg").getImage().getScaledInstance(240, 240, Image.SCALE_DEFAULT));
			hangman.setIcon(imageIcon);
			break;
		case 1:
			imageIcon = new ImageIcon(new ImageIcon(System.getProperty("user.dir") + "/Hangman/Hangman6.jpeg").getImage().getScaledInstance(240, 240, Image.SCALE_DEFAULT));
			hangman.setIcon(imageIcon);
			break;
		case 0:
			imageIcon = new ImageIcon(new ImageIcon(System.getProperty("user.dir") + "/Hangman/Hangman7.jpeg").getImage().getScaledInstance(240, 240, Image.SCALE_DEFAULT));
			hangman.setIcon(imageIcon);
			break;
		}
	}
	
	/**
	  * Displays a confirm dialog box to ask the user if they want to continue
	  * @param prompt Text to be displayed in the prompt
	  * @return
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
	 * function that adds incorrect letters guessed to an array of characters
	 * takes the lLetters Jlabel and the input box as a parameter
	 */
	/**
	 * Handles duplicate guesses and updates front and backend to refelct incorrect guesses
	 * @param lLetters JLabel to display incorrect guesses
	 * @param input JTextField that gets the user's guess
	 */
	private void addLetterPool(JLabel lLetters,JTextField input) {
		
		// if the user enters the same letter already guessed..
		if (lettersGuessedList.contains(input.getText().charAt(0))) {
			
			//prompt the user that they entered a duplicate
			JOptionPane.showMessageDialog(null, "You have already guessed this letter!");
			
			//add 1 to guesses so they don't get penalized
			guesses +=1;
		}
		
		//if they didn't enter a duplicate..
		else {
			
			//add the letter to the array
			lettersGuessedList.add(input.getText().charAt(0));
			
			//update the label
			lLetters.setText(lettersGuessedList.toString());
		}
			
	}
	
	/**
	  * Resets game UI and backend parameters
	  * @param charInput JtextField being used to collect letter guesses from the user
	  * @param lWordProgress JLabel that outputs the which letters the user has correctly guessed
	  * @param lNumGuesses JLabel displying the current number of guesses
	  * @param hangman JLabel that displays the current hangman picture to replect user's progress
	  */
	private void reset(JTextField charInput, JLabel lWordProgress, JLabel lNumGuesses, JLabel hangman,JLabel lLetters,ImageIcon imageIcon) {
		
		randomWord = requestWord(); //generates new word
		
		wordCharList = generateWordArray(randomWord); // generates new wordChar list from the new random word
		
		wordProgList = generateWordProgress(randomWord); // generates new wordProgress list from the new random word
		
		lettersGuessedList = new ArrayList<Character>(); //resets the letters guessed pool
		
		guesses = 6; //sets guesses back to 6
		
		lWordProgress.setText(String.valueOf(generateWordProgress(randomWord))); //sets the word progress on the screen to fit the new word
		
		lNumGuesses.setText(Integer.toString(guesses)); //sets the guesses back on the screen to 6
		
		imageIcon = new ImageIcon(new ImageIcon(System.getProperty("user.dir") + "/Hangman/Hangman.jpeg").getImage().getScaledInstance(240, 240, Image.SCALE_DEFAULT));
		hangman.setIcon(imageIcon); //resets the hangman state
		
		charInput.setDocument(new LetterInputFilter(1)); //resets the input filter back to 1 character
		
		lLetters.setText(""); // reset the letters guessed pool back to an empty string
	}
	
	
}

