package lv.parcels;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import org.apache.commons.lang.StringUtils;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private static MainFrame instance;

	private static final String DEFAULT_P_NAME = "parcels_1.exe";
	private static final String DEFAULT_TESTS_DIR = "tests";
	private static final String DEFAULT_RESULTS_DIR = "results";

	private JTextField programName;
	private JTextField testsDirField;
	private JTextField resultsDirField;
	private JList list;

	private MainFrame() {
	}

	public static MainFrame getInstance() {
		if (instance == null) {
			instance = new MainFrame();
		}
		return instance;
	}

	public void init() {
		this.setTitle("ParcelsTestTool");
		this.setPreferredSize(new Dimension(350, 530));

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		programName = new JTextField();
		programName.setText(DEFAULT_P_NAME);

		testsDirField = new JTextField();
		testsDirField.setText(DEFAULT_TESTS_DIR);

		resultsDirField = new JTextField();
		resultsDirField.setText(DEFAULT_RESULTS_DIR);

		list = new JList(new DefaultListModel());
		JScrollPane listScroller = new JScrollPane(list);
		listScroller.setPreferredSize(new Dimension(450, 300));

		JPanel buttons = new JPanel();
		buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));
		final JButton start = new JButton("Start");
		start.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (valid()) {
					 Automatization.run(programName.getText(), 
							testsDirField.getText(), resultsDirField.getText(), list);
				}
			}
		});
		final JButton clear = new JButton("Clear");
		clear.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultListModel model = (DefaultListModel) list.getModel();
				model.clear();
			}
		});
		buttons.add(start);
		buttons.add(clear);

		panel.add(new JLabel("Program Name"));
		panel.add(programName);
		panel.add(new JLabel("Tests Directory"));
		panel.add(testsDirField);
		panel.add(new JLabel("Results Directory"));
		panel.add(resultsDirField);
		panel.add(new JLabel("Information"));
		panel.add(listScroller);
		panel.add(buttons);
		getContentPane().add(panel);

		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
		this.pack();
	}

	private boolean valid() {
		return !StringUtils.isBlank(programName.getText()) && !StringUtils.isBlank(testsDirField.getText())
				&& !StringUtils.isBlank(resultsDirField.getText());

	}

}
