package lv.lu.mpt.pd2.main;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JSplitPane;

@SuppressWarnings("serial")
public class SplitPanePanel extends JPanel {

	private JSplitPane splitPane;
	private QueryPanel queryPanel;
	private DataScrollPane dataScrollPane;

	public SplitPanePanel() {
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
			splitPane.setDividerLocation(MainFrame.FRAME_HEIGHT / 4);
			splitPane.setEnabled(false);

			dataScrollPane = new DataScrollPane();
	    	queryPanel = new QueryPanel(dataScrollPane);
			
			splitPane.setTopComponent(queryPanel);
			splitPane.setBottomComponent(dataScrollPane);
		}
		return splitPane;
	}
}
