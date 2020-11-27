package moo.restservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/moo/web")
public class MooWebController {
	
	@Autowired
	MooService mooService;
	
	@GetMapping("/login")
	public 

}

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//
//@Controller
//public class IntegerPairWebController {
//	
//	@Autowired
//	IntegerAdder adder;
//	
//	@GetMapping("/adder")
//	public String getForm(Model m) {
//		m.addAttribute("pair", new IntegerPair());
//		return "addform";
//	}
//	
//	@PostMapping("/adder")
//	public String fillForm(IntegerPair p, Model m) {
//		m.addAttribute("pair", new IntegerPair());
//		m.addAttribute("sum", adder.add(p));
//		return "addform";
//	}
//	
//	@GetMapping("/all")
//	public String getList(Model m) {
//		m.addAttribute("list", adder.getList());
//		return "pairlist";
//	}
//}
