package moo.restservice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MooController {
	
	@Autowired
	MooService mooService;

	
	@PostMapping("/moo/{guess}")
	public ResponseEntity<List> guessFeedbackPair(@PathVariable("guess") String guess) {
		new GuessFeedbackPair(guess, mooService.checkGuess(mooService.getAnswerKey(), guess));
		List <GuessFeedbackPair> guessFeedbackPairs = new ArrayList<>(mooService.getGuessFeedbackPairs());
		mooService.resetIfCorrect(guess);
		return ResponseEntity.accepted().body(guessFeedbackPairs);
	}
	
	@PostMapping("/moo/body")
	public ResponseEntity<List> guessFeedbackPairBody(@RequestBody String guess) {
		new GuessFeedbackPair(guess, mooService.checkGuess(mooService.getAnswerKey(), guess));
		return ResponseEntity.accepted().
				body(mooService.getGuessFeedbackPairs());
	}

}
