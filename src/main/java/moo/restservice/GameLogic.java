package moo.restservice;

public interface GameLogic {

	void generateAnswerKey();

	String checkGuess(String answerKey, String guess);

	boolean isIncorrectGuess(String answerKey, String guess);

}