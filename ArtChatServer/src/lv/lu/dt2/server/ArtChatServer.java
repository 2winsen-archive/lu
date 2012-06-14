package lv.lu.dt2.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

/**
 * 
 * @author vitalijs.sakels
 * 
 */
public class ArtChatServer {

    private static final int PORT_NUMBER = 6329;

    private ServerSocket serverSocket;
    private Socket messageSocket;
    private Socket participantExitSocket;
    private Socket participantListSocket;
    private Socket imageSocket;

    private List<Socket> messageSocketClients = new Vector<Socket>();
    private List<Socket> exitSocketClients = new Vector<Socket>();
    private List<Socket> participantSocketClients = new Vector<Socket>();
    private List<Socket> imageSocketClients = new Vector<Socket>();
    
    private List<String> participants = new Vector<String>();

    public ArtChatServer() throws IOException {
        serverSocket = new ServerSocket(PORT_NUMBER);
        System.out.println(":: Server started successfully");
        
        while (true) {
            messageSocket = serverSocket.accept();
            participantExitSocket = serverSocket.accept();
            participantListSocket = serverSocket.accept();
            imageSocket = serverSocket.accept();
            
            messageSocketClients.add(messageSocket);
            MessageBroadcastRunner messageBroadCastRunner = new MessageBroadcastRunner(
                    messageSocket, messageSocketClients);
            
            participantSocketClients.add(participantListSocket);
            ParticipantListRunner participantListRunner = new ParticipantListRunner(participantListSocket,
                    participantSocketClients, participants);
            
            exitSocketClients.add(participantExitSocket);
            ParticipantExitRunner participantExitRunner = new ParticipantExitRunner(messageSocket, participantExitSocket,
                    participantListSocket, imageSocket, messageSocketClients, exitSocketClients,
                    participantSocketClients, imageSocketClients, participants);

            imageSocketClients.add(imageSocket);
            ImageBroadcastRunner imageBroadcastRunner = new ImageBroadcastRunner(
                    imageSocket, imageSocketClients);
            
            Thread messageBroadcastThread = new Thread(messageBroadCastRunner);
            messageBroadcastThread.start();
            System.out.println(":: messageSocket started successfully " + "[" + messageSocket + "]");
            
            Thread participantExitThread = new Thread(participantExitRunner);
            participantExitThread.start();
            System.out.println(":: participantExitSocket started successfully " + "[" + participantExitSocket + "]");
            
            Thread participantListThread = new Thread(participantListRunner);
            participantListThread.start();
            System.out.println(":: participantListSocket started successfully" + "[" + participantListSocket + "]");
            
            Thread imageBroadcastThread = new Thread(imageBroadcastRunner);
            imageBroadcastThread.start();
            System.out.println(":: imageSocket started successfully" + "[" + imageSocket + "]");
        }
    }
    
