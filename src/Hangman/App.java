package Hangman;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class App extends JFrame implements ActionListener {
	
	Logic gameLogic = new Logic();
	public App() {
		
		final int WIDTH = 640;
		final int HEIGHT = 480;
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(WIDTH, HEIGHT);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
		//this.setLayout(new FlowLayout());
		
		//labels
		JLabel guesses = new JLabel("Guesses: ");
		JLabel hangman = new JLabel(gameLogic.getHangManState(0));
		
		
		//buttons
		JButton bLetterGuess = new JButton("Guess Letter");
		
		//input
		JTextField tLetterGuess = new JTextField();
		tLetterGuess.setDocument(new LetterInputFilter(1));
		
		
		// add order
		this.add(guesses);
		this.add(hangman);
		this.add(tLetterGuess);
		
		
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		new App();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
