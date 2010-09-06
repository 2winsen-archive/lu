
package lv.lu.cg.utils;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ColorConvertOp;

import lv.lu.cg.menu.FilterEvent.KernelType;

/**
 * Utils class, contains methods for image data processing
 * @author vitalik
 *
 */
public class ImageUtils {
	
	/**
	 * Convert source image to grayscale
	 * @param source
	 * @return 
	 */
	public static BufferedImage convertToGrayscale(BufferedImage source) { 
	     BufferedImageOp op = new ColorConvertOp(
	       ColorSpace.getInstance(ColorSpace.CS_GRAY), null); 
	     return op.filter(source, null);
	}
	
	public static float gaussFunction(float x, float middle, float width) {
	    if (width == 0)
	        return 1F;
	    Double t = - (1.0 / width) * ((middle - x) * (middle - x));
	    return (float)Math.pow(1.5, t);
	}
	
	/**
	 * Returns Color from <code>int</code> color value
	 * @param colorValue
	 * @return 
	 */
	public static Color getColorValue(int colorValue) {
        int  red = (colorValue & 0x00ff0000) >> 16;
        int  green = (colorValue & 0x0000ff00) >> 8;
        int  blue = colorValue & 0x000000ff;
        int alpha = (colorValue >> 24) & 0xff;
        return new Color(red, green, blue, alpha);
	}
	
	/**
	 * Draws a pixel to a given image in the specified position
	 * @param image
	 * @param x
	 * @param y
	 * @param color
	 */
	public static void setPixel(BufferedImage image, int x, int y, Color color) {
		Graphics g = image.getGraphics();
		g.setColor(color);
		g.fillRect(x, y, 1, 1);
		g.dispose();
	}
	
	/**
	 * Negative values replaced with 0, grater than 255 values replaced with 255
	 * @param value
	 * @return
	 */
	public static int getCorrectedColorValue(float value) {
		return (int) Math.min(255, Math.max(0, value));
	}

	/**
	 * Applying filter to given image. For better performance using vertical 
	 * and horizontal parts of kernel, separately.
	 * @param image 
	 * @param horizontalKernel - array of horizontal kernel values
	 * @param verticalKernel - array of vertical kernel values
	 * @return filteredImage
	 */
	public static BufferedImage filter(BufferedImage image, float[] horizontalKernel, float[] verticalKernel) {
		BufferedImage tempImage = new BufferedImage(image.getWidth(), 
				image.getHeight(), BufferedImage.TYPE_INT_ARGB);
		tempImage.createGraphics();
		BufferedImage filteredImage = new BufferedImage(image.getWidth(), 
				image.getHeight(), BufferedImage.TYPE_INT_ARGB);
		filteredImage.createGraphics();
		
		// Horizontal filter
	    float weightsum;
        for (int row = 0; row < image.getHeight(); row++) {
            for (int col = 0; col < image.getWidth(); col++) {
                float r = 0, g = 0, b = 0, a = 0;
                weightsum = 0;
                for (int i = 0; i < horizontalKernel.length; i++) {
                    int x = col - (horizontalKernel.length-1)/2 + i;
                    if (x < 0) {
                        i += -x;
                        x = 0;
                    }
                    if (x > image.getWidth() - 1)
                        break;

                    int colorValue = image.getRGB(x, row);
                    Color color = getColorValue(colorValue);
                    
                    r += color.getRed() * horizontalKernel[i]/255.0 * color.getAlpha();
                    g += color.getGreen() * horizontalKernel[i]/255.0 * color.getAlpha();
                    b += color.getBlue() * horizontalKernel[i]/255.0 * color.getAlpha();
                    a += color.getBlue() * horizontalKernel[i]/255.0 * color.getAlpha();
                    weightsum += horizontalKernel[i];
                }
                r /= weightsum;
                g /= weightsum;
                b /= weightsum;
                a /= weightsum;
                int correctedRed = getCorrectedColorValue(r);
                int correctedGreen = getCorrectedColorValue(g);
                int correctedBlue = getCorrectedColorValue(b);
                int correctedAlpha = getCorrectedColorValue(a);
                setPixel(tempImage, col, row, 
                		new Color(correctedRed, correctedGreen, correctedBlue, correctedAlpha));
            }
        }
        
        // Vertical filter
        for (int col = 0; col < image.getWidth(); col++) {
            for (int row = 0; row < image.getHeight(); row++) {
                float r = 0, g = 0, b = 0, a = 0;
                weightsum = 0;
                for (int i = 0; i < verticalKernel.length; i++) {
                    int y = row - (verticalKernel.length-1)/2 + i;
                    if (y < 0) {
                        i += -y;
                        y = 0;
                    }
                    if (y > image.getHeight() - 1)
                        break;
                    int colorValue = tempImage.getRGB(col, y);
                    Color color = getColorValue(colorValue);
                    r += color.getRed() * verticalKernel[i];
                    g += color.getGreen() * verticalKernel[i];
                    b += color.getBlue() * verticalKernel[i];
                    weightsum += verticalKernel[i];
                }
                r /= weightsum;
                g /= weightsum;
                b /= weightsum;
                a /= weightsum;
                int correctedRed = getCorrectedColorValue(r);
                int correctedGreen = getCorrectedColorValue(g);
                int correctedBlue = getCorrectedColorValue(b);
                setPixel(filteredImage, col, row, 
                		new Color(correctedRed, correctedGreen, correctedBlue));
            }
        }
        return filteredImage;
    }
	
