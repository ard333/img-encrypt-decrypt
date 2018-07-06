package com.ard333.imgencryptdecrypt.views;

/**
 *
 * @author Ardiansyah <ard333.ardiansyah@gmail.com>
 */
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class OperationPanel extends JPanel{
	
	private JButton browseButton = new JButton("Browse");
	private JButton encryptButton = new JButton("Encrypt and Save");
	private JButton decryptButton = new JButton("Decrypt and Save");
	
	public OperationPanel() {
		super(new FlowLayout(FlowLayout.LEFT));
		add(browseButton);
		add(encryptButton);
		add(decryptButton);
	}

	public JButton getBrowseButton() {
		return browseButton;
	}

	public JButton getEncryptButton() {
		return encryptButton;
	}

	public JButton getDecryptButton() {
		return decryptButton;
	}
	
	
}
