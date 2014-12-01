package quizzes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import dataBase.DataBase;
import questions.*;

public class CreateQuiz {
	static ArrayList <String> questions = new ArrayList<String>(Arrays.asList("Bitches?", "Dominicans?"));
	static ArrayList<String> answers = new ArrayList<String>(Arrays.asList("yea", "fuckno"));
	static String name = "FIRSTZUIZZ";
	
	public CreateQuiz(){
		
	}
	
	public static void main(String args[]){
		System.out.println(questions);
		System.out.println(answers);
		System.out.println(questions.get(1));
		System.out.println(answers.get(1));
		CreateQuiz creator = new CreateQuiz();
		DataBase db = new DataBase();
		Quiz newQuiz = new Quiz();
		//public ResponseQuestion(String question, ArrayList<String> answers, int number, int worth)
		for(int i = 0; i < questions.size(); i++){
			ArrayList<String> ans = new ArrayList<String>();
			ans.add(answers.get(i));
			newQuiz.addQuestion(new ResponseQuestion(questions.get(i), ans, i, 1));
		}
		newQuiz.setCreated(new Date());
		newQuiz.setCreator("gandalf");
		newQuiz.setDescription("ahoymatey");
		newQuiz.setImmediateCorrection(false);
		newQuiz.setMultiplePages(true);
		newQuiz.setName(name);
		newQuiz.setNumPlays(0);
		newQuiz.setPracticeMode(true);
		newQuiz.setRandomized(false);
		newQuiz.setTag("bruh");
		newQuiz.setType("quiz");
		db.addQuiz(newQuiz);
	}
	
	
	
	
}
