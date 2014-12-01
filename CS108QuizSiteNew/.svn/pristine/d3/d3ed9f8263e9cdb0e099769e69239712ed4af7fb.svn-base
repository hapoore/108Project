package questions;

public class GradedQuestion implements Question {

	private int type;
	private int number;
	private double points;
	private int worth;
	private String question;
	
	public GradedQuestion(String question, int number, int worth) {
		this.type = GRADED;
		this.number = number;
		this.worth = worth;
		this.points = 0;
		this.question = question;
	}
	
	public void setPoints(double points) {
		this.points = points;
	}
	
	public double getPoints(String guess) {
		return points;
	}

	@Override
	public String getQuestion() {
		return question;
	}

	@Override
	public int getNumber() {
		return number;
	}

	@Override
	public int type() {
		return type;
	}

	@Override
	public int getWorth() {
		return worth;
	}

}
