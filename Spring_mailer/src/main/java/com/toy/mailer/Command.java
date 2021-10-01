package com.toy.mailer;

import java.util.Random;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.Model;

public class Command {
	
	@Autowired
	private JavaMailSender mailSender;
	
	public void excute(HttpServletRequest request, Model model) {
		String AuthenticationKey = authCodeMaker();
		String subject = "너공나공 가입 인증 메일입니다.";
		String content ="인증코드는 [ " + AuthenticationKey + " ]입니다.";
		String from = "dbswovlf2009@gmail.com";
		String to = "dbswovlf2009@naver.com";
		
		try {

			MimeMessage mail = mailSender.createMimeMessage();
//			멀티파트를 사용하는 경우
//			MimeMessageHelper mailHelper = new MimeMessageHelper(mail, true, "UTF-8");
			//반 메일일 경우
			MimeMessageHelper mailHelper = new MimeMessageHelper(mail, "UTF-8");
			mailHelper.setFrom(from);
			mailHelper.setTo(to);
			mailHelper.setSubject(subject);
//			html을 사용하겠다는 의미
//			mailHelper.setText(content, true);
			mailHelper.setText(content);
			mailSender.send(mail);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	// 인증 번호 생성기
	public String authCodeMaker() {
		String authCode = null;
		
		StringBuffer temp = new StringBuffer();
		Random rnd = new Random();
		for (int i = 0; i < 10; i++) {
			int rIndex = rnd.nextInt(3);
			switch (rIndex) {
			case 0:
				// a-z
				temp.append((char) ((int) (rnd.nextInt(26)) + 97));
				break;
			case 1:
				// A-Z
				temp.append((char) ((int) (rnd.nextInt(26)) + 65));
				break;
			case 2:
				// 0-9
				temp.append((rnd.nextInt(10)));
				break;
			}
		}
		
		authCode = temp.toString();
		System.out.println(authCode);
		
		return authCode;
	}
}
