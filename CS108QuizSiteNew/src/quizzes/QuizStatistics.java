package quizzes;
import java.util.*;

public class QuizStatistics{
	double average;
	double median;
	int numResults;
	double fastestTime;
	List<QuizResult> scores;
	
	public QuizStatistics(List<QuizResult> results){
		scores = results;
		if(scores == null)
			scores = new ArrayList<QuizResult>();
		else
			Collections.sort(scores);
		numResults = scores.size();
		setStats();
		
	}
	
	public List<QuizResult> getScores(){
		return scores;
	}
	
	public String toString(){
		return "Average: " + average + "<br><br>" + "Median: " + median + "<br><br>" + "Fastest Time: " + fastestTime/1000 + "<br><br>" + "Times Taken: " + numResults;
	}
	
	public int getRankOf(QuizResult result){
		return scores.indexOf(result) + 1;
	}
	
	public void addResult(QuizResult result){
		scores.add(result);
		numResults++;
		Collections.sort(scores);
		setStats();
	}
	
	public void clear(){
		scores.clear();
		average = 0;
		median = 0;
		fastestTime = 0;
		numResults = 0;
	}
	
	public double getAverage(){
		return average;
	}
	
	public double getMedian(){
		return median;
	}
	
	public void setStats(){
		if(numResults == 0){
			median = 0;
			average = 0;
			fastestTime = 0;
			return;
		}
		findMedian();
		findAverage();
	}
	
	public void findMedian(){
		int middle = numResults/2;
		if(numResults % 2 == 1){
			median = scores.get(middle).score;
			return;
		}
		median =  (double)(scores.get(middle).score + scores.get(middle - 1).score) / 2;
	}
	
	public void findAverage(){
		double sumScores = 0;
		double currFastest = 0;
		for(int i = 0; i < numResults; i++){
			sumScores += scores.get(i).getScore();
			if(currFastest == 0 || scores.get(i).timeNeeded < currFastest)
				currFastest = scores.get(i).timeNeeded;
		}
		fastestTime = currFastest;
		average = sumScores / numResults;
	}
	
	public List<QuizResult> getTimeSorted(){
		List<QuizResult> returnList = new ArrayList<QuizResult>();
		for(int i = 0; i < scores.size(); i++){
			returnList.add(scores.get(i));
		}
		Collections.sort(returnList, new TimeComparator());
		return returnList;
	}
	
	class TimeComparator implements Comparator<QuizResult> {
		@Override
		public int compare(QuizResult o1, QuizResult o2) {
			if(o1.timeNeeded > o2.timeNeeded)
				return 1;
			if(o2.timeNeeded > o1.timeNeeded)
				return -1;
			if(o1.score > o2.score)
				return -1;
			if(o2.score > o1.score)
				return 1;
			return 0;
		}
	}
}