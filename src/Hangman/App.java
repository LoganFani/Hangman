package Hangman;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.io.IOException;




/**
 * The App class creates and manages the JFrame used for the UI in which the game is played
 */
public class App extends JFrame{
	
	
	
	/**
	 * Generates the JFrame used for the UI
	 */
	public App() throws IOException{
		
		Logic gameLogic = new Logic();
		
		final int WIDTH = 640;
		final int HEIGHT = 360;
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(WIDTH, HEIGHT);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
		this.setLayout(new BorderLayout());
		
		
		
		//Initialized all panels needed for the layout
		JPanel pWin,pLoss,pCharInput,pWordInput,pGuesses, pLettersGuessed, pInputSouth, pNorth,pEast;
		pInputSouth = new JPanel();
		pWin= new JPanel();
		pLoss = new JPanel();
		pCharInput= new JPanel();
		pWordInput= new JPanel();
		pGuesses= new JPanel();
		pLettersGuessed= new JPanel();
		pNorth = new JPanel();
		pEast = new JPanel();
		
		//set each panel to a border layout
		pEast.setLayout(new BorderLayout());
		pNorth.setLayout(new BorderLayout());
		pInputSouth.setLayout(new BorderLayout());
		pWin.setLayout(new BorderLayout());
		pLoss.setLayout(new BorderLayout());
		pCharInput.setLayout(new BorderLayout());
		pWordInput.setLayout(new BorderLayout());
		pGuesses.setLayout(new BorderLayout());
		pLettersGuessed.setLayout(new BorderLayout());
		
		
		//initialized all labels
		JLabel lGuesses = new JLabel("Guesses: ");
		
		//num guesses initially grabs the guesses variable form the gameLogic object
		JLabel lNumGuesses = new JLabel(Integer.toString(gameLogic.guesses));
		
		//always starts a new game off with the hang man state 0
		ImageIcon imageIcon = new ImageIcon(new ImageIcon("../Hangman/src/images/Hangman.jpeg").getImage().getScaledInstance(240, 240, Image.SCALE_DEFAULT));
		JLabel hangman = new JLabel(imageIcon);
		
		

		
		JLabel lWins = new JLabel("Wins: ");
		JLabel lLosses = new JLabel("Losses: ");
		
		JLabel lNumWins = new JLabel("0");
		JLabel lNumLosses = new JLabel("0");
		
		//converts the character array from gameLogic into a string and sets this as the label text
		JLabel lWordProgress = new JLabel(String.valueOf(gameLogic.generateWordProgress(gameLogic.randomWord)));
		
		JLabel lLettersGuessed = new JLabel("Letters Guessed: ");
		
		//label for the incorrect letters guessed
		JLabel lLetters = new JLabel("");
		
		
		//initialized buttons with appropriate text
		JButton bLetterGuess = new JButton("Guess Letter");
		
		JButton bWordGuess = new JButton("Guess Word");
		
		//initialized the text boxes
		JTextField tLetterGuess = new JTextField();
		//sets the character limit for the char box to 1 character
		tLetterGuess.setDocument(new LetterInputFilter(1));
		tLetterGuess.setPreferredSize(new Dimension(100,24));
		
		JTextField tWordGuess = new JTextField();
		tWordGuess.setPreferredSize(new Dimension(100,24));
		
		
		// if one of the buttons pressed execute one of the functions from gameLogic
		bLetterGuess.addActionListener(e -> gameLogic.charInput(tLetterGuess,lWordProgress,lNumGuesses,hangman,lLetters,imageIcon));

		bWordGuess.addActionListener(e -> gameLogic.wordInput(tLetterGuess, tWordGuess, lWordProgress, lNumWins, lNumLosses,lNumGuesses,hangman,lLetters,imageIcon));

		bWordGuess.addActionListener(e -> gameLogic.wordInput(tLetterGuess, tWordGuess, lWordProgress, lNumWins, lNumLosses,lNumGuesses,hangman,lLetters,imageIcon));


		
		
		//adding all of the panels
		pGuesses.add(lGuesses,BorderLayout.WEST);
		pGuesses.add(lNumGuesses,BorderLayout.EAST);
		
		
		pCharInput.add(tLetterGuess,BorderLayout.WEST);
		pCharInput.add(bLetterGuess,BorderLayout.EAST);
		
		pWordInput.add(tWordGuess,BorderLayout.CENTER);
		pWordInput.add(bWordGuess,BorderLayout.WEST);
		
		pInputSouth.add(pWordInput,BorderLayout.CENTER);
		pInputSouth.add(pGuesses,BorderLayout.EAST);
		pInputSouth.add(pCharInput,BorderLayout.WEST);
		
		pWin.add(lWins,BorderLayout.WEST);
		pWin.add(lNumWins,BorderLayout.EAST);
		pLoss.add(lLosses,BorderLayout.WEST);
		pLoss.add(lNumLosses,BorderLayout.EAST);
		
		pNorth.add(pWin, BorderLayout.WEST);
		pNorth.add(pLoss, BorderLayout.EAST);
		
		pEast.add(lLettersGuessed,BorderLayout.NORTH);
		pEast.add(lLetters,BorderLayout.CENTER);
		
		this.add(pEast, BorderLayout.EAST);
		this.add(lWordProgress, BorderLayout.CENTER);
		this.add(hangman,BorderLayout.WEST);
		this.add(pNorth,BorderLayout.NORTH);
		this.add(pInputSouth,BorderLayout.SOUTH);
		
		
		//sets the window to be visible
		this.setVisible(true);
	}
	
	/**
	 * The main method calls the App() method to generate the UI and start the game
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		new App();
		

	}


}
