package questions;

import java.util.ArrayList;

public class MultipleChoice implements Question {

	private String question;
	private String answer;
	private ArrayList<String> choices;
	private int number;
	private int worth;
	private int type;
	
	public MultipleChoice(String question, String answer, ArrayList<String> allChoices, int number, int worth) {
		this.type = MULTIPLE_CHOICE;
		this.number = number;
		this.worth = worth;
		this.question = question;
		this.answer = answer;
		this.choices = allChoices;
	}
	
	@Override
	public String toString() {
		String display = "";
		display += question + '\n';
		display += answer;
		for (int i=0; i<choices.size(); i++) {
			display += '\t' + choices.get(i);
		}
		return display;
	}

	public double getPoints(String guess) {
		if (guess.equals(answer)) return worth;
		
		else return 0;
	}
	
	@Override
	public String getQuestion() {
		return question;
	}
	
	public String getAnswer() {
		return answer;
	}
	
	public ArrayList<String> getChoices() {
		return choices;
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
