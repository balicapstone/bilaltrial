package DataClasses;

public class VideoExperimentData {
	public String image;
	public String title;
	public String video;
	public String created;
	public String transcript;
	public String videoExperimentID;
	
	public VideoExperimentData(){
		
	}
	
	public void setImage(String i){
		image = i;
	}
	
	public void setTitle(String t){
		title = t;
	}
	
	public void setVideo(String v){
		video = v;
	}
	
	public void setCreated(String c){
		created = c;
	}
	
	public void setTranscript(String t){
		transcript = t;
	}
	
	public void setExperimentID(String id){
		videoExperimentID = id;
	}
}
