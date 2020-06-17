package DataClasses;

import java.util.ArrayList;

public class ModuleData {
	public String name;
	public String image;
	public ArrayList<ContentNode> children;
	public String langTxt;
	public String moduleID;
	public String nameShort;
	public String languageID;
	public String englishModuleID;
	public ArrayList<VideoExperimentData> videos;
	
	
	public ModuleData(){
		
	}
	
	public void setName(String n){
		name = n;
	}
	
	public void setImage(String i){
		image = i;
	}
	
	public void setChildren(ArrayList<ContentNode> child){
		children = child;
	}
	
	public void setLanguageText(String l){
		langTxt = l;
	}
	
	public void setModuleID(String m){
		moduleID = m;
	}
	
	public void setNameShort(String n){
		nameShort = n;
	}
	
	public void setLanguageID(String l){
		languageID = l;
	}
	
	public void setEnglishModuleID(String eMI){
		englishModuleID = eMI;
	}
	
	public void setVideos(ArrayList<VideoExperimentData> v){
		videos = v;
	}
	
	public ContentNode getChildByName(String name){
		ContentNode toReturn = new ContentNode();
		for(ContentNode child : children){
			if(child.title.equals(name)){
				toReturn = child;
			}
		}
		
		return toReturn;
	}
}
