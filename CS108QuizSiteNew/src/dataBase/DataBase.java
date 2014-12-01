package dataBase;

import account.*;
import questions.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.*;
import quizzes.*;




public class DataBase {
	//TODO
	//getters for various things (profile pic only)
	//in an account. In order
	//linked list of challenges SetChallenges/ getChallenges
	//LIST OF CHALLENGES (score INT, from CHAR(64), quizName CHAR(64), quizCreator CHAR(64))
	//ADD TABLE TO UPDATE NUMUSERS AND NUMQUIZZES
	//STORE UP TO 5 announcements in chronological order
	//CREATE GET QUIZ BY TAG
	/**
	 * Returns an arrayList of announcements where the first announcement is the most recent
	 */
	public ArrayList<String> getAnnouncements(){
		String command = "SELECT * from administrator";
		ResultSet rs = executeQuery(command);
		ArrayList<String> announcements = new ArrayList<String>();
		try{
			if(rs.next()){
				for(int i = 0; i < 4; i ++){
					String ann = rs.getString("announcement"+i+"");
					if(ann == null) continue;
					announcements.add(ann);
				}
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return announcements;
	}
	
	/**
	 * adds an announcement to the administrator announcements
	 * @param announcement
	 */
	public void addAnnouncement(String announcement){
		ArrayList<String> announcements = getAnnouncements();
		announcements.add(0,announcement);
		if(announcements.size() > 5) announcements.remove(5);
		for(int i = 0; i < 5; i ++){
			String command = "UPDATE administrator SET announcement"+i+" = ?";
			String[] list = {announcements.get(i)};
			executeStringUpdate(command, list);
			}
		}
		
		
		
	
	
	
	static String account = MyDBInfo.MYSQL_USERNAME;
	static String password = MyDBInfo.MYSQL_PASSWORD;
	static String server = MyDBInfo.MYSQL_DATABASE_SERVER;
	static String database = MyDBInfo.MYSQL_DATABASE_NAME;	
	private int numAccounts;
	private Connection con;
	
	/**
	 * Creates the DataBase object and opens the connection
	 */
	public DataBase(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://" + server, account, password);
			Statement stmt = con.createStatement();
			stmt.executeQuery("USE " + database + ";");
			String command = "SELECT number FROM accounts";
			ResultSet rs = executeQuery(command);
			numAccounts = 0;
			if(rs.last())
				numAccounts = rs.getInt("number");
		} catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();
			
		}
	}
	
