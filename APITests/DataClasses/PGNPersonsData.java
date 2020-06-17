package DataClasses;

import java.util.ArrayList;

public class PGNPersonsData {
	public String text;
	public String image;
	public String person;
	public String personID;
	public String timeFile;
	public String audioFile;
	public ArrayList<String> textTimecoded;
	
	public PGNPersonsData(){
		
	}
	
	public void setText(String t){
		text = t;
	}
	
	public void setImage(String i){
		image = i;
	}
	
	public void setPerson(String p){
		person = p;
	}
	
	public void setPersonID(String id){
		personID = id;
	}
	
	public void setTimeFile(String time){
		timeFile = time;
	}
	
	public void setAudioFile(String audio){
		audioFile = audio;
	}
	
	public void setTextTimecoded(ArrayList<String> timecoded){
		textTimecoded = timecoded;
	}
}
