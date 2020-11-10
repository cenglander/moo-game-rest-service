package moo.restservice;

public class GuessFeedbackPair {

	private String guess;
	private String feedback;
	
	public GuessFeedbackPair(String guess, String feedback) {
		this.guess = guess;
		this.feedback = feedback;
	}
	
	public String getGuess() {
		return guess;
	}

	public String getFeedback() {
		return feedback;
	}
	
}
