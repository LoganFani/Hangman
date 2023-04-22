package Hangman;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class App extends JFrame implements ActionListener {
	
	static Logic gameLogic = new Logic();
	
	String randomWord = gameLogic.generateWord();
	static int guesses = 6;
	
	JButton bLetterGuess, bWordGuess;
	JTextField tWordGuess;
	JLabel lWordProgress,lNumGuesses;
	
	public App() {
		
		
		final int WIDTH = 640;
		final int HEIGHT = 480;
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(WIDTH, HEIGHT);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
		this.setLayout(new BorderLayout());
		
		
		
		//panels
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
		
		pEast.setLayout(new BorderLayout());
		pNorth.setLayout(new BorderLayout());
		pInputSouth.setLayout(new BorderLayout());
		pWin.setLayout(new BorderLayout());
		pLoss.setLayout(new BorderLayout());
		pCharInput.setLayout(new BorderLayout());
		pWordInput.setLayout(new BorderLayout());
		pGuesses.setLayout(new BorderLayout());
		pLettersGuessed.setLayout(new BorderLayout());
		
		
		//labels
		JLabel lGuesses = new JLabel("Guesses: ");
		lNumGuesses = new JLabel();
		
		JLabel hangman = new JLabel(gameLogic.getHangManState(0));
		
		JLabel lWins = new JLabel("Wins: ");
		JLabel lLosses = new JLabel("Losses: ");
		
		JLabel lNumWins = new JLabel("0");
		JLabel lNumLosses = new JLabel("0");
		
		lWordProgress = new JLabel("");
		
		JLabel lLettersGuessed = new JLabel("Letters Guessed: ");
		JLabel lLetters = new JLabel("a");
		
		
		//buttons
		bLetterGuess = new JButton("Guess Letter");
		bLetterGuess.addActionListener(buttonListener);
		
		bWordGuess = new JButton("Guess Word");
		
		//input
		JTextField tLetterGuess = new JTextField();
		tLetterGuess.setDocument(new LetterInputFilter(1));
		tLetterGuess.setPreferredSize(new Dimension(100,24));
		
		tWordGuess = new JTextField();
		tWordGuess.setPreferredSize(new Dimension(100,24));
		
		
		// add order
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
		
		fillWordProgress(randomWord, lWordProgress);
		
		this.pack();
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		
		new App();
		

	}

	ActionListener buttonListener = new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e) {
			
			if (e.getSource() == bLetterGuess) {
				//charInput(tWordGuess.getText(),randomWord,lWordProgress,lNumGuesses);
				System.out.print(tWordGuess.getText());
				}
			}
	};
	
	
	private static void fillWordProgress(String word, JLabel label) {
		
		String tempStr = "";
		
		for (int i = 0; i<word.length();i++) {
			tempStr += "-";
		}
		
		label.setText(tempStr);
		
	}
	
	private static void charInput(String input, String word, JLabel progress, JLabel guessCounter) {
		
		String tempStr = "";
		
		for (int i =0; i<word.length(); i++) {
			
			
			if (input.charAt(i) == word.charAt(i)) {
				tempStr += input;
			}
			
			else {
				tempStr += "-";
			}
			
		}
		
		guesses -=1;
		guessCounter.setText(Integer.toString(guesses));
		
		progress.setText(tempStr);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	

}
