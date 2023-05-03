package Hangman;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 * The LetterInputFiler performs data validation on the user input fields
 */
public class LetterInputFilter extends PlainDocument {
	
	private static final long serialVersionUID = 7748466219553489230L;
	int charLimit; //sets the char limit to whatever is put into the constructor
	
	/**
	 * Constructor for the LetterInput Class
	 * @param limit The number of letters allowed in the JTextField
	 */
	public LetterInputFilter(int limit) {
		
		charLimit = limit;
		
	}
	
	// function that only allows input if the user enters a character/string of the limit set and if it is a letter
	public void insertString(int offset, String str, AttributeSet set) throws BadLocationException {
		
		if (str.equals(null)) {
			return;
		}
		
		else if (getLength() + str.length() <= charLimit && Character.isAlphabetic(str.charAt(0))) {
			super.insertString(offset,str,set);
		}
	}

}
