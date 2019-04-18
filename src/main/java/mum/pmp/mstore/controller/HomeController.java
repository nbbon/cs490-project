package mum.pmp.mstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping("/home")
	public String sayHello() {
		System.out.println();
		return "Hi";
	}
}
