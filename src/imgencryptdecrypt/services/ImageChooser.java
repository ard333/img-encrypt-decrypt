/*
 * Ardiansyah | http://ard.web.id
 */
package imgencryptdecrypt.services;

import imgencryptdecrypt.filter.ImageFilter;
import javax.swing.JFileChooser;

/**
 *
 * @author ardiansyah
 */
public class ImageChooser extends JFileChooser{

	public ImageChooser() {
		addChoosableFileFilter(new ImageFilter());
		setAcceptAllFileFilterUsed(false);
	}
	
}
