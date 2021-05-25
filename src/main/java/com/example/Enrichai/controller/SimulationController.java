package com.example.Enrichai.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SimulationController {

	@RequestMapping("/")
	public String  homes() {
		return "index";
	}
	
}
