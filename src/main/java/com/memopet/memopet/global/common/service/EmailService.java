package com.memopet.memopet.global.common.service;

import com.memopet.memopet.global.common.dto.EmailAuthRequestDto;
import com.memopet.memopet.global.common.dto.EmailAuthResponseDto;
import com.memopet.memopet.global.common.dto.EmailMessageDto;
import com.memopet.memopet.global.common.entity.VerificationStatusEntity;
import com.memopet.memopet.global.common.exception.BadRequestRuntimeException;
import com.memopet.memopet.global.common.repository.VertificationStatusRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Random;

@Component
@Slf4j
@RequiredArgsConstructor
public class EmailService {
    private final EmailRabbitPublisher emailRabbitPublisher;
    private final VertificationStatusRepository vertificationStatusRepository;

    public void sendRequestToRabbitMqForSendingEmail(long id, String email, String authNum) {
        EmailMessageDto emailMessageDto = EmailMessageDto.builder().auth(authNum).retryCount(0).email(email).id(String.valueOf(id)).build();
        emailRabbitPublisher.pubsubMessage(emailMessageDto);
    }
    @Transactional(readOnly = false)

    public EmailAuthResponseDto sendEmail(String toEmail)  {
        String authNum = createCode();
        long verificationEntityId = setDataExpire(authNum);

        sendRequestToRabbitMqForSendingEmail(verificationEntityId,toEmail,authNum);
        //log.info("authNum : {}", authNum);

        // Since the builder pattern can result in more verbose code, it might be worth considering using a constructor instead, depending on the context.
        return EmailAuthResponseDto.builder().authCode(authNum).verificationStatusId(verificationEntityId).build();
    }

    private long setDataExpire(String authKey) {
        VerificationStatusEntity verificationStatusEntity = VerificationStatusEntity.builder()
            .expiredAt(LocalDateTime.now().plusMinutes(3))
            .authKey(authKey)
            .build();

        VerificationStatusEntity savedEntity = vertificationStatusRepository.save(verificationStatusEntity);

        return savedEntity.getId();
    }

    public String getCertificationMessage(String certificationNum) {
        // tip 아래처럼 Text Block 으로 가독성 높게 만들수 있습니다.
        String message = """
            <h1 style='test-align:certer;'>[이메일 인증 코드]</h1>
            <h3 style='test-align:certer;'>인증코드 : <strong style='front-size: 32px; letter-spacing:8px;'>
            %s
            </strong></h3>
            """.formatted(certificationNum);
       return message;
    }

    //랜덤 인증 코드 생성
    public static String createCode() {
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
        String authNum = key.toString();
        return authNum;
        // tip 이렇게 멤버변수에 할당하는것보다는 리턴을 받고 활용하는게 나아보입니다. 밖에서 이 메소드를 호출결과로서 랜덤값을 활용할수 있기 때문입니다.
        // tip 오히려 static method 로 유틸성에 가깝기 때문에 따로 클래스로 빼주는게 좋습니다.

    }

    public EmailAuthResponseDto checkVerificationCode(EmailAuthRequestDto emailAuthRequestDto) {
        //String codeSaved = redisUtil.getValues(email);
        VerificationStatusEntity verificationStatusEntity = vertificationStatusRepository
                .findById(emailAuthRequestDto.getVerificationStatusId())
                .orElseThrow(() -> new BadRequestRuntimeException("verificationStatusId does not exist"));


        log.info("code : " + verificationStatusEntity.getAuthKey());

        if(LocalDateTime.now().isAfter(verificationStatusEntity.getExpiredAt())) {
            throw new BadRequestRuntimeException("expired");
        }

        if(!emailAuthRequestDto.getConfirmCode().equals(verificationStatusEntity.getAuthKey())) {
            throw new BadRequestRuntimeException("different");
        }

        return EmailAuthResponseDto.builder().build();
    }
}
