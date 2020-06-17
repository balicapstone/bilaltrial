package UserClasses;

public enum UserInfo {
	GARAGESTUDENT("trexstudent", "sm4ll4rms", LoginType.STUDENT, true, true, true, true, true,
			"fc60e391d07d9680077f1150481a0b5036eba2d037bb2708f107398217d0655a"), 
	CIGARAGE("trexreader", "sm4ll4rms", LoginType.STUDENT, true, true, true, true, true), 
	GARAGETEACHER("trexteacher", "sm4ll4rms", LoginType.TEACHER, false, false, true, true, false,
			"6537f6f637db185ecb320f018bb902d7ed63d9f2646322c8ca9a380cee14c9c7"), 
	PGOSTUDENT("pgoonly","NvDDe4FQ", LoginType.STUDENT, false, true, true, false, false,
			"13e5ce1f7698fda3d9e60f4612bd19958a3b4e7ed32933641f9e4bd58282ddea"), 
	PGOTEACHER("pgoteach", "NvDDe4FQ",LoginType.TEACHER, false, false, true, true, false,
			"867defd0866eeae03750b09798a663d5e57af8e54f9f194e5b8a8666deca2269"), 
	PGNSTUDENT("pgnstudent", "pFUPpr8j", LoginType.STUDENT, true, true, false, true, false,
			"f5f03fb7d09732e8f7fb5b79348ecde0b16bcf54ffedce908cfdf3992bf648b4"), 
	PGNTEACHER("pgnteach","pFUPpr8j", LoginType.TEACHER, false, false, true, true, false,
			"24d6b39e34b2e3f5c3bb5852f414b2cd5f21b0e49efbb62dea0e8e7a68db0cf3"), 
	MASTERACCOUNT("trexmaster", "sm4ll4rms", LoginType.MASTER, true, true, true, true, true,
			"$2y$10$FhU086IK6Nmg2ei5zBOV9eBVuaFRCGVcuFTljk0dFDrxirX1HSYI."),
	MASTERGOOGLE("teacher_M92O-7okEccODuOrgpDk@classroom-dev.com", "t@c07im3!", LoginType.GOOGLE, false, true, true, true, true),
	GOOGLEERROR("capstoneglobalgarage@gmail.com", "testingisfun", LoginType.GOOGLE, false, true, true, true, true),
	EXPIRED("expired", "fDw4v6mA", LoginType.EXPIRED, false, false, false, false, false), 
	DISTRICTADMIN("trexadmin", "sm4ll4rms", LoginType.ADMIN, false, false, true, true, false),
	ADMIN("trexadmin", "sm4ll4rms", LoginType.ADMIN, false, false, true, true, false), 
	CIONLY("cistudent", "w5N7j3rM", LoginType.STUDENT, false, true, false, false, true,
			"a275a7bead3d3188c16bc8797b751e9a744c204d930890a8dc1a20bace1c23c7"), 
	CIONLYTEACHER("citeacher", "w5N7j3rM", LoginType.STUDENT, false, true, false, false, true),
	CISTUDENT("trexreader", "sm4ll4rms", LoginType.STUDENT, true, true, false, false, true), 
	CITEACHER("trexbuildingadmin", "sm4ll4rms", LoginType.TEACHER, false, false, false, false, true),
	VISUALSEARCH("visual", "ufrnLCo9", LoginType.STUDENT, false, true, false, false, true), 
	NOLINKCI("nolinkci", "WUgF9Em3", LoginType.STUDENT, false, true, false, false, true), 
	NOLINKPGO("nolinkpgo", "PyuJfv6h", LoginType.STUDENT, false, true, true, false, false), 
	NOLINKPGN("nolinkpgn", "Hj9Bg2LT", LoginType.STUDENT, false, true, false, true, false), 
	NOLINKCITEACHER("nolinkciadmin", "WUgF9Em3", LoginType.TEACHER, false, false, false, false, true),
	NOLINKPGOTEACHER("nolinkpgoteach", "PyuJfv6h", LoginType.TEACHER, false, false, true, false, false),
	NOLINKPGNTEACHER("nolinkpgnteach", "Hj9Bg2LT", LoginType.TEACHER, false, false, false, true, false), 
	CIONLYMASTER("cimaster", "8EKuBhgN", LoginType.MASTER, true, true, false, false, true), 
	PGOONLYMASTER("pgoonly", "MXu5nqCE", LoginType.MASTER, true, true, true, false, false,
			"$2y$10$q40y4L.vNncStYKAFnIAUOWfhpNVzHuQVM3krIITmWVg3T8nMCTKO"), 
	PGNONLYMASTER("pgnstudent","L2TPimKE",LoginType.MASTER, true, true, false, true, false,
			"$2y$10$MH/hMgdqz6TdslDAFiMyuOrQgYancnlStmt5tE0tmsRFXxrArMH7G"),
	DELETEDMASTER("deletedmaster", "capdig", LoginType.MASTER, true, true, true, true, true), 
	CIDONT("cidont", "gCrk3pai", LoginType.STUDENT, false, true, false, false, true), 
	CIDONTTEACHER("cidontteacher", "gCrk3pai", LoginType.TEACHER, false, false, false, false, true),
	PGODONT("pgodont", "E24RhdvR", LoginType.STUDENT, false, true, true, false, false), 
	PGODONTTEACHER("pgodontteacher", "E24RhdvR", LoginType.TEACHER, false, false, true, false, false), 
	PGNDONT("pgndont", "9Z3syV2o", LoginType.STUDENT, false, true, false, true, false),
	PGNDONTTEACHER("pgndontteacher", "9Z3syV2o", LoginType.TEACHER, false, false, false, true, false),
	CRUDMASTERWILL("whall@capstonepub.com", "whcap0546", LoginType.MASTER, false, false, false, false, false), 
	NONOLESSONPLANS("TeacherLP", "l3ss0ns", LoginType.NONONSENSE, false, false, false, false, false), 
	NONOASSESSMENTS("TeacherA", "4ss3m3nts", LoginType.NONONSENSE, false, false, false, false, false), 
	NONOUK("NoNoUK", "r34ding", LoginType.NONONSENSE, false, false, false, false, false), 
	NONODONTSIGN("NoNoDont", "d0nt", LoginType.NONONSENSE, false, false, false, false, false), 
	CISUPERADMIN("whall@capstonepub.com", "books", LoginType.SUPERADMIN, false, false, false, false, false),
	PGOSUPERADMIN("whall@capstonedigital.com", "pebble", LoginType.SUPERADMIN, false, false, false, false, false),
	DELETEBUILDINGADMIN("deletebuildingadmin", "jdd27TZw", LoginType.ADMIN, false, false, false, false, false),
	DELETEBUILDINGSTUDENT("teststudentguy", "jdd27TZw", LoginType.STUDENT, false, true, false, false, false),
	DIFFERENTTEACHER("differentAdmin", "Fpi2GUsH", LoginType.TEACHER, false, false, false, false, true),
	GAMESTEST("games", "sm4ll4rms", LoginType.STUDENT, false, true, true, true, false), 
	DISABLESTUDENT("disable", "8XjFr2gt", LoginType.STUDENT, false, true, true, true, false),
	DISABLETEACHER("disableTeacher", "gehCjzE6", LoginType.TEACHER, false, false, true, true, false),
	ACCESSTABTEACHER("AccessTeacher", "WAkkcPM8", LoginType.TEACHER, false, false, true, true, false),
	ACCESSTABSTUDENT("AccessStudent", "6ivJjj4X", LoginType.STUDENT, false, true, true, true, false),
	BANNEDTEXASTEACHER("bannedTexasTeacher", "6PiuZY5X", LoginType.TEACHER, false, false, true, true, false),
	BANNEDTEXASSTUDENT("bannedTexasStudent", "6PiuZY5X", LoginType.STUDENT, false, true, true, true, false),
	EDITACCOUNTADMIN("EditAccountAdmin", "WWdLNWh2", LoginType.TEACHER, false, false, false, false, true),
	EDITACCOUNTSTUDENT("EditAccountStudent", "WWdLNWh2", LoginType.STUDENT, false, true, false, false, true),
	ANIMALSRMSTUDENT("animalsrm", "e4Dx7bLm", LoginType.STUDENT, false, true, true, false, false),
	SCIENCERMSTUDENT("sciencerm", "Ya6SBvmn", LoginType.STUDENT, false, true, true, false, false),
	PGOTESTINGSCHOOL("pgoTestingSchool", "ZGv7jZUt", LoginType.STUDENT, true, true, true, true, true),
	PGOTESTINGTEACHER("pgoTestingTeacher", "ZGv7jZUt", LoginType.TEACHER, true, true, true, true, true),
	CITESTINGSCHOOL("citestingstudent", "8jLXgRLN", LoginType.STUDENT, true, true, true, true, true),
	MASTERTESTINGSCHOOL("pgotestingmaster", "ojNcd8wU", LoginType.MASTER, true, true, true, true, true),
	
	
	SCIENCESTUDENT("ScienceStudent2", "5moYgiAP", LoginType.STUDENT, false, true, false, true, false), 
	STATESSTUDENT("StatesStudent2", "gPScabm4", LoginType.STUDENT, false, true, false, true, false); 
	
