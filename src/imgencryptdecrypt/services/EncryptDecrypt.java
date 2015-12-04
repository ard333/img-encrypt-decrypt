/*
 * Ardiansyah | http://ard.web.id
 */
package imgencryptdecrypt.services;

import java.awt.Color;
import java.util.Arrays;

/**
 *
 * @author ardiansyah
 */
public class EncryptDecrypt {
	
	private final Double x0;
	private Double[] x;
	private final Double r;
	private Integer lmIteration = 0;
	private final Integer p;
	private final Integer q;
	private final Integer acmIteration;
	private final Integer[][] M;
	private Integer[][] inputMatrix;
	private final Integer N;
	
	public EncryptDecrypt(Double x0, Double r, Integer p, Integer q, Integer acmIteration, Integer[][] inputMatrix) {
		this.x0 = x0;
		this.r = r;
		this.p = p;
		this.q = q;
		this.acmIteration = acmIteration;
		this.inputMatrix = inputMatrix;
		this.N = this.inputMatrix.length;
		this.x = new Double[this.N * this.N + 1];
		
		M = new Integer[2][2];
		M[0][0] = 1;		M[0][1] = this.p;
		M[1][0] = this.q;	M[1][1] = this.p * this.q + 1;
	}
	
	public Integer[][] encrypt() {
		//==========ACM==========
		Integer[][] result = this.ACM(this.inputMatrix, "encrypt");
		//=======================
		
		//==========sLM==========
		result = this.selectiveLM(result);
		//=======================
		return result;
	}
	public Integer[][] decrypt(Integer width, Integer height) {
		//==========sLM==========
		Integer[][] tempResult = this.selectiveLM(this.inputMatrix);
		//=======================
		
		//==========ACM==========
		tempResult = this.ACM(tempResult, "decrypt");
		//=======================
		
		Integer[][] result = new Integer[width][height];
		for (int i = 0; i < width; i++) {
			System.arraycopy(tempResult[i], 0, result[i], 0, height);
		}
		
		return result;
	}
	
	private Integer[][] ACM(Integer[][] matrix, String status) {
		Integer[] hasil = new Integer[2];
		Integer[][] result = new Integer[N][N];
		
		for (int i = 0; i < this.acmIteration; i++) {
			for (int a = 0; a < N; a++) {
				for (int b = 0; b < N; b++) {
					hasil[0] = (M[0][0]*a + M[0][1]*b) % N;
					hasil[1] = (M[1][0]*a + M[1][1]*b) % N;
					if (status.equals("encrypt")) {
						result[a][b] = matrix[hasil[0]][hasil[1]];
					} else {
						result[hasil[0]][hasil[1]] = matrix[a][b];
					}
				}
			}
			for (int a = 0; a < N; a++) {
				System.arraycopy(result[a], 0, matrix[a], 0, N);
			}
		}
		return result;
	}
	
	private Integer[][] selectiveLM(Integer[][] matrix) {
		String k;
		x[0] = this.x0;
		Integer[] kBinary;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				this.lmIteration++;
				x[lmIteration] = this.r*this.x[lmIteration-1]*(1-this.x[lmIteration-1]);
				k = this.getK(x[lmIteration]);
				
				kBinary = this.getIntegerOfBinaryString(k);
				
				Color pixelColor = new Color(matrix[i][j]);
				String rBinaryString = this.fillZero(8, Integer.toBinaryString(pixelColor.getRed()));
				String gBinaryString = this.fillZero(8, Integer.toBinaryString(pixelColor.getGreen()));
				String bBinaryString = this.fillZero(8, Integer.toBinaryString(pixelColor.getBlue()));
				
				Integer[] rBinary = this.getIntegerOfBinaryString(rBinaryString);
				Integer[] gBinary = this.getIntegerOfBinaryString(gBinaryString);
				Integer[] bBinary = this.getIntegerOfBinaryString(bBinaryString);
				
				Integer[] rBinaryMSB = Arrays.copyOfRange(rBinary, 0, 4);
				Integer[] gBinaryMSB = Arrays.copyOfRange(gBinary, 0, 4);
				Integer[] bBinaryMSB = Arrays.copyOfRange(bBinary, 0, 4);
				
				Integer[] rSelResult = this.XORArrayOfInt(kBinary, rBinaryMSB);
				Integer[] gSelResult = this.XORArrayOfInt(kBinary, gBinaryMSB);
				Integer[] bSelResult = this.XORArrayOfInt(kBinary, bBinaryMSB);
				
				Integer[] newRBinary = this.replaceBit(rBinary, rSelResult);
				Integer[] newGBinary = this.replaceBit(gBinary, gSelResult);
				Integer[] newBBinary = this.replaceBit(bBinary, bSelResult);
				
				Integer newR = Integer.parseInt(Arrays.toString(newRBinary).replace(",", "").replace("[", "").replace("]", "").replace(" ", "").trim(), 2);
				Integer newG = Integer.parseInt(Arrays.toString(newGBinary).replace(",", "").replace("[", "").replace("]", "").replace(" ", "").trim(), 2);
				Integer newB = Integer.parseInt(Arrays.toString(newBBinary).replace(",", "").replace("[", "").replace("]", "").replace(" ", "").trim(), 2);
				Color newPixelColor = new Color(newR, newG, newB);
				
				matrix[i][j] = newPixelColor.getRGB();
			}
		}
		return matrix;
	}
	
	private Integer[] XORArrayOfInt(Integer[] a, Integer[] b) {
		Integer[] result;
		if (a.length == b.length) {
			result = new Integer[a.length];
			for (int i = 0; i < a.length; i++) {
				result[i] = a[i] ^ b[i];
			}
			return result;
		} else {
			return null;
		}
	}
	
	private Integer[] replaceBit(Integer[] original, Integer[] xorResult) {
		for (int i = 0; i < xorResult.length; i++) {
			original[i] = xorResult[i];
		}
		return original;
	}
	
	private String fillZero(Integer length, String text) {
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < length; i++) {
			result.append("0");
		}
		for (int i = text.length()-1; i >= 0; i--) {
			length--;
			result.setCharAt(length, text.charAt(i));
		}
		return result.toString();
	}
	
	private String getK(Double x) {
		String k = x.toString();
		if (k.length()>6) {
			k = k.substring(2, 6);
		} else {
			k = k.substring(2, k.length());
		}
		k = Integer.toBinaryString(Integer.parseInt(k) % 256);
		
		if (k.length()<=4) {
			k = this.fillZero(4, k);
		} else {
			k = k.substring(0, 4);
			k = this.fillZero(4, k);
		}
		return k;
	}
	
	private Integer[] getIntegerOfBinaryString(String binaryString) {
		Integer[] result = new Integer[binaryString.length()];
		for (int a = 0; a < binaryString.length(); a++) {
			String temp = binaryString.charAt(a)+"";
			result[a] = Integer.parseInt(temp);
		}
		return result;
	}
}
