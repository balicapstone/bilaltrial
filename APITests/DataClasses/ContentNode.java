package DataClasses;

import java.util.ArrayList;

public class ContentNode {
	public String type;
	public String audio;
	public String image;
	public String title;
	public ArrayList<ContentNode> children;
	public String articleID;
	
	public ContentNode(){
		
	}
	
	public void setType(String t){
		type = t;
	}
	
	public void setAudio(String a){
		audio = a;
	}
	
	public void setImage(String i){
		image = i;
	}
	
	public void setTitle(String t){
		title = t;
	}
	
	public void setChildren(ArrayList<ContentNode> c){
		children = c;
	}
	
	public void setArticleID(String a){
		articleID = a;
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
