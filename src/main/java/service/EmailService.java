package service;

import javax.servlet.ServletContext;

import entities.User;

public interface EmailService {
	void sendMail(ServletContext context, User recipient , String type );
}
