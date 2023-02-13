package com.moaaz.bug.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailSenderService {

	@Autowired
	JavaMailSender javaMailSender;

	public void sendPasswordToEmail(String email, String password) {
		System.out.println("Send Message For Email");
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("moaazsuliman1@gmail.com");
		message.setTo(email);
		message.setText("Your Password From Bug Tracking System is " + password);
		message.setSubject("Bug Tracking");
		javaMailSender.send(message);
		System.out.println("Sendeed Success.");
	}
}
