package com.marko.MebleOnLine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.marko.MebleOnLine.data.EmailMessage;

@Controller
public class EmailMessageController {

	
	@Autowired
	private JavaMailSender mailSender;
	
    @GetMapping("/contact")
    public String createEmailMessageForm(Model model) {
        
        model.addAttribute("emailMessage", new EmailMessage());
        return "contact";
    }

    @PostMapping("/message-send")
    public String saveProjectSubmission(@ModelAttribute EmailMessage emailMessage) {

//    	String from = "bomba@gremail.cop";
		String to = "miekkiwazon@gmail.com";
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(emailMessage.getEmail());
		message.setReplyTo(emailMessage.getEmail());
		message.setTo(to);
		message.setSubject(emailMessage.getSubject());
		message.setText(emailMessage.getName() + "   " + 
						emailMessage.getEmail() + " \n\n" + 
						emailMessage.getText() + " \n\n" + "A skarpety noszę " + 
						emailMessage.getDescription());
		mailSender.send(message);
		
//		model.addAttribute("message", "A plain text email has been sent");

        return "message-send";
    }
}