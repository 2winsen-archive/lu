package lv.lu.cg.data;

import java.awt.Image;

import lv.lu.cg.menu.FilterEvent;
import lv.lu.cg.menu.FilterEvent.KernelType;

/**
 * Singleton. Contains data that are processed in app. Image data etc.
 * @author vitalik
 *
 */
public class DataModel {
	
	/**
	 * Pure loaded image
	 */
	private Image image;
	
	private KernelType kernelType = FilterEvent.DEFAULT_TYPE;
	
	// .................................
	// .........Singleton data..........
	// .................................	
	private static DataModel model;	
	
	private DataModel() {
	}
	
	public static DataModel getInstance() {
		if(model == null) {
			model = new DataModel();
		}
		return model;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public KernelType getKernelType() {
		return kernelType;
	}

	public void setKernelType(KernelType kernelType) {
		this.kernelType = kernelType;
	}
	
}
