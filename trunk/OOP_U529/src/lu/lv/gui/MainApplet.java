package lu.lv.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableModel;

import lu.lv.controller.FrontController;

/**
 * U529. Sastādīt Java apletu, kas uzzīmē krāsainu tortes diagrammu, kas attēlo relatīvo atzīmju ( N, 4, 5, 6, 7, 8, 9,
 * 10 ) skaitu kādā priekšmetā. Priekšmeta nosaukumu un atzīmju sakitus ievada lietotājs
 * 
 * @author vitalijs sakels (vs07035)
 * 
 */
@SuppressWarnings("serial")
public class MainApplet extends JApplet implements ActionListener {

	private static final int PADDING_TOP = 15;
	private static final int PADDING_LEFT = 10;

	private static final String[] TABLE_COLUMN_NAMES = { " 4 ", " 5 ", " 6 ", " 7 ", " 8 ", " 9 ", " 10 " };

	private static final String[][] INITIAL_TABLE_DATA = { { "", "", "", "", "", "", "" } };

	private static final String RESET_BUTTON_CLICK = "resetTableButtonClick";
	private static final String DRAW_BUTTON_CLICK = "drawTableButtonClick";

	private static final int CHART_PANEL_INDEX = 1;

	private final JTabbedPane tabbedPane = new JTabbedPane();
	private final JPanel userDataPanel = new JPanel();
	private final JTextField courseInput = new JTextField();
	private final JTable marksTable = new JTable(INITIAL_TABLE_DATA, TABLE_COLUMN_NAMES);
	private final JPanel chartPanel = new JPanel();
	private final JLabel chartTitle = new JLabel();
	private final ChartCanvas chartCanvas = new ChartCanvas();
	private JLabel validationError = new JLabel();

	private final FrontController controller = new FrontController();

	@Override
	public void init() {
		super.init();

		initTabbedPane();
	}

	private void initTabbedPane() {
		initUserDataPanel();
		initChartPanel();
		this.tabbedPane.addTab("User Data", userDataPanel);
		this.tabbedPane.addTab("Chart Panel", chartPanel);
		this.add(tabbedPane);
	}

	private void initUserDataPanel() {
		userDataPanel.setLayout(null);

		JLabel courseLabel = new JLabel("Course Name:");
		courseLabel.setBounds(PADDING_LEFT, PADDING_TOP, 100, 15);
		userDataPanel.add(courseLabel);

		courseInput.setBounds(PADDING_LEFT, PADDING_TOP + 15, 250, 20);
		userDataPanel.add(courseInput);

		JLabel marksTableLabel = new JLabel("Students' Marks:");
		marksTableLabel.setBounds(PADDING_LEFT, PADDING_TOP + 60, 100, 15);
		userDataPanel.add(marksTableLabel);

		marksTable.setRowSelectionAllowed(false);
		JScrollPane scrollPane = new JScrollPane(marksTable);
		scrollPane.setBounds(PADDING_LEFT, PADDING_TOP + 80, 600, 35);
		userDataPanel.add(scrollPane);

		final JButton resetTableButton = new JButton();
		resetTableButton.setText("Reset Table");
		resetTableButton.addActionListener(this);
		resetTableButton.setActionCommand(RESET_BUTTON_CLICK);
		resetTableButton.setBounds(PADDING_LEFT, PADDING_TOP + 130, 120, 20);
		userDataPanel.add(resetTableButton);

		final JButton okButton = new JButton();
		okButton.setText("Draw Chart");
		okButton.addActionListener(this);
		okButton.setActionCommand(DRAW_BUTTON_CLICK);
		okButton.setBounds(PADDING_LEFT + 480, PADDING_TOP + 130, 120, 20);
		userDataPanel.add(okButton);

		validationError.setForeground(Color.RED);
		validationError.setBounds(PADDING_LEFT + 350, PADDING_TOP + 133, 120, 15);
		userDataPanel.add(validationError);
	}
	
	private void initChartPanel() {
		chartPanel.setLayout(null);
		
		chartTitle.setBounds(PADDING_LEFT, PADDING_TOP, 600, 25);
		chartPanel.add(chartTitle);
		
		chartCanvas.setBounds(0, 45, 400, 400);
		chartPanel.add(chartCanvas);
	}

	private boolean validateData() {
		boolean result = false;
		if (courseInput.getText().trim().length() > 0) {
			result = true;
			TableModel model = marksTable.getModel();
			for (int i = 0; i < model.getColumnCount(); i++) {
				String mark = model.getValueAt(0, i).toString().trim();
				if (!mark.equals("")) {
					try {
						Integer.parseInt(mark);
					} catch (Exception e) {
						return result = false;
					}
				}
			}
		}
		return result;
	}

	private void resetMarksTable() {
		TableModel model = marksTable.getModel();
		for (int i = 0; i < model.getColumnCount(); i++) {
			model.setValueAt("", 0, i);
		}
	}

	private Map<String, Object> parseInputData() {
		Map<String, Object> data = new HashMap<String, Object>();
		TableModel model = marksTable.getModel();
		for (int i = 0; i < model.getColumnCount(); i++) {
			// Ignoring empty table data items
			if (!model.getValueAt(0, i).toString().equals("")) {
				data.put(model.getColumnName(i), Integer.parseInt(model.getValueAt(0, i).toString().trim()));
			}
		}
		return data;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals(DRAW_BUTTON_CLICK)) {
			if (validateData()) {
				validationError.setText("");
				controller.processData(parseInputData());
				tabbedPane.setSelectedIndex(CHART_PANEL_INDEX);
				chartTitle.setText("<html>Chart: students' marks in <i><font size='5' color='#A2A2A2'>" + courseInput.getText() + "</font></i></html>");
				chartTitle.validate();
				controller.draw(chartCanvas);				
			} else {
				validationError.setText("Incorrect Input Data!");
			}
		} else if (e.getActionCommand().equals(RESET_BUTTON_CLICK)) {
			resetMarksTable();
		}
	}

}
