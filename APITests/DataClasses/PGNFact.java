package DataClasses;

import java.util.ArrayList;

public class PGNFact {
	public String sort;
	public String text;
	public String image;
	public String title;
	public String factID;
	public String timefile;
	public String audioFile;
	public ArrayList<String> textTimecoded;
	public ArrayList<String> titleTimecoded;
	
	
	public PGNFact(){
		
	}
	
	public void setSort(String s){
		sort = s;
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
	
	public void setFactID(String id){
		factID = id;
	}
	
	public void setTimefile(String file){
		timefile = file;
	}
	
	public void setAudioFile(String audio){
		audioFile = audio;
	}
	
	public void setTextTimecoded(ArrayList<String> time){
		textTimecoded = time;
	}
	
	public void setTitleTimecoded(ArrayList<String> time){
		titleTimecoded = time;
	}
}
