package TrueUserTests.TrueUser;


import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Date;

import org.apache.tools.ant.taskdefs.optional.junit.JUnitResultFormatter;
import org.apache.tools.ant.taskdefs.optional.junit.JUnitTest;
import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;

import junit.framework.TestResult;

public class ExecutionListener extends RunListener {
	
    protected final JUnitResultFormatter formatter;
    private ByteArrayOutputStream stdout,stderr;
    private PrintStream oldStdout,oldStderr;
    private int problem;
    private long startTime;
	

    public ExecutionListener(JUnitResultFormatter formatter) {
        this.formatter = formatter;
    }
    
	  /**
     * Called before any tests have been run.
     * */
    @Override
    public void testRunStarted(Description description) throws java.lang.Exception
    {
        System.out.println(" ====================================== "+ new Date() + " === Number of tests to execute : " + description.testCount());
    }
 
    /**
     *  Called when all tests have finished
     * */
    @Override
    public void testRunFinished(Result result) throws java.lang.Exception
    {
        System.out.println(" ====================================== "+ new Date() + " === Number of tests executed : " + result.getRunCount());
    }
 
    /**
     *  Called when an atomic test is about to be started.
     * */
    @Override
    public void testStarted(Description description) throws java.lang.Exception
    {
        System.out.println(" ====================================== "+ new Date() + " === Starting execution of test case : "+ description.getMethodName());
        formatter.startTestSuite(new JUnitTest(description.getDisplayName()));
        formatter.startTest(new DescriptionAsTest(description));
        problem = 0;
        startTime = System.currentTimeMillis();

        this.oldStdout = System.out;
        this.oldStderr = System.err;
        System.setOut(new PrintStream(stdout = new ByteArrayOutputStream()));
        System.setErr(new PrintStream(stderr = new ByteArrayOutputStream()));
    }
 
    /**
     *  Called when an atomic test has finished, whether the test succeeds or fails.
     * */
    @Override
    public void testFinished(Description description) throws java.lang.Exception
    {
        System.out.println(" ====================================== "+ new Date() + " === Finished execution of test case : "+ description.getMethodName());
        System.out.flush();
        System.err.flush();
        System.setOut(oldStdout);
        System.setErr(oldStderr);

        formatter.setSystemOutput(stdout.toString());
        formatter.setSystemError(stderr.toString());
        formatter.endTest(new DescriptionAsTest(description));

        JUnitTest suite = new JUnitTest(description.getDisplayName());
        suite.setCounts(1,problem,0);
        suite.setRunTime(System.currentTimeMillis()-startTime);
        formatter.endTestSuite(suite);
    
    }
 
    /**
     *  Called when an atomic test fails.
     * */
    @Override
    public void testFailure(Failure failure) throws java.lang.Exception
    {
     //   System.out.println("Execution of test case failed : "+ failure.getMessage());
        System.out.println(" ====================================== "+ new Date() + " === Execution of test case failed :" + failure.getDescription().getMethodName());
        testAssumptionFailure(failure);
    
    }
 
    /**
     *  Called when a test will not be run, generally because a test method is annotated with Ignore.
     * */
    @Override
    public void testIgnored(Description description) throws java.lang.Exception
    {
        System.out.println(" ====================================== "+ new Date() + " === Execution of test case ignored : "+ description.getMethodName());
        super.testIgnored(description);
    }
    
    @Override
    public void testAssumptionFailure(Failure failure) {
        problem++;
        formatter.addError(new DescriptionAsTest(failure.getDescription()), failure.getException());
    }
}

