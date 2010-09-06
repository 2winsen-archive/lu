package lv.lu.cg.components;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import lv.lu.cg.actions.ActionModel;
import lv.lu.cg.actions.ActionModelEvent;
import lv.lu.cg.actions.ActionModelListener;

/**
 * Actions Panel. Displaying actions made by user
 * @author vitalik
 *
 */
@SuppressWarnings("serial")
public class ActionsPanel extends JPanel implements ActionModelListener {

	private JPanel clearButtonPanel;
	private JButton clearButton;
	private JScrollPane scrollPane;
	private JList actionsList;

	/**
	 * Creating ActionsPanel component
	 */
	public ActionsPanel() {
		ActionModel.addActionModelListener(this.getClass().toString(), this);
		initGUI();
	}

	/**
	 * initializing UI
	 */
	private void initGUI() {
		setLayout(new BorderLayout());
		add(createScrollPane(), BorderLayout.CENTER);
		add(createClearButtonPanel(), BorderLayout.SOUTH);
	}

	/**
	 * Creating ScrollPane to show scrollbars on ActionsList control
	 * @return
	 */
	private JScrollPane createScrollPane() {
		if (scrollPane == null) {			
			scrollPane = new JScrollPane(createActionsList());
		}
		return scrollPane;
	}
	
	/**
	 * Creating ClearButtonPanel
	 * Displayed at the bottom of ActionsPanel 
	 * @return
	 */
	private JPanel createClearButtonPanel() {
		if (clearButton == null) {			
			clearButtonPanel = new JPanel();
			clearButtonPanel.setLayout(new BorderLayout());
			clearButtonPanel.add(createClearButton(), BorderLayout.EAST);
		}
		return clearButtonPanel;
	}
	
	/**
	 * Creating ClearButton, to clear all ActionsList entries
	 * @return
	 */
	private JButton createClearButton() {
		if(clearButton == null) {
			clearButton = new JButton("clear");
			clearButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					ActionModel.getInstance().clearActions();
				}
			});
		}
		return clearButton;
	}
	
	/**
	 * Creating ActionsList to display all entries in ActionsModel (user actions)
	 * @return
	 */
	private JList createActionsList() {
		if(actionsList == null) {
			DefaultListModel actionListModel = new DefaultListModel();
			actionsList = new JList(actionListModel);
		}
		return actionsList;
	}

	/**
	 * Listener method.
	 * Method occurs every time data is added to ActionModel
	 */
	@Override
	public void dataUpdated(ActionModelEvent event) {
		DefaultListModel actionListModel = (DefaultListModel) actionsList.getModel();
		actionListModel.addElement(event.getAction());
	}

	/**
	 * Listener method.
	 * Method occurs every time data is removed from ActionModel
	 */
	@Override
	public void dataCleared() {
		DefaultListModel actionListModel = (DefaultListModel) actionsList.getModel();
		actionListModel.removeAllElements();
	}
	
}
