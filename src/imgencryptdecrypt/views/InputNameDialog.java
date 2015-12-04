/*
 * Ardiansyah | http://ard.web.id
 */
package imgencryptdecrypt.views;

/**
 *
 * @author ardiansyah
 */
import com.alee.laf.text.WebTextField;
import imgencryptdecrypt.filter.TextFieldLimit;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class InputNameDialog extends JDialog{
	
	public JButton okButton = new JButton("Save");
	public WebTextField nameField = new WebTextField();
	
	public InputNameDialog(MainWindow mainWindow, String additionalName) {
		super(mainWindow, "Input Name", true);
		setResizable(false);
		setSize(new Dimension(370, 110));
		setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		
		nameField.setDocument(new TextFieldLimit(100));
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener((ActionEvent e)->{ dispose(); });
		
		JPanel topPanel = new JPanel(new GridLayout(1, 2, 0, 5));
		topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));
		topPanel.add(nameField);
		
		if (!additionalName.equals("")) {
			JTextField additionalNameField = new JTextField(additionalName);
			additionalNameField.setEditable(false);
			topPanel.add(additionalNameField);
		}
		
		JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		bottomPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
		bottomPanel.add(okButton);bottomPanel.add(cancelButton);
		
		add(topPanel, BorderLayout.CENTER);
		add(bottomPanel, BorderLayout.SOUTH);
	}
	
}