	private String username;
	private String password;
	private LoginType loginType;
	private Boolean linked;
	private Boolean isStudent;
	private Boolean pgoSubscription;
	private Boolean pgnSubscription;
	private Boolean ciSubscription;
	private String sQS;
	
	private UserInfo(String u, String p, LoginType type, Boolean l, Boolean s, Boolean pgo, Boolean pgn, Boolean ci){
		username = u;
		password = p;
		loginType = type; 
		linked = l;
		isStudent = s;
		pgoSubscription = pgo;
		pgnSubscription = pgn;
		ciSubscription = ci;
	}
	
	private UserInfo(String u, String p, LoginType type, Boolean l, Boolean s, Boolean pgo, Boolean pgn, Boolean ci, String sqs){
		username = u;
		password = p;
		loginType = type; 
		linked = l;
		isStudent = s;
		pgoSubscription = pgo;
		pgnSubscription = pgn;
		ciSubscription = ci;
		sQS = sqs;
	}
	
	public String getUserName(){
		return username;
	}
	
	public String getPassword(){
		return password;
	}
	
	public LoginType getType(){
		return loginType;
	}
	
	public Boolean isLinked(){
		return linked;
	}
	
	public Boolean isStudent(){
		return isStudent;
	}
	
	public Boolean hasPGOSubscription(){
		return pgoSubscription;
	}
	
	public Boolean hasPGNSubscription(){
		return pgnSubscription;
	}
	
	public Boolean hasCISubscription(){
		return ciSubscription;
	}
	
	public String getSQS(){
		return sQS;
	}
}
