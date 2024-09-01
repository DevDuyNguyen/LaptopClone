package com.example.LaptopShopClone.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class TestController {
	@GetMapping("/test1")
	public String test1() {
		return "test1";
	}
	
}
