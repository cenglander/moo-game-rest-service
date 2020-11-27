package moo.restservice;

public interface GameLogic {

	void generateAnswerKey();

	String checkGuess(String answerKey, Guess guess);

	boolean isCorrectGuess(String answerKey, Guess guess);

}