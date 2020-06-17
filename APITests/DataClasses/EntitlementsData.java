package DataClasses;

import java.util.ArrayList;

public class EntitlementsData {
	public ArrayList<String> ownedContent;
	public ArrayList<String> bannedArticles;
	public ArrayList<String> bannedCategories;
	
	
	public EntitlementsData(ArrayList<String> content, ArrayList<String> articles, ArrayList<String> categories){
		ownedContent = content;
		bannedArticles = articles;
		bannedCategories = categories;
	}
}