	/**
	 * Closes the connection
	 */
	public void close(){
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Executes the command given in the parameters, knowing that it is an update.
	 * @param command
	 */
	public void executeUpdate(String command){
	
		
		try{
			Statement stmt = con.createStatement();
			stmt.executeUpdate(command);
			
		}catch(SQLException e){
			e.printStackTrace();
		}	
	}
	/**
	 * Executes the command given in the parameters by creating a prepared Statement
	 * and filling in the blanks in order with the elements in the String array list
	 * @param command
	 * @param list
	 */
	private void executeStringUpdate(String command, String[] list){
		try{
			PreparedStatement stmt = con.prepareStatement(command);
			for(int i = 0; i < list.length; i ++){
				if(list[i] == null){
					stmt.setNull(i+1, java.sql.Types.VARCHAR);
				}else
					stmt.setString(i+1, list[i]);
			}
			stmt.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * Executes the command given in the parameters, knowing that it is a query. 
	 * Returns the result set of that query.
	 * @param command
	 * @return rs Result set of the query.
	 */
	public ResultSet executeQuery(String command){
		try{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(command);
			return rs;
		}catch(SQLException e){
			e.printStackTrace();
			
		}	
		return null;
	}
	
	private ResultSet executeStringQuery(String command, String[] list){
		try{
			PreparedStatement stmt = con.prepareStatement(command);
			for(int i = 0; i < list.length; i ++){
				stmt.setString(i+1, list[i]);
			}
			return(stmt.executeQuery());
		}catch(SQLException e){
			e.printStackTrace();
		}
		return null;
	}
	
	
	public int getNumUsers(){
		String command = "SELECT numUsers from administrator";
		ResultSet rs = executeQuery(command);
		try{
			if(rs.next())
				return (rs.getInt("numUsers"));
			else
				return 0;
			
		} catch(SQLException e){
			e.printStackTrace();
		}
		return -1;
	}
	
	public int getNumQuizzes(){
		String command = "SELECT numQuizzes from administrator";
		ResultSet rs = executeQuery(command);
		try{
			if(rs.next())
				return (rs.getInt("numQuizzes"));
			else
				return 0;
		} catch(SQLException e){
			e.printStackTrace();
		}
		return -1;
	}
	
	
	
	
	
	/**
	 * Searches the database for an account with the same userName and password.
	 * Returns true, if they match, false otherwise
	 * password is hashed
	 * @param userName
	 * @param password
	 * @return
	 */
	public Boolean passwordMatch(String userName, String password){
		try {
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM accounts WHERE userName = ? AND password = ?");
			stmt.setString(1, userName);
			stmt.setString(2, password);
			ResultSet rs = stmt.executeQuery();
			if(!rs.next())
				return false;
		}catch(SQLException e){
			e.printStackTrace();
		}

		return true;
	}
	
	/**
	 * Takes in the userName. If there is already an account with that userName 
	 * then it returns true. 
	 * @param userName
	 * @return
	 */
	public Boolean accountExists(String givenName){
		try{
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM accounts WHERE userNAME = ?");
			stmt.setString(1, givenName);
			ResultSet rs = stmt.executeQuery();
			if(rs.next())
				return true;
		} catch(SQLException e){
			e.printStackTrace();
		}
		return false;
	}
	
	public String getSalt(String userName){
		try{
			PreparedStatement stmt = con.prepareStatement("SELECT salt from accounts WHERE accounts WHERE userName = ?");
			stmt.setString(1, userName);
			ResultSet rs = stmt.executeQuery();
			if(rs.next())
				rs.getString("salt");
		} catch(SQLException e){
			e.printStackTrace();
		} return null;
		
		/*String command = "SELECT salt from accounts WHERE userName=\""+userName+"\";";
		ResultSet rs = executeQuery(command);
		try {
		if(rs.next())
				return rs.getString("salt");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return null;*/
	}
	
	private int getAccountNumber(String userName){
		String command = "SELECT number FROM accounts WHERE userName = ?";
		String[] list = {userName};
		ResultSet rs = executeStringQuery(command, list);
		try {
			rs.next();
			return rs.getInt("number");
		}
		catch (SQLException e) {
			e.printStackTrace();
		} 
		return -1;
	}
		
	
	public void updateAccountSetUp(String userName,String passwordHint, String securityQuestion, String securityAnswer, String profilePic){
		updateAccountPasswordHint(userName, passwordHint);
		updateAccountSecurityQuestion(userName, securityQuestion);
		updateAccountSecurityAnswer(userName, securityAnswer);
		updateAccountProfilePic(userName, profilePic);
	}
		
	
	
	public void updateAccountPassword(String userName, String password){
		String command = "UPDATE accounts SET password = ? WHERE userName = ?";
		String[] list = {password, userName};
		executeStringUpdate(command, list);
		/*
		String command = "UPDATE accounts SET password = \""+password+"\" WHERE userName = \""+userName+"\";";
		executeUpdate(command);*/
	}
	
	public void updateAccountPasswordHint(String userName, String passwordHint){
		String command = "UPDATE accounts SET passwordHint = ? WHERE userName = ?";
		String[] list = {passwordHint, userName};
		executeStringUpdate(command, list);
		/*
		String command = "UPDATE accounts SET passwordHint = \""+passwordHint+"\" WHERE userName = \""+userName+"\";";
		executeUpdate(command);*/
	}
	
	public void updateAccountSecurityQuestion(String userName, String securityQuestion){
		String command = "UPDATE accounts SET securityQuestion = ? WHERE userName = ?";
		String[] list = {securityQuestion, userName};
		executeStringUpdate(command, list);
		/*
		String command = "UPDATE accounts SET securityQuestion = \""+securityQuestion+"\" WHERE userName = \""+userName+"\";";
		executeUpdate(command);*/
	}
	
	public void updateAccountSecurityAnswer(String userName, String securityAnswer){
		String command = "UPDATE accounts SET securityAnswer = ? WHERE userName = ?";
		String[] list = {securityAnswer, userName};
		executeStringUpdate(command, list);
		/*
		String command = "UPDATE accounts SET securityAnswer = \""+securityAnswer+"\" WHERE userName = \""+userName+"\";";
		executeUpdate(command);*/
	}
	
	public void updateAccountProfilePic(String userName, String profilePic){
		String command = "UPDATE accounts SET profilePic = ? WHERE userName = ?";
		String[] list = {profilePic, userName};
		executeStringUpdate(command, list);
		/*
		String command = "UPDATE accounts SET profilePic = \""+profilePic+"\" WHERE userName = \""+userName+"\";";
		executeUpdate(command);*/
	}
	
	public void updateAccountStatus(String userName, String status){
		String command = "UPDATE accounts SET status = ? WHERE userName = ?";
		String[] list = {status, userName};
		executeStringUpdate(command, list);
		/*
		String command = "UPDATE accounts SET status = \""+status+"\" WHERE userName = \""+userName+"\";";
		executeUpdate(command);*/
	}
	
	public void updateAccountAdminStatus(String userName, boolean isAdmin){
		String command = "UPDATE accounts SET administratorStatus = "+isAdmin+" WHERE userName = ?";
		String[] list = {userName};
		executeStringUpdate(command, list);
		/*
		String command = "UPDATE accounts SET administratorStatus = "+isAdmin+" WHERE userName = \""+userName+"\";";
		executeUpdate(command);*/
	}
	
	public void addAchievement(String userName, Achievement achievement){
		int accountNumber = getAccountNumber(userName);
		String command = "INSERT INTO "+accountNumber+"achievements(?,"+achievement.getPoints()+",?)";
		String[] list = {achievement.getName(), achievement.getURL()};
		executeStringUpdate(command, list);
		
/*		String command = "INSERT INTO "+ userName + "achievements(\""+achievement.getName()+","+achievement.getPoints()+",\""+achievement.getURL()+"\");";
		executeUpdate(command);*/
	}
	
	public void removeFriend(String userName, String friendName){
		int accountNumber = getAccountNumber(userName);
		String command = "DELETE FROM "+accountNumber+"friends WHERE userName = ?";
		String[] list = {friendName};
		executeStringUpdate(command, list);
		
	/*	String command = "DELETE FROM "+userName+"friends WHERE userName = \""+friendName+"\";";
		executeUpdate(command);*/
	}
	
	public void addFriend(String userName, String friendName){
		int accountNumber = getAccountNumber(userName);
		String command = "INSERT INTO "+accountNumber+"friends VALUES(?,?)";
		String[] list = {friendName, null};
		executeStringUpdate(command, list);
		/*String command = "INSERT INTO "+ userName+"friends VALUES(\""+friendName+"\","+null+");";
		executeUpdate(command);
		*/
	}
	
	public void addFriendRequest(String userName, String friendName){
		int accountNumber = getAccountNumber(userName);
		String command = "INSERT INTO "+accountNumber+"friendRequests VALUES(?)";
		String[] list = {friendName};
		executeStringUpdate(command, list);
		/*
		String command = "INSERT INTO "+userName+"friendRequests VALUES(\""+friendName+"\");";
		executeUpdate(command);
		*/
	}
	
	public void removeFriendRequest(String userName, String noLongerFriendName){
		int accountNumber = getAccountNumber(userName);
		String command = "DELETE FROM "+accountNumber+"friendRequests WHERE userName = ?";
		String[] list = {noLongerFriendName};
		executeStringUpdate(command, list);
		/*
		String command = "DELETE FROM "+userName+"friendRequests WHERE userName =\""+noLongerFriendName+"\";";
		executeUpdate(command);
		*/
	}
	
	public void addMessage(String userName, String friendName, String message){
		int accountNumber = getAccountNumber(userName);
		String command = "UPDATE "+accountNumber+"friends SET message = ? WHERE userName = ?";
		String[] list = {message, friendName};
		executeStringUpdate(command, list);
		/*
		String command = "UPDATE "+userName+"friends SET message = \""+message+"\" WHERE userName = \""+friendName+";";
		executeUpdate(command);*/
	}
	
	public void addQuizTaken(String userName, QuizResult result){
		Date date = result.date;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time =  sdf.format(date);
		int accountNumber = getAccountNumber(userName);
		String command = "INSERT INTO "+accountNumber+"quizzesTaken VALUES(?,"+result.score+","+result.highScore+","+result.percentage+",?,"+result.timeNeeded+")";
		String[] list = {result.user, time};
		executeStringUpdate(command, list);
	}

	public double getRating(String userName){
		String command = "SELECT rating FROM accounts WHERE userName = ?";
		String [] list = {userName};
		ResultSet rs = executeStringQuery(command, list);
		try{
			rs.next();
			return rs.getDouble("rating");
		}catch(SQLException e){
			e.printStackTrace();
		}
		return -1;
	}
	
	/**
	 * Assumes the account is valid and can be added to the database.
	 * Assumes the account is not already in the database
	 * @param account
	 */
	public void addAccount(Account account){		
		int numUsers = getNumUsers();
		numUsers ++;
		String command = "UPDATE administrator SET numUsers = "+numUsers+";";
		executeUpdate(command);
		
		numAccounts ++;
		command = "INSERT INTO accounts VALUES(?,?,?,?,?,?,?,?,"+account.getNumRatings()+","+account.getRating()+","+account.isAdmin()+","+numAccounts +")";
		String [] list = {account.getUserName(), account.getPassword(), account.getPasswordHint(), account.getSecurityQuestion(), account.getSecurityAnswer(), account.getProfilePic(), account.getSalt(), account.getStatus()};
		executeStringUpdate(command, list);
		/*String command = "INSERT INTO accounts VALUES (\""+account.getUserName()+"\",\""+account.getPassword()+
		"\",\""+account.getPasswordHint()+"\",\""+account.getSecurityQuestion()+"\",\""+account.getSecurityAnswer()+"\",\""+account.getProfilePic()+"\","+account.getNumRatings()+","+account.getRating()+","+
		account.isAdmin()+",\""+account.getSalt()+"\",\""+account.getStatus()+"\");";
		executeUpdate(command);
	*/
		
		
		//creates the friend table
		int accountNumber = getAccountNumber(account.getUserName());
		command = "DROP TABLE IF EXISTS "+accountNumber+"friends";
		executeUpdate(command);
		
		command = "CREATE TABLE " +accountNumber + "friends(userName CHAR(64), messages TEXT);";
		executeUpdate(command);
		
		Set<String> friends = account.getFriendList();
		Map<String, String> inbox = account.storeInbox();
		int i = 0;
		String message;
		if(friends != null){
			for(String name : friends){
				message = null;
				if(inbox != null && inbox.containsKey(name))
					message = inbox.get(name);
				this.addMessage(account.getUserName(), name, message);
				i ++;
			}
		}
		
		//adds friend requests
		command = "DROP TABLE IF EXISTS "+accountNumber+"friendRequests";
		executeUpdate(command);
		command = "CREATE TABLE " + accountNumber + "friendRequests(userName CHAR(64));";
		executeUpdate(command);
		Set<String> requests = account.getFriendRequests();
		if(requests != null){
			for(String request : requests){
				addFriendRequest(account.getUserName(), request);
			}
		}
		
		//adds quizzes to the dataBase
		LinkedList<Quiz> quizzes = account.getQuizzesCreated();
		if(quizzes != null){
			for(Quiz quiz : quizzes){
				this.addQuiz(quiz);
			}	
		}
	
		//adds quizzes taken info to the dataBase
		command = "DROP TABLE IF EXISTS "+accountNumber+"quizzesTaken";
		executeUpdate(command);
		command = "CREATE TABLE "+accountNumber+"quizzesTaken(quizName CHAR(64), score INT, highScore BOOLEAN, percentage DECIMAL(5,2), timeTaken DATETIME, timeNeeded DECIMAL(5,2));";
		executeUpdate(command);
		
		//TODO
		LinkedList<QuizResult> resultsList = account.getPastPerformances();
		//LinkedList<QuizResult> resultsList = account.getQuizResults();
		if(resultsList != null){
			for(QuizResult result: resultsList){
				this.addQuizTaken(account.getUserName(), result);
				/*				command = "INSERT INTO "+account.getUserName() + "quizzesTaken VALUES(\""+result.user+"\","+result.score+","+
					result.highScore+","+result.percentage+",\""+time+"\","+result.timeNeeded+");";
				executeUpdate(command);*/
			}
		}
		
		//adds achievement info to the database
		command = "DROP TABLE IF EXISTS "+accountNumber+"achievements";
		executeUpdate(command);
		command = "CREATE TABLE "+accountNumber+"achievements(achievement CHAR(64), points INT, url TEXT);";
		executeUpdate(command);
		Set<Achievement> achievementList = account.getAchievementList();
		if(achievementList != null){
			for(Achievement achievement: achievementList){
				addAchievement(account.getUserName(), achievement);
			}	
		}
	}
	
	public void updateAccount(Account account){
		removeAccount(account.getUserName());
		addAccount(account);
	}
	
	
	public void removeAccount(String userName){
		if(!this.accountExists(userName))
			return;
		int numUsers = getNumUsers();
		numUsers --;
		String command = "UPDATE administrator SET numUsers = "+numUsers+";";
		executeUpdate(command);
		
		
		/*
		String removeFromAccouts = "DELETE FROM accounts WHERE userName = "+ userName +";";
		this.executeUpdate(removeFromAccounts);
		*/
		//deletes all of the quizzes
		
		command = "DELETE FROM quizzes WHERE userName = ?";
		String[] list = {userName};
		executeStringUpdate(command, list);
		/*
		String command = "DELETE FROM quizzes WHERE userName = " + userName + ";";
		executeUpdate(command);
		*/
		//deletes all tables dealing with that account
		int accountNumber = getAccountNumber(userName);
		command = "SELECT TABLE_NAME from information_schema.tables WHERE TABLE_NAME like \"" +accountNumber+"%\";";
		ResultSet rs = this.executeQuery(command);
		if(rs == null) return;
		try {
			while(rs.next()){
				String TableName;
				TableName = rs.getString("TABLE_NAME");
				this.executeUpdate("DROP TABLE IF EXISTS "+TableName+";");
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		command = "DELETE FROM accounts WHERE userName = ?";
		executeStringUpdate(command, list);
		
		for(int i = 0; i < Question.NUM_QUESTION_TYPES; i ++){
			command = "DELETE FROM question"+i+" WHERE userName = ?";
			executeStringUpdate(command, list);
		}
	}
	
	public Set<String> getFriendList(String userName){
		int accountNumber = getAccountNumber(userName);
		String command = "SELECT * FROM "+accountNumber+"friends;";
		ResultSet rs = executeQuery(command);
		Set<String> friends = new HashSet<String>();
		try {
			while(rs.next()){
				friends.add(rs.getString("userName"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return friends;
	}
	
	private Map<String, String> getMessages(int accountNumber){
		String command = "SELECT * FROM "+accountNumber+"friends WHERE messages IS NOT NULL;";
		ResultSet rs = executeQuery(command);
		Map<String, String> inbox = new HashMap<String, String>();
		try{
			while(rs.next()){
				inbox.put(rs.getString("userName"), rs.getString("messages"));
			}
		} catch(SQLException e){
			e.printStackTrace();
		}
		return inbox;
	}
	
	private Set<Achievement> getAchievements(int accountNumber){
		String command = "SELECT * FROM "+accountNumber+"achievements;";
		ResultSet rs = executeQuery(command);
		Set<Achievement> achievements = new HashSet<Achievement>();
		try {
			while(rs.next()){
			
				Achievement achievement = new Achievement(rs.getString("achievement"), rs.getInt("point"), rs.getString("url"));
				achievements.add(achievement);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("ACH: " + achievements);
		return achievements;
	}

	
	
	private LinkedList<QuizResult> getQuizResults(String userName){
		int accountNumber = getAccountNumber(userName);
		String command = "SELECT * FROM "+accountNumber+"quizzesTaken;";
		ResultSet rs = executeQuery(command);
		LinkedList<QuizResult> results = new LinkedList<QuizResult>();
		try {
			while(rs.next()){
				int score = rs.getInt("score");
				String quizName = rs.getString("quizName");
				int numQuestions = rs.getInt("numQuestions");
				Date time = rs.getDate("time");
				int timeNeeded = rs.getInt("timeNeeded");
				QuizResult result = new QuizResult(quizName, userName, score, numQuestions, time, timeNeeded);
				results.add(result);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return results;
		
	}
	
	private LinkedList<Quiz> getQuizzesCreated(String userName){
		LinkedList<Quiz> quizzes = new LinkedList<Quiz>();
		String command = "SELECT quizName FROM quizzes WHERE userName = ?";
		String[] list = {userName};
		ResultSet rs = executeStringQuery(command, list);
		try{
			while(rs.next()){
				Quiz quiz = getQuiz(rs.getString("quizName"), userName);
				quizzes.add(quiz);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return quizzes;
	}
	
	
	private Set<String> getFriendRequests(int accountNumber){
		String command = "SELECT * FROM "+accountNumber+"friendRequests;";
		ResultSet rs = executeQuery(command);
		Set<String> requests = new HashSet<String>();
		try{
			while(rs.next()){
				String name = rs.getString("userName");
				requests.add(name);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return requests;
	}
	
	
	public Account getAccount(String userName){
		if(!this.accountExists(userName)) return null;
		String command = "SELECT * FROM accounts WHERE userName = ?";
		String[] list = {userName};
		ResultSet rs = executeStringQuery(command, list);
		String password = null;
		String salt = null;
		try {
			rs.next();
			if(rs.getString("password") != null)
				password = rs.getString("password");
			if(rs.getString("salt") != null)
				salt = rs.getString("salt");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		Account toReturn = new Account(userName, password, salt);
		try{
		toReturn.setPasswordHint(rs.getString("passwordHint"));
		toReturn.setSecurityQuestion(rs.getString("securityQuestion"));
		toReturn.setSecurityAnswer(rs.getString("securityAnswer"));
		toReturn.setProfilePic(rs.getString("profilePic"));
		toReturn.setRating(rs.getInt("numRatings"), rs.getDouble("rating"));
		toReturn.setAdministratorStatus(rs.getBoolean("administratorStatus"));
		toReturn.setStatus(rs.getString("status"));
		} catch(SQLException e){
			e.printStackTrace();
		}
		int accountNumber = getAccountNumber(userName);
		toReturn.setInbox(getMessages(accountNumber));
		toReturn.setFriendList(getFriendList(userName));
		toReturn.setAchievementList(getAchievements(accountNumber));
		toReturn.setPastPerformances(getQuizResults(userName));
		toReturn.setFriendRequests(getFriendRequests(accountNumber));
		toReturn.setQuizzesCreated(getQuizzesCreated(userName));
		return toReturn;
	}
	
	
	
	
	/**
	 * Searches the dataBase to see if the given quiz already exists. Returns
	 * true if the quiz exists, false otherwise.
	 * @param quizName
	 * @param accountName
	 * @return
	 */
	public Boolean containsQuiz(String quizName, String userName){
		String command = "SELECT * FROM quizzes WHERE userName = ? AND quizName = ?";
		String[] list = {userName, quizName};
		ResultSet rs = executeStringQuery(command, list);
		
		/*String command = "SELECT * FROM quizzes WHERE userName = \""+userName+"\" AND quizName = \""+quizName+"\";";
		ResultSet rs = executeQuery(command);*/
		try {
			return (rs.next());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	private ArrayList<String> parseAnswers(String answers){
		ArrayList<String> answerList = new ArrayList<String>();
		String answer = "";
		for(int i = 0; i < answers.length(); i ++){
			char cur = answers.charAt(i);
			if(cur == ':'){
				if(i + 1 < answers.length()){
					char next = answers.charAt(i + 1);
					if(next == cur){
						answerList.add(answer);
						answer = "";
						i +=1;
					}
				
				} else{
					break;
				}
			}else 
				answer += cur;
		}
		return answerList;
	}
	
	
	
	
	
	
	
	
	private Set<ResponseQuestion> getResponseQuestions(String userName, String quizName){
		Set<ResponseQuestion> rqs = new HashSet<ResponseQuestion>();
		String command = "SELECT * FROM question0 WHERE userName = ? AND quizName = ?";
		String[] list = {userName, quizName};
		ResultSet rs = executeStringQuery(command, list);
		
		/*
		String command = "SELECT * FROM responseQuestions WHERE userName = \""+userName+"\" AND quizName = \""+quizName+"\";";
		ResultSet rs = executeQuery(command);*/
		try {
			while(rs.next()){
				int number = rs.getInt("number");
				int worth = rs.getInt("worth");
				String question = rs.getString("question");
				ArrayList<String> answers = parseAnswers(rs.getString("answers"));
				ResponseQuestion rq = new ResponseQuestion(question, answers, number, worth);
				rqs.add(rq);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rqs;
	}
	
	private Set<FillBlankQuestion> getFillBlanks(String userName, String quizName){
		Set<FillBlankQuestion> fbqs = new HashSet<FillBlankQuestion>();
		String command = "SELECT * FROM question1 WHERE userName = ? AND quizName = ?";
		String[] list = {userName, quizName};
		ResultSet rs = executeStringQuery(command, list);
		
		/*
		String command = "SELECT * FROM fillBlankQuestions WHERE userName = \""+userName+"\" AND quizName = \""+ quizName+"\",";
		ResultSet rs = executeQuery(command);*/
		try {
			while(rs.next()){
				int index = rs.getInt("number");
				int value = rs.getInt("worth");
				String question = rs.getString("question");
				ArrayList<String> answers = parseAnswers(rs.getString("answers"));
				FillBlankQuestion fbq = new FillBlankQuestion(question, answers, index, value);
				fbqs.add(fbq);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return fbqs;
	}
	
	private Set<MultipleChoice> getMultipleChoices(String userName, String quizName){
		Set<MultipleChoice> mcs = new HashSet<MultipleChoice>();
		String command = "SELECT * FROM question2 WHERE userName = ? AND quizName = ?";
		String[] list = {userName, quizName};
		ResultSet rs = executeStringQuery(command, list);
		
		/*
		String command = "SELECT * FROM multipleChoice WHERE userName = \""+userName+"\" AND quizName = \""+ quizName+"\",";
		ResultSet rs = executeQuery(command);*/
		try {
			while(rs.next()){
				int index = rs.getInt("number");
				int value = rs.getInt("worth");
				String question = rs.getString("question");
				String answer = rs.getString("answer");
				ArrayList<String> wrongAnswers = parseAnswers(rs.getString("wrongAnswers"));
				MultipleChoice mc = new MultipleChoice(question, answer, wrongAnswers, index, value);
				mcs.add(mc);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mcs;
	}
	
	

	private Set<PictureResponseQuestion> getPictureResponses(String userName, String quizName){
		Set<PictureResponseQuestion> prqs = new HashSet<PictureResponseQuestion>();
		String command = "SELECT * FROM question3 WHERE userName = ? AND quizName = ?";
		String[] list = {userName, quizName};
		ResultSet rs = executeStringQuery(command, list);
		
		/*
		String command = "SELECT * FROM pictureResponseQuestions WHERE userName = \""+userName+"\" AND quizName = \""+quizName+"\",";
		ResultSet rs = executeQuery(command);*/
		try {
			while(rs.next()){
				int index = rs.getInt("number");
				int value = rs.getInt("worth");
				String question = rs.getString("question");
				String url = rs.getString("url");
				ArrayList<String> answers = parseAnswers(rs.getString("answers"));
				PictureResponseQuestion prq = new PictureResponseQuestion(question, url, answers, index, value);
				prqs.add(prq);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return prqs;
	}
	
	
	private Set<MatchingQuestion> getMatchingQuestions(String userName, String quizName){
		Set<MatchingQuestion> mqs = new HashSet<MatchingQuestion>();
		String command = "SELECT * FROM question4 WHERE userName = ? AND quizName = ?";
		String[] list = {userName, quizName};
		ResultSet rs = executeStringQuery(command, list);
		/*
		String command = "SELECT * FROM matchingQuestions WHERE userName = \""+userName+"\" AND quizName = \""+quizName+"\";";
		ResultSet rs = executeQuery(command);*/
		HashMap<String, String> pairs = new HashMap<String, String>();
		try {
			while(rs.next()){
				int number = rs.getInt("number");
				int worth = rs.getInt("worth");
				String question = rs.getString("question");
				String keys = rs.getString("objects");
				ArrayList<String> keyList = parseAnswers(keys);
				String values = rs.getString("value");
				ArrayList<String> valueList = parseAnswers(values);
				for(int i = 0; i < keys.length(); i ++){
					pairs.put(keyList.get(i), valueList.get(i));
				}
				pairs.put(rs.getString("key"), rs.getString("value"));
				MatchingQuestion mq = new MatchingQuestion(question, pairs, number, worth);
				mqs.add(mq);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mqs;
	}
	
	private Set<EstimateQuestion> getEstimateQuestions(String userName, String quizName){
		Set<EstimateQuestion> eqs = new HashSet<EstimateQuestion>();
		String command = "SELECT * FROM question5 WHERE userName = ? AND quizName = ?";
		String[] list = {userName, quizName};
		ResultSet rs = executeStringQuery(command, list);
		/*
		String command = "SELECT * FROM estimateQuestions WHERE userName = \"" +userName+"\" AND quizName = \""+ quizName+"\",";
		ResultSet rs = executeQuery(command);*/
		try {
			while(rs.next()){
				int number = rs.getInt("number");
				int worth = rs.getInt("worth");
				String question = rs.getString("question");
				EstimateQuestion eq = new EstimateQuestion(question, number, number, worth);
				eqs.add(eq);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return eqs;
	}
	/*
	get the question from the table with questions, get its index, and its quiz name
	search the answer table for the answers with the same index and quizname.*/
	
	//check to see if the table accountNumberQuestion6 exists.
	//if it doesnt, create the table
	//if it does, just add the values to the table
	//each row per question is an entry in the arrayList [....], [......], [.....],
	//and the name of the quiz
	
	//the original table will have the account name, the quiz name, the question, the number, the value, and whether or not it is sorted
	
	/*
	 * private String question;
private ArrayList<ArrayList<String>> answers;
private int number;
private int worth;
private int type;
private boolean ordered;
	 */
	private Set<MultiAnswerQuestion> getMultiAnswerQuestions(String userName, String quizName){
		Set<MultiAnswerQuestion> maqs = new HashSet<MultiAnswerQuestion>();
		String command = "SELECT * FROM question7 WHERE userName = ? AND quizName = ?";
		String[] list = {userName, quizName};
		ResultSet rs = executeStringQuery(command, list);
		int accountNumber = getAccountNumber(userName);
		ArrayList<ArrayList<String>> answers = new ArrayList<ArrayList<String>>();
		/*
		String command = "SELECT * FROM multiAnswerQuestions WHERE userName = \""+userName+"\" AND quizName = \""+quizName+"\",";
		ResultSet rs = executeQuery(command);*/
		try {
			while(rs.next()){
				int index = rs.getInt("number");
				int value = rs.getInt("worth");
				String question = rs.getString("question");
				Boolean inOrder = rs.getBoolean("inOrder");
				
				command = "SELECT answers FROM "+accountNumber+"Question7 WHERE userName = ? AND quizName = ? AND number = "+index;
				ResultSet answerSet = executeStringQuery(command, list);
		
					while(answerSet.next()){
						String answer = answerSet.getString("answers");
						ArrayList<String> answ = parseAnswers(answer);
						answers.add(answ);
					}
				
					MultiAnswerQuestion maq = new MultiAnswerQuestion(question, answers, inOrder, index, value);
					maqs.add(maq);
				}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return maqs;
	}
	


	private Set<RandomMathQuestion> getRandomMathQuestions(String userName, String quizName){
		Set<RandomMathQuestion> rmqs = new HashSet<RandomMathQuestion>();
		String command = "SELECT * FROM question6 WHERE userName = ? AND quizName = ?";
		String[] list = {userName, quizName};
		ResultSet rs = executeStringQuery(command, list);
		
		/*
		String command = "SELECT * FROM randomMathQuestions WHERE userName = \"" +userName+"\" AND quizName = \""+ quizName+"\",";
		ResultSet rs = executeQuery(command);*/
		try {
			while(rs.next()){
				int index = rs.getInt("number");
				int value = rs.getInt("worth");
				int difficulty = rs.getInt("difficulty");
				RandomMathQuestion rmq = new RandomMathQuestion(difficulty, index, value);
				rmqs.add(rmq);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rmqs;
	}
	
	
	private Set<MultiChoiceMultiAnswer> getMultiChoiceMultiAnswers(String userName, String quizName){
		Set<MultiChoiceMultiAnswer> mcmas = new HashSet<MultiChoiceMultiAnswer>();
		String command = "SELECT * FROM question8 WHERE userName = ? AND quizName = ?";
		String[] list = {userName, quizName};
		ResultSet rs = executeStringQuery(command, list);
		
		/*
		String command = "SELECT * FROM multiChoiceMultiAnswer WHERE userName = \""+userName+"\" AND quizName = \""+quizName+"\",";
		ResultSet rs = executeQuery(command);*/
		try {
			while(rs.next()){
				int index = rs.getInt("number");
				int value = rs.getInt("worth");
				String question = rs.getString("question");
				ArrayList<String> correctAnswers = parseAnswers(rs.getString("correctAnswers"));
				ArrayList<String> wrongAnswers = parseAnswers(rs.getString("wrongAnswers"));
				MultiChoiceMultiAnswer mcma = new MultiChoiceMultiAnswer(question, correctAnswers, wrongAnswers, index, value);
				mcmas.add(mcma);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mcmas;
	}
	
	private Set<GradedQuestion> getGradedQuestions(String userName, String quizName){
		Set<GradedQuestion> gqs = new HashSet<GradedQuestion>();
		String command = "SELECT * FROM question9 WHERE userName = ? AND quizName = ?";
		String[] list = {userName, quizName};
		ResultSet rs = executeStringQuery(command, list);
		try{
			while(rs.next()){
				int index = rs.getInt("number");
				int value = rs.getInt("worth");
				String question = rs.getString("question");
				GradedQuestion gq = new GradedQuestion(question, index, value);
				gqs.add(gq);
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return gqs;
	
		}
	
	
	
	
	
	/**
	 * given the tag, returns a set of all the quizzes created with the same tag
	 * @param tag
	 * @return
	 */
	public List<Quiz> getQuizByTag(String tag){
		List<Quiz> quizzes = new ArrayList<Quiz>();
		String command = "SELECT * FROM quizzes WHERE tag = ?";
		String[] list = {tag};
		ResultSet rs = executeStringQuery(command, list);
		try{
			while(rs.next()){
				quizzes.add(getQuiz(rs.getString("quizName"), rs.getString("userName")));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return quizzes;
	}
	/**
	 * returns a set of quizzes created by the userName
	 * @param userName
	 * @return
	 */
	public Set<Quiz> getQuizByUserName(String userName){
		Set<Quiz> quizzes = new HashSet<Quiz>();
		String command = "SELECT * FROM quizzes WHERE userName = ?";
		String[] list = {userName};
		ResultSet rs = executeStringQuery(command, list);
		try{
			while(rs.next()){
				quizzes.add(getQuiz(rs.getString("quizName"), userName));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return quizzes;
	}
	
	/**
	 * Given a partialQuiz name, returns a Set of all of the quizzes that  have names similar to that one
	 * @param quizName
	 * @return
	 */
	public Set<Quiz> getQuizzes(String quizName){
		Set<Quiz> quizzes = new HashSet<Quiz>();
		String command = "SELECT userName FROM quizzes WHERE quizName like ?";
		String[] list = {"%"+quizName+"%"};
		
		ResultSet rs = executeStringQuery(command, list);
		
		/*String command = "SELECT userName FROM quizzes WHERE quizName like \"%"+quizName+"%\";";
		ResultSet rs = executeQuery(command);*/
		try {
			while(rs.next()){
				quizzes.add(getQuiz(quizName, rs.getString("userName")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return quizzes;
	}
	
	public Set<String> getUserNames(String userName){
		Set<String> names = new HashSet<String>();
		String command = "SELECT userName FROM accounts WHERE userNAME like ?";
		String[] list = {"%"+userName+"%"};
		ResultSet rs = executeStringQuery(command, list);
		
		/*
		String command = "SELECT userName FROM accounts WHERE userName like \"%"+userName+"%\";";
		ResultSet rs = executeQuery(command);*/
		try{
			while(rs.next()){
				names.add(rs.getString("userName"));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return names;
	}
	
	

	
	public Quiz getQuiz(String quizName, String userName){
		if(!this.containsQuiz(quizName, userName)) return null;
		String command = "SELECT * FROM quizzes WHERE userName = ? AND quizName = ?";
		String[] list = {userName, quizName};
		ResultSet rs = executeStringQuery(command, list);
		
		
		/*
		String command = "SELECT * FROM quizzes WHERE quizName = \""+quizName+"\" AND userName = \""+userName+"\";";
		ResultSet rs = executeQuery(command);*/
		Quiz quiz = new Quiz();
		try{
			rs.next();
			quiz.setCreator(userName);
			quiz.setName(quizName);
			quiz.setDescription(rs.getString("description"));
			quiz.setTag(rs.getString("tag"));
			quiz.setRandomized(rs.getBoolean("rand"));
			quiz.setMultiplePages(rs.getBoolean("multiplePages"));
			quiz.setImmediateCorrection(rs.getBoolean("immediateCorrection"));
			quiz.setPracticeMode(rs.getBoolean("practiceMode"));
			quiz.setCreated(rs.getDate("dateCreated"));
			quiz.setNumPlays(rs.getInt("numPlays"));
			quiz.setTotalRating(rs.getInt("rating"));
			quiz.setTimesRated(rs.getInt("numRatings"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		command = "SELECT * FROM quizResults WHERE creator = ? AND quizName = ?";
		rs = executeStringQuery(command, list);
		int sumScores = 0;
		List<QuizResult> results = new ArrayList<QuizResult>();
		try {
			while(rs.next()){
				String user = rs.getString("userName");
				int score = rs.getInt("score");
				double percentage = rs.getDouble("percentage");
				Date timeTaken = rs.getDate("timeTaken");
				double timeNeeded = rs.getDouble("timeNeeded");
				QuizResult result = new QuizResult(quizName, user, score, percentage, timeTaken, timeNeeded);
				results.add(result);
				sumScores += score;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		QuizStatistics stats = new QuizStatistics(results);
		quiz.setStats(stats);
		Set<Question> questions = getQuestions(quiz);
		for(Question question : questions){
			quiz.addQuestion(question);
		}
			
		return quiz;
	}
	
	private String createAnswerString(ArrayList<String> answerList){
		String answers = "";
		for(String answer : answerList){
			answers += answer;
			answers += "::";
		}
		return answers;
	}
	
	/*
	private String createAnswerString(Set<String> answerList){
		String answers = "";
		for(String answer : answerList){
			answers += answer;
			answers += "::";
		}
		return answers;
	}*/
	
	private String createAnswerString(Collection<String> answerList){
		String answers = "";
		for(String answer : answerList){
			answers += answer;
			answers += "::";
		}
		return answers;
	}
	
	/*RESPONSE = 0;
	FILL_BLANK = 1;
	MULTIPLE_CHOICE = 2;
	PICTURE = 3;
	MATCHING = 4;
	ESTIMATE = 5;
	RANDOM_MATH = 6;
	MULTI_ANSWER = 7;
	MULTI_MULTI = 8;
	GRADED = 9;*/
	
	
	

	
	/**
	 * Adds a question of type ResponseQuestion to the database
	 * @param q
	 */
	private void addQuestion0(Question question, String userName, String quizName){
		
		ResponseQuestion q = (ResponseQuestion) question;
		String answers = createAnswerString(q.getAnswers());
		String command = "INSERT INTO question0 VALUES(?,?,?,?,"+q.getNumber()+","+q.getWorth()+")";
		String [] list = {userName, quizName, q.getQuestion(), answers};
		executeStringUpdate(command, list);
		/*
		String command = "INSERT INTO question0 VALUES(\""+q.getQuestion()+"\",\""+answers+
		"\","+q.getNumber()+","+q.getWorth()+";";
		executeUpdate(command);*/
	}
	
	
	/**
	 * Adds a question of type FillBlankQuestion to the database
	 * @param q
	 */
	private void addQuestion1(Question question, String userName, String quizName){
		FillBlankQuestion q = (FillBlankQuestion) question;
		String answers = createAnswerString(q.getAnswers());
		String command = "INSERT INTO question1 VALUES(?,?,?,?,"+q.getNumber()+","+q.getWorth()+")";
		String list[] = {userName, quizName, q.getQuestion(), answers};
		executeStringUpdate(command, list);
		/*
		String command = "INSERT INTO question4 VALUES(\""+q.getQuestion()+"\",\""+answers+
		"\","+q.getNumber()+","+q.getWorth()+";";
		executeUpdate(command);*/
	}
	
	
	/**
	 * Adds a question of type MultipleChoice to the database
	 * @param q
	 */
	private void addQuestion2(Question question, String userName, String quizName){
		MultipleChoice q = (MultipleChoice) question;
		String wrongAnswers = createAnswerString(q.getChoices());
		String command = "INSERT INTO question2 VALUES(?,?,?,?,?,"+q.getNumber()+","+q.getWorth()+")";
		String[] list = {userName, quizName, q.getQuestion(), q.getAnswer(), wrongAnswers};
		executeStringUpdate(command, list);
		/*
		String command = "INSERT INTO question1 VALUES(\""+q.getQuestion()+"\",\""+q.getAnswer()+
		"\",\""+wrongAnswers+"\","+q.getNumber()+", "+q.getWorth()+";";
		executeUpdate(command);*/
	}
	
	
	
	/**
	 * Adds a question of type PictureResponseQuestion to the database
	 * @param q
	 */
	private void addQuestion3(Question question, String userName, String quizName){
		PictureResponseQuestion q = (PictureResponseQuestion) question;
		String answers = createAnswerString(q.getAnswers());
		String command = "INSERT INTO question3 VALUES(?,?,?,?,?,"+q.getNumber()+","+q.getWorth()+")";
		String[] list = {userName, quizName, q.getQuestion(), q.getURL(), answers};
		executeStringUpdate(command, list);
		/*
		String command = "INSERT INTO question2 VALUES(\""+q.getQuestion()+"\",\""+q.getURL()+
		"\",\""+answers+"\","+q.getNumber()+","+q.getWorth()+";";
		executeUpdate(command);*/
	}
	
	
	/**
	 * Adds a question of type MatchngQuestion to the database
	 * @param q
	 */
	private void addQuestion4(Question question, String userName, String quizName){
		MatchingQuestion q = (MatchingQuestion) question;
		Set<String> keyList = q.getAnswers().keySet();
		String keys = createAnswerString(keyList);
		Collection<String> valueList = q.getAnswers().values();
		String values = createAnswerString(valueList);
		String command = "INSERT INTO question4 VALUES(?,?,?,"+q.getNumber()+","+q.getWorth()+")";
		String list[] = {userName, quizName, q.getQuestion(), keys, values};
		executeStringUpdate(command, list);
		/*
		
		String command = "INSERT INTO question3 VALUES(\""+q.getQuestion()+"\",\""+keys+
		"\",\""+values+"\","+q.getNumber()+","+q.getWorth()+");";
		executeUpdate(command);*/
	}
	
	
	
	
	private void addQuestion5 (Question question, String userName, String quizName){
		EstimateQuestion q = (EstimateQuestion) question;
		
		String command = "INSERT INTO question5 VALUES(?,?,?,"+q.getAnswer()+","+q.getNumber()+","+q.getWorth()+")";
		String[] list = {userName, quizName, q.getQuestion()};
		executeStringUpdate(command, list);
	}
	
	/**
	 * Adds a question of type RandomQuestion to the database
	 * @param q
	 */
	private void addQuestion6(Question question, String userName, String quizName){
		RandomMathQuestion q = (RandomMathQuestion) question;
		String command = "INSERT INTO question6 VALUES(?,?,"+q.getDifficulty()+","+q.getNumber()+","+q.getWorth()+")";
		String[] list = {userName, quizName};
		executeStringUpdate(command, list);
	}

	
	/**
	 * Adds a question of type MultiAnswerQuestion to the database
	 * @param q
	 */
	private void addQuestion7(Question question, String userName, String quizName){
		MultiAnswerQuestion q = (MultiAnswerQuestion) question;
		int accountNumber = getAccountNumber(userName);
		String command = "CREATE TABLE IF NOT EXISTS "+accountNumber+"Question7(question TEXT, answers TEXT, userName CHAR(64), quizName CHAR(64), number INT);";
		executeUpdate(command);
		for(int i = 0; i < q.getAnswers().size(); i++){
			String answers = createAnswerString(q.getAnswers().get(i));
			command = "INSERT INTO "+accountNumber+"Question7(?,?,?,"+q.getNumber()+")";
			String[] list = {answers, userName, quizName};
			executeStringUpdate(command, list);
		}
		command = "INSERT INTO question7 VALUES(?,?,?,"+q.getNumber()+","+q.getWorth()+")";
		String[] list = {userName, quizName, q.getQuestion()};
		executeStringUpdate(command, list);
	}
	
	/**
	 * Adds a question of type MultiChoceMultiAnswer to the database
	 * @param q
	 */
	private void addQuestion8(Question question, String userName, String quizName){
		MultiChoiceMultiAnswer q = (MultiChoiceMultiAnswer) question;
		String correctAnswers = createAnswerString(q.getAnswers());
		String wrongAnswers = createAnswerString(q.getAllChoices());
		String command = "INSERT INTO question8 VALUES(?,?,?,?,?,"+q.getNumber()+","+q.getWorth()+")";
		String[] list = {userName, quizName, q.getQuestion(), correctAnswers, wrongAnswers};
		executeStringUpdate(command, list);
		/*
		String command = "INSERT INTO question7 VALUES (\""+q.getQuestion()+"\",\""+correctAnswers+
		"\",\""+wrongAnswers+"\","+q.getNumber()+","+q.getWorth()+";";
		executeUpdate(command);*/
	}
	
	private void addQuestion9(Question question, String userName, String quizName){
		GradedQuestion q = (GradedQuestion) question;
		String command = "INSERT INTO question9 VALUES(?,?,?,"+q.getNumber()+","+q.getWorth()+")";
		String[] list = {userName, quizName, q.getQuestion()};
		executeStringUpdate(command, list);
	}

	
	
	
	
	
	
	/**
	 * Takes in a Quiz object and adds it into the dataBase
	 * If the quiz is already in the database
	 * returns -1. Otherwise, adds the new quiz to the database.
	 * @param quiz
	 * @return
	 */
	public boolean addQuiz(Quiz quiz){
		if(this.containsQuiz(quiz.getName(), quiz.getCreator()))
			return false;
		int numQuizzes = getNumQuizzes();
		numQuizzes ++;
		String command = "UPDATE administrator SET numQuizzes = "+numQuizzes+";";
		executeUpdate(command);
		
		String time = null;
		Date date = quiz.getCreated();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(date != null)
			time =  sdf.format(date);
	
		
		command = "INSERT INTO quizzes VALUES(?,?,?,?,?,"+quiz.getRandomized()+","+quiz.getMultiplePages()+","+quiz.getImmediateCorrection()+
		","+quiz.getPracticeMode()+","+quiz.getNumPlays()+","+quiz.getTotalRating()+","+quiz.getTimesRated()+")";
		System.out.println(quiz.getNumPlays());
		String[] list = {quiz.getCreator(), quiz.getName(), quiz.getDescription(), quiz.getTag(), time};
		executeStringUpdate(command, list);
		
		//TODO
		List<QuizResult> results = quiz.getStatistics().getScores();
		for(QuizResult result: results){
			command = "INSERT INTO quizResults VALUES(?,?,?,?,"+result.getScore()+","+result.getPercentage()+","+result.getTimeNeeded()+")";
			time = null;
			date = result.getDate();
			sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(date != null)
				time = sdf.format(date);
			String[] list1 = {result.getUser(), result.getQuizName(), quiz.getCreator(), time};
			executeStringUpdate(command, list1);
		}
		
		for (Question q : quiz.getQuestions()){
			addQuestion(quiz, q);
		}		
			return true;
	}
	
	//public void addResult(Quiz quiz)
	
	public void updateQuiz(Quiz quiz){
			if(!this.containsQuiz(quiz.getName(), quiz.getCreator())) return;
			removeQuiz(quiz);
			addQuiz(quiz);
			
	}
	
	public void removeQuestion(Quiz quiz, Question question){
		int type = question.type();
		String command = "DELETE FROM question"+type+" WHERE number = "+question.getNumber()+";";
		executeUpdate(command);
	}
	
	public void addQuestion(Quiz quiz, Question question){
		int type = question.type();
		switch(type){
		case 0: 
			addQuestion0(question, quiz.getCreator(), quiz.getName());
			break;
		case 1: addQuestion1(question, quiz.getCreator(), quiz.getName());
			break;
		case 2: addQuestion2(question, quiz.getCreator(), quiz.getName());
			break;
		case 3: addQuestion3(question, quiz.getCreator(), quiz.getName());
			break;
		case 4: addQuestion4(question, quiz.getCreator(), quiz.getName());
			break;
		case 5: addQuestion5(question, quiz.getCreator(), quiz.getName());
			break;
		case 6: addQuestion6(question, quiz.getCreator(), quiz.getName());
			break;
		case 7: addQuestion7(question, quiz.getCreator(), quiz.getName());
			break;
		case 8: addQuestion8(question, quiz.getCreator(), quiz.getName());
			//	break;
	

		}
	} 
	
	
	public Set<Question> getQuestions(Quiz quiz){
			Set<Question> questions = new HashSet<Question>();
			String userName = quiz.getCreator();
			String quizName = quiz.getName();
			Set<ResponseQuestion> responseQuestions = getResponseQuestions(userName, quizName);
			for(ResponseQuestion rq : responseQuestions)
				questions.add(rq);
			
			Set<MatchingQuestion> matchingQuestions = getMatchingQuestions(userName, quizName);
			for(MatchingQuestion mq : matchingQuestions)
				questions.add(mq);
			
			Set<EstimateQuestion> estimateQuestions = getEstimateQuestions(userName, quizName);
			for(EstimateQuestion eq: estimateQuestions)
				questions.add(eq);
			
			Set<MultipleChoice> multipleChoices = getMultipleChoices(userName, quizName);
			for(MultipleChoice mc : multipleChoices)
				questions.add(mc);
			
			Set<FillBlankQuestion> fillBlanks = getFillBlanks(userName, quizName);
			for(FillBlankQuestion fbq: fillBlanks)
				questions.add(fbq);
			
			Set<PictureResponseQuestion> pictureResponses = getPictureResponses(userName, quizName);
			for(PictureResponseQuestion prq : pictureResponses)
				questions.add(prq);
			
			Set<MultiAnswerQuestion> multiAnswers = getMultiAnswerQuestions(userName, quizName);
			for(MultiAnswerQuestion maq : multiAnswers)
				questions.add(maq);
			
			Set<MultiChoiceMultiAnswer> multiChoiceMultiAnswer = getMultiChoiceMultiAnswers(userName, quizName);
			for(MultiChoiceMultiAnswer mcma : multiChoiceMultiAnswer)
				questions.add(mcma);
			
			Set<RandomMathQuestion> randomMathQuestions = getRandomMathQuestions(userName, quizName);
			for(RandomMathQuestion rmq : randomMathQuestions)
				questions.add(rmq);
			
			Set<GradedQuestion> gradedQuestions = getGradedQuestions(userName, quizName);
			for(GradedQuestion gq : gradedQuestions)
				questions.add(gq);
			
			return questions;
			
	}
	
	public void updateQuestion(Question question, Quiz quiz, int index){
		int type = question.type();
		String command = "DELETE FROM question"+type+" WHERE number = "+index+";";
		executeUpdate(command);
		addQuestion(quiz, question);
	}
	
	
	
	/**
	 * Takes in a Quiz object and removes it from the database
	 * If the quiz is not in the database, just returns
	 * @param quiz
	 */
	public void removeQuiz(Quiz quiz){
		String user = quiz.getCreator();
		if(!this.containsQuiz(quiz.getName(), user))
			return;
		int numQuizzes = getNumQuizzes();
		numQuizzes --;
		String command = "UPDATE administrator SET numQuizzes = "+numQuizzes+";";
		executeUpdate(command);
		
		command = "DELETE FROM quizzes WHERE quizName = ? AND userName = ?";
		String[] list = {quiz.getName(), quiz.getCreator()};
		executeStringUpdate(command, list);
		
		for(int i = 0; i < Question.NUM_QUESTION_TYPES; i ++){
			command = "DELETE FROM question"+i+" WHERE quizName = ? AND userName = ?";
			executeStringUpdate(command, list);

		}
	}
	
	
	
}
