package TrueUserTests.TrueUser;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class Retry implements TestRule{
	private int retryCount;
	
	public Retry(int retryCount){
		this.retryCount = retryCount;
	}
	
	public Statement apply(Statement base, Description description){
		return statement(base, description);
	}
	
	private Statement statement(final Statement base, final Description description){
		return new Statement(){
			public void evaluate() throws Throwable{
				Throwable caughtThrowable = null;			
				for(int i = 0; i < retryCount; i++){
					try{
						base.evaluate();
						return;
					} catch(Throwable t){
						caughtThrowable = t;
						System.out.println(description.getDisplayName() + " is restarting");
					}
				}						
				throw caughtThrowable;
			}
		};
	}
}