package moo.restservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MooController {
	
	@Autowired
	MooService mooService;
	
//	String answerKey;
	
	@PostMapping("/moo/body")
	public ResponseEntity<List> guessFeedbackPairBody(@RequestBody String guess) {
		new GuessFeedbackPair(guess, mooService.checkGuess(mooService.getAnswerKey(), guess));
		return ResponseEntity.accepted().
				body(mooService.getGuessFeedbackPairs());
	}
	
//	@PostMapping(path="/moo/{guess}", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PostMapping("/moo/{guess}")
	public ResponseEntity<List> guessFeedbackPair(@PathVariable("guess") String guess) {
		new GuessFeedbackPair(guess, mooService.checkGuess(mooService.getAnswerKey(), guess));
		return ResponseEntity.accepted().
				body(mooService.getGuessFeedbackPairs());
	}
	
//	#POST med body och endast guess/feedback-par, ej lista
//	@PostMapping("/moo/body")
//	public ResponseEntity<GuessFeedbackPair> guessFeedbackPairBody(@RequestBody String guess) {
//		return ResponseEntity.accepted().
//				body(new GuessFeedbackPair(guess, mooService.checkGuess(mooService.getAnswerKey(), guess)));
//	}

}
