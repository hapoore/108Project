package web;

import dataBase.*;
import questions.*;
import account.*;
import java.security.*;
import java.util.*;

public class AccountManager {
	
	public static final int NUM_BYTES = 8;
	
	private DataBase database; //Change this to whatever the database class is called

	public AccountManager() {
		this.database = new DataBase(); //Connect to the database
	}

	//See if given username exists (returns account if it does, null if not)
	public Account getAccount(String username) {
		if(!database.accountExists(username)) return null;
		return database.getAccount(username);
	}

	//See if password matches for the username
	public boolean passwordMatch(String username, String attemptedPW) {
		//get account information from the database
		//hash the attempted pw with the username and salt
		//see if it matches that contained in the database
		//if yes, return true, else false
		String salt = database.getSalt(username);
		System.out.println("salt is " + salt);
		String passAndSalt = salt + attemptedPW;
		System.out.println("pass and salt is " + passAndSalt);
		String hashed = generateHash(passAndSalt);
		System.out.println("hashed is " + hashed);
		if(database.passwordMatch(username, hashed)) {
			return true;
		}
		return false;
		
	}
	
	private String generateHash(String word) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA");
			byte[] hashedBytes = digest.digest(word.getBytes());
			return hexToString(hashedBytes);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}

	/*
	 Given a byte[] array, produces a hex String,
	 such as "234a6f". with 2 chars for each byte in the array.
	 (provided code)
	*/
	public static String hexToString(byte[] bytes) {
		StringBuffer buff = new StringBuffer();
		for (int i = 0; i < bytes.length; i++) {
			int val = bytes[i];
			val = val & 0xff;  // remove higher bits, sign
			if (val < 16) buff.append('0'); // leading 0
			buff.append(Integer.toString(val, 16));
		}
		return buff.toString();
	}

	//Put a new username/pw combination into the database
	public Account addNewAccount(String username, String password) { //Perhaps other parameters, like security question
		//Hash the password with the username/salt
		//store in DB
		SecureRandom random = new SecureRandom();
	    byte[] bytes = new byte[NUM_BYTES];
	    random.nextBytes(bytes);
	    String salt = hexToString(bytes);
	    String hashed = generateHash(salt + password);
	    Account account = new Account(username, hashed, salt);
	    database.addAccount(account);
	    return account;
	}
	
	public void updatePassword(Account account, String clearTextPW) {
		 String salt = account.getSalt();
		 String hashed = generateHash(salt + clearTextPW);
		 account.setPassword(hashed);
		 database.updateAccountPassword(account.getUserName(), hashed);
	}

	public void updateSetUp(String userName, String passwordHint, String securityQuestion, String securityAnswer, String profilePic) {
		database.updateAccountSetUp(userName, passwordHint, securityQuestion, securityAnswer, profilePic);
	}
	
	public void closeDBConnection() {
		database.close();
	}
	
	//Other functions, like set/get profile picture, status, friends, etc. should probably all be handled through account class.

}

