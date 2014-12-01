package questions;

public class EstimateQuestion implements Question {

	private String question;
	private int answer;
	private int type;
	private int number;
	private int worth;
	
	public EstimateQuestion(String question, int answer, int number, int worth) {
		this.type = ESTIMATE;
		this.question = question;
		this.answer = answer;
		this.number = number;
		this.worth = worth;
	}
	
	public double getPoints(String userGuess) {
		double points = 0;
		double guess = Double.parseDouble(userGuess);
		
		if (guess == answer) points = 1;
		else if (guess >= 0.9*answer && guess <= 1.1*answer) points = 2.0/3;
		else if (guess >= 0.75*answer && guess <=1.25*answer) points = 1.0/3;
		
		return points*worth;
	}

	@Override
	public String getQuestion() {
		return question;
	}

	public int getAnswer() {
		return answer;		
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
