package Tests;

import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import PGNPages.LoginPage;
import PGNStudentResources.IndiansDatabaseHome;
import PGNStudentResources.ScienceDatabaseHome;
import PGNStudentResources.StatesDatabaseHome;
import PGNStudentResources.StudentHomePage;
import TrueUserTests.TrueUser.Retry;
import UserClasses.User;

public class ContentVerificationTests {
	static LoginPage login;
    static int waitTime = 5;
    static User user; 
    
 	@BeforeClass
    public static void executeBefore(){
    	user = new User(UserClasses.UserInfo.SCIENCESTUDENT);
    	
 		login = new LoginPage(user);
 		user.setCredentials(UserClasses.UserInfo.GARAGESTUDENT);
    	
    	login.login(user);
    }
    
    @AfterClass
    public static void executeAfter(){
    	user.quit();
    }
	
    @Rule
    public TestWatcher restart = new TestWatcher(){
    	@Override
    	public void failed(Throwable e, Description description){
    		try{
    			user.getWatcher().TakeScreenshot(description.getDisplayName());
    		} catch(Exception t){
    			System.out.print("There was a failure while trying to take a screenshot for " + description.getDisplayName());
    		}
    		
        	user.quit();
        	user.makeNewDriver();
        	
        	login = new LoginPage(user);
    	
        	user.setCredentials(UserClasses.UserInfo.GARAGESTUDENT);
        	
        	login.login(user);
    	}
    };
    	
	@Rule
	public Retry retry = new Retry(3);
    	
    @Test
    public void studentGetsImageSource(){
    	StudentHomePage student = new StudentHomePage(user.getDriver());
    	
    	student.waitImplicitly(1);
    	
    	String states = student.getDatabaseEntryImageSrc("States");
    	
    	assertTrue("Asserts that the states image is correct", states.equals("https://assets.pebblego.com/cms_content/images/pgnstates_module_image.jpg")
    			&& student.verifyImageActive(states));
    }
    
    @Test
    public void scienceDatabaseImgIsCorrect(){
    	StudentHomePage student = new StudentHomePage(user.getDriver());
    	
    	student.waitImplicitly(1);
    	
    	String science = student.getDatabaseEntryImageSrc("Science");
    	
    	assertTrue("Asserts that the science image is correct", science.equals("https://assets.pebblego.com/cms_content/images/pgnsci_module_image.jpg")
    			&& student.verifyImageActive(science));
    }
    
    @Test
    public void americanIndiansImgIsCorrect(){
    	StudentHomePage student = new StudentHomePage(user.getDriver());
    	
    	student.waitImplicitly(1);
    	
    	String indians = student.getDatabaseEntryImageSrc("American Indians");
    	
    	assertTrue("Asserts that the science image is correct", indians.equals("https://assets.pebblego.com/cms_content/images/pgnnative_module_image.jpg")
    			&& student.verifyImageActive(indians));
    }
    
    @Test
    public void testScienceCategoryImages(){
    	StudentHomePage student = new StudentHomePage(user.getDriver());
    	
    	student.waitImplicitly(1);
    	ScienceDatabaseHome science = student.goToScience();
    	
    	assertTrue("Asserts the correct image shows up for Earth Science", 
    			science.getDatabaseEntryImageSrc("Earth Science").equals("https://assets.pebblego.com/cms_content/images/pgnsci_earth_science_main_image_article_id_8448.jpg")
    			&& science.verifyImageActive(science.getDatabaseEntryImageSrc("Earth Science")));
    	assertTrue("Asserts the correct image shows up for Life Science", 
    			science.getDatabaseEntryImageSrc("Life Science").equals("https://assets.pebblego.com/cms_content/images/pgnsci_life_science_main_image_article_id_8460.jpg")
    			&& science.verifyImageActive(science.getDatabaseEntryImageSrc("Life Science")));
    	assertTrue("Asserts the correct image shows up for Physical Science", 
    			science.getDatabaseEntryImageSrc("Physical Science").equals("https://assets.pebblego.com/cms_content/images/pgnsci_physical_science_main_image_article_id_8471.jpg")
    			&& science.verifyImageActive(science.getDatabaseEntryImageSrc("Physical Science")));
    	assertTrue("Asserts the correct image shows up for The Field of Science", 
    			science.getDatabaseEntryImageSrc("The Field of Science").equals("https://assets.pebblego.com/cms_content/images/pgnsci_the_field_of_science_main_image_article_id_8478.jpg")
    			&& science.verifyImageActive(science.getDatabaseEntryImageSrc("The Field of Science")));
    	
    	science.clickHomeBreadcrumb();
    }
    
    @Test
    public void testEarthScienceCategoryImages(){
    	StudentHomePage student = new StudentHomePage(user.getDriver());
    	
    	student.waitImplicitly(1);
    	ScienceDatabaseHome science = student.goToScience();
    	science.clickCategory("Earth Science");
    	
    	assertTrue("Asserts the correct image shows up for Atmosphere", 
    			science.getDatabaseEntryImageSrc("Atmosphere").equals("https://assets.pebblego.com/cms_content/images/pgnsci_atmosphere_main_image_article_id_8449.jpg")
    			&& science.verifyImageActive(science.getDatabaseEntryImageSrc("Atmosphere")));
    	assertTrue("Asserts the correct image shows up for Geosphere", 
    			science.getDatabaseEntryImageSrc("Geosphere").equals("https://assets.pebblego.com/cms_content/images/pgnsci_geosphere_main_image_article_id_8451.jpg")
    			&& science.verifyImageActive(science.getDatabaseEntryImageSrc("Geosphere")));
    	assertTrue("Asserts the correct image shows up for Hydrosphere", 
    			science.getDatabaseEntryImageSrc("Hydrosphere").equals("https://assets.pebblego.com/cms_content/images/pgnsci_hydrosphere_main_image_article_id_8455.jpg")
    			&& science.verifyImageActive(science.getDatabaseEntryImageSrc("Hydrosphere")));
    	assertTrue("Asserts the correct image shows up for The Field of Science", 
    			science.getDatabaseEntryImageSrc("Planets and the Solar System").equals("https://assets.pebblego.com/cms_content/images/pgnsci_planets_and_the_solar_system_main_image_article_id_8457.jpg")
    			&& science.verifyImageActive(science.getDatabaseEntryImageSrc("Planets and the Solar System")));
    	
    	science.clickHomeBreadcrumb();
    }
    
    @Test
    public void testLifeScienceCategoryImages(){
    	StudentHomePage student = new StudentHomePage(user.getDriver());
    	
    	student.waitImplicitly(1);
    	ScienceDatabaseHome science = student.goToScience();
    	science.clickCategory("Life Science");
    	
    	assertTrue("Asserts the correct image shows up for Biological Dangers", 
    			science.getDatabaseEntryImageSrc("Biological Dangers").equals("https://assets.pebblego.com/cms_content/images/pgnsci_biological_dangers_main_image_article_id_8461.jpg")
    			&& science.verifyImageActive(science.getDatabaseEntryImageSrc("Biological Dangers")));
    	assertTrue("Asserts the correct image shows up for Genetics", 
    			science.getDatabaseEntryImageSrc("Genetics").equals("https://assets.pebblego.com/cms_content/images/pgnsci_genetics_main_image_article_id_8462.jpg")
    			&& science.verifyImageActive(science.getDatabaseEntryImageSrc("Genetics")));
    	assertTrue("Asserts the correct image shows up for Human Anatomy", 
    			science.getDatabaseEntryImageSrc("Human Anatomy").equals("https://assets.pebblego.com/cms_content/images/pgnsci_human_anatomy_main_image_article_id_8463.jpg")
    			&& science.verifyImageActive(science.getDatabaseEntryImageSrc("Human Anatomy")));
    	assertTrue("Asserts the correct image shows up for Life in an Ecosystem", 
    			science.getDatabaseEntryImageSrc("Life in an Ecosystem").equals("https://assets.pebblego.com/cms_content/images/pgnsci_life_in_an_ecosystem_main_image_article_id_8465.jpg")
    			&& science.verifyImageActive(science.getDatabaseEntryImageSrc("Life in an Ecosystem")));
    	assertTrue("Asserts the correct image shows up for Plants and Animals", 
    			science.getDatabaseEntryImageSrc("Plants and Animals").equals("https://assets.pebblego.com/cms_content/images/pgnsci_plants_and_animals_main_image_article_id_8468.jpg")
    			&& science.verifyImageActive(science.getDatabaseEntryImageSrc("Plants and Animals")));
    	science.clickHomeBreadcrumb();
    }
    
    @Test
    public void testPhysicalScienceCategoryImages(){
    	StudentHomePage student = new StudentHomePage(user.getDriver());
    	
    	student.waitImplicitly(1);
    	ScienceDatabaseHome science = student.goToScience();
    	science.clickCategory("Physical Science");
    	
    	assertTrue("Asserts the correct image shows up for Energy", 
    			science.getDatabaseEntryImageSrc("Energy").equals("https://assets.pebblego.com/cms_content/images/pgnsci_energy_main_image_article_id_8472.jpg")
    			&& science.verifyImageActive(science.getDatabaseEntryImageSrc("Energy")));
    	assertTrue("Asserts the correct image shows up for Forces and Motion", 
    			science.getDatabaseEntryImageSrc("Forces and Motion").equals("https://assets.pebblego.com/cms_content/images/pgnsci_forces_and_motion_main_image_article_id_8473.jpg")
    			&& science.verifyImageActive(science.getDatabaseEntryImageSrc("Forces and Motion")));
    	assertTrue("Asserts the correct image shows up for Matter", 
    			science.getDatabaseEntryImageSrc("Matter").equals("https://assets.pebblego.com/cms_content/images/pgnsci_matter_main_image_article_id_8475.jpg")
    			&& science.verifyImageActive(science.getDatabaseEntryImageSrc("Matter")));
    	assertTrue("Asserts the correct image shows up for Waves", 
    			science.getDatabaseEntryImageSrc("Waves").equals("https://assets.pebblego.com/cms_content/images/pgnsci_waves_main_image_article_id_8477.jpg")
    			&& science.verifyImageActive(science.getDatabaseEntryImageSrc("Waves")));
    	science.clickHomeBreadcrumb();
    }
    
    @Test
    public void testTheFieldofScienceCategoryImages(){
    	StudentHomePage student = new StudentHomePage(user.getDriver());
    	
    	student.waitImplicitly(1);
    	ScienceDatabaseHome science = student.goToScience();
    	science.clickCategory("The Field of Science");
    	
    	assertTrue("Asserts the correct image shows up for Jobs in Science", 
    			science.getDatabaseEntryImageSrc("Jobs in Science").equals("https://assets.pebblego.com/cms_content/images/pgnsci_jobs_in_science_main_image_article_id_8479.jpg")
    			&& science.verifyImageActive(science.getDatabaseEntryImageSrc("Jobs in Science")));
    	assertTrue("Asserts the correct image shows up for Science and Engineering Practices", 
    			science.getDatabaseEntryImageSrc("Science and Engineering Practices").equals("https://assets.pebblego.com/cms_content/images/pgnsci_science_and_engineering_practices_main_image_article_id_8480.jpg")
    			&& science.verifyImageActive(science.getDatabaseEntryImageSrc("Science and Engineering Practices")));
    	science.clickHomeBreadcrumb();
    }
    
