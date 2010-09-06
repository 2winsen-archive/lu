package lv.lu.cg.components;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class AboutPanel extends JPanel implements DialogPanel {
	
	private static Image aboutLogo;
	
	static {
		try {
			aboutLogo = ImageIO.read(new File("aboutLogo.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static final String ABOUT_TEXT = "Filters'n'Edges application \n\n" +
			"Author: Twinsen \n" +
			"Version: 1.01 \n" +
			"Year: 2010 \n\n" +
			"Java 1.6 platform with Swing Nimbus Look And Feel \n";
	
	private Object parent;
	
	public AboutPanel() {
		initGUI();
	}
	
	private void initGUI() {
		JPanel upperPanel = new JPanel();
		JLabel imageLabel = new JLabel();
		imageLabel.setIcon(new ImageIcon(aboutLogo));
		JTextArea textArea = new JTextArea(ABOUT_TEXT);
		textArea.setEditable(false);
		upperPanel.add(imageLabel);
		upperPanel.add(textArea);
		
		JPanel lowerPanel = new JPanel();
		JButton okButton = new JButton("OK");
		lowerPanel.add(okButton);
		okButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(parent != null) {
					((JDialog)parent).dispose();
				}
			}
		});
		this.add(upperPanel);
		this.add(lowerPanel);
	}
	
	@Override
	public void setParent(Object obj) {
		this.parent = obj;
	}

}
