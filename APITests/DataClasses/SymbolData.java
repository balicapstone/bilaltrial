package DataClasses;

import java.util.ArrayList;

public class SymbolData {
	public String text;
	public String image;
	public String title;
	public String symbolID;
	public String timeFile;
	public String audioFile;
	public ArrayList<String> textTimecoded;
	public ArrayList<String> titleTimecoded;
	
	public SymbolData(){
		
	}
	
	public void setText(String t){
		text = t;
	}
	
	public void setImage(String i){
		image = i;
	}
	
	public void setTitle(String t){
		title = t;
	}
	
	public void setSymbolID(String s){
		symbolID = s;
	}
	
	public void setTimeFile(String t){
		timeFile = t;
	}
	
	public void setAudioFile(String a){
		audioFile = a;
	}
	
	public void setTextTimecoded(ArrayList<String> text){
		textTimecoded = text;
	}
	
	public void setTitleTimecoded(ArrayList<String> title){
		titleTimecoded = title;
	}
}
