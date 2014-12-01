package questions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

import static org.junit.Assert.*;

//import Question;

import org.junit.Test;

public class QuestionTests {
	
	@Test
	public void EstimateQuestionTest() {
		String eQuestion = "How many countries are there in the world?";
		int eAnswer = 206;
		int worth = 5;
		EstimateQuestion e = new EstimateQuestion(eQuestion, eAnswer, 1, worth);
		
		assertTrue(e.type() == Question.ESTIMATE);
		assertTrue(e.getPoints("206") == worth);
		assertTrue(e.getQuestion().equals(eQuestion));
		System.out.println(e.getNumber() + ". " + e.getQuestion() + " Answer: " + e.getAnswer());
		System.out.println("Guess: 206 \t Points: " + e.getPoints("206"));
		System.out.println("Guess: 203 \t Points: " + e.getPoints("203"));
		System.out.println("Guess: 303 \t Points: " + e.getPoints("303"));
		System.out.println("Guess: 228 \t Points: " + e.getPoints("228"));
		System.out.println();
	}
	
	@Test
	public void FillBlankQuestionTest() {
		String fbQuestion = "President _________ was assasinated in 1963.";
		ArrayList<String> answers = new ArrayList<String>(Arrays.asList("John F. Kennedy","JFK","Kennedy","Jack Kennedy"));
		int worth = 5;
		FillBlankQuestion fb = new FillBlankQuestion(fbQuestion, answers, 2, worth);
		
		assertTrue(fb.type() == Question.FILL_BLANK);
		assertEquals(fb.getQuestion(), fbQuestion);
		assertTrue(worth == fb.getPoints("JFK"));
		System.out.println(fb.getNumber() + ". " + fb.getQuestion() + " Answer: " + fb.getAnswers().get(0));
		System.out.println("Guess: JFK \t Points: " + fb.getPoints("JFK"));
		System.out.println("Guess: jfk \t Points: " + fb.getPoints("jfk"));
		System.out.println("Guess: John Kennedy \t Points: " + fb.getPoints("John Kennedy"));
		System.out.println("Guess: Lincoln \t Points: " + fb.getPoints("Lincoln"));
		System.out.println();

	}
	
	@Test
	public void GradedQuestionTest() {
		// TODO Add in grading system
	}

	@Test
	public void MatchingQuestionTest() {
		HashMap<String, String> pairs = new HashMap<String, String>();
		pairs.put("Ice", "Solid");
		pairs.put("Water", "Liquid");
		pairs.put("Water Vapor", "Gas");
		String key = "";
		ArrayList<String> options = new ArrayList<String>();
		for (Iterator<String> itr = pairs.keySet().iterator(); itr.hasNext(); key=itr.next()) {
			options.add(pairs.get(key));
		}
		
		
//		MatchingQuestion letters = new MatchingQuestion("Match the letters", pairs, 0);
//		System.out.println(letters);
	}
	
	@Test
	public void MultipleChoiceTest() {
		
	}
}
