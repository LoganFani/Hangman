package Hangman;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class LetterInputFilter extends PlainDocument {
	
	int charLimit; //sets the char limit to whatever is put into the constructor
	
	//constructor
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
