package uta.mav.appoint.team3.chainofresponsibility;

import org.apache.log4j.Logger;

/**
 * Logger: Chain of responsibility pattern
 * @author Ruchi.U
 *
 */
public abstract class AbstractLogger {
	
	public static int INFO = 1;
	public static int DEBUG = 2;
	public static int ERROR = 3;
	protected int level;
	
	//next element in chain or responsibility
	protected AbstractLogger nextLogger;
	protected Logger logger = Logger.getLogger(this.getClass());
	
	public void setNextLogger(AbstractLogger nextLogger){
		this.nextLogger = nextLogger;
	}
	
	public void logMessage(int level, String message){
		if(this.level <= level){
			write(message);
		}
		if(nextLogger !=null){
			nextLogger.logMessage(level, message);
		}
	}
	
	abstract protected void write(String message);
	
	public static AbstractLogger getInstance(){
		AbstractLogger errorLogger = new ErrorLogger(AbstractLogger.ERROR);
		AbstractLogger debugLogger = new DebugLogger(AbstractLogger.DEBUG);
		AbstractLogger infoLogger = new InfoLogger(AbstractLogger.INFO);
		
		errorLogger.setNextLogger(debugLogger);
		debugLogger.setNextLogger(infoLogger);
		
		return errorLogger;	
	}

}
