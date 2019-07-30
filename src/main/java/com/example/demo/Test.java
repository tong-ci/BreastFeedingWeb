package com.example.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mysql.cj.util.StringUtils;

@RestController
@RequestMapping("/test")
public class Test {

    @RequestMapping("/test")
	public String test() {
		return "ok";
	}
    
    public static void main(String[] args) {
		if (StringUtils.isNullOrEmpty("asd")) {
			System.out.println("ok");
		}
	}
	
}
