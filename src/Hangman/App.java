package Hangman;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class App extends JFrame implements ActionListener {
	
	static Logic gameLogic = new Logic();
	
	static String randomWord = gameLogic.generateWord();
	static ArrayList<Character> wordCharList = gameLogic.generateWordArray(randomWord);
	static int guesses = 6;
	
	
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
		JLabel lNumGuesses = new JLabel();
		
		JLabel hangman = new JLabel(gameLogic.getHangManState(0));
		
		JLabel lWins = new JLabel("Wins: ");
		JLabel lLosses = new JLabel("Losses: ");
		
		JLabel lNumWins = new JLabel("0");
		JLabel lNumLosses = new JLabel("0");
		
		JLabel lWordProgress = new JLabel(String.valueOf(gameLogic.generateWordProgress(randomWord)));
		
		JLabel lLettersGuessed = new JLabel("Letters Guessed: ");
		JLabel lLetters = new JLabel("");
		
		
		//buttons
		JButton bLetterGuess = new JButton("Guess Letter");
		
		
		JButton bWordGuess = new JButton("Guess Word");
		
		//input
		JTextField tLetterGuess = new JTextField();
		tLetterGuess.setDocument(new LetterInputFilter(1));
		tLetterGuess.setPreferredSize(new Dimension(100,24));
		
		JTextField tWordGuess = new JTextField();
		tWordGuess.setPreferredSize(new Dimension(100,24));
		
		
		
		bLetterGuess.addActionListener(e -> charInput(tLetterGuess,randomWord,lWordProgress,lNumGuesses));
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
		
		
		
		
		this.pack();
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		System.out.println(randomWord);
		new App();
		

	}


	static ArrayList<Character> updateProg = gameLogic.generateWordProgress(randomWord);
	private static void charInput(JTextField input, String word, JLabel progress, JLabel guessCounter) {
		
		if (wordCharList.contains(input.getText().charAt(0))) {
			for (int i=0; i<wordCharList.size();i++) {
				
				if (wordCharList.get(i) == input.getText().charAt(0)) {
					updateProg.set(i, wordCharList.get(i)); 
					
				}
		}
		
			
			System.out.println(updateProg);
		}
		
		
		
		guesses -=1;
		guessCounter.setText(Integer.toString(guesses));
		
		progress.setText(String.valueOf(updateProg));
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	

}
