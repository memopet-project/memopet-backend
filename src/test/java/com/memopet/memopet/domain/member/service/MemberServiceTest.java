package com.memopet.memopet.domain.member.service;

import com.memopet.memopet.domain.member.dto.MemberInfoRequestDto;
import com.memopet.memopet.domain.member.dto.MemberInfoResponseDto;
import com.memopet.memopet.domain.member.dto.MemberProfileResponseDto;
import com.memopet.memopet.domain.member.entity.MemberSocial;
import com.memopet.memopet.domain.member.entity.MemberStatus;
import com.memopet.memopet.domain.member.repository.MemberRepository;
import com.memopet.memopet.domain.member.repository.MemberSocialRepository;
import com.memopet.memopet.domain.member.repository.RefreshTokenRepository;
import com.memopet.memopet.domain.pet.repository.CommentRepository;
import com.memopet.memopet.domain.pet.repository.MemoryImageRepository;
import com.memopet.memopet.domain.pet.repository.MemoryRepository;
import com.memopet.memopet.global.common.service.S3Uploader;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    // Mock repositories and dependencies
    @Mock
    private MemberSocialRepository memberSocialRepository;

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private RefreshTokenRepository refreshTokenRepository;

    @Mock
    private MemoryRepository memoryRepository;

    @Mock
    private MemoryImageRepository memoryImageRepository;

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private S3Uploader s3Uploader;

    @Mock
    private EntityManager em;

    @InjectMocks
    private MemberService memberService;

    /**
     * Test for retrieving a member's profile
     * It checks if the correct profile data is returned when a valid email is provided.
     */
    @Test
    void testGetMemberProfile_Success() {
        // Given - Mock data
        String email = "test@example.com";
        MemberSocial testMemberSocial = MemberSocial.builder()
                .id(1L)
                .memberId("member123")
                .username("TestUser")
                .email(email)
                .password("password123")
                .phoneNum("123-456-7890")
                .memberStatus(MemberStatus.NORMAL)
                .deletedDate(null)
                .lastLoginDate(LocalDateTime.now())
                .loginFailCount(0)
                .provider("GOOGLE")
                .providerId("google123")
                .roles("ROLE_USER")
                .build();

        // Mock repository method
        when(memberSocialRepository.findMemberByEmail(email)).thenReturn(Optional.of(testMemberSocial));

        // When - Call the service method
        MemberProfileResponseDto profile = memberService.getMemberProfile(email);

        // Then - Validate the response
        assertNotNull(profile);
        assertEquals(email, profile.getEmail());
        assertEquals("TestUser", profile.getUsername());

        // Verify repository call
        verify(memberSocialRepository, times(1)).findMemberByEmail(email);
    }

    /**
     * Test for updating member information
     * It checks if the member information is correctly updated in the database.
     */
    @Test
    void testChangeMemberInfo_Success() {
        // Given - Mock input data
        MemberInfoRequestDto requestDto = MemberInfoRequestDto.builder()
                .email("test@example.com")
                .username("UpdatedUsername")
                .phoneNum("9876543210")
                .build();

        MemberSocial updatedMemberSocial = MemberSocial.builder()
                .id(1L)
                .memberId("member123")
                .username(requestDto.getUsername())
                .email(requestDto.getEmail())
                .password("password123")
                .phoneNum(requestDto.getPhoneNum())
                .memberStatus(MemberStatus.NORMAL)
                .deletedDate(null)
                .lastLoginDate(LocalDateTime.now())
                .loginFailCount(0)
                .provider("GOOGLE")
                .providerId("google123")
                .roles("ROLE_USER")
                .build();

        // Mock repository method
        when(memberSocialRepository.findMemberByEmail(requestDto.getEmail())).thenReturn(Optional.of(updatedMemberSocial));

        // When - Call the service method
        MemberInfoResponseDto response = memberService.changeMemberInfo(requestDto);

        // Then - Validate the response
        assertNotNull(response);
        assertEquals("UpdatedUsername", response.getUsername());
        assertEquals("9876543210", response.getPhoneNum());

        // Verify repository and entity manager calls
        verify(memberRepository, times(1)).UpdateMemberInfo(requestDto);
        verify(em, times(1)).flush();
        verify(em, times(1)).clear();
        verify(memberSocialRepository, times(2)).findMemberByEmail(requestDto.getEmail());
    }

    /**
     * Test for retrieving a member by email
     * It verifies that the correct member data is retrieved when a valid email is provided.
     */
    @Test
    void testGetMemberByEmail_Success() {
        // Given - Mock data
        String email = "test@example.com";
        MemberSocial testMemberSocial = MemberSocial.builder()
                .id(1L)
                .memberId("member123")
                .username("TestUser")
                .email(email)
                .password("password123")
                .phoneNum("123-456-7890")
                .memberStatus(MemberStatus.NORMAL)
                .deletedDate(null)
                .lastLoginDate(LocalDateTime.now())
                .loginFailCount(0)
                .provider("GOOGLE")
                .providerId("google123")
                .roles("ROLE_USER")
                .build();

        // Mock repository method
        when(memberSocialRepository.findMemberByEmail(email)).thenReturn(Optional.of(testMemberSocial));

        // When - Call the service method
        Optional<MemberSocial> result = memberService.getMemberByEmail(email);

        // Then - Validate the response
        assertTrue(result.isPresent());
        assertEquals(email, result.get().getEmail());

        // Verify repository call
        verify(memberSocialRepository, times(1)).findMemberByEmail(email);
    }
}
