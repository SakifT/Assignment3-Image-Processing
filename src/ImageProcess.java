import java.awt.Color;

public class ImageProcess {

	public static void main(String[] args) throws InterruptedException {
		//Create new image utility.
		ImageUtils utils = new ImageUtils();
		
		//load in an image.
		Color[][] orig = utils.loadImage("src/LennaCV.png");
		Color[][] oldwoman = utils.loadImage("src/oldLenna.jpg");
		
		// Display original image.
		utils.addImage(orig, "Original image.");
		utils.display(); // display original image.
		
		Thread.sleep(500); // sleep for 500 milliseconds (.5 second)
		
		utils.disposeOld();
		

		// FADING Process the image.
		for(int i = 1; i < 10; i++) {
			Color[][] fade = fading(orig, oldwoman, (double)i/10);
			utils.addImage(fade,  "Processed image.");
			utils.display(); // display original image.
			Thread.sleep(500); // sleep for 500 milliseconds (.5 second)
			utils.disposeOld();
		}

		// Display old lenna
		utils.addImage(oldwoman, "Old Lenna");
		utils.display(); // display tabs
	}
	
	public static Color[][] fading(Color[][] young, Color[][] old, double pct) {
		Color[][] tmp = ImageUtils.cloneArray(young);
		
		for (int i=0; i<tmp.length; i++) {
			for (int j=0; j<tmp[i].length; j++) {
				if (i<tmp.length) {
					// young pixel
					Color youngPixel = young[i][j];
					// old pixel
					Color oldPixel = old[i][j];
					// Weighted value from young to old. (ie: 90% = 90% young, 10% old).
					int redMid = (int)(youngPixel.getRed()*(1-pct) + oldPixel.getRed()*pct);
					int greenMid = (int)(youngPixel.getGreen()*(1-pct) + oldPixel.getGreen()*pct);
					int blueMid = (int)(youngPixel.getBlue()*(1-pct) + oldPixel.getBlue()*pct);
					tmp[i][j] = new Color(redMid, greenMid, blueMid);
					
				}
			}
		}
		
		return tmp;
	}
}	
		
		
		