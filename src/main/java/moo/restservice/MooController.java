package moo.restservice;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Tuple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/moo")
public class MooController {

	@Autowired
	MooService mooService;

	@Autowired
	PlayerRepository playerRepository;

	@Autowired
	ResultRepository resultRepository;

	@GetMapping("/login/{name}")
	public @ResponseBody Integer findByPlayer(@PathVariable String name) {
		List<Player> players = playerRepository.findByName(name);
		Integer playerId = players.get(0).getId();
		mooService.setPlayerId(playerId);
		System.out.println(mooService.getPlayerId());
		return playerId;
	}

	@PostMapping("/guess/{guess}")
	public ResponseEntity<List> guessFeedbackPair(@PathVariable("guess") String guess) {
		new GuessFeedbackPair(guess, mooService.checkGuess(mooService.getAnswerKey(), guess));
		List<GuessFeedbackPair> guessFeedbackPairs = new ArrayList<>(mooService.getGuessFeedbackPairs());
		System.out.println("num of guesses: " + mooService.getNumOfGuesses() + "\tid: " + mooService.getPlayerId());
		if (!mooService.isIncorrectGuess(mooService.getAnswerKey(), guess)) {
			Result result = new Result();
			result.setPlayer(mooService.getPlayerId());
			result.setResult(mooService.getNumOfGuesses());
			resultRepository.save(result);
		}
		mooService.resetIfCorrect(guess);
		return ResponseEntity.accepted().body(guessFeedbackPairs);
	}

//	@GetMapping("/topTen")
//	public @ResponseBody List<PlayerAverage> getTopTen() {
//		List<PlayerAverage> topTen = playerRepository.getPlayerAverageTopTen();
//		return topTen;
//	}
	
	@GetMapping("/topList")
	public @ResponseBody List<PlayerAverage> getTopTen() {
		List<PlayerAverage> topList = playerRepository.getTopList();
		return topList;
	}
	
//	@GetMapping("/average")
//	public @ResponseBody List<Double> getTotalAverage() {
//		List<Double> averages = playerRepository.getAllPlayersAverage();
//		return averages;
//	}

	@GetMapping("/all_players")
	public @ResponseBody Iterable<Player> getAllPlayers() {
		return playerRepository.findAll();
	}

	@GetMapping("/all_results")
	public @ResponseBody Iterable<Result> getAllResults() {
		return resultRepository.findAll();
	}
	
	@PostMapping("/body")
	public ResponseEntity<List> guessFeedbackPairBody(@RequestBody String guess) {
		new GuessFeedbackPair(guess, mooService.checkGuess(mooService.getAnswerKey(), guess));
		return ResponseEntity.accepted().body(mooService.getGuessFeedbackPairs());
	}

}
