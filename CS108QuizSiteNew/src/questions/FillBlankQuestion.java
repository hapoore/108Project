package questions;

import java.util.ArrayList;

public class FillBlankQuestion implements Question {

	private String question;
	private ArrayList<String> answers;
	private int type;
	private int number;
	private int worth;
	
	
	public FillBlankQuestion(String question, ArrayList<String> answers, int number, int worth) {
		for (int i=0; i<answers.size(); i++) 
			answers.set(i, answers.get(i).toUpperCase());
		
		this.type = FILL_BLANK;
		this.question = question;
		this.answers = answers;
		this.number = number;
		this.worth = worth;
	}
	
	public double getPoints(String guess) {
		guess = guess.toUpperCase();
		
		for (int i=0; i< answers.size(); i++) {
			if (answers.get(i).equals(guess)) return worth;
		}
		
		return 0;
	}

	@Override
	public String getQuestion() {
		return question;
	}

	public ArrayList<String> getAnswers() {
		return answers;
	}

	@Override
	public int type() {
		return type;
	}
	
	@Override
	public int getNumber() {
		return number;
	}

	@Override
	public int getWorth() {
		return worth;
	}

}
