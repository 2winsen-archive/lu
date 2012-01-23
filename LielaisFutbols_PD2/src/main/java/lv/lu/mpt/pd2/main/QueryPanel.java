package lv.lu.mpt.pd2.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import lv.lu.mpt.pd2.constants.ApplicationConstants;
import lv.lu.mpt.pd2.interfaces.service.StatisticsService;
import lv.lu.mpt.pd2.interfaces.service.TeamService;
import lv.lu.mpt.pd2.interfaces.service.UploadService;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SuppressWarnings("serial")
public class QueryPanel extends JPanel implements ActionListener {
	
	private static final String UPLAOD_ACTION = "uplaodAction";
	private static final int GAP = 10;
	private static final int x0 = 10;
	private static final int y0 = 10;
	
	private static final String LEAGUGE_TABLE = "Leaguge table"; 
	private static final String TOP_10_SCORERS = "Top 10 Scorers"; 
	private static final String TOP_5_GOALKEEPERS = "Top 5 Goalkeepers"; 
	private static final String TOP_AGGRESSIVE_PLAYERS = "Top aggressive players"; 
	private static final String TOP_STRICT_REFEREES = "Top strict referees"; 
	private static final int COMBOBOX_NEW_STATE = 1; 
	
	private JButton uploadButton;
	private JTextField uploadField;
	private JFileChooser fileChooser;
	private JSeparator separator;
	private JComboBox overallStatistics;
	private JComboBox teamStatistics;
	private DataScrollPane dataScrollPane;
	
	private UploadService uploadService;
	
	private TeamService teamService;
	
	private StatisticsService statisticsService;

	public UploadService getUploadService() {
		return uploadService;
	}

	public void setUploadService(UploadService uploadService) {
		this.uploadService = uploadService;
	}

	public TeamService getTeamService() {
		return teamService;
	}

	public void setTeamService(TeamService teamService) {
		this.teamService = teamService;
	}

	public StatisticsService getStatisticsService() {
		return statisticsService;
	}

	public void setStatisticsService(StatisticsService statisticsService) {
		this.statisticsService = statisticsService;
	}

	public QueryPanel(DataScrollPane dataScrollPane) {
		this.dataScrollPane = dataScrollPane;
    	ApplicationContext appCtxt = new ClassPathXmlApplicationContext(ApplicationConstants.SPRING_CONFIG_FILE);
    	uploadService = (UploadService)appCtxt.getBean(ApplicationConstants.UPLOAD_SERVICE);
    	teamService = (TeamService)appCtxt.getBean(ApplicationConstants.TEAM_SERVICE);
    	statisticsService = (StatisticsService)appCtxt.getBean(ApplicationConstants.STATISTICS_SERVICE);
		initGUI();
	}

