package com.ard333.imgencryptdecrypt.views;

/**
 *
 * @author Ardiansyah <ard333.ardiansyah@gmail.com>
 */
import com.ard333.imgencryptdecrypt.controllers.MainController;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainWindow extends JFrame{
	
	private final int width = 1024;
	private final int height = 600;
	
	public MainWindow() throws HeadlessException {
		super("ImgEncrypt");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		ImagePanel inputImagePanel = new ImagePanel();
		ImagePanel resultImagePanel = new ImagePanel();
		
		JPanel iePanel = new JPanel(new GridLayout(1, 2));
		iePanel.add(inputImagePanel);
		iePanel.add(resultImagePanel);
		
		OperationPanel operationPanel = new OperationPanel();
		MainController mainController = new MainController(this, operationPanel, inputImagePanel, resultImagePanel);
		
		add(new JLabel("<html><h3>&nbsp;Image Encrypt Decrypt</h3></html>"), BorderLayout.NORTH);
		add(iePanel, BorderLayout.CENTER);
		add(operationPanel, BorderLayout.SOUTH);
		
		setLocationRelativeTo(null);
		setMinimumSize(new Dimension(width, height));
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		
	}
	
}
