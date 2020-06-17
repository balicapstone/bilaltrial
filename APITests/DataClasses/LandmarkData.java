package DataClasses;

import java.util.ArrayList;

public class LandmarkData {
	public String text;
	public String image;
	public String title;
	public String timeFile;
	public String audioFile;
	public String landmarkID;
	public ArrayList<String> textTimeCoded;
	
	public LandmarkData(){
		
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
	
	public void setTimefile(String t){
		timeFile = t;
	}
	
	public void setAudioFile(String a){
		audioFile = a;
	}
	
	public void setLandmarkID(String l){
		landmarkID = l;
	}
	
	public void setTextTimeCoded(ArrayList<String> time){
		textTimeCoded = time;
	}
}
