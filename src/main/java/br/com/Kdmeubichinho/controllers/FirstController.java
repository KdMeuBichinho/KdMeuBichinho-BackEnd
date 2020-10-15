package br.com.Kdmeubichinho.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstController {
	@RequestMapping(method = RequestMethod.GET, path = "/boasvindas")
	public String hello() {
		return "Hello Kdmeubichinho";
	}

}
