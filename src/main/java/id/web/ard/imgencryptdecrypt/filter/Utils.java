/*
 * Ardiansyah | http://ard.web.id
 */
package id.web.ard.imgencryptdecrypt.filter;

import java.io.File;

/**
 *
 * @author Ardiansyah <ard333.ardiansyah@gmail.com>
 */
public class Utils{
	public final static String png = "png";
	public final static String jpg = "jpg";

	public static String getExtension(File f) {
		String ext = null;
		String s = f.getName();
		int i = s.lastIndexOf('.');
		if (i > 0 &&  i < s.length() - 1) {
			ext = s.substring(i+1).toLowerCase();
		}
		return ext;
	}
}
