package moo.restservice;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

@Service
@SessionScope
public class MooService implements GameLogic {

	private String answerKey;
	private Integer	numOfGuesses = 0;
	private Integer playerId;
	private List<GuessFeedbackPair> guessFeedbackPairs = new ArrayList<>();	
	
	@Autowired
	Logger logger;
	
	public MooService() {
		System.out.println("HEJ från konstruktorn");
	}

	@Override
	@PostConstruct							
	public void generateAnswerKey() {
		String answerKey = "";
		String randomDigit = "";
		int randomNum = 0;

		for (int i = 0; i < 4; i++) {
			do {
				randomNum = RandomGenerator.getRandomNumber(0, 9);
				randomDigit = String.valueOf(randomNum);
			} while (answerKey.contains(randomDigit));
			answerKey = answerKey + randomDigit;
		}
		logger.info(String.format("Facit: %s", answerKey));
		this.answerKey = answerKey;
	}

	@Override
	public String checkGuess(String answerKey, String guess) {
		int cows = 0, bulls = 0;
		for (int i = 0; i < answerKey.length(); i++) {
			for (int j = 0; j < answerKey.length(); j++) {
				try {
					if (answerKey.charAt(i) == guess.charAt(j)) {
						if (i == j) {
							bulls++;
						} else {
							cows++;
						}
					}
				} catch (StringIndexOutOfBoundsException e) {
					throw new StringIndexOutOfBoundsException("MooLogic - Error in checkBullsCows()" + e);
				}
			}
		}
		String feedback = "";
		for (int i = 0; i < bulls; i++) {
			feedback = feedback + "B";
		}
		feedback = feedback + ",";
		for (int i = 0; i < cows; i++) {
			feedback = feedback + "C";
		}
		addGuessFeedbackPairToList(guess, feedback);
		numOfGuesses++;
		return feedback;
	}

	@Override
	public boolean isIncorrectGuess(String answerKey, String guess) {
		return !(answerKey.equalsIgnoreCase(guess));
	}
	
	public void resetIfCorrect(String guess) {
		if (!isIncorrectGuess(answerKey, guess)) {
			guessFeedbackPairs.clear();
			setNumOfGuesses(0);
			generateAnswerKey();
		}
	}
	
	private void addGuessFeedbackPairToList(String guess, String feedback) {
		GuessFeedbackPair guessFeedbackPair = new GuessFeedbackPair(guess, feedback);
		guessFeedbackPairs.add(guessFeedbackPair);
	}
	
	public List<GuessFeedbackPair> getGuessFeedbackPairs() {
		return guessFeedbackPairs;
	}
	
	public String getAnswerKey() {
		return answerKey;
	}

	public Integer getNumOfGuesses() {
		return numOfGuesses;
	}
	
	public void setNumOfGuesses(Integer numOfGuesses) {
		this.numOfGuesses = numOfGuesses;
	}
	
	public Integer getPlayerId() {
		return playerId;
	}
	
	public void setPlayerId(Integer playerId) {
		this.playerId = playerId;
	}
}
