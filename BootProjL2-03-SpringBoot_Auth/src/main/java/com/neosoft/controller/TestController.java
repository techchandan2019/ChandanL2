package com.neosoft.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

	@GetMapping("/")
	public String getHomePage() {
		return "home";
	}
	@GetMapping("/offer")
	public String getOfferPage() {
		return "show_offer";
	}
	@GetMapping("/price")
	public String getPricePage() {
		return "show_price";
	}
}