	/**
	 * Applying filter to given image.
	 * @param image
	 * @param kernel, kernel 2 dim array with weight values
	 * @param distance, distance from kernel center to any side
	 * @return filteredImage
	 */
	public static BufferedImage filter(BufferedImage image, float[][] kernel, int distance) {
		BufferedImage filteredImage = new BufferedImage(image.getWidth(), 
				image.getHeight(), BufferedImage.TYPE_INT_RGB);
		filteredImage.createGraphics();

		for(int col=0; col<image.getHeight(); col++) {
			for(int row=0; row<image.getWidth(); row++) {
				float r = 0, g = 0, b = 0;
				for(int y=-distance; y<=distance; y++) {
					for(int x=-distance; x<=distance; x++) {
						// image boundaries
						if(row+x < 0 || row+x >= image.getWidth()) 
							continue;
						if(col+y < 0 || col+y >= image.getHeight()) 
							continue;
						
						int colorValue = image.getRGB(row+x, col+y);
						Color color = getColorValue(colorValue);
						r += color.getRed() * kernel[y+distance][x+distance];
						g += color.getGreen() * kernel[y+distance][x+distance];
						b += color.getBlue() * kernel[y+distance][x+distance];
					}
					int correctedRed = getCorrectedColorValue(r);
					int correctedGreen = getCorrectedColorValue(g);
					int correctedBlue = getCorrectedColorValue(b);
					setPixel(filteredImage, row, col, 
							new Color(correctedRed, correctedGreen, correctedBlue));
				}
			}
		}
		return filteredImage;
	}
	
	/**
	 * Applying <b>Gaussian blur</b> filter to given image 
	 * @param image
	 * @param distance, distance from hot spot pixel of the kernel
	 * @return
	 */
	public static BufferedImage blur(BufferedImage image, int distance) {
		float[] horizontalOp = new float[distance*2+1];
		for(int i = 0; i<horizontalOp.length; i++) {
			horizontalOp[i] = ImageUtils.gaussFunction(-distance + i, 0, distance);
		}
		float[] verticalOp = new float[distance*2+1];
		for(int i = 0; i<verticalOp.length; i++) {
			verticalOp[i] = ImageUtils.gaussFunction(-distance + i, 0, distance);
		}
		return filter(image, horizontalOp, verticalOp);
	}
	
	/**
	 * Applying <b>sharpen</b> filter to given image.
	 * Using kernel (dependent on size): 
	 * <pre>
	 *    0, -1,  0
	 *   -1,  5, -1
	 *    0, -1,  0 
	 * <pre>
	 * 
	 * @param image
	 * @param distance
	 * @return
	 */
	public static BufferedImage sharpen(BufferedImage image, KernelType type, int distance) {
		float[][] kernel = null;
		switch (type) {
		case KERNEL_3x3:
			float[][] kernel3x3 = {
					{ 0, -1,  0},
					{-1,  5, -1},
					{ 0, -1,  0}
			};
			kernel = kernel3x3;
			break;
		case KERNEL_5x5:
			float[][] kernel5x5 = {
					{ 0,  0, -1,  0,  0},
					{ 0,  0, -1,  0,  0},
					{-1, -1,  9, -1, -1},
					{ 0,  0, -1,  0,  0},
					{ 0,  0, -1,  0,  0}
			};
			kernel = kernel5x5;
			break;
		case KERNEL_7x7:
			float[][] kernel7x7 = {
					{ 0, 0, 0,-1, 0, 0, 0},
					{ 0, 0, 0,-1, 0, 0, 0},
					{ 0, 0, 0,-1, 0, 0, 0},
					{-1,-1,-1,13,-1,-1,-1},
					{ 0, 0, 0,-1, 0, 0, 0},
					{ 0, 0, 0,-1, 0, 0, 0},
					{ 0, 0, 0,-1, 0, 0, 0}
			};
			kernel = kernel7x7;
			break;
		default:
			break;
		}
		return filter(image, kernel, distance);
	}
	
