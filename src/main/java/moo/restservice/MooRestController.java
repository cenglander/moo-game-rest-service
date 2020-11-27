package moo.restservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
public class MooRestController {

	@Autowired
	MooService mooService;

	@Autowired
	PlayerRepository playerRepository;

	@Autowired
	ResultRepository resultRepository;

	@GetMapping("/login/{name}")
	public ResponseEntity<String> findPlayerByName(@PathVariable String name) {
		if (mooService.login(name)) {
			return ResponseEntity.accepted().body("You are now logged in and may proceed to play.");
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Please register an account.");
		}
	}

	@PostMapping("/guess/{guess}")
	public ResponseEntity<List> guessFeedbackPair(@PathVariable("guess") String guess) {
		List<GuessFeedbackPair> guessFeedbackPairs = mooService.handleGuess(new Guess(guess));
		if (guessFeedbackPairs != null) {
			return ResponseEntity.accepted().body(guessFeedbackPairs);
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}
	
	@GetMapping("/toplist")
	public @ResponseBody List<PlayerAverage> getPlayerTopList() {
		List<PlayerAverage> topTen = playerRepository.getTopTen();
		return topTen;
	}
//////////////////////////////////////////////////////////////////////////////////
	@GetMapping("/average")
	public @ResponseBody List<Double> getTotalAverage() {
		List<Double> averages = playerRepository.getAllPlayersAverage();
		return averages;
	}

	@GetMapping("/all_players")
	public @ResponseBody Iterable<Player> getAllPlayers() {
		return playerRepository.findAll();
	}

	@GetMapping("/all_results")
	public @ResponseBody Iterable<Result> getAllResults() {
		return resultRepository.findAll();
	}
	
	@PostMapping("/body")
	public ResponseEntity<List> guessFeedbackPairBody(@RequestBody String input) {
		Guess guess=new Guess(input);
		new GuessFeedbackPair(guess, mooService.checkGuess(mooService.getAnswerKey(), guess));
		return ResponseEntity.accepted().body(mooService.getGuessFeedbackPairs());
	}

}
