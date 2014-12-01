package questions;

import java.util.ArrayList;

public class MultiAnswerQuestion implements Question {
	
	private String question;
	private ArrayList<ArrayList<String>> answers;
	private int number;
	private int worth;
	private int type;
	private boolean ordered;
	
	public MultiAnswerQuestion(String question, ArrayList<ArrayList<String>> answers, boolean inOrder, int number, int worth) {
		this.type = MULTI_ANSWER;
		this.number = number;
		this.worth = worth;
		this.question = question;
		this.answers = answers;
		this.ordered = inOrder;
	}
	
	public double getPoints(ArrayList<String> guesses) {
		
		if (ordered) return orderedScore(guesses) * worth ;
		
		else return unorderedScore(guesses) * worth;
	}

	private double unorderedScore(ArrayList<String> guesses) {
		double correct = 0, total = 0;
		
		for (int i=0; i<answers.size(); i++) {
			ArrayList<String> altAnswers = answers.get(i);
			total++;
			for (int j=0; j<altAnswers.size(); j++) {
				if (guesses.contains(altAnswers.get(j))) {
					correct++;
					break;
				}
			}
		}
		
		return correct / total;
	}

	private double orderedScore(ArrayList<String> guesses) {
		double correct = 0, total = 0;
		for (int i=0; i<guesses.size(); i++) {
			ArrayList<String> curAnswer = answers.get(i);
			total++;
			if (curAnswer.contains(guesses.get(i))) correct++;
		}
		
		return correct / total;
	}

	@Override
	public String getQuestion() {
		return question;
	}
	
	public ArrayList<ArrayList<String>> getAnswers() {
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
