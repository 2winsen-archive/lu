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
import lv.lu.mpt.pd2.interfaces.service.RefereeService;
import lv.lu.mpt.pd2.interfaces.service.StatisticsService;
import lv.lu.mpt.pd2.interfaces.service.TeamService;
import lv.lu.mpt.pd2.interfaces.service.UploadService;
import lv.lu.mpt.pd2.model.Referee;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SuppressWarnings("serial")
public class QueryPanel extends JPanel implements ActionListener, ItemListener {
	
	private static final String UPLAOD_ACTION = "uplaodAction";
	private static final int GAP = 10;
	private static final int x0 = 10;
	private static final int y0 = 10;
	
	private static final String LEAGUGE_TABLE = "Leaguge table"; 
	private static final String TOP_10_SCORERS = "Top 10 Scorers"; 
	private static final String TOP_5_GOALKEEPERS = "Top 5 Goalkeepers"; 
	private static final String TOP_AGGRESSIVE_PLAYERS = "Top aggressive players"; 
	private static final String TOP_STRICT_REFEREES = "Top strict referees";
	private static final String TOP_MOST_POPULAR_NAMES = "Top most popular names";
	private static final int COMBOBOX_NEW_STATE = 1; 
	
	private JButton uploadButton;
	private JTextField uploadField;
	private JFileChooser fileChooser;
	private JSeparator separator;
	private JComboBox overallStatistics;
	private JComboBox teamStatistics;
	private JComboBox refereeStatistics;
	private DataScrollPane dataScrollPane;
	
	private UploadService uploadService;
	
	private TeamService teamService;
	
	private StatisticsService statisticsService;
	
	private RefereeService refereeService;

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

	public RefereeService getRefereeService() {
		return refereeService;
	}

	public void setRefereeService(RefereeService refereeService) {
		this.refereeService = refereeService;
	}

	public QueryPanel(DataScrollPane dataScrollPane) {
		this.dataScrollPane = dataScrollPane;
    	ApplicationContext appCtxt = new ClassPathXmlApplicationContext(ApplicationConstants.SPRING_CONFIG_FILE);
    	uploadService = (UploadService)appCtxt.getBean(ApplicationConstants.UPLOAD_SERVICE);
    	teamService = (TeamService)appCtxt.getBean(ApplicationConstants.TEAM_SERVICE);
    	statisticsService = (StatisticsService)appCtxt.getBean(ApplicationConstants.STATISTICS_SERVICE);
    	refereeService = (RefereeService)appCtxt.getBean(ApplicationConstants.REFEREE_SERVICE);
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
		overallStatisticsLabel.setBounds(x0, y0 + 55, 110, 30);
		overallStatistics = new JComboBox(new Object[] { 
				LEAGUGE_TABLE, 
				TOP_10_SCORERS, 
				TOP_5_GOALKEEPERS, 
				TOP_AGGRESSIVE_PLAYERS, 
				TOP_STRICT_REFEREES, 
				TOP_MOST_POPULAR_NAMES 
				});
		overallStatistics.setBounds(x0 + 110, y0 + 55, 200, 30);
		overallStatistics.setSelectedItem(null);
		overallStatistics.addItemListener(this);
		this.add(overallStatisticsLabel);
		this.add(overallStatistics);
		
		JLabel teamStatisticsLabel = new JLabel("Team Statistics:");
		teamStatisticsLabel.setBounds(x0, y0 + 95, 110, 30);
		ComboBoxModel overallStatisticsModel = new DefaultComboBoxModel(teamService.getAllTeamNames().toArray());
		teamStatistics = new JComboBox(overallStatisticsModel);
		teamStatistics.setBounds(x0 + 110, y0 + 95, 200, 30);
		teamStatistics.setSelectedItem(null);
		teamStatistics.addItemListener(this);
		this.add(teamStatisticsLabel);
		this.add(teamStatistics);
		
		
		JLabel refereeStatisticsLabel = new JLabel("Referee Statistics:");
		refereeStatisticsLabel.setBounds(x0, y0 + 135, 110, 30);
		ComboBoxModel refereeStatisticsModel = new DefaultComboBoxModel(refereeService.getAllReferees().toArray());
		refereeStatistics = new JComboBox(refereeStatisticsModel);
		refereeStatistics.setBounds(x0 + 110, y0 + 135, 200, 30);
		refereeStatistics.setSelectedItem(null);
		refereeStatistics.addItemListener(this);
		this.add(refereeStatisticsLabel);
		this.add(refereeStatistics);
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
			    		teamStatistics.removeItemListener(this);
			    		for (String s : teamService.getAllTeamNames()) {
			    			((DefaultComboBoxModel)teamStatistics.getModel()).addElement(s);	
						}
			    		teamStatistics.addItemListener(this);
			    		teamStatistics.setSelectedItem(null);
			    		
			    		((DefaultComboBoxModel)refereeStatistics.getModel()).removeAllElements();
			    		refereeStatistics.removeItemListener(this);
			    		for (Referee r : refereeService.getAllReferees()) {
			    			((DefaultComboBoxModel)refereeStatistics.getModel()).addElement(r);	
			    		}
			    		refereeStatistics.addItemListener(this);
			    		refereeStatistics.setSelectedItem(null);
			    		
			    	} catch (Exception exception) {
			    		exception.printStackTrace();
			    	}
			    }
			}			
		}
		else {
			
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getSource().equals(overallStatistics) && e.getStateChange() == COMBOBOX_NEW_STATE) {
			
			teamStatistics.setSelectedItem(null);
			refereeStatistics.setSelectedItem(null);
			
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

			} else if (e.getItem().equals(TOP_MOST_POPULAR_NAMES)) {

				Object[] columnNames = {"First Name", "Frequency"};
				Object[][] data = statisticsService.getTopMostPopularNames();
				dataScrollPane.setModel(new DataTableModel(data, columnNames));
			}
			
		} else if (e.getSource().equals(teamStatistics) && e.getStateChange() == COMBOBOX_NEW_STATE) {
			
			overallStatistics.setSelectedItem(null);
			refereeStatistics.setSelectedItem(null);
			
			Object[] columnNames = {"Number", "Role", "First Name", "Last Name", "Total Games Played", "Total Games Played (Main LineUp)", 
					"Total Minutes Played", "Total Goals Scored", "Total Assists", "Total Lost Goals", "Average Lost Goals (Per Game)",
					"Total Yellow Cards", "Total Red Cards"};
			Object[][] data = statisticsService.getTeamStatistics((String)e.getItem());
			dataScrollPane.setModel(new DataTableModel(data, columnNames));
			
		} else if (e.getSource().equals(refereeStatistics) && e.getStateChange() == COMBOBOX_NEW_STATE) {
			
			overallStatistics.setSelectedItem(null);
			teamStatistics.setSelectedItem(null);
			
			Object[] columnNames = {"Number", "Role", "First Name", "Last Name", "Player's Team", "Minutes", 
					"Seconds", "Game Date", "Game Place", "Played Team 1", "Played Team 2"};
			Object[][] data = statisticsService.getRefereeStatistics(((Referee)e.getItem()).getId());
			dataScrollPane.setModel(new DataTableModel(data, columnNames));
			
		}		
	}
}
