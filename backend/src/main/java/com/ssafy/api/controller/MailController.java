package com.ssafy.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.api.model.service.MailService;

@RestController
@RequestMapping("/mail")
@CrossOrigin("*")
public class MailController {
	
	@Autowired
	private MailService mailService;

	@GetMapping("/pwd/findpwdmail/{toMail}/{content}/")
	public String mailMemberPwd(@PathVariable String toMail, @PathVariable String content) {
		if(mailService.mailSend(toMail, content)) {;
			return "SUCCESS";
		}
		return "FAIL";
	}
}
