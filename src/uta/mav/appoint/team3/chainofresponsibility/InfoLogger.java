package uta.mav.appoint.team3.chainofresponsibility;

public class InfoLogger extends AbstractLogger {

	public InfoLogger(int level) {
		this.level = level;
	}

	@Override
	protected void write(String message) {
		logger.info(message);
	}
}