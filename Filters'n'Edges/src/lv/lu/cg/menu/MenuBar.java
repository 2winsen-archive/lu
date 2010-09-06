package lv.lu.cg.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSeparator;
import javax.swing.filechooser.FileNameExtensionFilter;

import lv.lu.cg.actions.ActionModel;
import lv.lu.cg.components.AboutPanel;
import lv.lu.cg.components.DialogPanel;
import lv.lu.cg.components.PreferencesPanel;
import lv.lu.cg.data.DataModel;
import lv.lu.cg.main.MainFrame;
import lv.lu.cg.utils.DialogUtils;

/**
 * 
 * @author vitalik
 *
 */
@SuppressWarnings("serial")
public class MenuBar extends JMenuBar implements ActionListener {
	
	// MenuItems types
	private final int ITEM_CHECK = 1;
	private final int ITEM_RADIO = 2;
	
	// File menu actions
	public final static String OPEN_ACTION = "Open";
	public final static String PREFERENCES_ACTION = "Preferences...";
	public final static String EXIT_ACTION = "Exit";
	public final static String CONTENTS_ACTION = "Contents";
	public final static String ABOUT_ACTION = "About";
	// Filter menu actions
	public final static String BLUR_ACTION = "Blur";
	public final static String SHARPEN_ACTION = "Sharpen";
	public final static String EMBOSS_ACTION = "Emboss";
	public final static String SOBEL_ACTION = "Sobel operator";
	public final static String LAPLACE_ACTION = "Laplace operator";
	
	// Singletons
	private final MainFrame mainFrame = MainFrame.getInstance();
	private final DataModel dataModel = DataModel.getInstance(); 
	private final ActionModel actionModel = ActionModel.getInstance();
	private static Map<String, MenuBarListener> listeners = new HashMap<String, MenuBarListener>();

	public MenuBar() {
		initGUI();
	}
	
	/**
	 * Initializing MenuBar component 
	 */
	private void initGUI() {
		createMenus();
	}
	
	/**
	 * Creating MenuBar control
	 */
	private void createMenus() {
		this.add(createFileMenu());
		this.add(createEditMenu());
		this.add(createFilterMenu());
		this.add(createHelpMenu());
	}
	
	/**
	 * Creating File child of MenuBar control
	 * @return JMenu
	 */
	private JMenu createFileMenu() {
		JMenu fileMenu = new JMenu("File");

		JMenuItem openItem = createMenuItem(0, OPEN_ACTION, null, 0, null);
		fileMenu.add(openItem);
		fileMenu.add(new JSeparator());
		JMenuItem exitItem = createMenuItem(0, EXIT_ACTION, null, 0, null);
		fileMenu.add(exitItem);
		return fileMenu;
	}
	
	/**
	 * Creating Edit child of MenuBar control
	 * @return
	 */
	private JMenu createEditMenu() {
		JMenu editMenu = new JMenu("Edit");
		JMenuItem prefsItem = createMenuItem(0, PREFERENCES_ACTION, null, 0, null);
		editMenu.add(prefsItem);
		
		return editMenu;
	}
	
	/**
	 * Creating Filter child of MenuBar control
	 * @return
	 */
	private JMenu createFilterMenu() {
		JMenu filterMenu = new JMenu("Filter");
		
		filterMenu.add(new JLabel("<html><b>Image filters</b></html>"));
		JMenuItem blurItem = createMenuItem(0, BLUR_ACTION, null, 0, null);
		filterMenu.add(blurItem);
		JMenuItem sharpenItem = createMenuItem(0, SHARPEN_ACTION, null, 0, null);
		filterMenu.add(sharpenItem);
		JMenuItem embossItem = createMenuItem(0, EMBOSS_ACTION, null, 0, null);
		filterMenu.add(embossItem);
		filterMenu.add(new JLabel("<html><b>Edge detection filters</b></html>"));
		JMenuItem sobelItem = createMenuItem(0, SOBEL_ACTION, null, 0, null);
		filterMenu.add(sobelItem);
		JMenuItem laplaceItem = createMenuItem(0, LAPLACE_ACTION, null, 0, null);
		filterMenu.add(laplaceItem);
		
		return filterMenu;
	}
	
	/**
	 * Creating Help child of MenuBar control
	 * @return
	 */
	private JMenu createHelpMenu() {
		JMenu helpMenu = new JMenu("Help");
		
		JMenuItem contentsItem = createMenuItem(0, CONTENTS_ACTION, null, 0, null);
		helpMenu.add(contentsItem);
		
		JMenuItem aboutItem = createMenuItem(0, ABOUT_ACTION, null, 0, null);
		helpMenu.add(aboutItem);
		
		return helpMenu;
	}
	
