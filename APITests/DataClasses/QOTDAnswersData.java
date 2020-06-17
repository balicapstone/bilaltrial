package DataClasses;

public class QOTDAnswersData {
	public String id;
	public String answer;
	public String answerCount;
	public String image;
	
	public QOTDAnswersData(){
		
	}
	
	public QOTDAnswersData(String i, String a, String c){
		id = i;
		answer = a;
		answerCount = c;
	}
	
	public void setID(String i){
		id = i;
	}
	
	public void setAnswer(String a){
		answer = a;
	}
	
	public void setImage(String i){
		image = i;
	}
}
