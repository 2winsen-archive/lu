package lv.lu.mpt.pd2.main;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Main {

	public static void main(String[] args) throws Exception {
		setLAF("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		final MainFrame mainFrame = MainFrame.getInstance();
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				mainFrame.create();
			}
		});
	}

	private static void setLAF(String path) {
		try {
			UIManager.setLookAndFeel(path);
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

}
