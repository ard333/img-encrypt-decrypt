/*
 * Ardiansyah | http://ard.web.id
 */
package id.web.ard.imgencryptdecrypt.views;

/**
 *
 * @author Ardiansyah <ard333.ardiansyah@gmail.com>
 */
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class OperationPanel extends JPanel{
	
	public JButton browseButton = new JButton("Browse");
	public JButton encryptButton = new JButton("Encrypt and Save");
	public JButton decryptButton = new JButton("Decrypt and Save");
	
	public OperationPanel() {
		super(new FlowLayout(FlowLayout.LEFT));
		add(browseButton);
		add(encryptButton);
		add(decryptButton);
	}
	
}
