package lv.lu.cg.main;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

import lv.lu.cg.actions.ActionModel;
import lv.lu.cg.components.MainPanel;
import lv.lu.cg.menu.MenuBar;

/**
 * Singleton. Main application frame, containing all controls
 * @author vitalik
 * 
 */
@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	
	public static final String PROJECT_NAME = "Filters'n'Edges";
	public static final int FRAME_WIDTH = 600;
	public static final int FRAME_HEIGHT = 750;
	public static final Cursor WAIT_CURSOR = new Cursor(Cursor.WAIT_CURSOR);
	public static final Cursor DEFAULT_CURSOR = new Cursor(Cursor.DEFAULT_CURSOR);
	
	private final ActionModel actionModel = ActionModel.getInstance();

	// .................................
	// .........Component data..........
	// .................................
	private JPanel panel;
	private JMenuBar menu;

	// .................................
	// .........Singleton data..........
	// .................................
	private static MainFrame instance;
	/**
	 * Getting singleton class
	 * @return
	 */
	public static MainFrame getInstance() {
		if (instance == null) {
			instance = new MainFrame();
		}
		return instance;
	}
	
	/**
	 * Creating MainFrame component
	 */
	public void create() {
		initGUI();
	}
	
	/**
	 * Initializing GUI for MainFrame component
	 * loading and setting properties 
	 */
	private void initGUI() {
		setFrameSpecificProperties();
		panel = new MainPanel();
		this.setJMenuBar(createMenuBar());
		this.getContentPane().add(panel);
		this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		actionModel.addAction("Application created with dimensions " + FRAME_WIDTH + "x" + FRAME_HEIGHT + "px");
	}
	
	/**
	 * Setting frame specific properties, such as title, colors, icons, sizes etc.
	 */
	private void setFrameSpecificProperties() {
		this.setTitle(PROJECT_NAME);
		this.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
	}

	/**
	 * Creating MenuBar control
	 */
	private JMenuBar createMenuBar() {
		if (menu == null) {
			menu = new MenuBar();
		}
		return menu;
	}
	
	/**
	 * Set cursor to wait state
	 */
	public void setWaitCursor() {
		this.setCursor(WAIT_CURSOR);
	}
	
	/**
	 * restores cursor to default state
	 */
	public void restoreCursor() {
		this.setCursor(DEFAULT_CURSOR);
	}
	
	/**
	 * Get preferred modal dialog location
	 * @return
	 */
	public Point getModalDialogLocation() {
		return new Point(this.getLocation().x + 20, this.getLocation().y + 70);
	}
	
}

