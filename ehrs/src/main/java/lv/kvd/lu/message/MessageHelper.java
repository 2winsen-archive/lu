package lv.kvd.lu.message;

import java.util.List;

import lv.kvd.lu.user.UserDaoImpl;

import org.springframework.security.context.SecurityContextHolder;
import org.springframework.security.userdetails.User;

/**
 * Helper class contains methods for group funcionality
 * 
 * @author vitalik
 * 
 */
public class MessageHelper {
	
	private static final Boolean DEFAULT_FLAG = false;

	private MessageDaoImpl messageDao;
	private UserDaoImpl userDao;

	public MessageDaoImpl getMessageDao() {
		return messageDao;
	}

	public void setMessageDao(MessageDaoImpl messageDao) {
		this.messageDao = messageDao;
	}

	public UserDaoImpl getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDaoImpl userDao) {
		this.userDao = userDao;
	}

	/**
	 * Populates data in user select excludes current user
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List populateUser() {
		// Getting current logged user username
		String currentUser = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
				.getUsername();
		return userDao.getRecords(false, "username", currentUser);
	}

	/**
	 * Send message this method saves new message in messages table
	 * 
	 * @param message
	 * @return
	 */
	public int sendMessage(Message form) {
		// Getting current logged user username
		String senderUsername = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
				.getUsername();
		form.setSenderUsername(senderUsername);
		form.setReadFlag(DEFAULT_FLAG);
		// Gets first 20 symbols of message or even less
		form.setShortEntry(form.getEntry());
		messageDao.saveRecord(form);
		return 1;
	}
	
	/**
	 * Gets all currently logged user messages
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List viewMessages() {
		// Getting current logged user username
		String currentUser = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
				.getUsername();
		// Getting current user Object
		List list = userDao.getRecords("username", currentUser);
		// Getting current user id
		Long id = ((lv.kvd.lu.user.User)list.get(0)).getId();

		return messageDao.getRecords("user_id", id.toString());
	}

	/**
	 * Checks if current user has any unread message
	 * @return 1 if has 0 has not
	 */
	@SuppressWarnings("unchecked")
	public int hasNewMessages() {
		// Getting current logged user username
		String currentUser = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
				.getUsername();
		// Getting current user Object
		List users = userDao.getRecords("username", currentUser);
		// Getting current user id
		Long id = ((lv.kvd.lu.user.User)users.get(0)).getId();
		String[] fieldNames = {"read_flag", "user_id"};
		String[] values = {"false", id.toString()};
		List list = messageDao.getRecords("=",fieldNames, values);
		if(!list.isEmpty()) {
			return 1;
		}
		return 0;
	}
	
	/**
	 * Sets readFlag of message to true and changes message in DB
	 * @param message
	 */
	public void setRead(Message message) {
		message.setReadFlag(true);
		messageDao.saveRecord(message);
	}

}
