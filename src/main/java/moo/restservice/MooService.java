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
	private int	numOfGuesses = 0;
	private Player player;
	private boolean isLoggedIn;
	private List<GuessFeedbackPair> guessFeedbackPairs = new ArrayList<>();	
	
	@Autowired
	Logger logger;
	
	@Autowired
	PlayerRepository playerRepository;
	
	public MooService() {
		System.out.println("HEJ från konstruktorn");
	}

	public boolean login(String name) {
		List<Player> players = playerRepository.findByName(name);
		if (players.size() > 0) {
			player = players.get(0);
			isLoggedIn = true;
			return true;
		} else {
			return false;
		}
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

	public List<GuessFeedbackPair> handleGuess(String guess) {
		if (!isLoggedIn) return null;
		String feedback = checkGuess(getAnswerKey(), guess);
		guessFeedbackPairs.add(new GuessFeedbackPair(guess, feedback));
		List<GuessFeedbackPair> temp = new ArrayList<>(guessFeedbackPairs);
		numOfGuesses++;
		resetIfCorrect(guess);
		return temp;
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
					throw new StringIndexOutOfBoundsException("MooService - Error in checkGuess()" + e);
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
		return feedback;
	}
	
	@Override
	public boolean isCorrectGuess(String answerKey, String guess) {
		return answerKey.equalsIgnoreCase(guess);
	}
	
	public void resetIfCorrect(String guess) {
		if (isCorrectGuess(answerKey, guess)) {
			registerResult();
			guessFeedbackPairs.clear();
			setNumOfGuesses(0);
			generateAnswerKey();
		}
	}
	
	private void registerResult() {
		logger.info(String.format("PlayerId: %s", player.getId()));
		logger.info(String.format("NumOfGuesses: %s", numOfGuesses));
		player.addResult(numOfGuesses);
		playerRepository.save(player);	
	}
	
	public List<GuessFeedbackPair> getGuessFeedbackPairs() {
		return guessFeedbackPairs;
	}
	
	public String getAnswerKey() {
		return answerKey;
	}

	public int getNumOfGuesses() {
		return numOfGuesses;
	}
	
	public void setNumOfGuesses(int numOfGuesses) {
		this.numOfGuesses = numOfGuesses;
	}
	
//	public Integer getPlayerId() {
//		return playerId;
//	}
//	
//	public void setPlayerId(Integer playerId) {
//		this.playerId = playerId;
//	}
}
