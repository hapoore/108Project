package account;

import java.util.*;

public class Conversation {

	private LinkedList<Message> messages; 
	private int newNotifications;
	private String user;
	
	public Conversation(String input, String user) {
		this.user = user;
		int code = Integer.parseInt(input.substring(input.indexOf("(")+1,input.indexOf(")")));
		this.newNotifications = code;
		
		String toSplit = input.substring(input.indexOf(")") + 1);
		String[] splitted = toSplit.split("\\s*quiznessSucks\\s*");
		
	     for (String split: splitted) {
	         System.out.println(split);
	         char who = split.charAt(0);
	         String message = split.substring(1);
	         
	         boolean newMessage = false;
	         if (code > 0) {
	        	 newMessage = true;
	        	 --code;
	         }
	         messages.add(new Message(newMessage, message, who));
	     }
	}
	
	public String getUserName() {
		return user;
	}
	
	public String storeValue() {
		String returned = "";
		int size = this.messages.size();
		for (int i = 0; i < size; i++) {
			Message cur = this.messages.get(i); //gets first (front)
			String message = cur.getMessage();
			char who = cur.who();
			returned += who + message + "quiznessSucks";
		}
		return returned;
	}
	
	public LinkedList<Message> getMessages() {
		return messages;
	}
	
	public void addMessage(boolean newMessage, String input, char who) {
		Message message = new Message(newMessage, input, who);
		messages.add(0, message);
	}
	
	public int getNewNotifications() {
		return this.newNotifications;
	}
	
	public void resetNotifications() {
		this.newNotifications = 0;
	}
	
}
