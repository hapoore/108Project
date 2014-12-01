package account;

import dataBase.*;

import java.util.*;

public class Inbox {

	private Map<String, Conversation> inbox;
	private LinkedList<String> order;
	private Account you;
	private DataBase db;
	//private int newMessages;
	//private ArrayList<String> notifications;
	
	public Inbox(Map<String, String> messages, Account yourAccount, DataBase db) {
		this.db = db;
		this.you = yourAccount;
		Map<String, Conversation> c_inbox = new HashMap<String, Conversation>();
		String c_copy[] = new String[messages.size()]; 
		for (String key_code: messages.keySet()) {
			int code = Integer.parseInt(key_code.substring(key_code.indexOf("(")+1,key_code.indexOf(")")));
			
			//use this username
			String real_username = key_code.substring(key_code.indexOf(")") + 1);
			c_copy[code] = real_username;
			c_inbox.put(real_username, new Conversation(messages.get(key_code), real_username));
			
			
		}
		this.order = new LinkedList<String>(Arrays.asList(c_copy));
		this.inbox = c_inbox;
		
		System.out.println(order);
		System.out.println(inbox);
	}
	
	public LinkedList<Conversation> getNewMessages() {
		LinkedList<Conversation> newMessages = new LinkedList<Conversation>();
		int size = this.order.size();
		for (int i = 0; i < size; i++) {
			Conversation cur_con = inbox.get(order.get(i));
			if (cur_con.getNewNotifications() == 0) continue;
			newMessages.add(cur_con);
		}
		return newMessages;
	}
	
	
	//returns order
	public LinkedList<String> getInboxOrder() {
		return order;
	}
	
	
	//sends message
	public void sendMessage(String to, String message) {
		if (!inbox.keySet().contains(to)) { //checks for initial message
			order.add(0, to);
			inbox.put(to, new Conversation("(0)", to));
		}
		
		Account other_person = Account.getOnlineAccount(to);
		
		//change this string first
		Conversation holder = this.inbox.get(to);
		holder.addMessage(false, message, 'y');
		
		//now change other person
		if (other_person == null) {
			other_person = db.getAccount(to);
			other_person.getInbox().addMessage(true, message, 'o', you.getUserName()); //TODO: change after other things
			db.updateAccount(other_person);
		} else { //is online
			System.out.println(other_person);
			System.out.println(other_person.getInbox());
			other_person.getInbox().addMessage(true, message, 'o', you.getUserName());
		}
		order.remove(to);
		order.add(0, to);
	}

	//FOR INTERNAL USE ONLY
	public void addMessage(boolean newMessage, String input, char who, String from) {
		if (!inbox.keySet().contains(from)) {
			inbox.put(from, new Conversation("(0)", from));
			order.add(0, from);
		}
		Conversation holder = this.inbox.get(from);
		holder.addMessage(true, input, 'o');
		order.remove(from);
		order.add(0, from);
	}
	
	public Conversation getConversation(String userName) {
		return inbox.get(userName);
	}
	
	
	/*public String getConversation(String userName) {
		Message holder = this.inbox.get(userName);
		return holder.getMessages();
	}*/
	
	/*public boolean newNotification(String userName) {
		return this.inbox.get(userName).getNotification();
	}*/
	
	//TODO
	public Map<String, String> storeInbox() {
		Map<String, String> stored = new HashMap<String, String>();
		int size = order.size();
		
		for (int i = 0; i < size; i++) {
			String user = order.get(i);
			Conversation holder = this.inbox.get(user);
			String username = "(" + i + ")" + user;
			String value = "(" + holder.getNewNotifications() + ")";
			
			value += holder.storeValue();
			stored.put(username, value);	
		}
		
		return stored;
		
	}
	
}
