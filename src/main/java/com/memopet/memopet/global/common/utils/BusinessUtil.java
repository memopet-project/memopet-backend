package com.memopet.memopet.global.common.utils;

import com.memopet.memopet.domain.member.entity.MemberSocial;
import com.memopet.memopet.domain.member.entity.MemberStatus;
import com.memopet.memopet.domain.member.repository.MemberSocialRepository;
import com.memopet.memopet.global.common.exception.BadRequestRuntimeException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BusinessUtil {
    
    private final MemberSocialRepository memberSocialRepository;
    private final String IS_MOBILE = "MOBILE";
    private final String IS_PHONE = "PHONE";
    private final String IS_TABLET = "TABLET";
    private final String IS_PC = "PC";

    public MemberSocial getValidEmail(String email) {
        return memberSocialRepository
                .findMemberByEmail(email)
                .orElseThrow(()->new UsernameNotFoundException("User Not Found"));

    }

    public void isAccountLock(String email) {
        MemberSocial memberByEmail = memberSocialRepository
                .findMemberByEmail(email)
                .orElseThrow(()->new UsernameNotFoundException("User Not Found"));

        if(MemberStatus.LOCKED.equals(memberByEmail.getMemberStatus())) {
            throw new BadRequestRuntimeException("Your account is locked because of 5 failed Login attempts");
        }
    }

    public void isValidEmail(String email) {
        memberSocialRepository
                .findMemberByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
    }

    public String getOsInfo(HttpServletRequest request) {
        String os = null;
        String agent = request.getHeader("User-Agent");
        
        if(agent.indexOf("NT 6.0") != -1)    os = "Windows Vista/Server 2008";
        else if(agent.indexOf("NT 5.2") != -1) os = "Windows Server 2003";
        else if(agent.indexOf("NT 5.1") != -1) os = "Windows XP";
        else if(agent.indexOf("NT 5.0") != -1) os = "Windows 2000";
        else if(agent.indexOf("NT") != -1)   os = "Windows NT";
        else if(agent.indexOf("9x 4.90") != -1) os = "Windows Me";
        else if(agent.indexOf("98") != -1)   os = "Windows 98";
        else if(agent.indexOf("95") != -1)   os = "Windows 95";
        else if(agent.indexOf("Win16") != -1) os = "Windows 3.x";
        else if(agent.indexOf("Windows") != -1) os = "Windows";
        else if(agent.indexOf("Linux") != -1)   os = "Linux";
        else if(agent.indexOf("Macintosh") != -1) os = "Macintosh";
        else os = "";

        return os;
    }

    /**
     * Device type distinction: Mobile, Tablet, and PC
     * @param req
     * @return
     */
    public String isDevice(HttpServletRequest req) {
        String userAgent = req.getHeader("User-Agent").toUpperCase();

        if(userAgent.indexOf(IS_MOBILE) > -1) {
            if(userAgent.indexOf(IS_PHONE) == -1)
                return IS_MOBILE;
            else
                return IS_TABLET;
        } else
            return IS_PC;
    }


}
