package lv.lu.cg.components;

import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import lv.lu.cg.actions.ActionModel;
import lv.lu.cg.data.DataModel;
import lv.lu.cg.main.MainFrame;
import lv.lu.cg.menu.FilterEvent;
import lv.lu.cg.menu.MenuBar;
import lv.lu.cg.menu.MenuBarListener;
import lv.lu.cg.utils.ImageUtils;

/**
 * Panel where image label is located, here starts all filtering logic
 * @author vitalik
 *
 */
@SuppressWarnings("serial")
public class CanvasPanel extends JPanel implements MenuBarListener {
	
	private final MainFrame mainFrame = MainFrame.getInstance();
	private final DataModel dataModel = DataModel.getInstance();
	private final ActionModel actionModel = ActionModel.getInstance();
	private JLabel imageLabel;
	
	public CanvasPanel() {
		initGUI();
		MenuBar.addMenuBarListener(this.getClass().toString(), this);
	}
	
	private void initGUI() {
		this.add(createImage());
	}
	
	private JLabel createImage() {
		if(imageLabel == null) {
			imageLabel = new JLabel();
		}
		return imageLabel;
	}
	
	private void updateImage(Image image) {
		imageLabel.setIcon(new ImageIcon(image));
	}

	@Override
	public void actionSelected(String name) {
		if(name.equals(MenuBar.OPEN_ACTION)) {
			updateImage(dataModel.getImage());
			this.revalidate();
		}
	}

	@Override
	public void filterSelected(FilterEvent event) {
		// Do nothing if no image is selected
		if((BufferedImage)dataModel.getImage() == null) 
			return;
		mainFrame.setWaitCursor();
		Image filteredImage = null;
		if(event.getName().equals(MenuBar.BLUR_ACTION)) {
			
			filteredImage = ImageUtils.blur((BufferedImage)dataModel.getImage(), 
					event.getDistance());
			actionModel.addAction("Applied blur filter, kernel size: " + 
					(event.getDistance()*2+1) + "x" + (event.getDistance()*2+1));
			
		} else if(event.getName().equals(MenuBar.SHARPEN_ACTION)) {
			
			filteredImage = ImageUtils.sharpen((BufferedImage)dataModel.getImage(), 
					event.getType(), event.getDistance());
			actionModel.addAction("Applied sharpen filter, kernel size: " + 
					(event.getDistance()*2+1) + "x" + (event.getDistance()*2+1));
			
		} else if(event.getName().equals(MenuBar.EMBOSS_ACTION)) {
			
			filteredImage = ImageUtils.emboss((BufferedImage)dataModel.getImage(), 
					event.getType(), event.getDistance());
			actionModel.addAction("Applied emboss filter, kernel size: " + 
					(event.getDistance()*2+1) + "x" + (event.getDistance()*2+1));
			
		} else if(event.getName().equals(MenuBar.SOBEL_ACTION)) {
			
			filteredImage = ImageUtils.sobel((BufferedImage)dataModel.getImage(), 
					event.getType(), event.getDistance());
			actionModel.addAction("Applied Sobel operator");
			
		} else if(event.getName().equals(MenuBar.LAPLACE_ACTION)) {
			
			filteredImage = ImageUtils.laplace((BufferedImage)dataModel.getImage());
			actionModel.addAction("Applied Laplace operator");
		}
		updateImage(filteredImage);
		dataModel.setImage(filteredImage);
		mainFrame.restoreCursor();
	}

}
