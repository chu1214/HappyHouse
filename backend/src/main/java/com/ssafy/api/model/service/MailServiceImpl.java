package com.ssafy.api.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.ssafy.api.util.MailHandler;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MailServiceImpl implements MailService{
		@Autowired
	    private JavaMailSender mailSender;

	    public boolean mailSend(String toMail, String content) {
	        try {
	            MailHandler mailHandler = new MailHandler(mailSender);
	    		String pwd = content;
	    		content = "\n회원님께 임시 비밀번호가 발급되었습니다.\n 아래 임시 비밀번호로 접속하여 비밀번호를 변경해주세요.\n\n";
	            // 받는 사람
	           mailHandler.setTo(toMail);
	            // 보내는 사람
	           mailHandler.setFrom("platinadark@gmail.com");
	            // 제목
	           mailHandler.setSubject("[해피하우스] 임시 비밀번호를 발급해드립니다.");
	            // HTML Layout
	           content = content.replace("\n", "<br/>");
	           content += "<font size=4 color=red>" + pwd + "</font><br>";
	           content += "<br><hr><h3>해피하우스를 이용해주셔서 감사합니다.</h3></hr><br>";
	           content += "<p>오늘도 행복한 시간되세요</p>";
	           
	            String htmlContent = 
	            		"<div align='center'><img src='cid:happyhouse' width='70%' height='70%'></div> <div align='center'>" + content + "</div>";
	            mailHandler.setText(htmlContent, true);
	            // 첨부 파일
	           // mailHandler.setAttach("newTestxtxt", "static/originTest.txt");
	            // 이미지 삽입
	           mailHandler.setInline("happyhouse", "static/mailImage.png");
	            mailHandler.send();
	            
	            return true;
	        }
	        catch(Exception e){
	            e.printStackTrace();
	    }
            return false;
	}
}
