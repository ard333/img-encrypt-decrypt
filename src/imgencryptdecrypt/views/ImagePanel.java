/*
 * Ardiansyah | http://ard.web.id
 */
package imgencryptdecrypt.views;

/**
 *
 * @author ardiansyah
 */
import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class ImagePanel extends JPanel{
	
	private JPanel panel = new JPanel();
	private JLabel imageCanvas = new JLabel();
	
	public ImagePanel() {
		super(new BorderLayout());
		this.panel.add(imageCanvas);
		JScrollPane ImagePane = new JScrollPane(this.panel);
		add(ImagePane, BorderLayout.CENTER);
	}
	
	public Boolean setImage(BufferedImage image) {
		try {
			imageCanvas.setIcon(new ImageIcon(image));
			this.panel.repaint();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public Boolean removeImage () {
		try {
			imageCanvas.setIcon(null);
			this.panel.repaint();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
