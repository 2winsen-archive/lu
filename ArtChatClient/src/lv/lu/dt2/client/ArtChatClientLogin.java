package lv.lu.dt2.client;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * 
 * @author vitalijs.sakels
 *
 */
public class ArtChatClientLogin extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 3435449741971109575L;
	
	private static final int WIDTH = 400; 
	private static final int HEIGHT = 300;
	
	private static final String TITLE = "ArtChat -> Login page";
	private static final String HEADING_LABEL = "Login to Art Chat";
	private static final String LOGIN_LABEL = "[Nickname]";
	private static final String HOST_LABEL = "[Host:Port]";
	
	private JTextField nicknameTextField;
	private JTextField hostTextField;
	private JButton loginButton;
	private JLabel heading;
	private JLabel nicknameLabel;
	private JLabel hostLabel;
	private JLabel notificationLabel;
	
	
	public void initUI() {
		this.setTitle(TITLE);
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.setResizable(false);
		
		JPanel panel = new JPanel(null);
		panel.setBackground(Color.WHITE);
		
		heading = new JLabel(HEADING_LABEL);
		heading.setBounds(20, 20, 330, 35);
		heading.setFont(new Font("Serif", Font.BOLD, 30));
		panel.add(heading);
		
		hostLabel = new JLabel(HOST_LABEL);
		// x = 20, y = 85
		hostLabel.setBounds(20, 20 + 35 + 30, 330, 35);
		hostLabel.setFont(new Font("Serif", Font.PLAIN, 24));
		panel.add(hostLabel);
		
		hostTextField = new JTextField();
		// x = 130, y = 85
		hostTextField.setBounds(20 + 118, 20 + 35 + 30, 150, 35);
		hostTextField.setText("localhost:6329"); // TODO: REMOVE!!!
		panel.add(hostTextField);
		
		nicknameLabel = new JLabel(LOGIN_LABEL);
		// x = 20, y = 85
		nicknameLabel.setBounds(20, 20 + 35 + 10 + 35 + 30, 330, 35);
		nicknameLabel.setFont(new Font("Serif", Font.PLAIN, 24));
		panel.add(nicknameLabel);
		
		nicknameTextField = new JTextField();
		// x = 130, y = 145
		nicknameTextField.setBounds(20 + 118, 20 + 35 + 10 + 35 + 30, 150, 35);
		nicknameTextField.setText("Vitalijs"); // TODO: REMOVE!!!
		panel.add(nicknameTextField);
		
		loginButton = new JButton("Login");
		// x = 130, y = 200
		loginButton.setBounds(20 + 110, 20 + 35 + 10 + 35 + 30 + 35 + 20, 120, 40);
		loginButton.addActionListener(this);
		panel.add(loginButton);
		
		notificationLabel = new JLabel(LOGIN_LABEL);
		// x = 130, y = 200
		notificationLabel.setBounds(20, 20 + 35 + 10 + 35 + 30 + 35 + 20 + 40, 320, 40);
		notificationLabel.setFont(new Font("Serif", Font.PLAIN, 18));
		notificationLabel.setVisible(false);
		panel.add(notificationLabel);
		
		this.getContentPane().add(panel);
		
		this.pack();
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private boolean isValidHost(String[] splittedHost) {
		if (splittedHost.length == 2) {
			try {
				Integer.parseInt(splittedHost[1]);
			}
			catch (Exception e) {
				return false;
			}
		} else {
			return false;
		}	
		return true;
	}
	
	public void actionPerformed(ActionEvent e) {
	    String[] splittedHost = hostTextField.getText().split(":");
	    if (isValidHost(splittedHost)) {
	        this.setVisible(false);
	        String host = splittedHost[0];
	        int port = Integer.parseInt(splittedHost[1]);
	        new ArtChatClientMainFrame(host, port, nicknameTextField.getText());
	    }
	    else
	    {
	        notificationLabel.setForeground(Color.RED);
	        notificationLabel.setText("Invalid host!");
	        notificationLabel.setVisible(true);
	    }
	}
}
