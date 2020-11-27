package moo.restservice;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/moo/web")
public class MooWebController {
	
	@Autowired
	MooService mooService;
	
	@GetMapping("/login")
	public String loginForm(Model m) {
		m.addAttribute("loginFormBean", new LoginFormBean());
		m.addAttribute("message", "Welcome, please log in.");
		return "loginpage";
	}
	
	@PostMapping("/login")
	public String acceptForm(@ModelAttribute("loginFormBean") LoginFormBean loginFormBean, Model m) {
		String playerName = loginFormBean.getName();
		if (mooService.login(playerName)) {
		m.addAttribute("guessFormBean", new GuessFormBean());
			return "playpage";
		} else {
			m.addAttribute("message", "Not registered, plz talk to admin.");
			return "loginpage";
		}
	}
	
	@PostMapping("/play")
	public String play(@ModelAttribute("guessFormBean") GuessFormBean guess, Model m) {
		Optional<List<GuessFeedbackPair>> guessFeedbackList = mooService.handleGuess(guess.getGuess());
		if (guessFeedbackList.isPresent()) {
			m.addAttribute("player", mooService.getPlayer().getName());
			m.addAttribute("guessFormBean", new GuessFormBean());
			m.addAttribute("guessFeedbackPairs", guessFeedbackList.get());
			return "playpage";
		} else {
			//TODO - Code below not working atm
			m.addAttribute("loginFormBean", new LoginFormBean());
			m.addAttribute("message", "Log in here before playing");
			return "loginpage";
		}
	}
	
	@GetMapping("/topten")
	public String getTopTen(Model m) {
		m.addAttribute("topTenList", mooService.getTopTen());
		return "toptenpage";
	}

}
