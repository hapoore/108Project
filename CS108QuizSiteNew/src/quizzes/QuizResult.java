package quizzes;
import java.util.Date;

public class QuizResult implements Comparable<QuizResult>{
	public String quizName;
	public String user;
	public int score;
	public double percentage;
	public Date date;
	public boolean highScore;
	public double timeNeeded;
	
	//	QuizResult result = new QuizResult(String user,int score,double percentage,Date time, int timeNeeded);
	
	public QuizResult(String quizName, String user, int score, double percentage, Date date, double timeNeeded){
		this.quizName = quizName;
		this.score = score;
		this.user = user;
		this.percentage = percentage;
		this.date = date;
		this.timeNeeded = timeNeeded;
	}
	
	public String toString(){
		return user + " took " + quizName + " and scored " + score + " points on " + date;
		
	}
	
	public String onScreen(){
		return "Score: " + score + "<br><br>" + "Percentage: " + percentage + "<br><br>" + "Time Needed: " + timeNeeded/1000 + "<br><br>" + "Highscore: " + highScore;
	}

	@Override
	public int compareTo(QuizResult other) {
		if(score > other.score)
			return -1;
		if(score < other.score)
			return 1;
		if(timeNeeded > other.timeNeeded)
			return 1;
		if(timeNeeded < other.timeNeeded)
			return -1;
		return 0;
	}
	
	public String getUser(){
		return user;
	}
	
	public int getScore(){
		return score;
	}
	
	public double getPercentage(){
		return percentage;
	}
	
	public Date getDate(){
		return date;
	}
	
	public double getTimeNeeded(){
		return timeNeeded;
	}
	
	public void setHighScore(boolean isHigh){
		highScore = isHigh;
	}
	
	public boolean getHighSCore(){
		return highScore;
	}
	public String getQuizName(){
		return quizName;
	}
	public void setQuizName(String name){
		this.quizName = name;
	}
}