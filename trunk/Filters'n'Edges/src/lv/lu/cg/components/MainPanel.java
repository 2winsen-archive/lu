package lv.lu.cg.components;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import lv.lu.cg.main.MainFrame;

/**
 * 
 * @author vitalik
 * 
 */
@SuppressWarnings("serial")
public class MainPanel extends JPanel {

	private JSplitPane splitPane;
	private CanvasPanel canvasPanel;
	private ActionsPanel actionsPanel;
	
	public MainPanel() {
		initGUI();
	}

	private void initGUI() {
		this.setLayout(new BorderLayout());
		splitPane = createSplitPane();
		this.add(splitPane);
	}

	private JSplitPane createSplitPane() {
		if (splitPane == null) {
			splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
			splitPane.setOneTouchExpandable(true);
			splitPane.setDividerLocation(MainFrame.FRAME_HEIGHT/2);
			
			canvasPanel = new CanvasPanel();
			JScrollPane canvasScrollPane = new JScrollPane(canvasPanel, 
					JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			splitPane.setTopComponent(canvasScrollPane);
			
			actionsPanel = new ActionsPanel();
			splitPane.setBottomComponent(actionsPanel);
		}
		return splitPane;
	}

}

