package account;

import quizzes.*;

public class RecentFriendActivity {

	public static int QUIZ_TAKEN = 0;
	public static int QUIZ_CREATED = 420;
	public static int ACHIEVEMENT_EARNED = 69;
	
	public String userName;
	public String quizName;
	public int activity;
	public String quizCreator;
	
	public RecentFriendActivity(String userName, String quizName, String quizCreator, int activity) {
		this.userName = userName;
		this.quizName = quizName;
		this.quizCreator = quizCreator;
		this.activity = activity;
		this.quizCreator = quizCreator;
		//should work now
	}
}