    public static void main(String[] args) {
        try {
            new ArtChatServer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ParticipantExitRunner implements Runnable {
    private Socket exitSocket;
    private Socket messageSocket;
    private Socket participantListSocket;
    private Socket imageSocket;
    
    private List<Socket> exitSocketClients;
    private List<Socket> messageSocketClients;
    private List<Socket> participantSocketClients;
    private List<Socket> imageSocketClients;
    
    private List<String> participants;
    private String participantToRemove;
    private DataInputStream inputStream;

    public ParticipantExitRunner(Socket messageMocket, Socket exitSocket,
            Socket participantListSocket, Socket imageSocket,
            List<Socket> messageSocketClients,
            List<Socket> exitSocketClients, List<Socket> participantSocketClients,
            List<Socket> imageSocketClients,
            List<String> participants) {
        this.exitSocket = exitSocket;
        this.messageSocket = messageMocket;
        this.participantListSocket = participantListSocket;
        this.imageSocket = imageSocket;

        this.exitSocketClients = exitSocketClients;
        this.messageSocketClients = messageSocketClients;
        this.participantSocketClients = participantSocketClients;
        this.imageSocketClients = imageSocketClients;

        this.participants = participants;
    }

    public void run() {
        try {
            inputStream = new DataInputStream(exitSocket.getInputStream());
            while (true) {
                participantToRemove = inputStream.readUTF();
                String participantExitMessage = participantToRemove + " has logged out";
                System.out.println(participantExitMessage);
                
                participants.remove(participantToRemove);
                ParticipantListRunner.broadcastObject(participantSocketClients, participants);
                MessageBroadcastRunner.broadcastMessage(participantExitMessage, messageSocketClients);
                
                exitSocketClients.remove(exitSocket);
                messageSocketClients.remove(messageSocket);
                participantSocketClients.remove(participantListSocket);
                imageSocketClients.remove(imageSocket);
            }
        } catch (Exception e) {
            // Do not read stream of disconnected user
        }
    }
}

class ParticipantListRunner implements Runnable {
    private Socket participantListSocket;
    private List<Socket> participantSocketClients;
    private List<String> participants;

    public ParticipantListRunner(Socket participantListSocket, List<Socket> participantSocketClients, List<String> participants) {
        this.participantListSocket = participantListSocket;
        this.participantSocketClients = participantSocketClients;
        this.participants = participants;
    }

    public void run() {
        try {
            DataInputStream inputStream = new DataInputStream(participantListSocket.getInputStream());
            participants.add(inputStream.readUTF());
            ParticipantListRunner.broadcastObject(participantSocketClients, participants);
        } catch (Exception e) {
            // Do not read stream of disconnected user
        }
    }

    public synchronized static void broadcastObject(List<Socket> socketClients, Object object)  throws Exception {
        Iterator<Socket> iterator = socketClients.iterator();
        Socket socket;
        DataOutputStream outputStream = null;
        while (iterator.hasNext()) {
            socket = (Socket) iterator.next();
            outputStream = new DataOutputStream(socket.getOutputStream());
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(object);
            
            outputStream.flush();
            objectOutputStream.flush();
        }
    }
}

class MessageBroadcastRunner implements Runnable {
    private Socket messageSocket;
    private List<Socket> messageSocketClients;

    public MessageBroadcastRunner(Socket messageSocket, List<Socket> messageSocketClients) {
        this.messageSocket = messageSocket;
        this.messageSocketClients = messageSocketClients; 
    }

    public void run() {
        DataInputStream inputStream = null;
        try {
            inputStream = new DataInputStream(messageSocket.getInputStream());
        } catch (Exception e) {
            // Do not read stream of disconnected user
        }
        while (true) {
            try {
                MessageBroadcastRunner.broadcastMessage(inputStream.readUTF(), messageSocketClients);
            } catch (IOException e) {
                // Do not read stream of disconnected user
                break;
            }
        }
    }

    public synchronized static void broadcastMessage(String string, List<Socket> messageSocketClients) throws IOException {
        DataOutputStream outputStream = null;
        Iterator<Socket> iterator = messageSocketClients.iterator();
        Socket socket;
        while (iterator.hasNext()) {
            socket = (Socket) iterator.next();
            outputStream = new DataOutputStream(socket.getOutputStream());
            outputStream.writeUTF(string);
            outputStream.flush();
        }
    }
}

class ImageBroadcastRunner implements Runnable {
    private Socket imageSocket;
    private List<Socket> imageSocketClients;
    
    public ImageBroadcastRunner(Socket imageSocket, List<Socket> imageSocketClients) {
        this.imageSocket = imageSocket;
        this.imageSocketClients = imageSocketClients; 
    }
    
    public void run() {
        ObjectInputStream inputStream = null;
        try {
            inputStream = new ObjectInputStream(imageSocket.getInputStream());
        } catch (Exception e) {
            // Do not read stream of disconnected user
        }
        while (true && inputStream != null) {
            try {
                ParticipantListRunner.broadcastObject(imageSocketClients, inputStream.readObject());
            } catch (IOException e) {
                // Do not read stream of disconnected user
                break;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
}
