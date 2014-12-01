package questions;

import java.util.ArrayList;

public class PictureResponseQuestion implements Question {
	
	private String question;
	private String url;
	private ArrayList<String> answers;
	private int type;
	private int number;
	private int worth;
	
	/**
	 * Constructor for a PictureResponseQuestion.
	 * @param url where the picture can be found on the web
	 * @param answers ArrayList containing all acceptable answers
	 * @param number position in the quiz
	 */
	public PictureResponseQuestion(String question, String url, ArrayList<String> answers, int number, int worth) {
		this.question = question;
		this.url = url;
		this.answers = answers;
		this.type = PICTURE;
		this.number = number;
		this.worth = worth;
	}

	public double getPoints(String guess) {
		
		if (answers.contains(guess)) return worth;
		
		else return 0;
	}

	@Override
	public String getQuestion() {
		return question;
	}
	
	public ArrayList<String> getAnswers() {
		return answers;
	}
	
	public String getURL() {
		return url;
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
