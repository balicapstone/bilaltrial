package LearnMorePages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import CITests.CIPages.CILoginPage;

public class LearnMoreAboutPage extends LearnMoreBasePage{
	public By overViewLink = By.id("nav-features");
	public By requirementsLink = By.id("nav-requirements");
	public By researchLibraryLink = By.id("nav-researchlibrary");
	public By faqLink = By.id("nav-faq");
	public By helpDocumentsLink = By.id("nav-helpdocs");
	public By salesRepLink = By.xpath("//*[@id='menu']/div/b/a");
	public By loginButton = By.id("log-out");
	public FAQModal faq;
	public HelpDocumentsModal help;
	public OverviewModal over;
	public RequirementsModal requirements;
	public ResearchLibraryModal research;
	
	public By[] initialVisibleElements = {overViewLink, requirementsLink, researchLibraryLink, faqLink,
			helpDocumentsLink, salesRepLink, loginButton, aboutCapstoneLink, privacyPolicyLink, termsOfUseLink, licenseAgreementLink, 
			contactUsTop, contactUsBottom, educatorsLink, capstoneLogoTop, capstoneLogoBottom, revisionText};
			
	public LearnMoreAboutPage(WebDriver driver){
		pageDriver = driver;
		over = new OverviewModal(pageDriver);
	}
	
	public By[] getInitialVisibleElements(){
		return initialVisibleElements;
	}
	
	public LearnMoreAboutPage clickOverViewLink(){
		click(overViewLink);
		over = new OverviewModal(pageDriver);
		return this;
	}
	
	public LearnMoreAboutPage clickRequirementsLink(){
		click(requirementsLink);
		requirements = new RequirementsModal(pageDriver);
		return this;
	}
	
	public LearnMoreAboutPage clickResearchLibraryLink(){
		click(researchLibraryLink);
		research = new ResearchLibraryModal(pageDriver);	
		return this;
	}
	
	public LearnMoreAboutPage clickFAQLink(){
		click(faqLink);
		faq = new FAQModal(pageDriver);
		return this;
	}
	
	public LearnMoreAboutPage clickHelpDocumentsLink(){
		click(helpDocumentsLink);
		help = new HelpDocumentsModal(pageDriver);
		return this;
	}
	
	public CILoginPage clickLoginButton(){
		click(loginButton);
		return new CILoginPage(pageDriver);
	}
}
