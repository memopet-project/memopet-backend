package com.memopet.memopet.domain.member.service;


import com.memopet.memopet.domain.member.dto.*;
import com.memopet.memopet.domain.member.entity.Member;
import com.memopet.memopet.domain.member.repository.MemberRepository;
import com.memopet.memopet.domain.pet.entity.Pet;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = false)
public class MemberService  {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * to deactivate the member status
     * @param email
     * @param deactivationReason
     * @param deactivationReasonComment
     * @return
     */
    public DeactivateMemberResponseDto deactivateMember(String email, String deactivationReason, String deactivationReasonComment) {

        // insert deleted_date, activated = false, deactivation_reason, deactivation_reason_comment
        Member member = memberRepository.findByEmail(email);
        DeactivateMemberResponseDto deactivateMemberResponseDto;
        if(member == null)  {
            deactivateMemberResponseDto = DeactivateMemberResponseDto.builder().dscCode("0").errMessage("member does not exist").build();
            return deactivateMemberResponseDto;
        }

        member.deactivateMember(LocalDateTime.now(),deactivationReason,deactivationReasonComment, false);

        // find pet info and insert deleted_date
        List<Pet> pets = member.getPets();
        for (Pet pet : pets) {
            pet.changeDeletedDate(LocalDateTime.now());
        }

        deactivateMemberResponseDto = DeactivateMemberResponseDto.builder().dscCode("1").build();
        return deactivateMemberResponseDto;
    }



    public MemberProfileResponseDto getMemberProfile(String email) {
        Member member = memberRepository.findByEmail(email);
        MemberProfileResponseDto memberProfileResponseDto = MemberProfileResponseDto.builder().build();

        if(member == null) return memberProfileResponseDto;

        memberProfileResponseDto = MemberProfileResponseDto.builder().email(member.getEmail()).username(member.getUsername()).phoneNum(member.getPhoneNum()).build();
        return memberProfileResponseDto;
    }


    public MemberInfoResponseDto changeMemberInfo(MemberInfoRequestDto memberInfoRequestDto) {

        Member member = memberRepository.findByEmail(memberInfoRequestDto.getEmail());
        MemberInfoResponseDto memberInfoResponseDto = MemberInfoResponseDto.builder().dscCode("0").build();
        if(member == null) return memberInfoResponseDto;

        if(memberInfoRequestDto.getDscCode() == 1) { // password changes
            member.changePassword(passwordEncoder.encode(memberInfoRequestDto.getPassword()));
        } else if(memberInfoRequestDto.getDscCode() == 2) { // username changes
            member.changeUsername(memberInfoRequestDto.getUsername());
        }else if(memberInfoRequestDto.getDscCode() == 3) { // cellphoneNum changes
            member.changePhoneNum(memberInfoRequestDto.getPhoneNum());
        }
        memberInfoResponseDto = MemberInfoResponseDto.builder().dscCode("1").username(member.getUsername()).phoneNum(member.getPhoneNum()).email(member.getEmail()).build();

        return memberInfoResponseDto;

    }
}
