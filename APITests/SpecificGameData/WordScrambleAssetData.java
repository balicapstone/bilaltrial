package SpecificGameData;

public class WordScrambleAssetData extends SpecificGameData{
	public String image;
	public String correctAnswer;
	public String suggestion;
	
	public WordScrambleAssetData(){
		
	}
	
	public void setCorrectAnswer(String c){
		correctAnswer = c;
	}
	
	public void setSuggestion(String s){
		suggestion = s;
	}
}
