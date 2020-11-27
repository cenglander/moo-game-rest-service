package moo.restservice;

public class GuessFeedbackPair {

	private Guess guess;
	private String feedback;

	public GuessFeedbackPair() {
	}

	public GuessFeedbackPair(Guess guess, String feedback) {
		this.guess = guess;
		this.feedback = feedback;
	}

	public Guess getGuess() {
		return guess;
	}

	public String getFeedback() {
		return feedback;
	}

}
