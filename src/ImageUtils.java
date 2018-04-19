import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
public class ImageUtils {

	private JFrame frame;
	private JTabbedPane tabbedPane;
	
	ImageUtils(){
		
		frame = new JFrame("Project Images");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		tabbedPane = new JTabbedPane();
		
		frame.setContentPane(tabbedPane);
		
	}
	
	public Color[][] loadImage(String filepath) {
		BufferedImage buffImg= loadBufferedImage(filepath);
		Color[][] colorImg = convertTo2DFromBuffered(buffImg);
		return colorImg;
	}
	
	public void addImage(Color[][] img, String tabName) {
		BufferedImage buffImg = convertToBufferedFrom2D(img);
		ImageIcon icon = new ImageIcon(buffImg);
		icon.getImage().flush();
		
		ImageIcon tabIcon = new ImageIcon(
			buffImg.getScaledInstance(32, 32, Image.SCALE_SMOOTH));
		JLabel label = new JLabel();
		label.setIcon(icon);
		
		tabbedPane.addTab(tabName, tabIcon, label);
		
	}
	
	public void disposeOld() {
		tabbedPane.removeAll();
	}
	
	public void display() {
		frame.pack();
		frame.setMinimumSize(frame.getPreferredSize());
		
		frame.setVisible(true);
	}
	
	public static Color[][] cloneArray(Color[][] orig) {
		
		Color[][] copy = new Color[orig.length][orig[0].length];
		
		for (int i = 0; i<orig.length; i++) {
			for (int j = 0; j<orig[i].length;j++) {
				copy[i][j] = orig[i][j];
				
			}
		}
	
	
				
		return copy;
	}
	private static BufferedImage loadBufferedImage(String filepath) {
		BufferedImage img = null;
		
		try {
			img= ImageIO.read(new File(filepath));
		} catch (IOException e)	{
		  System.out.println("Could not load the image. Try again! Please "
		  		+ "ensure the filepath" + " was properly specified.");
		  e.printStackTrace();
		  System.exit(1);
		}  
		  
		return img;	
		
	}
	private static BufferedImage convertToBufferedFrom2D(Color[][] img) {
		
		int width = img.length;
		int height = img[0].length;
		BufferedImage buffImg = new BufferedImage(width, height, 1);
		
		for (int x = 0; x< width; x++) {
			for(int y = 0; y < height; y++) {
				buffImg.setRGB(x, y, img[x][y].getRGB());
				
			}
		}
		return buffImg;
	}
	private static Color[][] convertTo2DFromBuffered(BufferedImage img) {
		int width = img.getWidth();
		int height = img.getHeight();
		Color[][] result = new Color [width][height];
		
		for (int row =0; row < width; row++) {
			for (int col = 0; col < height; col++) {
				int intRGB = img.getRGB(row, col);
				int red = (intRGB >> 16)&255;
				int green = (intRGB >> 8)&255;
				int blue = intRGB&255;
				result[row][col] = new Color(red, green, blue);
				
			}
		}
		return result;
	}
}
