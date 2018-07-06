package com.ard333.imgencryptdecrypt.services;

import com.ard333.imgencryptdecrypt.filter.ImageFilter;
import javax.swing.JFileChooser;

/**
 *
 * @author Ardiansyah <ard333.ardiansyah@gmail.com>
 */
public class ImageChooser extends JFileChooser{

	public ImageChooser() {
		addChoosableFileFilter(new ImageFilter());
		setAcceptAllFileFilterUsed(false);
	}
	
}
