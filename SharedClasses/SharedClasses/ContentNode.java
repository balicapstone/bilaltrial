package SharedClasses;

import java.util.ArrayList;

import com.google.gson.JsonArray;

public class ContentNode {

	public String title;
	public Boolean isTitleCorrect;
	public String type;
	public Boolean isTypeCorrect;
	public String id;
	public Boolean isIDCorrect;
	public String baseName;
	public Boolean isBaseNameCorrect;
	public String mainImage;
	public Boolean isMainImageCorrect;
	public String menuAudio;
	public Boolean isMenuAudioCorrect;
	public String path;
	public ArrayList<ContentNode> childrenContent;
	public JsonArray childrenJson;
	
	
	public ContentNode(){
		childrenContent = new ArrayList<ContentNode>();
	}
	
	public ContentNode(String newTitle, String newType, String newId, String newBaseName, String newMainImage, String newMenuAudio, String newPath, ArrayList<ContentNode> newChildrenContent, JsonArray newChildrenJson){
		title = newTitle;
		type = newType;
		id = newId;
		baseName = newBaseName;
		mainImage = newMainImage;
		menuAudio = newMenuAudio;
		path = newPath;
		childrenContent = newChildrenContent;
		childrenJson = newChildrenJson;
	}
	
	public ContentNode copyNode(){
		return new ContentNode(this.title, this.type, this.id, this.baseName, this.mainImage, this.menuAudio, this.path, this.childrenContent, this.childrenJson);
	}
	
	public void addContentToChildren(ContentNode c){
		childrenContent.add(c);
	}
	
	public ArrayList<ContentNode> getChildren(){
		return childrenContent;
	}
	
	public void setChildrenJson(JsonArray children){
		childrenJson = children;
	}
	
	public JsonArray getChildrenJson(){
		return childrenJson;
	}
	
	public void setPath(String p){
		path = p;
	}
	
	public String getPath(){
		return path;
	}
	
	public void setTitle(String t){
		title = t;
	}
	
	public void setType(String t){
		type = t;
	}
	
	public void setID(String i){
		id = i;
	}
	
	public void setBaseName(String n){
		baseName = n;
	}
	
	public void setMainImage(String m){
		mainImage = m;
	}
	
	public void setMenuAudio(String a){
		menuAudio = a;
	}
	
	public String getTitle(){
		return title;
	}
	
	public String getType(){
		return type; 
	}
	
	public String getID(){
		return id;
	}
	
	public String getBaseName(){
		return baseName;
	}
	
	public String getMainImage(){
		return mainImage;
	}
	
	public String getMainAudio(){
		return menuAudio;
	}
	
	public void checkTitle(String t){
		isTitleCorrect = title.equals(t);
	}
	
	public void checkType(String t){
		isTypeCorrect = type.equals(t);
	}
	
	public void checkID(String i){
		isIDCorrect = id.equals(i);
	}
	
	public void checkBaseName(String b){
		isBaseNameCorrect = baseName.equals(b);
	}
	
	public void checkMainImage(String m){
		isMainImageCorrect = mainImage.equals(m);
	}
	
	public void checkMenuAudio(String m){
		isMenuAudioCorrect = menuAudio.equals(m);
	}
	
	public void setTitleCorrect(Boolean bool){
		isTitleCorrect = bool;
	}
	
	public void setTypeCorrect(Boolean bool){
		isTypeCorrect = bool;
	}
	
	public void setIDCorrect(Boolean bool){
		isIDCorrect = bool;
	}
	
	public void setBaseNameCorrect(Boolean bool){
		isBaseNameCorrect = bool;
	}
	
	public void setMainImageCorrect(Boolean bool){
		isMainImageCorrect = bool;
	}
	
	public void setMenuAudioCorrect(Boolean bool){
		isMenuAudioCorrect = bool;
	}
	
	public Boolean isTitleCorrect(){
		return isTitleCorrect;
	}
	
	public Boolean isTypeCorrect(){
		return isTypeCorrect;
	}
	
	public Boolean isIDCorrect(){
		return isIDCorrect;
	}
	
	public Boolean isBaseNameCorrect(){
		return isBaseNameCorrect;
	}
	
	public Boolean isMainImageCorrect(){
		return isMainImageCorrect;
	}
	
	public Boolean isMenuAudioCorrect(){
		return isMenuAudioCorrect;
	}
}