    @Test
    public void testAtmosphereCategoryImages(){
    	StudentHomePage student = new StudentHomePage(user.getDriver());
    	
    	student.waitImplicitly(1);
    	ScienceDatabaseHome science = student.goToScience();
    	science.clickCategory("Earth Science");
    	science.clickCategory("Atmosphere");
    	
    	assertTrue("Asserts the correct image shows up for Greenhouse Gases and Global Warming", 
    			science.getDatabaseEntryImageSrc("Greenhouse Gases and Global Warming").equals("https://assets.pebblego.com/cms_content/images/pgnsci_greenhouse_gases_and_global_warming_main_image_article_id_8491.jpg")
    			&& science.verifyImageActive(science.getDatabaseEntryImageSrc("Greenhouse Gases and Global Warming")));
    	assertTrue("Asserts the correct image shows up for Humans and the Atmosphere", 
    			science.getDatabaseEntryImageSrc("Humans and the Atmosphere").equals("https://assets.pebblego.com/cms_content/images/pgnsci_humans_and_the_atmosphere_main_image_article_id_8492.jpg")
    			&& science.verifyImageActive(science.getDatabaseEntryImageSrc("Humans and the Atmosphere")));
    	assertTrue("Asserts the correct image shows up for Layers of the Atmosphere", 
    			science.getDatabaseEntryImageSrc("Layers of the Atmosphere").equals("https://assets.pebblego.com/cms_content/images/pgnsci_layers_of_the_atmosphere_main_image_article_id_8493.jpg")
    			&& science.verifyImageActive(science.getDatabaseEntryImageSrc("Layers of the Atmosphere")));
    	assertTrue("Asserts the correct image shows up for Natural Wonders in the Sky", 
    			science.getDatabaseEntryImageSrc("Natural Wonders in the Sky").equals("https://assets.pebblego.com/cms_content/images/pgnsci_natural_wonders_in_the_sky_main_image_article_id_8494.jpg")
    			&& science.verifyImageActive(science.getDatabaseEntryImageSrc("Natural Wonders in the Sky")));
    	assertTrue("Asserts the correct image shows up for Weather and Climate", 
    			science.getDatabaseEntryImageSrc("Weather and Climate").equals("https://assets.pebblego.com/cms_content/images/pgnsci_weather_and_climate_main_image_article_id_8450.jpg")
    			&& science.verifyImageActive(science.getDatabaseEntryImageSrc("Weather and Climate")));
    	science.clickHomeBreadcrumb();
    }
    
    @Test
    public void testWeatherAndClimateCategoryImages(){
    	StudentHomePage student = new StudentHomePage(user.getDriver());
    	
    	student.waitImplicitly(1);
    	ScienceDatabaseHome science = student.goToScience();
    	science.clickCategory("Earth Science");
    	science.clickCategory("Atmosphere");
    	science.clickCategory("Weather and Climate");
    	
    	assertTrue("Asserts the correct image shows up for Blizzards", 
    			science.getDatabaseEntryImageSrc("Blizzards").equals("https://assets.pebblego.com/cms_content/images/pgnsci_blizzards_main_image_article_id_8483.jpg")
    			&& science.verifyImageActive(science.getDatabaseEntryImageSrc("Blizzards")));
    	assertTrue("Asserts the correct image shows up for Climate Types", 
    			science.getDatabaseEntryImageSrc("Climate Types").equals("https://assets.pebblego.com/cms_content/images/pgnsci_climate_types_main_image_article_id_8486.jpg")
    			&& science.verifyImageActive(science.getDatabaseEntryImageSrc("Climate Types")));
    	assertTrue("Asserts the correct image shows up for Clouds", 
    			science.getDatabaseEntryImageSrc("Clouds").equals("https://assets.pebblego.com/cms_content/images/pgnsci_clouds_main_image_article_id_8487.jpg")
    			&& science.verifyImageActive(science.getDatabaseEntryImageSrc("Clouds")));
    	assertTrue("Asserts the correct image shows up for Droughts", 
    			science.getDatabaseEntryImageSrc("Droughts").equals("https://assets.pebblego.com/cms_content/images/pgnsci_droughts_main_image_article_id_8481.jpg")
    			&& science.verifyImageActive(science.getDatabaseEntryImageSrc("Droughts")));
    	assertTrue("Asserts the correct image shows up for Floods", 
    			science.getDatabaseEntryImageSrc("Floods").equals("https://assets.pebblego.com/cms_content/images/pgnsci_floods_main_image_article_id_8482.jpg")
    			&& science.verifyImageActive(science.getDatabaseEntryImageSrc("Floods")));
    	assertTrue("Asserts the correct image shows up for Forecasting Weather", 
    			science.getDatabaseEntryImageSrc("Forecasting Weather").equals("https://assets.pebblego.com/cms_content/images/pgnsci_forecasting_weather_main_image_article_id_8488.jpg")
    			&& science.verifyImageActive(science.getDatabaseEntryImageSrc("Forecasting Weather")));
    	assertTrue("Asserts the correct image shows up for Hurricanes", 
    			science.getDatabaseEntryImageSrc("Hurricanes").equals("https://assets.pebblego.com/cms_content/images/pgnsci_hurricanes_main_image_article_id_8484.jpg")
    			&& science.verifyImageActive(science.getDatabaseEntryImageSrc("Hurricanes")));
    	assertTrue("Asserts the correct image shows up for Measuring and Mapping Weather", 
    			science.getDatabaseEntryImageSrc("Measuring and Mapping Weather").equals("https://assets.pebblego.com/cms_content/images/pgnsci_measuring_and_mapping_weather_main_image_article_id_8489.jpg")
    			&& science.verifyImageActive(science.getDatabaseEntryImageSrc("Measuring and Mapping Weather")));
    	assertTrue("Asserts the correct image shows up for Tornadoes", 
    			science.getDatabaseEntryImageSrc("Tornadoes").equals("https://assets.pebblego.com/cms_content/images/pgnsci_tornadoes_main_image_article_id_8485.jpg")
    			&& science.verifyImageActive(science.getDatabaseEntryImageSrc("Tornadoes")));
    	assertTrue("Asserts the correct image shows up for Weather Patterns", 
    			science.getDatabaseEntryImageSrc("Weather Patterns").equals("https://assets.pebblego.com/cms_content/images/pgnsci_weather_patterns_main_image_article_id_8490.jpg")
    			&& science.verifyImageActive(science.getDatabaseEntryImageSrc("Weather Patterns")));
    	science.clickHomeBreadcrumb();
    }
    
    @Test
    public void testGeosphereCategoryImages(){
    	StudentHomePage student = new StudentHomePage(user.getDriver());
    	
    	student.waitImplicitly(1);
    	ScienceDatabaseHome science = student.goToScience();
    	science.clickCategory("Earth Science");
    	science.clickCategory("Geosphere");

    	
    	assertTrue("Asserts the correct image shows up for Earth Materials",
    			science.getDatabaseEntryImageSrc("Earth Materials").equals("https://assets.pebblego.com/cms_content/images/pgnsci_earth_materials_main_image_article_id_8452.jpg") &&
    			science.verifyImageActive(science.getDatabaseEntryImageSrc("Earth Materials")));
    	assertTrue("Asserts the correct image shows up for Erosion and Weathering", 
    			science.getDatabaseEntryImageSrc("Erosion and Weathering").equals("https://assets.pebblego.com/cms_content/images/pgnsci_erosion_and_weathering_main_image_article_id_8509.jpg") &&
    			science.verifyImageActive(science.getDatabaseEntryImageSrc("Erosion and Weathering")));
    	assertTrue("Asserts the correct image shows up for Humans and Earth", 
    			science.getDatabaseEntryImageSrc("Humans and Earth").equals("https://assets.pebblego.com/cms_content/images/pgnsci_humans_and_earth_main_image_article_id_8510.jpg") &&
    			science.verifyImageActive(science.getDatabaseEntryImageSrc("Humans and Earth")));
    	assertTrue("Asserts the correct image shows up for Natural Disasters", 
    			science.getDatabaseEntryImageSrc("Natural Disasters").equals("https://assets.pebblego.com/cms_content/images/pgnsci_natural_disasters_main_image_article_id_8453.jpg") &&
    			science.verifyImageActive(science.getDatabaseEntryImageSrc("Natural Disasters")));
    	assertTrue("Asserts the correct image shows up for Natural Resources", 
    			science.getDatabaseEntryImageSrc("Natural Resources").equals("https://assets.pebblego.com/cms_content/images/pgnsci_natural_resources_main_image_article_id_8454.jpg") &&
    			science.verifyImageActive(science.getDatabaseEntryImageSrc("Natural Resources")));
    	assertTrue("Asserts the correct image shows up for Natural Wonders of Earth", 
    			science.getDatabaseEntryImageSrc("Natural Wonders of Earth").equals("https://assets.pebblego.com/cms_content/images/pgnsci_natural_wonders_of_earth_main_image_article_id_8511.jpg") &&
    			science.verifyImageActive(science.getDatabaseEntryImageSrc("Natural Wonders of Earth")));
    	assertTrue("Asserts the correct image shows up for Tectonic Plates", 
    			science.getDatabaseEntryImageSrc("Tectonic Plates").equals("https://assets.pebblego.com/cms_content/images/pgnsci_tectonic_plates_main_image_article_id_8512.jpg") &&
    			science.verifyImageActive(science.getDatabaseEntryImageSrc("Tectonic Plates")));
    	assertTrue("Asserts the correct image shows up for What is the Geosphere?", 
    			science.getDatabaseEntryImageSrc("What Is the Geosphere?").equals("https://assets.pebblego.com/cms_content/images/pgnsci_what_is_the_geosphere?_main_image_article_id_8513.jpg") &&
    			science.verifyImageActive(science.getDatabaseEntryImageSrc("What Is the Geosphere?")));
    	science.clickHomeBreadcrumb();
    }
    
    
    @Test
    public void testEarthMaterialsCategoryImages(){
    	StudentHomePage student = new StudentHomePage(user.getDriver());
    	
    	student.waitImplicitly(1);
    	ScienceDatabaseHome science = student.goToScience();
    	science.clickCategory("Earth Science");
    	science.clickCategory("Geosphere");
    	science.clickCategory("Earth Materials");
    	
    	assertTrue("Asserts the correct image shows up for Fossils", 
    			science.getDatabaseEntryImageSrc("Fossils").equals("https://assets.pebblego.com/cms_content/images/pgnsci_fossils_main_image_article_id_8495.jpg") &&
    			science.verifyImageActive(science.getDatabaseEntryImageSrc("Fossils")));
    	assertTrue("Asserts the correct image shows up for Igneous Rocks", 
    			science.getDatabaseEntryImageSrc("Igneous Rocks").equals("https://assets.pebblego.com/cms_content/images/pgnsci_igneous_rocks_main_image_article_id_8496.jpg") &&
    			science.verifyImageActive(science.getDatabaseEntryImageSrc("Igneous Rocks")));
    	assertTrue("Asserts the correct image shows up for Metamorphic Rocks",     			
    			science.getDatabaseEntryImageSrc("Metamorphic Rocks").equals("https://assets.pebblego.com/cms_content/images/pgnsci_metamorphic_rocks_main_image_article_id_8497.jpg") &&
    			science.verifyImageActive(science.getDatabaseEntryImageSrc("Metamorphic Rocks")));
    	assertTrue("Asserts the correct image shows up for Minerals", 
    			science.getDatabaseEntryImageSrc("Minerals").equals("https://assets.pebblego.com/cms_content/images/pgnsci_minerals_main_image_article_id_8498.jpg") &&
    			science.verifyImageActive(science.getDatabaseEntryImageSrc("Minerals")));
    	assertTrue("Asserts the correct image shows up for Rock Layers and the Rock Cycle", 
    			science.getDatabaseEntryImageSrc("Rock Layers and the Rock Cycle").equals("https://assets.pebblego.com/cms_content/images/pgnsci_rock_layers_and_the_rock_cycle_main_image_article_id_8499.jpg") &&
    			science.verifyImageActive(science.getDatabaseEntryImageSrc("Rock Layers and the Rock Cycle")));
    	assertTrue("Asserts the correct image shows up for Sedimentary Rocks", 
    			science.getDatabaseEntryImageSrc("Sedimentary Rocks").equals("https://assets.pebblego.com/cms_content/images/pgnsci_sedimentary_rocks_main_image_article_id_8500.jpg") &&
    			science.verifyImageActive(science.getDatabaseEntryImageSrc("Sedimentary Rocks")));
    	assertTrue("Asserts the correct image shows up for Soil", 
    			science.getDatabaseEntryImageSrc("Soil").equals("https://assets.pebblego.com/cms_content/images/pgnsci_soil_main_image_article_id_8501.jpg") &&
    			science.verifyImageActive(science.getDatabaseEntryImageSrc("Soil")));
    	science.clickHomeBreadcrumb();
    }
    
