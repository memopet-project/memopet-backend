package com.memopet.memopet.auth;


import com.memopet.memopet.domain.member.repository.MemberRepository;
import com.memopet.memopet.domain.member.service.AuthService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Transactional
@Rollback(false)
public class SignUp {

    @Autowired
    AuthService authService;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    PasswordEncoder passwordEncoder;


    @Test
    @DisplayName("Sign Up test")
    public void SignUpTest() {

        String email = "tony123@gmail.com";
        String username = "Lee";
        String password = "1234";

        System.out.println("******email " + email);

        //Long member_id = authService.join(new SignUpDto(username, password, email));

        //Optional<Member> member = memberRepository.findById(member_id);

        //System.out.println("*******memeber password " + member.get().getPassword());
        //System.out.println(passwordEncoder.encode(member.get().getPassword()));

//        assertThat(email).isEqualTo(member.get().getEmail());
//        assertThat(username).isEqualTo(member.get().getUsername());
//        assertThat(password).isEqualTo(passwordEncoder.encode(member.get().getPassword()));



    }
}
