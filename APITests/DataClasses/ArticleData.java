package DataClasses;

import java.util.ArrayList;

import com.google.gson.JsonArray;

public class ArticleData {

	
	public String sort;
	public String type;
	public String title;
	public JsonArray videos;
	public String usOnly;
	public String baseName;
	public String moduleID;
	public String articleID;
	public String mainImage;
	public String menuAudio;
	public String texasOnly;
	public String languageID;
	public RangeMapData rangeMapData;
	public JsonArray translations;
	public ArrayList<PGOScreen> articleScreens;
	public ArrayList<SymbolData> articleSymbols;
	public ArrayList<LandmarkData> articleLandmarks;
	public ArrayList<TimelineData> articleTimelines;
	public String languageString;
	public JsonArray relatedArticles;
	public ArrayList<ArticleGlossaryData> glossaries;
	public String soundFile;
	public ActivityData activityData;
	public ArrayList<DocumentData> pgnDocuments;
	public SongData song;
	public ArrayList<PGNFact> pgnFacts;
	public PGNRecipeData recipe;
	public ArrayList<PGNPersonsData> pgnPersons;
	public JsonArray pgoPersons;
	
	
	public ArticleData(){
		
	}
	
	public void setSort(String s){
		sort = s;
	}
	
	public void setType(String t){
		type = t;
	}
	
	public void setTitle(String t){
		title = t;
	}
	
	public void setVideos(JsonArray v){
		videos = v;
	}
	
	public void setUSOnly(String u){
		usOnly = u;
	}
	
	public void setBaseName(String b){
		baseName = b;
	}
	
	public void setModuleID(String m){
		moduleID = m;
	}
	
	public void setArticleID(String a){
		articleID = a;
	}
	
	public void setMainImage(String m){
		mainImage = m;
	}
	
	public void setMenuAudio(String m){
		menuAudio = m;
	}
	
	public void setTexasOnly(String t){
		texasOnly = t;
	}
	
	public void setLanduageID(String l){
		languageID = l;
	}
	
	public void setMapFileName(RangeMapData m){
		rangeMapData = m;
	}
	
	public void setTranslations(JsonArray t){
		translations = t;
	}
	
	public void setArticleScreens(ArrayList<PGOScreen> arrayList){
		articleScreens = arrayList;
	}
	
	public void setArticleSymbols(ArrayList<SymbolData> a){
		articleSymbols = a;
	}
	
	public void setLanguageISOString(String l){
		languageString = l;
	}
	
	public void setRelatedArticles(JsonArray r){
		relatedArticles = r;
	}
	
	public void setGlossaries(ArrayList<ArticleGlossaryData> g){
		glossaries = g;
	}
	
	public void setSoundFile(String s){
		soundFile = s;
	}
	
	public void setArticleLandmarks(ArrayList<LandmarkData> land){
		articleLandmarks = land;
	}
	
	public void setArticleTimelines(ArrayList<TimelineData> a){
		articleTimelines = a;
	}
	
	public void setActivityData(ActivityData a){
		activityData = a;
	}
	
	public void setPGNDocumentData(ArrayList<DocumentData> doc){
		pgnDocuments = doc;
	}
	
	public void setPGNSong(SongData s){
		song = s;
	}
	
	public void setPGNFacts(ArrayList<PGNFact> pgn){
		pgnFacts = pgn;
	}
	
	public void setPGNRecipe(PGNRecipeData r){
		recipe = r;
	}
	
	public void setPGNPersonsData(ArrayList<PGNPersonsData> persons){
		pgnPersons = persons;
	}
	
	public void setArticlePGOPersons(JsonArray persons){
		pgoPersons = persons;
	}
}
