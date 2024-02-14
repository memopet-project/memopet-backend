package com.memopet.memopet.global.common.service;

import com.memopet.memopet.global.common.utils.RedisUtil;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.util.Random;

@Component
@Slf4j
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender emailSender ;
    private final RedisUtil redisUtil;
    private final String SUBJECT = "[이메일 인증 메일]";
    private String authNum; //랜덤 인증 코드

    public MimeMessage createEmailForm(String email) throws MessagingException, UnsupportedEncodingException  {
        createCode(); //인증 코드 생성
        String setFrom = "jaelee9212@naver.com"; //email-config에 설정한 자신의 이메일 주소(보내는 사람)
        String toEmail = email; //받는 사람
        String title = SUBJECT; //제목

        MimeMessage message = emailSender.createMimeMessage();
        message.addRecipients(MimeMessage.RecipientType.TO, toEmail); //보낼 이메일 설정
        message.setSubject(title); //제목 설정
        message.setFrom(setFrom); //보내는 이메일
        message.setText(getCertificationMessage(authNum), "utf-8", "html");

        return message;

    }
    @Transactional(readOnly = true)
    //실제 메일 전송
    public String sendEmail(String toEmail) throws MessagingException, UnsupportedEncodingException {

        //메일전송에 필요한 정보 설정
        MimeMessage emailForm = createEmailForm(toEmail);
        //실제 메일 전송
        emailSender.send(emailForm);
        setDataExpire(toEmail, authNum,60 * 1L);
        return authNum; //인증 코드 반환
    }

    private void setDataExpire(String email, String authKey, Long duration) {
        //Redis에 3분동안 인증코드 {email, authKey} 저장
        try {
            redisUtil.setDataExpire(email, authKey,duration);
        } catch (Exception e) {
            e.printStackTrace();
            // 에러처리 필요
        }
    }
    private String getCertificationMessage(String certificationNum) {
        String certificationMessage = "";
        certificationMessage += "<h1 style='test-align:certer;'>[이메일 인증 코드]</h1>";
        certificationMessage += "<h3 style='test-align:certer;'>인증코드 : <strong style='front-size: 32px; letter-spacing:8px;'>" + certificationNum + "</strong></h3>";
        return certificationMessage;
    }
    //랜덤 인증 코드 생성
    public void createCode() {
        Random random = new Random();
        StringBuffer key = new StringBuffer();

        for(int i=0;i<8;i++) {
            int index = random.nextInt(3);

            switch (index) {
                case 0 :
                    key.append((char) ((int)random.nextInt(26) + 97));
                    break;
                case 1:
                    key.append((char) ((int)random.nextInt(26) + 65));
                    break;
                case 2:
                    key.append(random.nextInt(9));
                    break;
            }
        }
        authNum = key.toString();
    }

    public String checkVerificationCode(String email, String code) {
        String response = "Verification done successfully";

        String codeSaved = redisUtil.getValues(email);
        if(codeSaved.equals("false")) {
            response = "Verification failed : code is expired..";
            return response;
        }
        if(!codeSaved.equals(code)) {
            response = "Verification failed : input code is different";
            return response;
        }
        return response;
    }
}