	/**
	 * Creating MenuItems
	 * @param type - type of item radiobutton, checkbox, or plain button
	 * @param text - caption
	 * @param image
	 * @param acceleratorKey - hotkey
	 * @param toolTip
	 * @return
	 */
	private JMenuItem createMenuItem(int type, String text, 
			ImageIcon image, int acceleratorKey,
			String toolTip) {
		JMenuItem menuItem;

		switch (type) {
		case ITEM_RADIO:
			menuItem = new JRadioButtonMenuItem();
			break;
		case ITEM_CHECK:
			menuItem = new JCheckBoxMenuItem();
			break;
		default:
			menuItem = new JMenuItem();
			break;
		}

		menuItem.setText(text);

		if (image != null)
			menuItem.setIcon(image);
		if (acceleratorKey > 0)
			menuItem.setMnemonic(acceleratorKey);
		if (toolTip != null)
			menuItem.setToolTipText(toolTip);
		
		menuItem.addActionListener(this);
		menuItem.setActionCommand(text);
		return menuItem;
	}
	
	/**
	 * Main method of processing MenuItems' actions
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		if(action.equalsIgnoreCase(OPEN_ACTION)) {
			openFile(OPEN_ACTION);
		} else if(action.equalsIgnoreCase(EXIT_ACTION)) {
			mainFrame.dispose();
		} else if(action.equalsIgnoreCase(PREFERENCES_ACTION)) {
			
			File file = new File("classpeth:test.txt");
			try {
				file.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			final int WIDTH = 150;
			final int HEIGHT = 185;
			DialogPanel prefPanel = new PreferencesPanel();
			actionModel.addAction("Preferences dialog created with dimensions " + WIDTH + "x" + HEIGHT + "px");
			DialogUtils.createModalDialog(mainFrame.getModalDialogLocation(), WIDTH, HEIGHT, prefPanel, PREFERENCES_ACTION);
		} else if(action.equalsIgnoreCase(CONTENTS_ACTION)) {
			// OPEN BROWSER
		} else if(action.equalsIgnoreCase(ABOUT_ACTION)) {
			final int WIDTH = 410;
			final int HEIGHT = 225;
			DialogPanel aboutPanel = new AboutPanel();
			actionModel.addAction("About dialog created with dimensions " + WIDTH + "x" + HEIGHT + "px");
			DialogUtils.createModalDialog(mainFrame.getModalDialogLocation(), WIDTH, HEIGHT, aboutPanel, ABOUT_ACTION);
		} else if(action.equalsIgnoreCase(BLUR_ACTION)) {
			dispatchFilterEvent(new FilterEvent(BLUR_ACTION, dataModel.getKernelType()));
		} else if(action.equalsIgnoreCase(SHARPEN_ACTION)) {
			dispatchFilterEvent(new FilterEvent(SHARPEN_ACTION, dataModel.getKernelType()));
		} else if(action.equalsIgnoreCase(EMBOSS_ACTION)) {
			dispatchFilterEvent(new FilterEvent(EMBOSS_ACTION, dataModel.getKernelType()));
		} else if(action.equalsIgnoreCase(SOBEL_ACTION)) {
			dispatchFilterEvent(new FilterEvent(SOBEL_ACTION));
		} else if(action.equalsIgnoreCase(LAPLACE_ACTION)) {
			dispatchFilterEvent(new FilterEvent(LAPLACE_ACTION));
		}
	}
	
	/**
	 * Processes action after file open click,
	 * store a file into this model
	 */
	private void openFile(String action) {
		actionModel.addAction("Opened file chooser");
		final JFileChooser fc = new JFileChooser();
		
		// supporting image extensions 
		String[] extensions = {"jpeg", "jpg" ,"png", "bmp"};
		fc.setFileFilter(new FileNameExtensionFilter("Image files", extensions));
		
		int returnVal = fc.showOpenDialog(this);			
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			BufferedImage bufferedImage = null;
			File file = null;
			try {
				file = fc.getSelectedFile();
				actionModel.addAction("Opened <" + file.getName() + ">");
				bufferedImage = ImageIO.read(file);
				// Probably non-image file 
				if(bufferedImage == null) {
					actionModel.addAction("Can't open non-image file <" + file.getName() + ">");
					return;	
				}
				dataModel.setImage(bufferedImage);
				dispatchSelectEvent(action);
			} catch (Exception e) {
				actionModel.addAction("Can't open non-image file <" + file.getName() + ">");
				e.printStackTrace();
			}
		} else {
			actionModel.addAction("Cancelled to open a file in file chooser");
		}
	}
	
	/**
	 * Adding Menu Bar listener that listens on changes in this class in menu bar control
	 * @param key - listener identifier
	 * @param listener
	 */
	public static void addMenuBarListener(String key, MenuBarListener listener) {
		listeners.put(key, listener);
	}
	
	/**
	 * Removing particular listener from listeners Map
	 * @param key
	 */
	public static void removeMenuBarListener(String key) {
		listeners.remove(key);
	}

	/**
	 * Dispatching all listeners after file open selected
	 */
	private void dispatchSelectEvent(String action) {
		for (MenuBarListener listener : listeners.values()) {
			listener.actionSelected(action);
		}
	}
	
	/**
	 * Dispatching all listeners after filter selected
	 */
	private void dispatchFilterEvent(FilterEvent event) {
		for (MenuBarListener listener : listeners.values()) {
			listener.filterSelected(event);
		}
	}

}