	/**
	 * Applying <b>emboss</b> filter to given image.
	 * @param image
	 * @param distance
	 * @return
	 */
	public static BufferedImage emboss(BufferedImage image, KernelType type, int distance) {
		float[][] kernel = null;
		switch (type) {
		case KERNEL_3x3:
			float[][] kernel3x3 = {
					{-2, -1, 0},
					{-1, 1, 1},
					{0, 1, 2}
			};
			kernel = kernel3x3;
			break;
		case KERNEL_5x5:
			float[][] kernel5x5 = {
					{-2, -1, -1, 0, 0},
					{-1, -1, -1, 0, 0},
					{-1, -1, 1, 1, 1},
					{0, 0, 1, 1, 1},
					{0, 0, 1, 1, 2}
			};
			kernel = kernel5x5;
			break;
		case KERNEL_7x7:
			float[][] kernel7x7 = {
					{-2, -1, -1, -1, 0, 0, 0},
					{ -1, -1, -1, -1, 0, 0, 0},
					{-1, -1, -1, -1, 0, 0, 0},
					{-1, -1, -1, 1, 1, 1, 1},
					{0, 0, 0, 1, 1, 1, 1},
					{0, 0, 0, 1, 1, 1, 1},
					{0, 0, 0, 1, 1, 1, 2}
			};
			kernel = kernel7x7;
			break;
		default:
			break;
		}
		return filter(image, kernel, distance);
	}
	
	/**
	 * Edge detection using Sobel operator, working only with grayscale images
	 * @param image
	 * @return
	 */
	public static BufferedImage sobel(BufferedImage image, KernelType type, int distance) {
		BufferedImage grayscaleImage = convertToGrayscale(image);
		BufferedImage filteredImage = new BufferedImage(image.getWidth(), 
				image.getHeight(), BufferedImage.TYPE_INT_RGB);
		filteredImage.createGraphics();

		float[][] GX = {
				{-1, 0, 1},
				{-2, 0, 2},
				{-1, 0, 1}
		};
		float[][] GY = {
				{1, 2, 1},
				{0, 0, 0},
				{-1, -2, -1}
		};

		float weightsumX = 0;
		float weightsumY = 0;
		int newColor = 0;

		for(int col=0; col<image.getHeight(); col++)  {
			for(int row=0; row<image.getWidth(); row++)  {
				float cx = 0;
				float cy = 0;
				// x gradient approximation
				for(int y=-distance; y<=distance; y++)  {
					for(int x=-distance; x<=distance; x++)  {
						if(row+x < 0 || row+x >= image.getWidth()) 
							continue;
						if(col+y < 0 || col+y >= image.getHeight()) 
							continue;
						int colorValue = grayscaleImage.getRGB(row+x, col+y);						
						Color color = getColorValue(colorValue);
						cx += color.getRed() * GX[y+distance][x+distance];
						weightsumX += GX[y+distance][x+distance];
					}
				}
				// y gradient approximation
				for(int y=-distance; y<=distance; y++)  {
					for(int x=-distance; x<=distance; x++)  {
						if(row+x < 0 || row+x >= image.getWidth()) 
							continue;
						if(col+y < 0 || col+y >= image.getHeight()) 
							continue;
						int colorValue = grayscaleImage.getRGB(row+x, col+y);
						
						Color color = getColorValue(colorValue);
						cy += color.getRed() * GY[y+distance][x+distance];
						weightsumY += GY[y+distance][x+distance];
					}
				}
					
				newColor = (int) (Math.abs(cx) + Math.abs(cy));
				if(newColor > 255) 
					newColor = 255;
				setPixel(filteredImage, row, col, 
						new Color(newColor, newColor, newColor));
			}
		}
		return filteredImage;
	}
	
	/**
	 * Edge detection using Laplace operator
	 * @param image
	 * @return
	 */
	public static BufferedImage laplace(BufferedImage image) {
		BufferedImage grayscaleImage = convertToGrayscale(image);
		float kernel[][] = {
				{-1, -1, -1, -1, -1},
				{-1, -1, -1, -1, -1},
				{-1, -1, 24, -1, -1},
				{-1, -1, -1, -1, -1},
				{-1, -1, -1, -1, -1}
		};
		return filter(grayscaleImage, kernel, 2);
	}
	
}
