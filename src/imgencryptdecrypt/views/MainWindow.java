/*
 * Ardiansyah | http://ard.web.id
 */
package imgencryptdecrypt.views;

/**
 *
 * @author ardiansyah
 */
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.HeadlessException;
import javax.swing.JFrame;

public class MainWindow extends JFrame{
	
	private final int width = 1024;
	private final int height = 600;
	
	public MainWindow() throws HeadlessException {
		super("ImgEncrypt");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(width, height));
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
	}
	
}
