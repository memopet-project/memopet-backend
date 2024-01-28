package com.memopet.memopet.domain.member.service;

import com.memopet.memopet.domain.member.dto.LoginDto;
import com.memopet.memopet.domain.member.dto.SignUpDto;
import com.memopet.memopet.domain.member.entity.Authority;
import com.memopet.memopet.domain.member.entity.Member;
import com.memopet.memopet.domain.member.repository.MemberRepository;
import com.memopet.memopet.global.common.utils.SecurityUtil;
import com.memopet.memopet.global.token.TokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final TokenProvider tokenProvider;


    @Override
    @Transactional(readOnly = false)
    // 로그인시에 DB에서 유저정보와 권한정보를 가져와서 해당 정보를 기반으로 userdetails.User 객체를 생성해 리턴
    public UserDetails loadUserByUsername(String Email) throws UsernameNotFoundException {
        System.out.println("loadUserByUsername start with Email : " + Email);
        return memberRepository.findOneWithAuthoritiesByEmail(Email)
                .map(member -> createUser(Email, member))
                .orElseThrow(() -> new UsernameNotFoundException(Email + " -> 데이터베이스에서 찾을 수 없습니다."));
    }

    private User createUser(String username, Member member) {
        System.out.println("createUser : " + member);
        if (!member.isActivated()) {
            throw new RuntimeException(username + " -> 활성화되어 있지 않습니다.");
        }

        List<GrantedAuthority> grantedAuthorities = member.getAuthorities().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getAuthority()))
                .collect(Collectors.toList());

        return new User(member.getEmail(),
                        member.getPassword(),
                        grantedAuthorities);
    }

    // 유저,권한 정보를 가져오는 메소드
    @Transactional
    public Optional<Member> getUserWithAuthorities(String Email) {
        return memberRepository.findOneWithAuthoritiesByEmail(Email);
    }

    // 현재 securityContext에 저장된 username의 정보만 가져오는 메소드
    @Transactional
    public Optional<Member> getMyUserWithAuthorities() {
        return SecurityUtil.getCurrentEmail()
                .flatMap(memberRepository::findOneWithAuthoritiesByEmail);
    }

    /**
     * return the user id if the sign up process is successfully completed
     * if the same email exists, it will return -2L
     *
     * @param signUpDto
     * @return user_id
     */
    @Transactional(readOnly = false)
    public String join (SignUpDto signUpDto)  {
        // add check for email exists in DB
        if(memberRepository.existsByUsername(signUpDto.getUsername())){
            return "Email is already taken!";
        }

        // create user object
        Member user = Member.builder()
                .username(signUpDto.getUsername())
                .password(passwordEncoder.encode(signUpDto.getPassword()))
                .email(signUpDto.getEmail())
                .authority(Authority.USER)
                .activated(true)
                .build();

        System.out.println("saved member");
        Member savedMember = memberRepository.save(user);

        return "User registered successfully";
    }
    public Authentication authenicateUser(LoginDto loginDto) {
        // Created the authentication token
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword());

        // when this line of code executes, it will call the loadUserByUsername method in AuthService.
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // save authentication object in the SecurityContextHolder
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return authentication;
    }

    public String createToken(Authentication authentication) {
        // created JWT token by authentication object
        return tokenProvider.createToken(authentication);
    }

    public HttpHeaders createHttpHeaders(String authorizationHeader, String headerContent) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(authorizationHeader, headerContent);

        return httpHeaders;
    }
}
