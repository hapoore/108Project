package dataBase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import account.*;
import org.junit.Test;

import questions.Question;
import questions.ResponseQuestion;
import quizzes.Quiz;

public class dataBaseTester {

	public DataBase db;
	
	
	public dataBaseTester(){
		db = new DataBase();
	}
	
	/*@Test
	public void Test1(){
		Account account = new Account("userName", "password","salt");
		db.addAccount(account);
		db.removeAccount(account.getUserName());
		
		Account account2 = new Account("userName2", "password", "salt");
		db.addAccount(account2);
		db.removeAccount(account2.getUserName());
	}*/
	
	@Test
	public void Test2(){
		Account account = new Account("userName", "password","salt");
		db.addAccount(account);
		
		String question = "Who won WWII";
		ArrayList<String> answers = new ArrayList<String>();
		answers.add("AMERICA");
		answers.add("NOT GERMANY");
		Quiz quiz = new Quiz();
		quiz.setTag("tag2");
		quiz.setCreator(account.getUserName());
		quiz.setDescription("description");
		quiz.setImmediateCorrection(false);
		quiz.setMultiplePages(false);
		quiz.setName("quiz");
		quiz.setCreated(new Date());
		quiz.setPracticeMode(false);
		quiz.setRandomized(false);
		
		
		Question q = new ResponseQuestion(question, answers, 0, 5);
		quiz.addQuestion(q);
		//account.addQuizCreated(quiz);
		db.addQuiz(quiz);
		Quiz quiz1 = db.getQuiz(quiz.getName(), account.getUserName());
		String qui = quiz1.getCreator();
	}
	
}