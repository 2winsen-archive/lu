package lv.lu.dt2.client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileFilter;

/**
 * 
 * @author vitalijs.sakels
 *
 */
public class ArtChatClientMainFrame extends JFrame implements ActionListener {

    private static final long serialVersionUID = 6184710132939207089L;

    private static final String TITLE = "ArtChat -> Main page";
    private static final String SEND = "Send";
    private static final String SEND_IMAGE = "Send Image";
    private static final String SEND_COMMAND = "SEND_COMMAND";

    private static final String RECONNECT = "Reconnect";
    private static final String RECONNECT_COMMAND = "RECONNECT_COMMAND";

    private static final int WIDTH = 800;
    private static final int HEIGHT = 700;
    
    private static final Color GREEN_COLOR = new Color(0, 130, 0);

    private JList chatList;
    private JList participantsList;
    private JTextArea messageArea;
    private DefaultListModel chatListModel;
    private DefaultListModel participantsListModel;
    private JButton sendButton;
    private JButton sendImageButton;
    private JScrollPane chatListScrollPane;
    private JScrollPane participantsListScrollPane;
    private JLabel notificationLabel;
    private Socket messageSocket;
    private Socket exitSocket;
    private Socket participantListSocket;
    private Socket imageSocket;
    private DataInputStream messageIncomingStream;
    private DataOutputStream messageOutgoingStream;
    private DataOutputStream exitMessageOutgoingStream;
    private DataOutputStream participantListOutgoingStream;
    private DataInputStream participantListIncomingStream;
    private DataInputStream imageIncomingStream;
    private DataOutputStream imageOutgoingStream;
    private JFileChooser fileChooser;

    private String host;
    private int port;
    private String nickname;
    
    private Thread messageReceivingThread;
    private Thread participantListMaintainThread;
    private Thread imageReceivingThread;

    private boolean isOnline = true;

    public ArtChatClientMainFrame(String host, int port, String nickname) {
        this.host = host;
        this.port = port;
        this.nickname = nickname;

        initUI();
        connect(host, port, nickname);
        startThreads();
    }

