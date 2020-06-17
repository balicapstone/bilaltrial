package XMLReader;

public class Configuration {
	public Environment environment;
	public Browser browser;
	public Language language;
	
	//Default
	public Configuration(){
		environment = Environment.PROD;
		browser = Browser.CHROME;
		language = Language.ENGLISH;
	}
	
	//Specific
	public Configuration(Environment e, Browser b, Language l){
		environment = e;
		browser = b;
		language = l;
	}
}
