package SharedClasses;

public enum LogTypes {
	ACTIVITY("activity"),
	ARTICLECLICK("article", "action_name:click"),
	ARTICLEVIEW("article", "action_name:view"),
	ARTICLENAV("article_nav"),
	BREADCRUMBS("breadcrumbs"),
	CATEGORYCLICK("category", "action_name:click"),
	CATEGORYVIEW("category", "action_name:view"),
	CITATION("citation"),
	DICTIONARY("dictionary"),
	GAMES("game"),
	GLOSSARY("glossary"),
	HAMBURGER("hamburger"),
	IMAGEMODALBUTTON("image_modal_button"),
	IMAGEMODALIMAGE("image_modal_image"),
	LOGINUSERNAME("login", "login_type:username"), 
	LOGINSQS("login", "login_type:sqs"),
	LOGO("logo"),
	MODULECLICK("module", "action_name:click"),
	MODULECLICKFROMHAMBURGER("module", "action_name:click%20AND%20from:hamburger"),
	MODULEVIEW("module", "action_name:view"), 
	POLL("poll"), 
	PRINT("print"),
	RELATEDARTICLE("related_article"),
	TEACHERRESOURCES("teacher_resource"),
	TERMINOLOGY("terminology"),
	VIDEOEXPERIMENTSPAGE("video_experiments_page"),
	VIDEOEXPERIMENTMODAL("video_experiments_modal");
	
	String indexName;
	String searchTerms;
	
	private LogTypes(String index){
		indexName = index;
	}
	
	private LogTypes(String index, String search){
		indexName = index;
		searchTerms = search;
	}
	
	public String getName(){
		return this.indexName;
	}
	
	public String getSearch(){
		return this.searchTerms;
	}
}
