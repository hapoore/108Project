package account;

public class Message {

	private boolean newMessage;
	private String input;
	private char who;
	
	public Message(boolean newMessage, String input, char who) {
		this.newMessage = newMessage;
		this.input = input;
		this.who = who;
	}
	
	public void turnOnNotification() {
		newMessage = true;
	}
	
	public void turnOffNotification() {
		newMessage = false;
	}
	
	public String getMessage() {
		return input;
	}
	
	public boolean newMessage() {
		return newMessage;
	}
	
	public char who() {
		return who;
	}
	
	
	
	
	
	
	/*private boolean newNotification;
	private StringBuilder message;
	
	public Message(String input) {
		int code = Integer.parseInt(input.substring(input.indexOf("(")+1,input.indexOf(")")));
		if (code == 0) newNotification = false;
		else newNotification = true;
		message = new StringBuilder(input.substring(input.indexOf(")") + 1));
	}
	
	public void appendMessageSelf(String input) {
		String new_message = "You: " + input + "\n\n";
		message.append(new_message);
		newNotification = false;
	}
	
	public void appendMessageOther(String otherAccount, String input) {
		String new_message = otherAccount + ": " + input + "\n\n";
		message.append(new_message);
		newNotification = true;
	}
	
	public boolean newNotification() {
		return newNotification;
	}

	public void setNotification(boolean isNew) {
		this.newNotification = isNew;
	}
	
	public String getMessages() {
		return message.toString();
	}
	
	public boolean getNotification() {
		return newNotification;
	}
	
	public String toString() {
		String returned = this.getMessages() + "\n" + newNotification;
		return returned;
	}*/
	
}
