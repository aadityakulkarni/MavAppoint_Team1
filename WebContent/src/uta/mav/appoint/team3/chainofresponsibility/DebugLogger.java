package uta.mav.appoint.team3.chainofresponsibility;

public class DebugLogger extends AbstractLogger {

	public DebugLogger(int level) {
		this.level = level;
	}

	@Override
	protected void write(String message) {
		logger.debug(message);
	}
}