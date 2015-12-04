/*
 * Ardiansyah | http://ard.web.id
 */
package imgencryptdecrypt.views;

/**
 *
 * @author ardiansyah
 */
import com.alee.laf.panel.WebPanel;
import com.alee.laf.progressbar.WebProgressBar;
import java.awt.BorderLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;

public class ProgressDialog extends JDialog{
	public ProgressDialog(JFrame frame, String title){
		super(frame, title, true);
		setLayout(new BorderLayout());
		setResizable(false);
		setSize(350, 70);
		setLocationRelativeTo(null);
		
		WebProgressBar progressBar = new WebProgressBar();
		progressBar.setIndeterminate(true);
		progressBar.setStringPainted(true);
		progressBar.setString("Please wait...");
		
		WebPanel progressPanel = new WebPanel(new BorderLayout());
		progressPanel.setMargin(10, 10, 10, 10);
		progressPanel.add(progressBar, BorderLayout.CENTER);
		
		add(progressPanel, BorderLayout.CENTER);
	}
}