    private void initUI() {
        this.setTitle(TITLE);
        this.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent w) {
                processExit();
            }
        });

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
                initChatPane(), initActionPanel());
        splitPane.setOneTouchExpandable(true);
        splitPane.setContinuousLayout(true);
        splitPane.setEnabled(false);
        splitPane.setDividerLocation(530);

        this.getContentPane().add(splitPane);

        this.setVisible(true);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private JScrollPane initChatPane() {
        chatListModel = new DefaultListModel();
        chatList = new JList(chatListModel);
        chatListScrollPane = new JScrollPane(chatList);
        return chatListScrollPane;
    }

    @SuppressWarnings("serial")
	private JScrollPane initActionPanel() {
    	messageArea = new JTextArea();
    	messageArea.setDropTarget(new DropTarget() {
			@SuppressWarnings("unchecked")
			public synchronized void drop(DropTargetDropEvent evt) {
		        try {
		            evt.acceptDrop(DnDConstants.ACTION_COPY);
		            List<File> droppedFiles = (List<File>)
		            evt.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
		            if (droppedFiles.size() > 0) {
		                sendImage(droppedFiles.get(0));
		            }
		        } catch (Exception ex) {
		            ex.printStackTrace();
		        }
		    }
		});
    	JScrollPane messageAreaScrollPane = new JScrollPane(messageArea);

        participantsListModel = new DefaultListModel();
        participantsList = new JList(participantsListModel);
        participantsList.setPreferredSize(new Dimension(250, (int)participantsList.getPreferredSize().getHeight()));
        participantsListScrollPane = new JScrollPane(participantsList);

        JPanel bottomPanel = new JPanel(new BorderLayout(20, 20));
        sendButton = new JButton(SEND);
        sendButton.addActionListener(this);
        sendButton.setPreferredSize(new Dimension(150, 30));
        bottomPanel.add(sendButton, BorderLayout.WEST);
        notificationLabel = new JLabel();
        notificationLabel.setFont(new Font("Serif", Font.BOLD, 15));
        notificationLabel.setVisible(false);
        notificationLabel.setPreferredSize(new Dimension(400, 30));
        bottomPanel.add(notificationLabel, BorderLayout.CENTER);
        sendImageButton = new JButton(SEND_IMAGE);
        sendImageButton.addActionListener(this);
        sendImageButton.setPreferredSize(new Dimension(150, 30));
        bottomPanel.add(sendImageButton, BorderLayout.EAST);
        sendImageButton.setVisible(false);

        JPanel actionPanel = new JPanel(new BorderLayout());
        actionPanel.setPreferredSize(new Dimension(actionPanel
                .getPreferredSize().width, 100));
        actionPanel.add(messageAreaScrollPane, BorderLayout.CENTER);
        actionPanel.add(participantsListScrollPane, BorderLayout.EAST);
        actionPanel.add(bottomPanel, BorderLayout.PAGE_END);

        return new JScrollPane(actionPanel);
    }

    private void goOnline() {
        notificationLabel.setForeground(GREEN_COLOR);
        notificationLabel.setText("Online.");
        notificationLabel.setVisible(true);

        sendButton.setActionCommand(SEND_COMMAND);
        sendButton.setText(SEND);
        
        sendImageButton.setVisible(true);
        isOnline = true;
    }

    public void goOffline() {
        notificationLabel.setForeground(Color.RED);
        notificationLabel.setText("Host is not responding! Offline.");
        notificationLabel.setVisible(true);

        sendButton.setActionCommand(RECONNECT_COMMAND);
        sendButton.setText(RECONNECT);
        
        sendImageButton.setVisible(false);
        isOnline = false;
    }

    private void reconnect() {
        stopThreads();
        connect(this.host, this.port, this.nickname);
        startThreads();
    }

    private void connect(String host, int port, String nickname) {
        try {
            messageSocket = new Socket(host, port);
            messageIncomingStream = new DataInputStream(
                    messageSocket.getInputStream());
            messageOutgoingStream = new DataOutputStream(
                    messageSocket.getOutputStream());

            exitSocket = new Socket(host, port);
            exitMessageOutgoingStream = new DataOutputStream(
                    exitSocket.getOutputStream());

            participantListSocket = new Socket(host, port);
            participantListOutgoingStream = new DataOutputStream(
                    participantListSocket.getOutputStream());
            participantListIncomingStream = new DataInputStream(
                    participantListSocket.getInputStream());
            
            imageSocket = new Socket(host, port);
            imageIncomingStream = new DataInputStream(
                    imageSocket.getInputStream());
            imageOutgoingStream = new DataOutputStream(
                    imageSocket.getOutputStream());
            
            // Broadcast log in message
            messageOutgoingStream.writeUTF(nickname + " has logged in");

            if (isValidConnection()) {
                goOnline();
            } else {
                goOffline();
            }
        } catch (IOException ioe) {
            goOffline();
        }
    }

    private boolean isValidConnection() {
        return messageSocket != null && exitSocket != null && participantListSocket != null;
    }

    private void startThreads() {
        ParticipantListMaintainRunner participantListMaintainRunner = new ParticipantListMaintainRunner(
                participantListOutgoingStream, participantsListModel, nickname,
                participantListIncomingStream, this);
        participantListMaintainThread = new Thread(
                participantListMaintainRunner);
        participantListMaintainThread.start();

        MessageReceivingRunner messageReceivingRunner = new MessageReceivingRunner(
                messageIncomingStream, chatListModel, chatList);
        messageReceivingThread = new Thread(messageReceivingRunner);
        messageReceivingThread.start();
        
        ImageReceivingRunner imageReceivingRunner = new ImageReceivingRunner(
                imageIncomingStream, chatListModel, chatList);
        imageReceivingThread = new Thread(imageReceivingRunner);
        imageReceivingThread.start();
    }
    
    private void stopThreads() {
        participantListMaintainThread.interrupt();
        messageReceivingThread.interrupt();
    }
    
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == sendButton) {
            if (event.getActionCommand().equals(SEND_COMMAND)) {
                String newMessage = messageArea.getText();
                if (!newMessage.trim().equals("")) {
                    messageArea.setText("");
                    newMessage = nickname + ": > " + newMessage;
                    try {
                        messageOutgoingStream.writeUTF(newMessage);
                        messageOutgoingStream.flush();
                    } catch (IOException ioe) {
                        ioe.printStackTrace();
                    }
                }
            } else if (event.getActionCommand().equals(RECONNECT_COMMAND)) {
                reconnect();
            }
        }
        else if (event.getSource() == sendImageButton) {
			if (fileChooser == null) {
				fileChooser = new JFileChooser();
				fileChooser.setFileFilter(new FileFilter() {
					@Override
					public String getDescription() {
						return "Images Only";
					}
					
				    public String getExtension(File f) {
				        String ext = null;
				        String s = f.getName();
				        int i = s.lastIndexOf('.');

				        if (i > 0 &&  i < s.length() - 1) {
				            ext = s.substring(i+1).toLowerCase();
				        }
				        return ext;
				    }
					
					@Override
					public boolean accept(File f) {
					    if (f.isDirectory()) {
					        return false;
					    }
					    String extension = getExtension(f);
					    if (extension != null) {
					        if (extension.equals("png") ||
					            extension.equals("jpeg") ||
					            extension.equals("jpg") ) {
					                return true;
					        } else {
					            return false;
					        }
					    }
					    return false;
					}
				});
				fileChooser.setCurrentDirectory(new java.io.File("C:\\Users\\vitalik\\Desktop"));
				fileChooser.setDialogTitle("Select image file...");
				fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			}

			if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			    final File imageFile = fileChooser.getSelectedFile();
			    sendImage(imageFile);
			}	
        }
    }
    
    private void sendImage(File imageFile) {
        Icon iconImage = new ImageIcon(imageFile.getAbsolutePath());
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(imageOutgoingStream);
            objectOutputStream.writeObject(iconImage);
            imageOutgoingStream.flush();
            objectOutputStream.flush();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        
    }

    private void processExit() {
        try {
            if (isOnline) {
                exitMessageOutgoingStream.writeUTF(nickname);
                exitMessageOutgoingStream.flush();
            }
            System.exit(0);
        } catch (Exception oe) {
            oe.printStackTrace();
        }
    }
}

