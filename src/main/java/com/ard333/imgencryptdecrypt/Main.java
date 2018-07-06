package com.ard333.imgencryptdecrypt;

import com.alee.laf.WebLookAndFeel;
import com.ard333.imgencryptdecrypt.views.MainWindow;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Ardiansyah <ard333.ardiansyah@gmail.com>
 */
public class Main {
	
	public static void main(String[] args) throws IOException {
		try{
			UIManager.setLookAndFeel(WebLookAndFeel.class.getCanonicalName());
			MainWindow mainWindow = new MainWindow();
			
		} catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		
	}
	
}