    @Test
    public void testNaturalDisastersCategoryImages(){
    	StudentHomePage student = new StudentHomePage(user.getDriver());
    	
    	student.waitImplicitly(1);
    	ScienceDatabaseHome science = student.goToScience();
    	science.clickCategory("Earth Science");
    	science.clickCategory("Geosphere");
    	science.clickCategory("Natural Disasters");
    	
    	assertTrue("Asserts the correct image shows up for Avalanches", 
    			science.getDatabaseEntryImageSrc("Avalanches").equals("https://assets.pebblego.com/cms_content/images/pgnsci_avalanches_main_image_article_id_8502.jpg") &&
    			science.verifyImageActive(science.getDatabaseEntryImageSrc("Avalanches")));
    	assertTrue("Asserts the correct image shows up for Earthquakes", 
    			science.getDatabaseEntryImageSrc("Earthquakes").equals("https://assets.pebblego.com/cms_content/images/pgnsci_earthquakes_main_image_article_id_8503.jpg") &&
    			science.verifyImageActive(science.getDatabaseEntryImageSrc("Earthquakes")));
    	assertTrue("Asserts the correct image shows up for Tsunamis",     			
    			science.getDatabaseEntryImageSrc("Tsunamis").equals("https://assets.pebblego.com/cms_content/images/pgnsci_tsunamis_main_image_article_id_8504.jpg") &&
    			science.verifyImageActive(science.getDatabaseEntryImageSrc("Tsunamis")));
    	assertTrue("Asserts the correct image shows up for Volcanic Eruptions", 
    			science.getDatabaseEntryImageSrc("Volcanic Eruptions").equals("https://assets.pebblego.com/cms_content/images/pgnsci_volcanic_eruptions_main_image_article_id_8505.jpg") &&
    			science.verifyImageActive(science.getDatabaseEntryImageSrc("Volcanic Eruptions")));
    	science.clickHomeBreadcrumb();
    }
    
    @Test
    public void testNaturalResourcesCategoryImages(){
    	StudentHomePage student = new StudentHomePage(user.getDriver());
    	
    	student.waitImplicitly(1);
    	ScienceDatabaseHome science = student.goToScience();
    	science.clickCategory("Earth Science");
    	science.clickCategory("Geosphere");
    	science.clickCategory("Natural Resources");
    	
    	assertTrue("Asserts the correct image shows up for Alternative Energy", 
    			science.getDatabaseEntryImageSrc("Alternative Energy").equals("https://assets.pebblego.com/cms_content/images/pgnsci_alternative_energy_main_image_article_id_8507.jpg") &&
    			science.verifyImageActive(science.getDatabaseEntryImageSrc("Alternative Energy")));
    	assertTrue("Asserts the correct image shows up for Natural Resources and the Environment", 
    			science.getDatabaseEntryImageSrc("Natural Resources and the Environment").equals("https://assets.pebblego.com/cms_content/images/pgnsci_natural_resources_and_the_environment_main_image_article_id_8506.jpg") &&
    			science.verifyImageActive(science.getDatabaseEntryImageSrc("Natural Resources and the Environment")));
    	assertTrue("Asserts the correct image shows up for Nonrenewable Resources",     			
    			science.getDatabaseEntryImageSrc("Nonrenewable Resources").equals("https://assets.pebblego.com/cms_content/images/pgnsci_nonrenewable_resources_main_image_article_id_8508.jpg") &&
    			science.verifyImageActive(science.getDatabaseEntryImageSrc("Nonrenewable Resources")));
    	science.clickHomeBreadcrumb();
    }
    
    @Test 
    public void testHydrosphereCategoryImages(){
    	StudentHomePage student = new StudentHomePage(user.getDriver());
    	
    	student.waitImplicitly(1);
    	ScienceDatabaseHome science = student.goToScience();
    	science.clickCategory("Earth Science");
    	science.clickCategory("Hydrosphere");
    	
    	assertTrue("Asserts the correct image shows up for Humans and the Hydrosphere", 
    			science.getDatabaseEntryImageSrc("Humans and the Hydrosphere").equals("https://assets.pebblego.com/cms_content/images/pgnsci_humans_and_the_hydrosphere_main_image_article_id_8519.jpg") &&
    			science.verifyImageActive(science.getDatabaseEntryImageSrc("Humans and the Hydrosphere")));
    	assertTrue("Asserts the correct image shows up for Natural Wonders in the Water", 
    			science.getDatabaseEntryImageSrc("Natural Wonders in the Water").equals("https://assets.pebblego.com/cms_content/images/pgnsci_natural_wonders_in_the_water_main_image_article_id_8520.jpg") &&
    			science.verifyImageActive(science.getDatabaseEntryImageSrc("Natural Wonders in the Water")));
    	assertTrue("Asserts the correct image shows up for Water Sources",     			
    			science.getDatabaseEntryImageSrc("Water Sources").equals("https://assets.pebblego.com/cms_content/images/pgnsci_water_sources_main_image_article_id_8456.jpg") &&
    			science.verifyImageActive(science.getDatabaseEntryImageSrc("Water Sources")));
    	assertTrue("Asserts the correct image shows up for What Is the Hydrosphere?", 
    			science.getDatabaseEntryImageSrc("What Is the Hydrosphere?").equals("https://assets.pebblego.com/cms_content/images/pgnsci_what_is_the_hydrosphere?_main_image_article_id_8521.jpg") &&
    			science.verifyImageActive(science.getDatabaseEntryImageSrc("What Is the Hydrosphere?")));
    	science.clickHomeBreadcrumb();
    }
    
    @Test
    public void testWaterSourcesCategoryImages(){
    	StudentHomePage student = new StudentHomePage(user.getDriver());
    	
    	student.waitImplicitly(1);
    	ScienceDatabaseHome science = student.goToScience();
    	science.clickCategory("Earth Science");
    	science.clickCategory("Hydrosphere");
    	science.clickCategory("Water Sources");
    	
    	assertTrue("Asserts the correct image shows up for Glaciers as a Water Source", 
    			science.getDatabaseEntryImageSrc("Glaciers as a Water Source").equals("https://assets.pebblego.com/cms_content/images/pgnsci_glaciers_as_a_water_source_main_image_article_id_8514.jpg") &&
    			science.verifyImageActive(science.getDatabaseEntryImageSrc("Glaciers as a Water Source")));
    	assertTrue("Asserts the correct image shows up for Lakes", 
    			science.getDatabaseEntryImageSrc("Lakes").equals("https://assets.pebblego.com/cms_content/images/pgnsci_lakes_main_image_article_id_8515.jpg") &&
    			science.verifyImageActive(science.getDatabaseEntryImageSrc("Lakes")));
    	assertTrue("Asserts the correct image shows up for Oceans as a Water Source",     			
    			science.getDatabaseEntryImageSrc("Oceans as a Water Source").equals("https://assets.pebblego.com/cms_content/images/pgnsci_oceans_as_a_water_source_main_image_article_id_8516.jpg") &&
    			science.verifyImageActive(science.getDatabaseEntryImageSrc("Oceans as a Water Source")));
    	assertTrue("Asserts the correct image shows up for Rivers and Streams", 
    			science.getDatabaseEntryImageSrc("Rivers and Streams").equals("https://assets.pebblego.com/cms_content/images/pgnsci_rivers_and_streams_main_image_article_id_8517.jpg") &&
    			science.verifyImageActive(science.getDatabaseEntryImageSrc("Rivers and Streams")));
    	assertTrue("Asserts the correct image shows up for The Water Cycle", 
    			science.getDatabaseEntryImageSrc("The Water Cycle").equals("https://assets.pebblego.com/cms_content/images/pgnsci_the_water_cycle_main_image_article_id_8518.jpg") &&
    			science.verifyImageActive(science.getDatabaseEntryImageSrc("The Water Cycle")));
    	science.clickHomeBreadcrumb();
    }
    
    @Test
    public void testPlanetsAndTheSolarSystemImages(){
    	StudentHomePage student = new StudentHomePage(user.getDriver());
    	
    	student.waitImplicitly(1);
    	ScienceDatabaseHome science = student.goToScience();
    	science.clickCategory("Earth Science");
    	science.clickCategory("Planets and the Solar System");
    	
    	assertTrue("Asserts the correct image shows up for Planets", 
    			science.getDatabaseEntryImageSrc("Planets").equals("https://assets.pebblego.com/cms_content/images/pgnsci_planets_main_image_article_id_8458.jpg") &&
    			science.verifyImageActive(science.getDatabaseEntryImageSrc("Planets")));
    	assertTrue("Asserts the correct image shows up for Stars and the Solar System", 
    			science.getDatabaseEntryImageSrc("Stars and the Solar System").equals("https://assets.pebblego.com/cms_content/images/pgnsci_stars_and_the_solar_system_main_image_article_id_8459.jpg") &&
    			science.verifyImageActive(science.getDatabaseEntryImageSrc("Stars and the Solar System")));
    	science.clickHomeBreadcrumb();
    }
    
