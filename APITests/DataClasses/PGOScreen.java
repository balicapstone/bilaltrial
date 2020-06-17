package DataClasses;

import java.util.ArrayList;

public class PGOScreen {
	public String title;
	public ArrayList<Image> images;
	public String number;
	public String content;
	public String layoutID;
	public String screenID;
	public String timeFile;
	public String audioFile;
	public String titleAudioFile;
	public ArrayList<String> screenTimeCode;
	
	public PGOScreen(){
		
	}
	
	public void setTitle(String t){
		title = t;
	}
	
	public void setImages(ArrayList<Image> i){
		images = i;
	}
	
	public void setNumber(String n){
		number = n;
	}
	
	public void setContent(String c){
		content = c;
	}
	
	public void setLayoutID(String layout){
		layoutID = layout;
	}
	
	public void setScreenID(String screen){
		screenID = screen;
	}
	
	public void setTimeFile(String time){
		timeFile = time;
	}
	
	public void setAudioFile(String audio){
		audioFile = audio;
	}
	
	public void setTitleAudioFile(String titleAudio){
		titleAudioFile = titleAudio;
	}
	
	public void setScreenTimeCode(ArrayList<String> timeCodes){
		screenTimeCode = timeCodes;
	}
}
