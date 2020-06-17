package DataClasses;

import java.util.ArrayList;

public class GlossaryData {

	public String word;
	public String baseWord;
	public String timefile;
	public String audioFile;
	public String description;
	public String glossaryID;
	public ArrayList<String> descriptionTimeCode;
	
	public GlossaryData(String w, String bW, String tF, String aF, String d, String gID, ArrayList<String> dTC){
		word = w;
		baseWord = bW;
		timefile = tF;
		audioFile = aF;
		description = d;
		glossaryID = gID;
		descriptionTimeCode = dTC;
	}
}