    @Test
    public void testPlanetsCategoryImages(){
    	StudentHomePage student = new StudentHomePage(user.getDriver());
    	
    	student.waitImplicitly(1);
    	ScienceDatabaseHome science = student.goToScience();
    	science.clickCategory("Earth Science");
    	science.clickCategory("Planets and the Solar System");
    	science.clickCategory("Planets");
    	
    	assertTrue("Asserts the correct image shows up for Dwarf Planets", 
    			science.getDatabaseEntryImageSrc("Dwarf Planets").equals("https://assets.pebblego.com/cms_content/images/pgnsci_dwarf_planets_main_image_article_id_8522.jpg") &&
    			science.verifyImageActive(science.getDatabaseEntryImageSrc("Dwarf Planets")));
    	assertTrue("Asserts the correct image shows up for Earth", 
    			science.getDatabaseEntryImageSrc("Earth").equals("https://assets.pebblego.com/cms_content/images/pgnsci_earth_main_image_article_id_8523.jpg") &&
    			science.verifyImageActive(science.getDatabaseEntryImageSrc("Earth")));
    	assertTrue("Asserts the correct image shows up for Jupiter",     			
    			science.getDatabaseEntryImageSrc("Jupiter").equals("https://assets.pebblego.com/cms_content/images/pgnsci_jupiter_main_image_article_id_8524.jpg") &&
    			science.verifyImageActive(science.getDatabaseEntryImageSrc("Jupiter")));
    	assertTrue("Asserts the correct image shows up for Mars", 
    			science.getDatabaseEntryImageSrc("Mars").equals("https://assets.pebblego.com/cms_content/images/pgnsci_mars_main_image_article_id_8525.jpg") &&
    			science.verifyImageActive(science.getDatabaseEntryImageSrc("Mars")));
    	assertTrue("Asserts the correct image shows up for Mercury", 
    			science.getDatabaseEntryImageSrc("Mercury").equals("https://assets.pebblego.com/cms_content/images/pgnsci_mercury_main_image_article_id_8526.jpg") &&
    			science.verifyImageActive(science.getDatabaseEntryImageSrc("Mercury")));
    	assertTrue("Asserts the correct image shows up for Neptune", 
    			science.getDatabaseEntryImageSrc("Neptune").equals("https://assets.pebblego.com/cms_content/images/pgnsci_neptune_main_image_article_id_8527.jpg") &&
    			science.verifyImageActive(science.getDatabaseEntryImageSrc("Neptune")));
    	assertTrue("Asserts the correct image shows up for Saturn", 
    			science.getDatabaseEntryImageSrc("Saturn").equals("https://assets.pebblego.com/cms_content/images/pgnsci_saturn_main_image_article_id_8528.jpg") &&
    			science.verifyImageActive(science.getDatabaseEntryImageSrc("Saturn")));
    	assertTrue("Asserts the correct image shows up for Uranus", 
    			science.getDatabaseEntryImageSrc("Uranus").equals("https://assets.pebblego.com/cms_content/images/pgnsci_uranus_main_image_article_id_8529.jpg") &&
    			science.verifyImageActive(science.getDatabaseEntryImageSrc("Uranus")));
    	assertTrue("Asserts the correct image shows up for Venus", 
    			science.getDatabaseEntryImageSrc("Venus").equals("https://assets.pebblego.com/cms_content/images/pgnsci_venus_main_image_article_id_8530.jpg") &&
    			science.verifyImageActive(science.getDatabaseEntryImageSrc("Venus")));
    	science.clickHomeBreadcrumb();
    }
    
    @Test
    public void testStarsAndSolarSystemCategoryImages(){
    	StudentHomePage student = new StudentHomePage(user.getDriver());
    	
    	student.waitImplicitly(1);
    	ScienceDatabaseHome science = student.goToScience();
    	science.clickCategory("Earth Science");
    	science.clickCategory("Planets and the Solar System");
    	science.clickCategory("Stars and the Solar System");
    	
    	assertTrue("Asserts the correct image shows up for Asteroids, Comets, and Meteors", 
    			science.getDatabaseEntryImageSrc("Asteroids, Comets, and Meteors").equals("https://assets.pebblego.com/cms_content/images/pgnsci_asteroids,_comets,_and_meteors_main_image_article_id_8531.jpg") &&
    			science.verifyImageActive(science.getDatabaseEntryImageSrc("Asteroids, Comets, and Meteors")));
    	assertTrue("Asserts the correct image shows up for Black Holes", 
    			science.getDatabaseEntryImageSrc("Black Holes").equals("https://assets.pebblego.com/cms_content/images/pgnsci_black_holes_main_image_article_id_8532.jpg") &&
    			science.verifyImageActive(science.getDatabaseEntryImageSrc("Black Holes")));
    	assertTrue("Asserts the correct image shows up for Moon",     			
    			science.getDatabaseEntryImageSrc("Moon").equals("https://assets.pebblego.com/cms_content/images/pgnsci_moon_main_image_article_id_8536.jpg") &&
    			science.verifyImageActive(science.getDatabaseEntryImageSrc("Moon")));
    	assertTrue("Asserts the correct image shows up for Space Exploration", 
    			science.getDatabaseEntryImageSrc("Space Exploration").equals("https://assets.pebblego.com/cms_content/images/pgnsci_space_exploration_main_image_article_id_8533.jpg") &&
    			science.verifyImageActive(science.getDatabaseEntryImageSrc("Space Exploration")));
    	assertTrue("Asserts the correct image shows up for Stars and Constellations", 
    			science.getDatabaseEntryImageSrc("Stars and Constellations").equals("https://assets.pebblego.com/cms_content/images/pgnsci_stars_and_constellations_main_image_article_id_8534.jpg") &&
    			science.verifyImageActive(science.getDatabaseEntryImageSrc("Stars and Constellations")));
    	assertTrue("Asserts the correct image shows up for The Milky Way and Other Galaxies", 
    			science.getDatabaseEntryImageSrc("The Milky Way and Other Galaxies").equals("https://assets.pebblego.com/cms_content/images/pgnsci_the_milky_way_and_other_galaxies_main_image_article_id_8535.jpg") &&
    			science.verifyImageActive(science.getDatabaseEntryImageSrc("The Milky Way and Other Galaxies")));
    	assertTrue("Asserts the correct image shows up for The Sun", 
    			science.getDatabaseEntryImageSrc("The Sun").equals("https://assets.pebblego.com/cms_content/images/pgnsci_the_sun_main_image_article_id_8537.jpg") &&
    			science.verifyImageActive(science.getDatabaseEntryImageSrc("The Sun")));
    	science.clickHomeBreadcrumb();
    }
    
    @Test
    public void testStatesCategoryImages(){
    	StudentHomePage student = new StudentHomePage(user.getDriver());
    	
    	student.waitImplicitly(1);
    	StatesDatabaseHome states = student.goToStates();
    	
    	assertTrue("Asserts the correct image shows up for West", 
    			states.getDatabaseEntryImageSrc("West").equals("https://assets.pebblego.com/cms_content/images/pgnstates_west_main_image_article_id_6989.jpg") &&
    			states.verifyImageActive(states.getDatabaseEntryImageSrc("West")));
    	assertTrue("Asserts the correct image shows up for Midwest", 
    			states.getDatabaseEntryImageSrc("Midwest").equals("https://assets.pebblego.com/cms_content/images/pgnstates_midwest_main_image_article_id_6994.jpg") &&
    			states.verifyImageActive(states.getDatabaseEntryImageSrc("Midwest")));
    	assertTrue("Asserts the correct image shows up for Northeast", 
    			states.getDatabaseEntryImageSrc("Northeast").equals("https://assets.pebblego.com/cms_content/images/pgnstates_northeast_main_image_article_id_6996.jpg") &&
    			states.verifyImageActive(states.getDatabaseEntryImageSrc("Northeast")));
    	assertTrue("Asserts the correct image shows up for Southwest", 
    			states.getDatabaseEntryImageSrc("Southwest").equals("https://assets.pebblego.com/cms_content/images/pgnstates_southwest_main_image_article_id_6992.jpg") &&
    			states.verifyImageActive(states.getDatabaseEntryImageSrc("Southwest")));
    	assertTrue("Asserts the correct image shows up for Southeast", 
    			states.getDatabaseEntryImageSrc("Southeast").equals("https://assets.pebblego.com/cms_content/images/pgnstates_southeast_main_image_article_id_6995.jpg") &&
    			states.verifyImageActive(states.getDatabaseEntryImageSrc("Southwest")));
    
    	states.clickHomeBreadcrumb();
    }
    
    @Test
    public void testWestCategoryImages(){
    	StudentHomePage student = new StudentHomePage(user.getDriver());
    	
    	student.waitImplicitly(1);
    	StatesDatabaseHome states = student.goToStates();
    	states.clickCategory("West");
    	
    	assertTrue("Asserts the correct image shows up for Alaska", 
    			states.getDatabaseEntryImageSrc("Alaska").equals("https://assets.pebblego.com/cms_content/images/pgnstates_alaska_main_image_article_id_6002.jpg") &&
    			states.verifyImageActive(states.getDatabaseEntryImageSrc("Alaska")));
    	assertTrue("Asserts the correct image shows up for California", 
    			states.getDatabaseEntryImageSrc("California").equals("https://assets.pebblego.com/cms_content/images/pgnstates_california_main_image_article_id_6005.jpg") &&
    			states.verifyImageActive(states.getDatabaseEntryImageSrc("California")));
    	assertTrue("Asserts the correct image shows up for Colorado", 
    			states.getDatabaseEntryImageSrc("Colorado").equals("https://assets.pebblego.com/cms_content/images/pgnstates_colorado_main_image_article_id_6006.jpg") &&
    			states.verifyImageActive(states.getDatabaseEntryImageSrc("Colorado")));
    	assertTrue("Asserts the correct image shows up for Hawaii", 
    			states.getDatabaseEntryImageSrc("Hawaii").equals("https://assets.pebblego.com/cms_content/images/pgnstates_hawaii_main_image_article_id_6011.jpg") &&
    			states.verifyImageActive(states.getDatabaseEntryImageSrc("Hawaii")));
    	assertTrue("Asserts the correct image shows up for Idaho", 
    			states.getDatabaseEntryImageSrc("Idaho").equals("https://assets.pebblego.com/cms_content/images/pgnstates_idaho_main_image_article_id_6012.jpg") &&
    			states.verifyImageActive(states.getDatabaseEntryImageSrc("Idaho")));
    	assertTrue("Asserts the correct image shows up for Montana", 
    			states.getDatabaseEntryImageSrc("Montana").equals("https://assets.pebblego.com/cms_content/images/pgnstates_montana_main_image_article_id_6026.jpg") &&
    			states.verifyImageActive(states.getDatabaseEntryImageSrc("Montana")));
    	assertTrue("Asserts the correct image shows up for Nevada", 
    			states.getDatabaseEntryImageSrc("Nevada").equals("https://assets.pebblego.com/cms_content/images/pgnstates_nevada_main_image_article_id_6028.jpg") &&
    			states.verifyImageActive(states.getDatabaseEntryImageSrc("Nevada")));
    	assertTrue("Asserts the correct image shows up for Oregon", 
    			states.getDatabaseEntryImageSrc("Oregon").equals("https://assets.pebblego.com/cms_content/images/pgnstates_oregon_main_image_article_id_6037.jpg") &&
    			states.verifyImageActive(states.getDatabaseEntryImageSrc("Oregon")));
    	assertTrue("Asserts the correct image shows up for Utah", 
    			states.getDatabaseEntryImageSrc("Utah").equals("https://assets.pebblego.com/cms_content/images/pgnstates_utah_main_image_article_id_6045.jpg") &&
    			states.verifyImageActive(states.getDatabaseEntryImageSrc("Utah")));
    	assertTrue("Asserts the correct image shows up for Washington", 
    			states.getDatabaseEntryImageSrc("Washington").equals("https://assets.pebblego.com/cms_content/images/pgnstates_washington_main_image_article_id_6048.jpg") &&
    			states.verifyImageActive(states.getDatabaseEntryImageSrc("Washington")));
    	assertTrue("Asserts the correct image shows up for Wyoming", 
    			states.getDatabaseEntryImageSrc("Wyoming").equals("https://assets.pebblego.com/cms_content/images/pgnstates_wyoming_main_image_article_id_6052.jpg") &&
    			states.verifyImageActive(states.getDatabaseEntryImageSrc("Wyoming")));
    	states.clickHomeBreadcrumb();
    }
    
