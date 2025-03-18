package com.memopet.memopet.domain.member.service;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.memopet.memopet.domain.member.dto.MemberProfileResponseDto;
import com.memopet.memopet.domain.member.entity.MemberSocial;
import com.memopet.memopet.domain.member.repository.MemberSocialRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.Optional;

@SpringBootTest
class MemberServiceTest {

    @MockBean
    private JwtEncoder jwtEncoder;

    @MockBean
    private MemberSocialRepository memberSocialRepository;

    @Autowired
    private MemberService memberService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Mockito 초기화
    }

    @Test
    @WithMockUser(username = "testuser", roles = {"USER"})
    void testGetMemberProfile_Success() {
        // Given (Mock 데이터 준비)
        String email = "jaelee9212@gmail.com";
        String username = "jaehoon";
        String phoneNum = "01032849838";
        // Given (Mock 데이터 준비)
        MemberSocial mockMember = MemberSocial.builder()
                .email(email).username(username).phoneNum(phoneNum).build();

        when(memberSocialRepository.findMemberByEmail(email)).thenReturn(Optional.of(mockMember));

        // When (메서드 실행)
        MemberProfileResponseDto memberProfile = memberService.getMemberProfile(email);

        // Then (검증)
        assertNotNull(memberProfile);
        assertEquals(email, memberProfile.getEmail());
        assertEquals("jaehoon", memberProfile.getUsername());
        assertEquals("01032849838", memberProfile.getPhoneNum());

        // Mock 메서드가 실제로 호출되었는지 검증
        verify(memberSocialRepository, times(1)).findMemberByEmail(email);
    }

    @Test
    void testGetMemberProfile_UserNotFound() {
        // Given (사용자가 존재하지 않는 경우)
        String email = "notfound@example.com";
        when(memberSocialRepository.findMemberByEmail(email)).thenReturn(Optional.empty());

        // When & Then (예외 발생 테스트)
        Exception exception = assertThrows(UsernameNotFoundException.class, () -> {
            memberService.getMemberProfile(email);
        });

        assertEquals("User Not Found", exception.getMessage());

        // Mock 메서드가 실제로 호출되었는지 검증
        verify(memberSocialRepository, times(1)).findMemberByEmail(email);
    }
}
