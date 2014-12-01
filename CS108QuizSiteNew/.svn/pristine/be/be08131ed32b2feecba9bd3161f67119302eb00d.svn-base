package questions;

import java.util.Random;

public class RandomMathQuestion implements Question {

	public static final int EASY = 0;
	public static final int MEDIUM = 1;
	public static final int HARD = 2;
	
	private int type;
	private int number;
	private int worth;
	private int difficulty;
	private char op;
	private double num1;
	private double num2;
	private double answer;
	
	public RandomMathQuestion(int difficulty, int number, int worth) {
		this.number = number;
		this.worth = worth;
		this.difficulty = difficulty;
		this.type = RANDOM_MATH;
		
		Random random = new Random();
		
		if (difficulty == HARD) {
			op = getOp(random.nextInt(4));
			num1 = random.nextDouble() * 1000;
			num2 = random.nextDouble() * 1000;
		}
		
		else if (difficulty == MEDIUM) {
			op = getOp(random.nextInt(3));
			num1 = random.nextDouble() * 100;
			num2 = random.nextDouble() * 100;
		}
		
		else { // If difficulty isn't HARD or MEDIUM, default to EASY
			op = getOp(random.nextInt(2));
			num1 = random.nextInt(20);
			num2 = random.nextInt((int) num1);
		}
		
		switch (op) {
			case '+': 
				answer = num1 + num2;
				break;
			case '-': 
				answer = num1 - num2;
				break;
			case 'X': 
				answer = num1 * num2;
				break;
			case '/': 
				answer = num1 / num2;
				break;
		}
	}
	
	private char getOp(int index) {
		switch (index) {
			case 0: return '+';
			case 1: return '-';
			case 2: return 'X';
			case 3: return '/';
			default: return '+';
		}		
	}
	
	public double getPoints(String userGuess) {
		double guess = Double.parseDouble(userGuess);
		
		if (guess == answer) return worth;
		
		else return 0;
	}

	@Override
	public String getQuestion() {
		String question = "";
		question += num1;
		question += op;
		question += num2;
		return question;
	}
	
	public double getAnswer() {
		return answer;
	}
	
	public int getDifficulty() {
		return difficulty;
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
