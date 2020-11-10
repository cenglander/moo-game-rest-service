package moo.restservice;

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
	
	String answerKey;
	
	@PostMapping("/moo/body")
	public ResponseEntity<GuessFeedbackPair> guessFeedbackPairBody(@RequestBody String guess) {
		answerKey = mooService.generateAnswerKey();
		return ResponseEntity.accepted().
				body(new GuessFeedbackPair(guess, mooService.checkGuess(answerKey, guess)));
	}
	
	@PostMapping("/moo/{guess}")
	public ResponseEntity<GuessFeedbackPair> guessFeedbackPair(@PathVariable("guess") String guess) {
		answerKey = mooService.generateAnswerKey();
		return ResponseEntity.accepted().
				body(new GuessFeedbackPair(guess, mooService.checkGuess(answerKey, guess)));
	}
	
//	@GetMapping("/adder/{i1}/{i2}")
//	public UlfsInt add(@PathVariable("i1") int n, @PathVariable("i2") int m) {
//		return new UlfsInt(adderService.add(n,m));
//	}
	
	
	


}
