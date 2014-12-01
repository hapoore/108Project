package questions;

import java.util.ArrayList;

public class ResponseQuestion implements Question {

	private String question;
	private ArrayList<String> answers;
	private int type;
	private int number;
	private int worth;
	
	public ResponseQuestion(String question, ArrayList<String> answers, int number, int worth) {
		type = RESPONSE;
		this.question = question;
		this.answers = answers;
		this.number = number;
		this.worth = worth;
	
	}

	public double getPoints(String guess) {	
		if (answers.contains(guess)) return worth;
		
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
		// TODO Auto-generated method stub
		return number;
	}

	@Override
	public int getWorth() {
		// TODO Auto-generated method stub
		return worth;
	}

}
