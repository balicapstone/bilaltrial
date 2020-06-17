package DataClasses;

import java.util.ArrayList;

public class TimelineData {
	public String startDate;
	public String endDate;
	public String timelineID;
	public String audioFile;
	public String timecodeFile;
	public String description;
	public ArrayList<String> dateTimecode;
	public ArrayList<String> descriptionTimecode;
	
	public TimelineData(String start, String end, String id, String des, String audio, String timeFile, ArrayList<String> time, ArrayList<String> descriptionTime){
		startDate = start;
		endDate = end;
		timelineID = id;
		description = des;
		audioFile = audio;
		timecodeFile = timeFile;
		dateTimecode = time;
		descriptionTimecode = descriptionTime;
	}
}
