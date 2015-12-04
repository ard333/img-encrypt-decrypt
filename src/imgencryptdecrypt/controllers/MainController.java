/*
 * Ardiansyah | http://ard.web.id
 */
package imgencryptdecrypt.controllers;

import com.alee.managers.notification.NotificationIcon;
import com.alee.managers.notification.NotificationManager;
import com.alee.managers.notification.WebNotificationPopup;
import imgencryptdecrypt.ImgEncryptDecrypt;
import imgencryptdecrypt.services.EncryptDecrypt;
import imgencryptdecrypt.services.ImageChooser;
import imgencryptdecrypt.services.NameParser;
import imgencryptdecrypt.services.ReconstructImage;
import imgencryptdecrypt.views.ImagePanel;
import imgencryptdecrypt.views.MainWindow;
import imgencryptdecrypt.views.OperationPanel;
import imgencryptdecrypt.views.InputNameDialog;
import imgencryptdecrypt.views.ProgressDialog;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URLConnection;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JLabel;

/**
 *
 * @author ardiansyah
 */
public class MainController {
	
	private final MainWindow mainWindow;
	private final ImagePanel inputImagePanel;
	private final ImagePanel resultImagePanel;
	
	private Integer[][] inputMatrix;
	private Integer w;
	private Integer h;
	private Integer N;
	private String imageName;
	
	private final String SnBSPattern = ".*[/\\\\].*";
	private final WebNotificationPopup notificationPopup = new WebNotificationPopup();
	private NameParser nameParser;
	
	public MainController(MainWindow mainWindow, OperationPanel operationPanel, ImagePanel inputImagePanel, ImagePanel resultImagePanel) {
		this.mainWindow = mainWindow;
		this.inputImagePanel = inputImagePanel;
		this.resultImagePanel = resultImagePanel;
		notificationPopup.setDisplayTime(3000);
		
		operationPanel.browseButton.addActionListener((ActionEvent e) -> {
			this.chooseImage();
		});
		operationPanel.encryptButton.addActionListener((ActionEvent e) -> {
			this.Do("encrypt");
		});
		operationPanel.decryptButton.addActionListener((ActionEvent e) -> {
			this.Do("decrypt");
		});
	}
	
