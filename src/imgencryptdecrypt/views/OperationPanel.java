/*
 * Ardiansyah | http://ard.web.id
 */
package imgencryptdecrypt.views;

/**
 *
 * @author ardiansyah
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
