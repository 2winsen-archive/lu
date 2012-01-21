package lv.lu.mpt.pd2.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import lv.lu.mpt.pd2.constants.ApplicationConstants;
import lv.lu.mpt.pd2.interfaces.service.UploadService;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SuppressWarnings("serial")
public class QueryPanel extends JPanel implements ActionListener {
	
	private JButton uploadButton;
	private JTextField uploadField;
	private JFileChooser fileChooser;
	private JSeparator separator;
	
	private static final String UPLAOD_ACTION = "uplaodAction";
	private static final int GAP = 10;
	private static final int x0 = 10;
	private static final int y0 = 10;
	
	private UploadService uploadService;

	public UploadService getUploadService() {
		return uploadService;
	}

	public QueryPanel() {
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
			    	ApplicationContext appCtxt = new ClassPathXmlApplicationContext(ApplicationConstants.SPRING_CONFIG_FILE);
			    	UploadService uploadService = (UploadService) appCtxt.getBean(ApplicationConstants.UPLOAD_SERVICE_ID);
			    	try {
			    		uploadService.upload(children);
			    	} catch (Exception exception) {
			    		exception.printStackTrace();
			    	}
			    }
			} else {
				System.out.println("No Selection ");
			}			
		}
		else {
			
		}
	}
}
