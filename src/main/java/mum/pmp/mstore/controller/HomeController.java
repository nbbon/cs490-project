package mum.pmp.mstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	@GetMapping("/home")
	public String sayHello() {

		System.out.println("Hello MUM PMP group 1!!");
		System.out.println("Hello MUM PMP group 2!!");

		System.out.println("Hello MUM PMP group!!");
		System.out.println("This is Romie!");

		System.out.println("...");

		System.out.println("...Me romie");

		System.out.println("Good Friday.");
		System.out.println("...Me romie");

		System.out.println("Good Friday.");

		return "welcome";
	}
}
