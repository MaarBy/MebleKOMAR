package com.marko.MebleOnLine.data;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

public class EmailMessage implements Serializable {

    private String name;
    private String email;
    private String subject;
    private String text;
    private String color;
    private String description;    

    public EmailMessage() {
    }

    public EmailMessage(String name, String email, String subject, String text, String color, 
    				String description) {
        this.name = name;
        this.name = email;
        this.name = subject;
        this.text = text;
        this.color = color;
        this.description = description;
    }

    public String getName() {
    	return name;
    }
    
    public void setName(String name) {
    	this.name = name;
    }
    
    public String getEmail() {
    	return email;
    }
    
    public void setEmail(String email) {
    	this.email = email;
    }
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

	@Override
	public String toString() {
		return "EmailMessage [name=" + name + ", email=" + email + ", subject=" + subject + ", text=" + text
				+ ", color=" + color + ", description=" + description + "]";
	}  
}



