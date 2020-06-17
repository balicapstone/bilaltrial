package DataClasses;

public class SongData {
	public String info;
	public String text;
	public String title;
	public String songID;
	public String articleID;
	
	public SongData(String thisInfo, String thisText, String thisTitle, String song, String id){
		info = thisInfo;
		text = thisText;
		title = thisTitle;
		songID = song;
		articleID = id;
	}
}
