package lv.lu.cg.utils;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JDialog;
import javax.swing.JFrame;

import lv.lu.cg.components.DialogPanel;

public class DialogUtils {
	
	/**
	 * Create custom modal dialog
	 * @param x location coord x
	 * @param y location coord y
	 * @param width size
	 * @param height size
	 * @param panel custom panel of dialog
	 * @param title dialog title
	 */
	public static void createModalDialog(Point location, int width, int height, DialogPanel panel, String title) {
		JDialog dialog = new JDialog();
		panel.setParent(dialog);
		dialog.setPreferredSize(new Dimension(width, height));
		dialog.getContentPane().add((Component) panel);
		dialog.setTitle(title);
		dialog.setModal(true);
		dialog.setResizable(false);
		dialog.setLocation(location);
		dialog.pack();
		dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		dialog.setVisible(true);
	}

}
