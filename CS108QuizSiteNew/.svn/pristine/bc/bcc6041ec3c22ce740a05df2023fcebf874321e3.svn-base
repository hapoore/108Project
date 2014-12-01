package account;

public class Achievement {

	public static String AMATEUR_AUTHOR = "(5)Amateur Author[http://www.photosinbox.com/download/blue-pencil-icon.jpg]";
	public static String PROLIFIC_AUTHOR = "(10)Prolific Author[http://api.ning.com/files/f3b0PLHghvI-84Uuq78dyLn*VFrEZSmbkh5CUjUiayEWRKk*X-TbBjBoF5H-uiHzOIVaZLY8sItndUg11Tjox2R-lwLOT*d9/fountain_pen08fountainpens530.jpg]";
	public static String PRODIGIOUS_AUTHOR = "(30)Prodigious Author[http://colleenanderson.files.wordpress.com/2010/12/globe-and-quill.jpg]";
	public static String QUIZ_MACHINE = "(20)Quiz Machine[http://media.filmschoolrejects.com/images/terminator-eyes.jpg]";
	public static String I_AM_THE_GREATEST = "(25)I am the Greatest[http://www.mlrehab.com/inmotion/wp-content/uploads/2013/02/Man-on-top-of-mountain-silhouette_4.5x3@300.jpg]";
	public static String PRACTICE_MAKES_PERFECT = "(10)Practice Makes Perfect[http://ibxwalkthetalk.com/wp-content/uploads/2012/01/practice-makes-perfect2.jpg]";
	
	private int points;
	private String name;
	private String url;
	
	public Achievement(String info) {
		this.points = Integer.parseInt(info.substring(info.indexOf("(")+1,info.indexOf(")")));
		this.url = info.substring(info.indexOf("[")+1, info.indexOf("]"));
		this.name = info.substring(info.indexOf(")")+1, info.indexOf("["));
	}
	
	public Achievement(String name, int score, String url) {
		this.name = name;
		this.points = score;
		this.url = url;
	}
	
	public String getName() {
		return name;
	}
	
	public int getPoints() {
		return points;
	}
	
	public String getURL() {
		return url;
	}
}
