package questions;

import java.util.HashMap;
import java.util.Iterator;

public class MatchingQuestion implements Question {

	private String question;
	private HashMap<String, String>	pairs;
	private int number;
	private int worth;
	private int type;
	
	public MatchingQuestion(String question, HashMap<String, String> pairs, int number, int worth) {
		this.type = MATCHING;
		this.number = number;
		this.worth = worth;
		this.question = question;
		this.pairs = pairs;
	}
	
	@Override
	public String toString() {
		String display = "";
		display += question + '\n';
		Iterator<String> keys = pairs.keySet().iterator();
		Iterator<String> values = pairs.values().iterator();
		while (keys.hasNext() && values.hasNext()) {
			display += keys.next() + '\t' + values.next() + '\n';
		}
		
		return display;
	}
	
	public double getPoints(HashMap<String, String> guesses) {
		double total = 0, correct = 0;
		Iterator<String> itr = guesses.keySet().iterator();
		while (itr.hasNext()) {
			String key = itr.next();
			if (pairs.containsKey(key) && guesses.get(key).equals(pairs.get(key)))
				correct++;
			total++;
		}
		
		return (correct / total) * worth;
	}

	@Override
	public String getQuestion() {
		return question;
	}
	
	public HashMap<String, String> getAnswers() {
		return pairs;
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
