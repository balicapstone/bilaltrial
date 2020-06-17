package TrueUserTests.TrueUser;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import Tests.PGNTrialPageTests;
import Tests.PGOTrialPageTests;

@RunWith(Suite.class)

@SuiteClasses({PGOTrialPageTests.class, PGNTrialPageTests.class})

public class TrialTests {

}
