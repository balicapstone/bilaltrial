package DataClasses;

import java.util.ArrayList;

public class QOTDQuestionsData {
	public String id;
	public String question;
	public ArrayList<QOTDAnswersData> answers;
	
	public QOTDQuestionsData(String i, String q){
		id = i;
		question = q;
	}
	
	public QOTDQuestionsData(String i, String q, ArrayList<QOTDAnswersData> a){
		id = i;
		question = q;
		answers = a;
	}
	
	public void addAnswer(QOTDAnswersData answer){
		answers.add(answer);
	}
	
}