    @Test
    public void testMidwestCategoryImages(){
    	StudentHomePage student = new StudentHomePage(user.getDriver());
    	
    	student.waitImplicitly(1);
    	StatesDatabaseHome states = student.goToStates();
    	states.clickCategory("Midwest");
    	
    	
    	assertTrue("Asserts the correct image shows up for Illinois", 
    			states.getDatabaseEntryImageSrc("Illinois").equals("https://assets.pebblego.com/cms_content/images/pgnstates_illinois_main_image_article_id_6013.jpg") &&
    			states.verifyImageActive(states.getDatabaseEntryImageSrc("Illinois")));
    	assertTrue("Asserts the correct image shows up for Indiana", 
    			states.getDatabaseEntryImageSrc("Indiana").equals("https://assets.pebblego.com/cms_content/images/pgnstates_indiana_main_image_article_id_6014.jpg") &&
    			states.verifyImageActive(states.getDatabaseEntryImageSrc("Indiana")));
    	assertTrue("Asserts the correct image shows up for Iowa", 
    			states.getDatabaseEntryImageSrc("Iowa").equals("https://assets.pebblego.com/cms_content/images/pgnstates_iowa_main_image_article_id_6015.jpg") &&
    			states.verifyImageActive(states.getDatabaseEntryImageSrc("Iowa")));
    	assertTrue("Asserts the correct image shows up for Kansas", 
    			states.getDatabaseEntryImageSrc("Kansas").equals("https://assets.pebblego.com/cms_content/images/pgnstates_kansas_main_image_article_id_6016.jpg") &&
    			states.verifyImageActive(states.getDatabaseEntryImageSrc("Kansas")));
    	assertTrue("Asserts the correct image shows up for Michigan", 
    			states.getDatabaseEntryImageSrc("Michigan").equals("https://assets.pebblego.com/cms_content/images/pgnstates_michigan_main_image_article_id_6022.jpg") &&
    			states.verifyImageActive(states.getDatabaseEntryImageSrc("Michigan")));
    	assertTrue("Asserts the correct image shows up for Minnesota", 
    			states.getDatabaseEntryImageSrc("Minnesota").equals("https://assets.pebblego.com/cms_content/images/pgnstates_minnesota_main_image_article_id_6023.jpg") &&
    			states.verifyImageActive(states.getDatabaseEntryImageSrc("Minnesota")));
    	assertTrue("Asserts the correct image shows up for Missouri", 
    			states.getDatabaseEntryImageSrc("Missouri").equals("https://assets.pebblego.com/cms_content/images/pgnstates_missouri_main_image_article_id_6025.jpg") &&
    			states.verifyImageActive(states.getDatabaseEntryImageSrc("Missouri")));
    	assertTrue("Asserts the correct image shows up for Nebraska", 
    			states.getDatabaseEntryImageSrc("Nebraska").equals("https://assets.pebblego.com/cms_content/images/pgnstates_nebraska_main_image_article_id_6027.jpg") &&
    			states.verifyImageActive(states.getDatabaseEntryImageSrc("Nebraska")));
    	assertTrue("Asserts the correct image shows up for North Dakota", 
    			states.getDatabaseEntryImageSrc("North Dakota").equals("https://assets.pebblego.com/cms_content/images/pgnstates_north_dakota_main_image_article_id_6034.jpg") &&
    			states.verifyImageActive(states.getDatabaseEntryImageSrc("North Dakota")));
    	assertTrue("Asserts the correct image shows up for Ohio", 
    			states.getDatabaseEntryImageSrc("Ohio").equals("https://assets.pebblego.com/cms_content/images/pgnstates_ohio_main_image_article_id_6035.jpg") &&
    			states.verifyImageActive(states.getDatabaseEntryImageSrc("Ohio")));
    	assertTrue("Asserts the correct image shows up for South Dakota", 
    			states.getDatabaseEntryImageSrc("South Dakota").equals("https://assets.pebblego.com/cms_content/images/pgnstates_south_dakota_main_image_article_id_6042.jpg") &&
    			states.verifyImageActive(states.getDatabaseEntryImageSrc("South Dakota")));
    	assertTrue("Asserts the correct image shows up for Wisconsin", 
    			states.getDatabaseEntryImageSrc("Wisconsin").equals("https://assets.pebblego.com/cms_content/images/pgnstates_wisconsin_main_image_article_id_6051.jpg") &&
    			states.verifyImageActive(states.getDatabaseEntryImageSrc("Wisconsin")));
    	states.clickHomeBreadcrumb();	
    }
    
    @Test
    public void testNortheastCategoryImages(){
    	StudentHomePage student = new StudentHomePage(user.getDriver());
    	
    	student.waitImplicitly(1);
    	StatesDatabaseHome states = student.goToStates();
    	states.clickCategory("Northeast");
    	
    	assertTrue("Asserts the correct image shows up for Connecticut", 
    			states.getDatabaseEntryImageSrc("Connecticut").equals("https://assets.pebblego.com/cms_content/images/pgnstates_connecticut_main_image_article_id_6007.jpg") &&
    			states.verifyImageActive(states.getDatabaseEntryImageSrc("Connecticut")));
    	assertTrue("Asserts the correct image shows up for Delaware", 
    			states.getDatabaseEntryImageSrc("Delaware").equals("https://assets.pebblego.com/cms_content/images/pgnstates_delaware_main_image_article_id_6008.jpg") &&
    			states.verifyImageActive(states.getDatabaseEntryImageSrc("Delaware")));
    	assertTrue("Asserts the correct image shows up for Maine", 
    			states.getDatabaseEntryImageSrc("Maine").equals("https://assets.pebblego.com/cms_content/images/pgnstates_maine_main_image_article_id_6019.jpg") &&
    			states.verifyImageActive(states.getDatabaseEntryImageSrc("Maine")));
    	assertTrue("Asserts the correct image shows up for Maryland", 
    			states.getDatabaseEntryImageSrc("Maryland").equals("https://assets.pebblego.com/cms_content/images/pgnstates_maryland_main_image_article_id_6020.jpg") &&
    			states.verifyImageActive(states.getDatabaseEntryImageSrc("Maryland")));
    	assertTrue("Asserts the correct image shows up for Massachusetts", 
    			states.getDatabaseEntryImageSrc("Massachusetts").equals("https://assets.pebblego.com/cms_content/images/pgnstates_massachusetts_main_image_article_id_6021.jpg") &&
    			states.verifyImageActive(states.getDatabaseEntryImageSrc("Massachusetts")));
    	assertTrue("Asserts the correct image shows up for New Hampshire", 
    			states.getDatabaseEntryImageSrc("New Hampshire").equals("https://assets.pebblego.com/cms_content/images/pgnstates_new_hampshire_main_image_article_id_6029.jpg") &&
    			states.verifyImageActive(states.getDatabaseEntryImageSrc("New Hampshire")));
    	assertTrue("Asserts the correct image shows up for New Jersey", 
    			states.getDatabaseEntryImageSrc("New Jersey").equals("https://assets.pebblego.com/cms_content/images/pgnstates_new_jersey_main_image_article_id_6030.jpg") &&
    			states.verifyImageActive(states.getDatabaseEntryImageSrc("New Jersey")));
    	assertTrue("Asserts the correct image shows up for New York", 
    			states.getDatabaseEntryImageSrc("New York").equals("https://assets.pebblego.com/cms_content/images/pgnstates_new_york_main_image_article_id_6032.jpg") &&
    			states.verifyImageActive(states.getDatabaseEntryImageSrc("New York")));
    	assertTrue("Asserts the correct image shows up for Pennsylsvania", 
    			states.getDatabaseEntryImageSrc("Pennsylvania").equals("https://assets.pebblego.com/cms_content/images/pgnstates_pennsylvania_main_image_article_id_6038.jpg") &&
    			states.verifyImageActive(states.getDatabaseEntryImageSrc("Pennsylvania")));
    	assertTrue("Asserts the correct image shows up for Rhode Island", 
    			states.getDatabaseEntryImageSrc("Rhode Island").equals("https://assets.pebblego.com/cms_content/images/pgnstates_rhode_island_main_image_article_id_6040.jpg") &&
    			states.verifyImageActive(states.getDatabaseEntryImageSrc("Rhode Island")));
    	assertTrue("Asserts the correct image shows up for Vermont", 
    			states.getDatabaseEntryImageSrc("Vermont").equals("https://assets.pebblego.com/cms_content/images/pgnstates_vermont_main_image_article_id_6046.jpg") &&
    			states.verifyImageActive(states.getDatabaseEntryImageSrc("Vermont")));
    	states.clickHomeBreadcrumb();	
    }
    
    @Test
    public void testSouthwestCategoryImages(){
    	StudentHomePage student = new StudentHomePage(user.getDriver());
    	
    	student.waitImplicitly(1);
    	StatesDatabaseHome states = student.goToStates();
    	states.clickCategory("Southwest");
    	
    	assertTrue("Asserts the correct image shows up for Arizona", 
    			states.getDatabaseEntryImageSrc("Arizona").equals("https://assets.pebblego.com/cms_content/images/pgnstates_arizona_main_image_article_id_6003.jpg") &&
    			states.verifyImageActive(states.getDatabaseEntryImageSrc("Arizona")));
    	assertTrue("Asserts the correct image shows up for New Mexico", 
    			states.getDatabaseEntryImageSrc("New Mexico").equals("https://assets.pebblego.com/cms_content/images/pgnstates_new_mexico_main_image_article_id_6031.jpg") &&
    			states.verifyImageActive(states.getDatabaseEntryImageSrc("New Mexico")));
    	assertTrue("Asserts the correct image shows up for Oklahoma", 
    			states.getDatabaseEntryImageSrc("Oklahoma").equals("https://assets.pebblego.com/cms_content/images/pgnstates_oklahoma_main_image_article_id_6036.jpg") &&
    			states.verifyImageActive(states.getDatabaseEntryImageSrc("Oklahoma")));
    	assertTrue("Asserts the correct image shows up for Texas", 
    			states.getDatabaseEntryImageSrc("Texas").equals("https://assets.pebblego.com/cms_content/images/pgnstates_texas_main_image_article_id_6044.jpg") &&
    			states.verifyImageActive(states.getDatabaseEntryImageSrc("Texas")));
    	states.clickHomeBreadcrumb();
    }
    
