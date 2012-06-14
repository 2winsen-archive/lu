package lv.lu.dt2.client;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * 
 * @author vitalijs.sakels
 *
 */
public class ArtChatClient {

	public static void main(String[] args) throws Exception {
		setLAF("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				(new ArtChatClientLogin()).initUI();
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