package lv.lu.mpt.pd2.main;

import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {

	public static final String PROJECT_NAME = "LielaisFutbols_PD2";
	public static final int FRAME_WIDTH = 800;
	public static final int FRAME_HEIGHT = 750;
	public static final Cursor WAIT_CURSOR = new Cursor(Cursor.WAIT_CURSOR);
	public static final Cursor DEFAULT_CURSOR = new Cursor(Cursor.DEFAULT_CURSOR);

	private JPanel panel;

	private static MainFrame instance;

	public static MainFrame getInstance() {
		if (instance == null) {
			instance = new MainFrame();
		}
		return instance;
	}

	public void create() {
		initGUI();
	}

	/**
	 * Initializing GUI for MainFrame component loading and setting properties
	 */
	private void initGUI() {
		setFrameSpecificProperties();
		panel = new SplitPanePanel();
		this.getContentPane().add(panel);
		this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	/**
	 * Setting frame specific properties, such as title, colors, icons, sizes etc.
	 */
	private void setFrameSpecificProperties() {
		this.setTitle(PROJECT_NAME);
		this.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
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

}
