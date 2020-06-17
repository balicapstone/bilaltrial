package DataClasses;

public class PGNRecipeData {
	public String text;
	public String audio;
	public String image;
	public String intro;
	public String title;
	public String recipeID;
	
	public PGNRecipeData(){
		
	}
	
	public void setText(String t){
		text = t;
	}
	
	public void setAudio(String a){
		audio = a;
	}
	
	public void setImage(String i){
		image = i;
	}
	
	public void setIntro(String i){
		intro = i;
	}
	
	public void setTitle(String t){
		title = t;
	}
	
	public void setRecipeID(String id){
		recipeID = id;
	}
}
