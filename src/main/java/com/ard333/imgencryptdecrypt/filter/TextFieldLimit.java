package com.ard333.imgencryptdecrypt.filter;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 *
 * @author Ardiansyah <ard333.ardiansyah@gmail.com>
 */
public class TextFieldLimit extends PlainDocument{
	private final int limit;
	public TextFieldLimit(int limit) {
		super();
		this.limit = limit;
	}
	
	@Override
	public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
		if(str == null)
			return;
		if((getLength() + str.length()) <= limit){
			super.insertString(offset, str, attr);
		}
	}
}