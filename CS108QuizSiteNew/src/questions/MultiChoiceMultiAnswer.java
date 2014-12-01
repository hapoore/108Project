package questions;

import java.util.ArrayList;

public class MultiChoiceMultiAnswer implements Question {

	private int type;
	private int number;
	private int worth;
	private String question;
	private ArrayList<String> correct;
	private ArrayList<String> choices;
	
	public MultiChoiceMultiAnswer(String question, ArrayList<String> correctAnswers, ArrayList<String> allChoices, int number, int worth) {
		this.type = MULTI_MULTI;
		this.number = number;
		this.worth = worth;
		this.question = question;
		this.correct = correctAnswers;
		this.choices = allChoices;
	
	}
	
	public double getPoints(ArrayList<String> guess) {
		double points = 0, total = 0;
		
		for (int i=0; i<choices.size(); i++) {
			if (correct.contains(choices.get(i)) && guess.contains(choices.get(i))) points++;
			else if (!correct.contains(choices.get(i)) && !guess.contains(choices.get(i))) points++;
			
			total++;
		}
		
		return (points / total) * worth;
	}

	@Override
	public String getQuestion() {
		return question;
	}
	
	public ArrayList<String> getAnswers() {
		return correct;
	}
	
	public ArrayList<String> getAllChoices() {
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
