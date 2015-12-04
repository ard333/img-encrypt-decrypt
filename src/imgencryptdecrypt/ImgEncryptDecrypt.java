/*
 * Ardiansyah | http://ard.web.id
 */
package imgencryptdecrypt;

import com.alee.laf.WebLookAndFeel;
import imgencryptdecrypt.controllers.MainController;
import imgencryptdecrypt.views.ImagePanel;
import imgencryptdecrypt.views.MainWindow;
import imgencryptdecrypt.views.OperationPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author ardiansyah
 */
public class ImgEncryptDecrypt {
	
	public static void main(String[] args) throws IOException {
		try{
			UIManager.setLookAndFeel(WebLookAndFeel.class.getCanonicalName());
		} catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
			Logger.getLogger(ImgEncryptDecrypt.class.getName()).log(Level.SEVERE, null, ex);
		}
		MainWindow mainWindow = new MainWindow();
		
		ImagePanel inputImagePanel = new ImagePanel();
		ImagePanel resultImagePanel = new ImagePanel();
		
		JPanel iePanel = new JPanel(new GridLayout(1, 2));
		iePanel.add(inputImagePanel);
		iePanel.add(resultImagePanel);
		
		OperationPanel operationPanel = new OperationPanel();
		MainController mainController = new MainController(mainWindow, operationPanel, inputImagePanel, resultImagePanel);
		
		mainWindow.add(new JLabel("<html><h3>&nbsp;Image Encrypt Decrypt</h3></html>"), BorderLayout.NORTH);
		mainWindow.add(iePanel, BorderLayout.CENTER);
		mainWindow.add(operationPanel, BorderLayout.SOUTH);
		mainWindow.setVisible(true);
	}
	
}