    @Test
    public void testSoutheastCategoryImages(){
    	StudentHomePage student = new StudentHomePage(user.getDriver());
    	
    	student.waitImplicitly(1);
    	StatesDatabaseHome states = student.goToStates();
    	states.clickCategory("Southeast");
    	
    	assertTrue("Asserts the correct image shows up for Alabama", 
    			states.getDatabaseEntryImageSrc("Alabama").equals("https://assets.pebblego.com/cms_content/images/pgnstates_alabama_main_image_article_id_6001.jpg") &&
    			states.verifyImageActive(states.getDatabaseEntryImageSrc("Alabama")));
    	assertTrue("Asserts the correct image shows up for Arkansas", 
    			states.getDatabaseEntryImageSrc("Arkansas").equals("https://assets.pebblego.com/cms_content/images/pgnstates_arkansas_main_image_article_id_6004.jpg") &&
    			states.verifyImageActive(states.getDatabaseEntryImageSrc("Arkansas")));
    	assertTrue("Asserts the correct image shows up for Florida", 
    			states.getDatabaseEntryImageSrc("Florida").equals("https://assets.pebblego.com/cms_content/images/pgnstates_florida_main_image_article_id_6009.jpg") &&
    			states.verifyImageActive(states.getDatabaseEntryImageSrc("Florida")));
    	assertTrue("Asserts the correct image shows up for Georgia", 
    			states.getDatabaseEntryImageSrc("Georgia").equals("https://assets.pebblego.com/cms_content/images/pgnstates_georgia_main_image_article_id_6010.jpg") &&
    			states.verifyImageActive(states.getDatabaseEntryImageSrc("Georgia")));
    	assertTrue("Asserts the correct image shows up for Kentucky", 
    			states.getDatabaseEntryImageSrc("Kentucky").equals("https://assets.pebblego.com/cms_content/images/pgnstates_kentucky_main_image_article_id_6017.jpg") &&
    			states.verifyImageActive(states.getDatabaseEntryImageSrc("Kentucky")));
    	assertTrue("Asserts the correct image shows up for Louisiana", 
    			states.getDatabaseEntryImageSrc("Louisiana").equals("https://assets.pebblego.com/cms_content/images/pgnstates_louisiana_main_image_article_id_6018.jpg") &&
    			states.verifyImageActive(states.getDatabaseEntryImageSrc("Louisiana")));
    	assertTrue("Asserts the correct image shows up for Mississippi", 
    			states.getDatabaseEntryImageSrc("Mississippi").equals("https://assets.pebblego.com/cms_content/images/pgnstates_mississippi_main_image_article_id_6024.jpg") &&
    			states.verifyImageActive(states.getDatabaseEntryImageSrc("Mississippi")));
    	assertTrue("Asserts the correct image shows up for North Carolina", 
    			states.getDatabaseEntryImageSrc("North Carolina").equals("https://assets.pebblego.com/cms_content/images/pgnstates_north_carolina_main_image_article_id_6033.jpg") &&
    			states.verifyImageActive(states.getDatabaseEntryImageSrc("North Carolina")));
    	assertTrue("Asserts the correct image shows up for Puerto Rico", 
    			states.getDatabaseEntryImageSrc("Puerto Rico").equals("https://assets.pebblego.com/cms_content/images/pgnstates_puerto_rico_main_image_article_id_6039.jpg") &&
    			states.verifyImageActive(states.getDatabaseEntryImageSrc("Puerto Rico")));
    	assertTrue("Asserts the correct image shows up for South Carolina", 
    			states.getDatabaseEntryImageSrc("South Carolina").equals("https://assets.pebblego.com/cms_content/images/pgnstates_south_carolina_main_image_article_id_6041.jpg") &&
    			states.verifyImageActive(states.getDatabaseEntryImageSrc("South Carolina")));
    	assertTrue("Asserts the correct image shows up for Tennessee", 
    			states.getDatabaseEntryImageSrc("Tennessee").equals("https://assets.pebblego.com/cms_content/images/pgnstates_tennessee_main_image_article_id_6043.jpg") &&
    			states.verifyImageActive(states.getDatabaseEntryImageSrc("Tennessee")));
    	assertTrue("Asserts the correct image shows up for Virginia", 
    			states.getDatabaseEntryImageSrc("Virginia").equals("https://assets.pebblego.com/cms_content/images/pgnstates_virginia_main_image_article_id_6047.jpg") &&
    			states.verifyImageActive(states.getDatabaseEntryImageSrc("Virginia")));
    	assertTrue("Asserts the correct image shows up for Washington, D.C.", 
    			states.getDatabaseEntryImageSrc("Washington, D.C.").equals("https://assets.pebblego.com/cms_content/images/pgnstates_washington,_d.c._main_image_article_id_6049.jpg") &&
    			states.verifyImageActive(states.getDatabaseEntryImageSrc("Washington, D.C.")));
    	assertTrue("Asserts the correct image shows up for West Virginia", 
    			states.getDatabaseEntryImageSrc("West Virginia").equals("https://assets.pebblego.com/cms_content/images/pgnstates_west_virginia_main_image_article_id_6050.jpg") &&
    			states.verifyImageActive(states.getDatabaseEntryImageSrc("West Virginia")));
    	states.clickHomeBreadcrumb();
    }
    
    
    @Test
    public void testIndiansCategoryImages(){
    	StudentHomePage student = new StudentHomePage(user.getDriver());
    	
    	student.waitImplicitly(1);
    	IndiansDatabaseHome indians = student.goToIndians();
    	
    	assertTrue("Asserts the correct image shows up for Arctic Culture Area", 
    			indians.getDatabaseEntryImageSrc("Arctic Culture Area").equals("https://assets.pebblego.com/cms_content/images/pgnnative_arctic_culture_area_main_image_article_id_6988.jpg") &&
    			indians.verifyImageActive(indians.getDatabaseEntryImageSrc("Arctic Culture Area")));
    	assertTrue("Asserts the correct image shows up for Subarctic Culture Area", 
    			indians.getDatabaseEntryImageSrc("Subarctic Culture Area").equals("https://assets.pebblego.com/cms_content/images/pgnnative_subarctic_culture_area_main_image_article_id_6979.jpg") &&
    			indians.verifyImageActive(indians.getDatabaseEntryImageSrc("Subarctic Culture Area")));
    	assertTrue("Asserts the correct image shows up for Northwest Coast Culture Area", 
    			indians.getDatabaseEntryImageSrc("Northwest Coast Culture Area").equals("https://assets.pebblego.com/cms_content/images/pgnnative_northwest_coast_culture_area_main_image_article_id_6983.jpg") &&
    			indians.verifyImageActive(indians.getDatabaseEntryImageSrc("Northwest Coast Culture Area")));
    	assertTrue("Asserts the correct image shows up for Plateau Culture Area", 
    			indians.getDatabaseEntryImageSrc("Plateau Culture Area").equals("https://assets.pebblego.com/cms_content/images/pgnnative_plateau_culture_area_main_image_article_id_6982.jpg") &&
    			indians.verifyImageActive(indians.getDatabaseEntryImageSrc("Plateau Culture Area")));
    	assertTrue("Asserts the correct image shows up for Great Plains Culture Area", 
    			indians.getDatabaseEntryImageSrc("Great Plains Culture Area").equals("https://assets.pebblego.com/cms_content/images/pgnnative_great_plains_culture_area_main_image_article_id_6985.jpg") &&
    			indians.verifyImageActive(indians.getDatabaseEntryImageSrc("Great Plains Culture Area")));
    	assertTrue("Asserts the correct image shows up for Northeast Culture Area", 
    			indians.getDatabaseEntryImageSrc("Northeast Culture Area").equals("https://assets.pebblego.com/cms_content/images/pgnnative_northeast_culture_area_main_image_article_id_6984.jpg") &&
    			indians.verifyImageActive(indians.getDatabaseEntryImageSrc("Northeast Culture Area")));
    	assertTrue("Asserts the correct image shows up for Great Basin Culture Area", 
    			indians.getDatabaseEntryImageSrc("Great Basin Culture Area").equals("https://assets.pebblego.com/cms_content/images/pgnnative_great_basin_culture_area_main_image_article_id_6986.jpg") &&
    			indians.verifyImageActive(indians.getDatabaseEntryImageSrc("Great Basin Culture Area")));
    	assertTrue("Asserts the correct image shows up for California Culture Area", 
    			indians.getDatabaseEntryImageSrc("California Culture Area").equals("https://assets.pebblego.com/cms_content/images/pgnnative_california_culture_area_main_image_article_id_6987.jpg") &&
    			indians.verifyImageActive(indians.getDatabaseEntryImageSrc("California Culture Area")));
    	assertTrue("Asserts the correct image shows up for Southwest Culture Area", 
    			indians.getDatabaseEntryImageSrc("Southwest Culture Area").equals("https://assets.pebblego.com/cms_content/images/pgnnative_southwest_culture_area_main_image_article_id_6980.jpg") &&
    			indians.verifyImageActive(indians.getDatabaseEntryImageSrc("Southwest Culture Area")));
    	assertTrue("Asserts the correct image shows up for Southeast Culture Area", 
    			indians.getDatabaseEntryImageSrc("Southeast Culture Area").equals("https://assets.pebblego.com/cms_content/images/pgnnative_southeast_culture_area_main_image_article_id_6981.jpg") &&
    			indians.verifyImageActive(indians.getDatabaseEntryImageSrc("Southeast Culture Area")));
    	assertTrue("Asserts the correct image shows up for Culture Areas", 
    			indians.getDatabaseEntryImageSrc("Culture Areas").equals("https://assets.pebblego.com/cms_content/images/pgnnative_culture_areas_main_image_article_id_6997.jpg") &&
    			indians.verifyImageActive(indians.getDatabaseEntryImageSrc("Culture Areas")));
    	assertTrue("Asserts the correct image shows up for Native Hawaiians", 
    			indians.getDatabaseEntryImageSrc("Native Hawaiians").equals("https://assets.pebblego.com/cms_content/images/pgnnative_native_hawaiians_main_image_article_id_6102.jpg") &&
    			indians.verifyImageActive(indians.getDatabaseEntryImageSrc("Native Hawaiians")));
    	indians.clickHomeBreadcrumb();
    	//indians.clickCategory("Southeast");
    }
    
    @Test
    public void testAIArcticCultureArea(){
    	StudentHomePage student = new StudentHomePage(user.getDriver());
    	
    	student.waitImplicitly(1);
    	IndiansDatabaseHome indians = student.goToIndians();
    	indians.clickCategory("Arctic Culture Area");
    	
    	assertTrue("Asserts the correct image shows up for Arctic Culture Area", 
    			indians.getDatabaseEntryImageSrc("Arctic Culture Area").equals("https://assets.pebblego.com/cms_content/images/pgnnative_arctic_culture_area_main_image_article_id_6053.jpg") &&
    			indians.verifyImageActive(indians.getDatabaseEntryImageSrc("Arctic Culture Area")));
    	assertTrue("Asserts the correct image shows up for Inuit", 
    			indians.getDatabaseEntryImageSrc("Inuit").equals("https://assets.pebblego.com/cms_content/images/pgnnative_inuit_main_image_article_id_6054.jpg") &&
    			indians.verifyImageActive(indians.getDatabaseEntryImageSrc("Inuit")));
    	indians.clickHomeBreadcrumb();
    }
    
    @Test
    public void testAISubarcticCultureArea(){
    	StudentHomePage student = new StudentHomePage(user.getDriver());
    	
    	student.waitImplicitly(1);
    	IndiansDatabaseHome indians = student.goToIndians();
    	indians.clickCategory("Subarctic Culture Area");
    	
    	assertTrue("Asserts the correct image shows up for Subarctic Culture Area", 
    			indians.getDatabaseEntryImageSrc("Subarctic Culture Area").equals("https://assets.pebblego.com/cms_content/images/pgnnative_subarctic_culture_area_main_image_article_id_6055.jpg") &&
    			indians.verifyImageActive(indians.getDatabaseEntryImageSrc("Subarctic Culture Area")));
    	assertTrue("Asserts the correct image shows up for Cree", 
    			indians.getDatabaseEntryImageSrc("Cree").equals("https://assets.pebblego.com/cms_content/images/pgnnative_cree_main_image_article_id_6056.jpg") &&
    			indians.verifyImageActive(indians.getDatabaseEntryImageSrc("Cree")));
    	indians.clickHomeBreadcrumb();
    }
    