/**
 * Runner for maintaining participants list
 * 
 * @author vitalijs.sakels
 * 
 */
class ParticipantListMaintainRunner implements Runnable {
    private DataOutputStream participantListOutgoingStream;
    private DefaultListModel participantsListModel;
    private DataInputStream participantListIncomingStream;
    private String nickname;
    private List<String> newParticipants = new Vector<String>();
    private ArtChatClientMainFrame frame;
    
    private ObjectInputStream objectInputStream;
    private int currentParticipantsCount = 0;

    public ParticipantListMaintainRunner(DataOutputStream participantListOutgoingStream,
            DefaultListModel participantsListModel, String nickname,
            DataInputStream participantListIncomingStream, ArtChatClientMainFrame frame) {
        this.participantListOutgoingStream = participantListOutgoingStream;
        this.participantsListModel = participantsListModel;
        this.nickname = nickname;
        this.participantListIncomingStream = participantListIncomingStream;
        this.frame = frame;
    }

    @SuppressWarnings("unchecked")
    public void run() {
        try {
            participantListOutgoingStream.writeUTF(nickname);
            while (true && participantListIncomingStream != null) {
                objectInputStream = new ObjectInputStream(participantListIncomingStream);
                newParticipants = (Vector<String>) objectInputStream.readObject();
                if (currentParticipantsCount > 0) {
                    participantsListModel.clear();
                }
                Iterator<String> iterator = newParticipants.iterator();
                while (iterator.hasNext()) {
                    participantsListModel.addElement((String)iterator.next());
                    currentParticipantsCount++;
                }
            }
        } catch (Exception oe) {
            frame.goOffline();
        }
    }
}

/**
 * Runner for receiving messages
 * 
 * @author vitalijs.sakels
 * 
 */
class MessageReceivingRunner implements Runnable {
    private DataInputStream messageIncomingStream;
    private DefaultListModel chatListModel;
    private JList chatList;

    public MessageReceivingRunner(DataInputStream messageIncomingStream,
            DefaultListModel chatListModel, JList chatList) {
        this.messageIncomingStream = messageIncomingStream;
        this.chatListModel = chatListModel;
        this.chatList = chatList;
    }
    
    private void scrollDown() {
        final int lastIndex = chatListModel.getSize() - 1;
        if (lastIndex >= 0) {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    chatList.ensureIndexIsVisible(lastIndex);                
                }
            });
        }
    }

    public void run() {
        while (true && messageIncomingStream != null) {
            try {
                String receivedMessage = messageIncomingStream.readUTF();
                chatListModel.addElement(receivedMessage);
                scrollDown();
            } catch (Exception e) {
                // Do not read stream of disconnected user
                break;
            }
        }
    }
}

class ImageReceivingRunner implements Runnable {
    private DataInputStream imageIncomingStream;
    private ObjectInputStream objectInputStream;
    private Icon iconImage;
    
    private DefaultListModel chatListModel;
    private JList chatList;
    
    public ImageReceivingRunner(DataInputStream imageIncomingStream, DefaultListModel chatListModel, JList chatList) {
        this.imageIncomingStream = imageIncomingStream;
        this.chatListModel = chatListModel;
        this.chatList = chatList;
    }
    
    private void scrollDown() {
        final int lastIndex = chatListModel.getSize() - 1;
        if (lastIndex >= 0) {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    chatList.ensureIndexIsVisible(lastIndex);
                }
            });
        }
    }
    
    public void run() {
        while (true && imageIncomingStream != null) {
            try {
                objectInputStream = new ObjectInputStream(imageIncomingStream);
                iconImage = (Icon)objectInputStream.readObject();
                chatListModel.addElement(iconImage);
                scrollDown();
            } catch (Exception e) {
                // Do not read stream of disconnected user
                break;
            }
        }
    }
}
