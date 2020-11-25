package moo.restservice;

public interface GameLogic {

	void generateAnswerKey();

	String checkGuess(String answerKey, String guess);

	boolean isCorrectGuess(String answerKey, String guess);

}