    @Test
    public void testAINorthwestCoastCultureArea(){
    	StudentHomePage student = new StudentHomePage(user.getDriver());
    	
    	student.waitImplicitly(1);
    	IndiansDatabaseHome indians = student.goToIndians();
    	indians.clickCategory("Northwest Coast Culture Area");
    	
    	assertTrue("Asserts the correct image shows up for Northwest Coast Culture Area", 
    			indians.getDatabaseEntryImageSrc("Northwest Coast Culture Area").equals("https://assets.pebblego.com/cms_content/images/pgnnative_northwest_coast_culture_area_main_image_article_id_6059.jpg") &&
    			indians.verifyImageActive(indians.getDatabaseEntryImageSrc("Northwest Coast Culture Area")));
    	assertTrue("Asserts the correct image shows up for Chinook", 
    			indians.getDatabaseEntryImageSrc("Chinook").equals("https://assets.pebblego.com/cms_content/images/pgnnative_chinook_main_image_article_id_6060.jpg") &&
    			indians.verifyImageActive(indians.getDatabaseEntryImageSrc("Chinook")));
    	assertTrue("Asserts the correct image shows up for Tlingit", 
    			indians.getDatabaseEntryImageSrc("Tlingit").equals("https://assets.pebblego.com/cms_content/images/pgnnative_tlingit_main_image_article_id_6061.jpg") &&
    			indians.verifyImageActive(indians.getDatabaseEntryImageSrc("Tlingit")));
    	indians.clickHomeBreadcrumb();
    }
    
    @Test
    public void testAIPlateauCultureArea(){
    	StudentHomePage student = new StudentHomePage(user.getDriver());
    	
    	student.waitImplicitly(1);
    	IndiansDatabaseHome indians = student.goToIndians();
    	indians.clickCategory("Plateau Culture Area");
    	
    	assertTrue("Asserts the correct image shows up for Plateau Culture Area", 
    			indians.getDatabaseEntryImageSrc("Plateau Culture Area").equals("https://assets.pebblego.com/cms_content/images/pgnnative_plateau_culture_area_main_image_article_id_6057.jpg") &&
    			indians.verifyImageActive(indians.getDatabaseEntryImageSrc("Plateau Culture Area")));
    	assertTrue("Asserts the correct image shows up for Nez Perce", 
    			indians.getDatabaseEntryImageSrc("Nez Perce").equals("https://assets.pebblego.com/cms_content/images/pgnnative_nez_perce_main_image_article_id_6058.jpg") &&
    			indians.verifyImageActive(indians.getDatabaseEntryImageSrc("Nez Perce")));
    	indians.clickHomeBreadcrumb();
    }
    
    @Test
    public void testAIGreatPlainsCultureAreaCultureArea(){
    	StudentHomePage student = new StudentHomePage(user.getDriver());
    	
    	student.waitImplicitly(1);
    	IndiansDatabaseHome indians = student.goToIndians();
    	indians.clickCategory("Great Plains Culture Area");
    	
    	assertTrue("Asserts the correct image shows up for Great Plains Culture Area", 
    			indians.getDatabaseEntryImageSrc("Great Plains Culture Area").equals("https://assets.pebblego.com/cms_content/images/pgnnative_great_plains_culture_area_main_image_article_id_6075.jpg") &&
    			indians.verifyImageActive(indians.getDatabaseEntryImageSrc("Great Plains Culture Area")));
    	assertTrue("Asserts the correct image shows up for Arapaho", 
    			indians.getDatabaseEntryImageSrc("Arapaho").equals("https://assets.pebblego.com/cms_content/images/pgnnative_arapaho_main_image_article_id_6076.jpg") &&
    			indians.verifyImageActive(indians.getDatabaseEntryImageSrc("Arapaho")));
    	assertTrue("Asserts the correct image shows up for Blackfeet", 
    			indians.getDatabaseEntryImageSrc("Blackfeet").equals("https://assets.pebblego.com/cms_content/images/pgnnative_blackfeet_main_image_article_id_6077.jpg") &&
    			indians.verifyImageActive(indians.getDatabaseEntryImageSrc("Blackfeet")));
    	assertTrue("Asserts the correct image shows up for Cheyenne", 
    			indians.getDatabaseEntryImageSrc("Cheyenne").equals("https://assets.pebblego.com/cms_content/images/pgnnative_cheyenne_main_image_article_id_6078.jpg") &&
    			indians.verifyImageActive(indians.getDatabaseEntryImageSrc("Cheyenne")));
    	assertTrue("Asserts the correct image shows up for Comanche", 
    			indians.getDatabaseEntryImageSrc("Comanche").equals("https://assets.pebblego.com/cms_content/images/pgnnative_comanche_main_image_article_id_6079.jpg") &&
    			indians.verifyImageActive(indians.getDatabaseEntryImageSrc("Comanche")));
    	assertTrue("Asserts the correct image shows up for Crow", 
    			indians.getDatabaseEntryImageSrc("Crow").equals("https://assets.pebblego.com/cms_content/images/pgnnative_crow_main_image_article_id_6080.jpg") &&
    			indians.verifyImageActive(indians.getDatabaseEntryImageSrc("Crow")));
    	assertTrue("Asserts the correct image shows up for Lakota (Teton Sioux)", 
    			indians.getDatabaseEntryImageSrc("Lakota (Teton Sioux)").equals("https://assets.pebblego.com/cms_content/images/pgnnative_lakota_(teton_sioux)_main_image_article_id_6081.jpg") &&
    			indians.verifyImageActive(indians.getDatabaseEntryImageSrc("Lakota (Teton Sioux)")));
    	assertTrue("Asserts the correct image shows up for Osage", 
    			indians.getDatabaseEntryImageSrc("Osage").equals("https://assets.pebblego.com/cms_content/images/pgnnative_osage_main_image_article_id_6082.jpg") &&
    			indians.verifyImageActive(indians.getDatabaseEntryImageSrc("Osage")));
    	assertTrue("Asserts the correct image shows up for Pawnee", 
    			indians.getDatabaseEntryImageSrc("Pawnee").equals("https://assets.pebblego.com/cms_content/images/pgnnative_pawnee_main_image_article_id_6083.jpg") &&
    			indians.verifyImageActive(indians.getDatabaseEntryImageSrc("Pawnee")));
    	assertTrue("Asserts the correct image shows up for Sioux", 
    			indians.getDatabaseEntryImageSrc("Sioux").equals("https://assets.pebblego.com/cms_content/images/pgnnative_sioux_main_image_article_id_6084.jpg") &&
    			indians.verifyImageActive(indians.getDatabaseEntryImageSrc("Sioux")));
    	indians.clickHomeBreadcrumb();
    }
    
    @Test
    public void testAINortheastCultureArea(){
    	StudentHomePage student = new StudentHomePage(user.getDriver());
    	
    	student.waitImplicitly(1);
    	IndiansDatabaseHome indians = student.goToIndians();
    	indians.clickCategory("Northeast Culture Area");
    	
    	assertTrue("Asserts the correct image shows up for Northeast Culture Area", 
    			indians.getDatabaseEntryImageSrc("Northeast Culture Area").equals("https://assets.pebblego.com/cms_content/images/pgnnative_northeast_culture_area_main_image_article_id_6085.jpg") &&
    			indians.verifyImageActive(indians.getDatabaseEntryImageSrc("Northeast Culture Area")));
    	assertTrue("Asserts the correct image shows up for Abenaki", 
    			indians.getDatabaseEntryImageSrc("Abenaki").equals("https://assets.pebblego.com/cms_content/images/pgnnative_abenaki_main_image_article_id_6086.jpg") &&
    			indians.verifyImageActive(indians.getDatabaseEntryImageSrc("Abenaki")));
    	assertTrue("Asserts the correct image shows up for Algonquin", 
    			indians.getDatabaseEntryImageSrc("Algonquin").equals("https://assets.pebblego.com/cms_content/images/pgnnative_algonquin_main_image_article_id_6087.jpg") &&
    			indians.verifyImageActive(indians.getDatabaseEntryImageSrc("Algonquin")));
    	assertTrue("Asserts the correct image shows up for Iroquois", 
    			indians.getDatabaseEntryImageSrc("Iroquois").equals("https://assets.pebblego.com/cms_content/images/pgnnative_iroquois_main_image_article_id_6089.jpg") &&
    			indians.verifyImageActive(indians.getDatabaseEntryImageSrc("Iroquois")));
    	assertTrue("Asserts the correct image shows up for Lenape (Delaware)", 
    			indians.getDatabaseEntryImageSrc("Lenape (Delaware)").equals("https://assets.pebblego.com/cms_content/images/pgnnative_lenape_(delaware)_main_image_article_id_6088.jpg") &&
    			indians.verifyImageActive(indians.getDatabaseEntryImageSrc("Lenape (Delaware)")));
    	assertTrue("Asserts the correct image shows up for Ojibwa", 
    			indians.getDatabaseEntryImageSrc("Ojibwa").equals("https://assets.pebblego.com/cms_content/images/pgnnative_ojibwa_main_image_article_id_6090.jpg") &&
    			indians.verifyImageActive(indians.getDatabaseEntryImageSrc("Ojibwa")));
    	assertTrue("Asserts the correct image shows up for Pequot", 
    			indians.getDatabaseEntryImageSrc("Pequot").equals("https://assets.pebblego.com/cms_content/images/pgnnative_pequot_main_image_article_id_6091.jpg") &&
    			indians.verifyImageActive(indians.getDatabaseEntryImageSrc("Pequot")));
    	assertTrue("Asserts the correct image shows up for Potawatomi", 
    			indians.getDatabaseEntryImageSrc("Potawatomi").equals("https://assets.pebblego.com/cms_content/images/pgnnative_potawatomi_main_image_article_id_6092.jpg") &&
    			indians.verifyImageActive(indians.getDatabaseEntryImageSrc("Potawatomi")));
    	assertTrue("Asserts the correct image shows up for Powhatan", 
    			indians.getDatabaseEntryImageSrc("Powhatan").equals("https://assets.pebblego.com/cms_content/images/pgnnative_powhatan_main_image_article_id_6093.jpg") &&
    			indians.verifyImageActive(indians.getDatabaseEntryImageSrc("Powhatan")));
    	assertTrue("Asserts the correct image shows up for Shawnee", 
    			indians.getDatabaseEntryImageSrc("Shawnee").equals("https://assets.pebblego.com/cms_content/images/pgnnative_shawnee_main_image_article_id_6094.jpg") &&
    			indians.verifyImageActive(indians.getDatabaseEntryImageSrc("Shawnee")));
    	assertTrue("Asserts the correct image shows up for Wampanoag", 
    			indians.getDatabaseEntryImageSrc("Wampanoag").equals("https://assets.pebblego.com/cms_content/images/pgnnative_wampanoag_main_image_article_id_6095.jpg") &&
    			indians.verifyImageActive(indians.getDatabaseEntryImageSrc("Wampanoag")));
    	indians.clickHomeBreadcrumb();    	
    }
    
    @Test
    public void testAIGreatBasinCultureArea(){
    	StudentHomePage student = new StudentHomePage(user.getDriver());
    	
    	student.waitImplicitly(1);
    	IndiansDatabaseHome indians = student.goToIndians();
    	indians.clickCategory("Great Basin Culture Area");
    	
    	assertTrue("Asserts the correct image shows up for Great Basin Culture Area", 
    			indians.getDatabaseEntryImageSrc("Great Basin Culture Area").equals("https://assets.pebblego.com/cms_content/images/pgnnative_great_basin_culture_area_main_image_article_id_6066.jpg") &&
    			indians.verifyImageActive(indians.getDatabaseEntryImageSrc("Great Basin Culture Area")));
    	assertTrue("Asserts the correct image shows up for Shoshone", 
    			indians.getDatabaseEntryImageSrc("Shoshone").equals("https://assets.pebblego.com/cms_content/images/pgnnative_shoshone_main_image_article_id_6067.jpg") &&
    			indians.verifyImageActive(indians.getDatabaseEntryImageSrc("Shoshone")));
    	assertTrue("Asserts the correct image shows up for Ute", 
    			indians.getDatabaseEntryImageSrc("Ute").equals("https://assets.pebblego.com/cms_content/images/pgnnative_ute_main_image_article_id_6068.jpg") &&
    			indians.verifyImageActive(indians.getDatabaseEntryImageSrc("Ute")));
    	indians.clickHomeBreadcrumb();
    }
    