	private void initGUI() {
		this.setLayout(null);
		uploadButton = new JButton("Upload directory...");
		uploadButton.setActionCommand(UPLAOD_ACTION);
		uploadButton.addActionListener(this);
		uploadField = new JTextField();
		uploadField.setEditable(false);
		uploadField.setActionCommand(UPLAOD_ACTION);
		uploadField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				actionPerformed(new ActionEvent(uploadButton, -1, UPLAOD_ACTION));
			}
		});
		uploadField.setSize(200, uploadField.getHeight());
		
		uploadButton.setBounds(x0, y0, 100, 30);
		this.add(uploadButton);
		uploadField.setBounds(x0 + 100 + GAP, y0, 300, 30);
		this.add(uploadField);
		
		separator = new JSeparator(JSeparator.HORIZONTAL);
		separator.setBounds(x0, y0 + 40, 430, 2);
		this.add(separator);
		
		JLabel overallStatisticsLabel = new JLabel("Overall Statistics:");
		overallStatisticsLabel.setBounds(x0, y0 + 55, 100, 30);
		overallStatistics = new JComboBox(new Object[] { LEAGUGE_TABLE, TOP_10_SCORERS, TOP_5_GOALKEEPERS, TOP_AGGRESSIVE_PLAYERS, TOP_STRICT_REFEREES });
		overallStatistics.setBounds(x0 + 100, y0 + 55, 200, 30);
		overallStatistics.setSelectedItem(null);
		overallStatistics.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == COMBOBOX_NEW_STATE) {
					if (e.getItem().equals(LEAGUGE_TABLE)) {
						
						Object[] columnNames = {"Team Name", "Total Points", "Total Wins", "Total Losses", "Total Wins (Extra time)", 
								"Total Losses (Extra time)", "Total Goals Scored", "Total Goals Lost"};
						Object[][] data = statisticsService.getLeagueTable();
						dataScrollPane.setModel(new DataTableModel(data, columnNames));
						
					} else if (e.getItem().equals(TOP_10_SCORERS)) {
						
						Object[] columnNames = {"First Name", "Last Name", "Team", "Total Goals", "Total Assists"};
						Object[][] data = statisticsService.getTop10Scorers();
						dataScrollPane.setModel(new DataTableModel(data, columnNames));
						
					} else if (e.getItem().equals(TOP_5_GOALKEEPERS)) {
						
						Object[] columnNames = {"First Name", "Last Name", "Team", "Average Lost Goals (Per Game)"};
						Object[][] data = statisticsService.getTop5GoalKeepers();
						dataScrollPane.setModel(new DataTableModel(data, columnNames));
						
					} else if (e.getItem().equals(TOP_AGGRESSIVE_PLAYERS)) {
						
						Object[] columnNames = {"First Name", "Last Name", "Team", "Total Penalties"};
						Object[][] data = statisticsService.getTopAggressivePlayers();
						dataScrollPane.setModel(new DataTableModel(data, columnNames));
						
					} else if (e.getItem().equals(TOP_STRICT_REFEREES)) {

						Object[] columnNames = {"First Name", "Last Name", "Total Penalties Given"};
						Object[][] data = statisticsService.getTopStrictReferees();
						dataScrollPane.setModel(new DataTableModel(data, columnNames));
					}
				}
			}
		});
		this.add(overallStatisticsLabel);
		this.add(overallStatistics);
		
		JLabel teamStatisticsLabel = new JLabel("Team Statistics:");
		teamStatisticsLabel.setBounds(x0, y0 + 95, 100, 30);
		ComboBoxModel overallStatisticsModel = new DefaultComboBoxModel(teamService.getAllTeamNames().toArray());
		teamStatistics = new JComboBox(overallStatisticsModel);
		teamStatistics.setBounds(x0 + 100, y0 + 95, 200, 30);
		teamStatistics.setSelectedItem(null);
		teamStatistics.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == 1) {
					
					Object[] columnNames = {"Number", "Role", "First Name", "Last Name", "Total Games Played", "Total Games Played (Main LineUp)", 
							"Total Minutes Played", "Total Goals Scored", "Total Assists", "Total Lost Goals", "Average Lost Goals (Per Game)",
							"Total Yellow Cards", "Total Red Cards"};
					Object[][] data = statisticsService.getTeamStatistics((String)e.getItem());
					dataScrollPane.setModel(new DataTableModel(data, columnNames));
				}
			}
		});
		this.add(teamStatisticsLabel);
		this.add(teamStatistics);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals(UPLAOD_ACTION)) {
			if (fileChooser == null) {
				fileChooser = new JFileChooser(); 
				fileChooser.setCurrentDirectory(new java.io.File("."));
				fileChooser.setDialogTitle("Select directory with xml data files...");
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			}

			if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				uploadField.setText(fileChooser.getSelectedFile().toString());
				
			    File dir = fileChooser.getSelectedFile();
			    File[] children = dir.listFiles();
			    if (children != null) {
			    	try {
			    		uploadService.upload(children);
			    		((DefaultComboBoxModel)teamStatistics.getModel()).removeAllElements();
			    		for (String s : teamService.getAllTeamNames()) {
			    			((DefaultComboBoxModel)teamStatistics.getModel()).addElement(s);	
						}
			    		teamStatistics.setSelectedItem(null);
			    	} catch (Exception exception) {
			    		exception.printStackTrace();
			    	}
			    }
			}			
		}
		else {
			
		}
	}
}
