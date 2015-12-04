/*
 * Ardiansyah | http://ard.web.id
 */
package imgencryptdecrypt.services;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author ardiansyah
 */
public class NameParser {
	
	private Integer w;
	private Integer h;
	private Integer p;
	private Integer q;
	private Integer m;
	private Double x0;
	private Double r;
	private Boolean status;
	
	public NameParser(String imageName) {
		this.status = true;
		
		String additionalName = "";
		
		Pattern additionalNamePattern = Pattern.compile("\\([0-9]{1,2}-[0-9]{1,2}-[0-9]{1,2}--[0-9]{1}\\.[0-9]{1,3}-[0-9]{1}\\.[0-9]{1,3}\\)-[0-9]+x[0-9]+$");
		Matcher additionalNameMatcher = additionalNamePattern.matcher(imageName);
		
		if (additionalNameMatcher.find()) {
			additionalName = additionalNameMatcher.group(0);
		} else {
			this.status = false;
		}
		if (!additionalName.equals("")) {
			Pattern pattern = Pattern.compile("[0-9]+x");
			Matcher matcher = pattern.matcher(additionalName);
			if (matcher.find()) {
				this.w = Integer.parseInt(matcher.group(0).substring(0, matcher.group(0).length()-1));
			}
			
			pattern = Pattern.compile("x[0-9]+");
			matcher = pattern.matcher(additionalName);
			if (matcher.find()) {
				this.h = Integer.parseInt(matcher.group(0).substring(1, matcher.group(0).length()));
			}
			
			pattern = Pattern.compile("\\([0-9]{1,2}");
			matcher = pattern.matcher(additionalName);
			if (matcher.find()) {
				this.p = Integer.parseInt(matcher.group(0).substring(1, matcher.group(0).length()));
			}
			
			pattern = Pattern.compile("-[0-9]{1,2}-");
			matcher = pattern.matcher(additionalName);
			if (matcher.find()) {
				this.q = Integer.parseInt(matcher.group(0).substring(1, matcher.group(0).length()-1));
			}
			
			pattern = Pattern.compile("-[0-9]{1,2}--");
			matcher = pattern.matcher(additionalName);
			if (matcher.find()) {
				this.m = Integer.parseInt(matcher.group(0).substring(1, matcher.group(0).length()-2));
			}
			
			pattern = Pattern.compile("--[0-9]{1}\\.[0-9]{1,3}-");
			matcher = pattern.matcher(additionalName);
			if (matcher.find()) {
				this.x0 = Double.parseDouble(matcher.group(0).substring(2, matcher.group(0).length()-1));
			}
			
			pattern = Pattern.compile("-[0-9]{1}\\.[0-9]{1,3}\\)");
			matcher = pattern.matcher(additionalName);
			if (matcher.find()) {
				this.r = Double.parseDouble(matcher.group(0).substring(1, matcher.group(0).length()-1));
			}
		}
	}
	public Integer getW() {
		return this.w;
	}
	public Integer getH() {
		return this.h;
	}
	public Integer getP() {
		return this.p;
	}
	public Integer getQ() {
		return this.q;
	}
	public Integer getM() {
		return this.m;
	}
	public Double getX0() {
		return this.x0;
	}
	public Double getR() {
		return this.r;
	}
	public Boolean getStatus() {
		return this.status;
	}
}