    @Test
    public void testAICaliforniaCultureArea(){
    	StudentHomePage student = new StudentHomePage(user.getDriver());
    	
    	student.waitImplicitly(1);
    	IndiansDatabaseHome indians = student.goToIndians();
    	indians.clickCategory("California Culture Area");
    	
    	assertTrue("Asserts the correct image shows up for California Culture Area", 
    			indians.getDatabaseEntryImageSrc("California Culture Area").equals("https://assets.pebblego.com/cms_content/images/pgnnative_california_culture_area_main_image_article_id_6062.jpg") &&
    			indians.verifyImageActive(indians.getDatabaseEntryImageSrc("California Culture Area")));
    	assertTrue("Asserts the correct image shows up for Chumash", 
    			indians.getDatabaseEntryImageSrc("Chumash").equals("https://assets.pebblego.com/cms_content/images/pgnnative_chumash_main_image_article_id_6063.jpg") &&
    			indians.verifyImageActive(indians.getDatabaseEntryImageSrc("Chumash")));
    	assertTrue("Asserts the correct image shows up for Coast Miwok", 
    			indians.getDatabaseEntryImageSrc("Coast Miwok").equals("https://assets.pebblego.com/cms_content/images/pgnnative_coast_miwok_main_image_article_id_6064.jpg") &&
    			indians.verifyImageActive(indians.getDatabaseEntryImageSrc("Coast Miwok")));
    	assertTrue("Asserts the correct image shows up for Pomo", 
    			indians.getDatabaseEntryImageSrc("Pomo").equals("https://assets.pebblego.com/cms_content/images/pgnnative_pomo_main_image_article_id_6065.jpg") &&
    			indians.verifyImageActive(indians.getDatabaseEntryImageSrc("Pomo")));
    	indians.clickHomeBreadcrumb();
    }
    
    @Test
    public void testAISouthwestCultureArea(){
    	StudentHomePage student = new StudentHomePage(user.getDriver());
    	
    	student.waitImplicitly(1);
    	IndiansDatabaseHome indians = student.goToIndians();
    	indians.clickCategory("Southwest Culture Area");
    	
    	assertTrue("Asserts the correct image shows up for Southwest Culture Area", 
    			indians.getDatabaseEntryImageSrc("Southwest Culture Area").equals("https://assets.pebblego.com/cms_content/images/pgnnative_southwest_culture_area_main_image_article_id_6069.jpg") &&
    			indians.verifyImageActive(indians.getDatabaseEntryImageSrc("Southwest Culture Area")));
    	assertTrue("Asserts the correct image shows up for Apache", 
    			indians.getDatabaseEntryImageSrc("Apache").equals("https://assets.pebblego.com/cms_content/images/pgnnative_apache_main_image_article_id_6070.jpg") &&
    			indians.verifyImageActive(indians.getDatabaseEntryImageSrc("Apache")));
    	assertTrue("Asserts the correct image shows up for Hopi", 
    			indians.getDatabaseEntryImageSrc("Hopi").equals("https://assets.pebblego.com/cms_content/images/pgnnative_hopi_main_image_article_id_6071.jpg") &&
    			indians.verifyImageActive(indians.getDatabaseEntryImageSrc("Hopi")));
    	assertTrue("Asserts the correct image shows up for Navajo", 
    			indians.getDatabaseEntryImageSrc("Navajo").equals("https://assets.pebblego.com/cms_content/images/pgnnative_navajo_main_image_article_id_6072.jpg") &&
    			indians.verifyImageActive(indians.getDatabaseEntryImageSrc("Navajo")));
    	assertTrue("Asserts the correct image shows up for Pueblo", 
    			indians.getDatabaseEntryImageSrc("Pueblo").equals("https://assets.pebblego.com/cms_content/images/pgnnative_pueblo_main_image_article_id_6073.jpg") &&
    			indians.verifyImageActive(indians.getDatabaseEntryImageSrc("Pueblo")));
    	assertTrue("Asserts the correct image shows up for Zuni", 
    			indians.getDatabaseEntryImageSrc("Zuni").equals("https://assets.pebblego.com/cms_content/images/pgnnative_zuni_main_image_article_id_6074.jpg") &&
    			indians.verifyImageActive(indians.getDatabaseEntryImageSrc("Zuni")));
    	indians.clickHomeBreadcrumb();
    }
    
    @Test
    public void testAISoutheastCultureArea(){
    	StudentHomePage student = new StudentHomePage(user.getDriver());
    	
    	student.waitImplicitly(1);
    	IndiansDatabaseHome indians = student.goToIndians();
    	indians.clickCategory("Southeast Culture Area");
    	
    	assertTrue("Asserts the correct image shows up for Southeast Culture Area", 
    			indians.getDatabaseEntryImageSrc("Southeast Culture Area").equals("https://assets.pebblego.com/cms_content/images/pgnnative_southeast_culture_area_main_image_article_id_6096.jpg") &&
    			indians.verifyImageActive(indians.getDatabaseEntryImageSrc("Southeast Culture Area")));
    	assertTrue("Asserts the correct image shows up for Cherokee", 
    			indians.getDatabaseEntryImageSrc("Cherokee").equals("https://assets.pebblego.com/cms_content/images/pgnnative_cherokee_main_image_article_id_6097.jpg") &&
    			indians.verifyImageActive(indians.getDatabaseEntryImageSrc("Cherokee")));
    	assertTrue("Asserts the correct image shows up for Chickasaw", 
    			indians.getDatabaseEntryImageSrc("Chickasaw").equals("https://assets.pebblego.com/cms_content/images/pgnnative_chickasaw_main_image_article_id_6098.jpg") &&
    			indians.verifyImageActive(indians.getDatabaseEntryImageSrc("Chickasaw")));
    	assertTrue("Asserts the correct image shows up for Choctaw", 
    			indians.getDatabaseEntryImageSrc("Choctaw").equals("https://assets.pebblego.com/cms_content/images/pgnnative_choctaw_main_image_article_id_6099.jpg") &&
    			indians.verifyImageActive(indians.getDatabaseEntryImageSrc("Choctaw")));
    	assertTrue("Asserts the correct image shows up for Creek", 
    			indians.getDatabaseEntryImageSrc("Creek").equals("https://assets.pebblego.com/cms_content/images/pgnnative_creek_main_image_article_id_6100.jpg") &&
    			indians.verifyImageActive(indians.getDatabaseEntryImageSrc("Creek")));
    	assertTrue("Asserts the correct image shows up for Seminole", 
    			indians.getDatabaseEntryImageSrc("Seminole").equals("https://assets.pebblego.com/cms_content/images/pgnnative_seminole_main_image_article_id_6101.jpg") &&
    			indians.verifyImageActive(indians.getDatabaseEntryImageSrc("Seminole")));
    	indians.clickHomeBreadcrumb();
    }
    
    @Test
    public void testCultureAreasArticCultureArea(){
    	StudentHomePage student = new StudentHomePage(user.getDriver());
    	
    	student.waitImplicitly(1);
    	IndiansDatabaseHome indians = student.goToIndians();
    	indians.clickCategory("Culture Areas");
    	
    	assertTrue("Asserts the correct image shows up for Arctic Culture Area", 
    			indians.getDatabaseEntryImageSrc("Arctic Culture Area").equals("https://assets.pebblego.com/cms_content/images/pgnnative_arctic_culture_area_main_image_article_id_6053.jpg") &&
    			indians.verifyImageActive(indians.getDatabaseEntryImageSrc("Arctic Culture Area")));
    	assertTrue("Asserts the correct image shows up for California Culture Area", 
    			indians.getDatabaseEntryImageSrc("California Culture Area").equals("https://assets.pebblego.com/cms_content/images/pgnnative_california_culture_area_main_image_article_id_6062.jpg") &&
    			indians.verifyImageActive(indians.getDatabaseEntryImageSrc("California Culture Area")));
    	assertTrue("Asserts the correct image shows up for Great Basin Culture Area", 
    			indians.getDatabaseEntryImageSrc("Great Basin Culture Area").equals("https://assets.pebblego.com/cms_content/images/pgnnative_great_basin_culture_area_main_image_article_id_6066.jpg") &&
    			indians.verifyImageActive(indians.getDatabaseEntryImageSrc("Great Basin Culture Area")));
    	assertTrue("Asserts the correct image shows up for Great Plains Culture Area", 
    			indians.getDatabaseEntryImageSrc("Great Plains Culture Area").equals("https://assets.pebblego.com/cms_content/images/pgnnative_great_plains_culture_area_main_image_article_id_6075.jpg") &&
    			indians.verifyImageActive(indians.getDatabaseEntryImageSrc("Great Plains Culture Area")));
    	assertTrue("Asserts the correct image shows up for Northwest Coast Culture Area", 
    			indians.getDatabaseEntryImageSrc("Northwest Coast Culture Area").equals("https://assets.pebblego.com/cms_content/images/pgnnative_northwest_coast_culture_area_main_image_article_id_6059.jpg") &&
    			indians.verifyImageActive(indians.getDatabaseEntryImageSrc("Northwest Coast Culture Area")));
    	assertTrue("Asserts the correct image shows up for Northeast Culture Area", 
    			indians.getDatabaseEntryImageSrc("Northeast Culture Area").equals("https://assets.pebblego.com/cms_content/images/pgnnative_northeast_culture_area_main_image_article_id_6085.jpg") &&
    			indians.verifyImageActive(indians.getDatabaseEntryImageSrc("Northeast Culture Area")));
    	assertTrue("Asserts the correct image shows up for Plateau Culture Area", 
    			indians.getDatabaseEntryImageSrc("Plateau Culture Area").equals("https://assets.pebblego.com/cms_content/images/pgnnative_plateau_culture_area_main_image_article_id_6057.jpg") &&
    			indians.verifyImageActive(indians.getDatabaseEntryImageSrc("Plateau Culture Area")));
    	assertTrue("Asserts the correct image shows up for Southwest Culture Area", 
    			indians.getDatabaseEntryImageSrc("Southwest Culture Area").equals("https://assets.pebblego.com/cms_content/images/pgnnative_southwest_culture_area_main_image_article_id_6069.jpg") &&
    			indians.verifyImageActive(indians.getDatabaseEntryImageSrc("Southwest Culture Area")));
    	assertTrue("Asserts the correct image shows up for Southeast Culture Area", 
    			indians.getDatabaseEntryImageSrc("Southeast Culture Area").equals("https://assets.pebblego.com/cms_content/images/pgnnative_southeast_culture_area_main_image_article_id_6096.jpg") &&
    			indians.verifyImageActive(indians.getDatabaseEntryImageSrc("Southeast Culture Area")));
    	assertTrue("Asserts the correct image shows up for Subarctic Culture Area", 
    			indians.getDatabaseEntryImageSrc("Subarctic Culture Area").equals("https://assets.pebblego.com/cms_content/images/pgnnative_subarctic_culture_area_main_image_article_id_6055.jpg") &&
    			indians.verifyImageActive(indians.getDatabaseEntryImageSrc("Subarctic Culture Area")));
    	indians.clickHomeBreadcrumb();
    }
}
