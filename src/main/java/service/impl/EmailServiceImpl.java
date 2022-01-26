package service.impl;

import javax.servlet.ServletContext;

import entities.User;
import service.EmailService;
import utils.SendMailUtils;

public class EmailServiceImpl implements EmailService{

	@Override
	public void sendMail(ServletContext context, User recipient, String type) {
		
		final String EMAIL_WELCOME_SUBJECT ="Welcome to ONLINE ENTERTAIMENT";
		final String EMAIL_FORGOT_PASSWORD ="ONLINE ENTERTAIMENT - NEW PASSWORD";
			String host = context.getInitParameter("host");
			String   port = context.getInitParameter("port");
			String   user = context.getInitParameter("user");
			String   pass = context.getInitParameter("pass");
		
			try {
				String content = null;
				String subject = null;
				switch(type) {
				case "welcome":
					subject = EMAIL_WELCOME_SUBJECT;
					content = "Dear "+ recipient.getUsername() + ", have a nice day!";
					break;
				case "forgot":
					subject = EMAIL_FORGOT_PASSWORD;
					content = "Dear "+ recipient.getUsername() + ", your new password here "+ recipient.getPassword();
					break;
				default:
					subject = "Online Entertaiment";
					content = "Mayle email is wrong, don't care about it";
				}
				SendMailUtils.sendEmail(host, port, user, pass, recipient.getEmail(), subject, content);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
	}

}
