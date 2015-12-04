/*
 * Ardiansyah | http://ard.web.id
 */
package imgencryptdecrypt.services;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 *
 * @author ardiansyah
 */
public class ReconstructImage {
	
	private final BufferedImage inputImage;
	private final Integer w;
	private final Integer h;
	private final Integer N;
	private BufferedImage result;
	
	
	public ReconstructImage(BufferedImage inputImage) {
		this.inputImage = inputImage;
		
		this.w = this.inputImage.getWidth();
		this.h = this.inputImage.getHeight();
		if (this.w < this.h) {
			this.N = this.h;
		} else {
			this.N = this.w;
		}
		result = new BufferedImage(this.N, this.N, BufferedImage.TYPE_INT_RGB);
		this.reconstruct();
	}
	
	private void reconstruct () {
		for (int i = 0; i < this.N; i++) {
			for (int j = 0; j < this.N; j++) {
				if (i >= this.w || j >= this.h) {
					this.result.setRGB(i, j, new Color(255, 255, 255).getRGB());
				} else {
					this.result.setRGB(i, j, new Color(inputImage.getRGB(i, j)).getRGB());
				}
			}
		}
	}
	
	public BufferedImage getNewImage() {
		return this.result;
	}
}
