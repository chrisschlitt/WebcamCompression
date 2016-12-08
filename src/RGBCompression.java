import java.awt.Color;
import java.awt.image.BufferedImage;

public class RGBCompression {
	int[][] red;
	int[][] green;
	int[][] blue;
	byte[] compressedImage;

	public RGBCompression(BufferedImage image, int ratio) {
		
		red = new int[image.getHeight()][image.getWidth()];
		green = new int[image.getHeight()][image.getWidth()];
		blue = new int[image.getHeight()][image.getWidth()];

		setUpRGBArrays(image);
		ImageCompression redCompression = new ImageCompression(red, ratio);
		ImageCompression greenCompression = new ImageCompression(green, ratio);
		ImageCompression blueCompression = new ImageCompression(blue, ratio);
	    
		Thread redThread = new Thread(redCompression);
	    Thread greenThread = new Thread(greenCompression);
	    Thread blueThread = new Thread(blueCompression);
	    redThread.start();
	    greenThread.start();
	    blueThread.start();
	    try {
			redThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	    
	    try {
			greenThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	    
	    try {
			blueThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	        
		
		byte[] redCompressed = redCompression.getCompressedImage();
		byte[] greenCompressed = greenCompression.getCompressedImage();
		byte[] blueCompressed = blueCompression.getCompressedImage();
		compressedImage = new byte[blueCompressed.length * 3];
		compressedImage = mergeArray(redCompressed,greenCompressed);
		compressedImage = mergeArray(compressedImage,blueCompressed);
		
	}
	
	public byte[] getCompressedImage() {
		return compressedImage;
	}
	
	private byte[] mergeArray(byte[] redCompressed, byte[] greenCompressed) {
		
		byte[] combined = new byte[redCompressed.length + greenCompressed.length];
		int k = 0;
		
		for (int i = 0; i < redCompressed.length; i++) {
			combined[k] = redCompressed[i];
			k++;
		}
		
		for (int i = 0; i < greenCompressed.length; i++) {
			combined[k] = greenCompressed[i];
			k++;
		}
		
		return combined;
	}


	private void setUpRGBArrays(BufferedImage image) {
		Color color;
		int[] imageColors = image.getRGB(0, 0, image.getWidth(), image.getHeight(), null, 0, image.getWidth());
		int count = 0; 
		int x = 0;
		int y = 0;
		while (count < imageColors.length) {
			if (x==image.getWidth()) {
				x = 0;
				y++;
			}
			color = new Color(imageColors[count]);
//			System.out.println( x + "," + y);
			red[y][x] = color.getRed();
			green[y][x] = color.getGreen();
			blue[y][x] = color.getBlue();
			
			x++;
			count++;
		}
	}
}