	private void chooseImage() {
		ImageChooser imageChooser = new ImageChooser();
		
		int returnVal = imageChooser.showDialog(this.mainWindow, "Pilih Gambar");
		if(returnVal == ImageChooser.APPROVE_OPTION){
			File inputImageFile = imageChooser.getSelectedFile();
			try {
				File imageFile = new File(inputImageFile.getPath());
				this.imageName = imageFile.getName();
				
				InputStream is = new BufferedInputStream(new FileInputStream(imageFile));
				String mimeType = URLConnection.guessContentTypeFromStream(is);
				
				if (mimeType != null && (mimeType.equals("image/jpeg") || mimeType.equals("image/png"))) {
					BufferedImage inputImage = ImageIO.read(imageFile);
					this.w = inputImage.getWidth();
					this.h = inputImage.getHeight();
					BufferedImage tempImage = inputImage;
					if (this.w != this.h) {
						ReconstructImage newImage = new ReconstructImage(inputImage);
						tempImage = newImage.getNewImage();
					}
					if (this.inputImagePanel.setImage(inputImage)) {
						this.N = tempImage.getHeight();
						this.inputMatrix = new Integer[N][N];

						for (int i = 0; i < N; i++) {
							for (int j = 0; j < N; j++) {
								inputMatrix[i][j] = tempImage.getRGB(i, j);
							}
						}
					}
				} else {
					notificationPopup.setIcon(NotificationIcon.error);
					notificationPopup.setContent(new JLabel("Invalid Image"));
					NotificationManager.showNotification(notificationPopup);
				}
				nameParser = new NameParser(this.imageName.substring(0, this.imageName.length()-4));
			} catch (IOException ex) {
				Logger.getLogger(ImgEncryptDecrypt.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}
	
	private void Do(String type) {
		String additionalName;
		
		Integer p;	Integer q;	Integer m;
		Double x0;	Double r;
		if (this.inputMatrix != null) {
			if (type.equals("encrypt")) {
				Random random = new Random();
				p = random.nextInt(99);
				q = random.nextInt(99);
				m = random.nextInt(30);
				x0 = Double.parseDouble(new BigDecimal(0 + (1 - (0)) * random.nextDouble()).setScale(3, RoundingMode.UP).toString());
				r = Double.parseDouble(new BigDecimal(3.75 + (4 - (3.75)) * random.nextDouble()).setScale(3, RoundingMode.UP).toString());
				additionalName = "-("+p+"-"+q+"-"+m+"--"+x0+"-"+r+")-"+this.w+"x"+this.h+"";
			} else {
				p = nameParser.getP();
				q = nameParser.getQ();
				m = nameParser.getM();
				x0 = nameParser.getX0();
				r = nameParser.getR();
				additionalName = "";
			}
			
			InputNameDialog inputNameDialog = new InputNameDialog(this.mainWindow, additionalName);
			final String tempName = additionalName;
			//==================================================================
			inputNameDialog.okButton.addActionListener((ActionEvent e)->{
				String newAdditionalName = tempName;
				String fileName = inputNameDialog.nameField.getText();
				Boolean validInput = true;
				if (!fileName.matches(SnBSPattern) && !fileName.equals("")) {
					
					EncryptDecrypt encryptDecrypt = new EncryptDecrypt(x0, r, p, q, m, this.inputMatrix);
					String title;
					if (type.equals("encrypt")) {
						title = "Encrypting";
					} else {
						title = "Decrypting";
					}
					ProgressDialog loading = new ProgressDialog(this.mainWindow, title);
					
					//++++++++++++++++++++++++++++++++++++++++++++++++++++++
					Runnable proccess = ()->{
						Integer[][] result;
						if (type.equals("encrypt")) {
							result = encryptDecrypt.encrypt();
						} else {
							result = encryptDecrypt.decrypt(nameParser.getW(), nameParser.getH());
						}
						BufferedImage resultImage = new BufferedImage(result.length, result[0].length, BufferedImage.TYPE_INT_RGB);
						for (int i = 0; i < resultImage.getWidth(); i++) {
							for (int j = 0; j < resultImage.getHeight(); j++) {
								resultImage.setRGB(i, j, result[i][j]);
							}
						}
						File outputfile = new File(fileName+newAdditionalName+".png");
						try {
							ImageIO.write(resultImage, "png", outputfile);
							this.resultImagePanel.setImage(ImageIO.read(new File(fileName+newAdditionalName+".png")));
						} catch (IOException ex) {
							Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
						}
						loading.dispose();
					};
					//++++++++++++++++++++++++++++++++++++++++++++++++++++++
					
					Thread proccessThread = new Thread(proccess);
					inputNameDialog.dispose();
					proccessThread.start();
					loading.setVisible(true);
				} else {
					validInput = false;
				}
				if (!validInput) {
					notificationPopup.setIcon(NotificationIcon.error);
					notificationPopup.setContent(new JLabel("Invalid Input"));
					NotificationManager.showNotification(notificationPopup);
				}
			});
			//==================================================================
			if (nameParser.getStatus() || type.equals("encrypt")) {
				inputNameDialog.setVisible(true);
			} else {
				notificationPopup.setIcon(NotificationIcon.error);
				notificationPopup.setContent(new JLabel("Invalid Name"));
				NotificationManager.showNotification(notificationPopup);	
			}
		} else {
			notificationPopup.setIcon(NotificationIcon.error);
			notificationPopup.setContent(new JLabel("Choose an image"));
			NotificationManager.showNotification(notificationPopup);
		}
	}
	
}
