package SpecificGameData;

import java.util.ArrayList;

public class ZoomAssetData extends SpecificGameData{
	public String image;
	public ArrayList<String> answers;
	public String correctAnswer;
	public String question;
	
	public ZoomAssetData(){
		
	}
	
	public void setCorrectAnswer(String c){
		correctAnswer = c;
	}
	
	public void setQuestion(String q){
		question = q;
	}
	
	public void setAnswers(ArrayList<String> a){
		answers = a;
	}
}
