package com.memopet.memopet.domain.member.service;


import com.memopet.memopet.domain.member.dto.DeactivateMemberResponseDto;
import com.memopet.memopet.domain.member.dto.MemberInfoResponseDto;
import com.memopet.memopet.domain.member.dto.MemberInfoUpdateRequestDto;
import com.memopet.memopet.domain.member.dto.MemberProfileResponseDto;
import com.memopet.memopet.domain.member.entity.Member;
import com.memopet.memopet.domain.member.entity.MemberSocial;
import com.memopet.memopet.domain.member.entity.RefreshToken;
import com.memopet.memopet.domain.member.repository.MemberRepository;
import com.memopet.memopet.domain.member.repository.MemberSocialRepository;
import com.memopet.memopet.domain.member.repository.RefreshTokenRepository;
import com.memopet.memopet.domain.pet.entity.Memory;
import com.memopet.memopet.domain.pet.entity.Pet;
import com.memopet.memopet.domain.pet.repository.CommentRepository;
import com.memopet.memopet.domain.pet.repository.MemoryImageRepository;
import com.memopet.memopet.domain.pet.repository.MemoryRepository;
import com.memopet.memopet.domain.pet.repository.PetRepository;
import com.memopet.memopet.global.common.service.S3Uploader;
import com.memopet.memopet.global.common.utils.BusinessUtil;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = false)
@Slf4j
public class MemberService  {

    private final MemberRepository memberRepository;
    private final MemberSocialRepository memberSocialRepository;
    private final PetRepository petRepository;
    private final CommentRepository commentRepository;
    private final MemoryRepository memoryRepository;
    private final MemoryImageRepository memoryImageRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final S3Uploader s3Uploader;

    private final EntityManager em;
    private final BusinessUtil businessUtil;


    @Transactional(readOnly = false)
    public DeactivateMemberResponseDto deactivateMember(String email, String deactivationReason, String deactivationReasonComment) {


        MemberSocial memberSocial = businessUtil.getValidEmail(email);

        Member member = memberRepository.findMemberByMemberId(memberSocial.getMemberId()).get();

        // deactivate the member entity
        member.deactivateMember(LocalDateTime.now(), deactivationReason, deactivationReasonComment, false);

        List<MemberSocial> memberSocials = memberSocialRepository.findMemberByMemberId(member.getMemberId());


        memberSocials.forEach(memorySocial -> {
            memorySocial.deactivateMemberSocial(LocalDateTime.now());

            // expired the refreshtoken
            Optional<RefreshToken> byMemberIdToken = refreshTokenRepository.findByMemberId(memorySocial.getId());

            if(byMemberIdToken.isPresent()) {
                RefreshToken refreshToken = byMemberIdToken.get();
                refreshToken.setRevoked(true);
                refreshTokenRepository.save(refreshToken);
            }
        });

        // find pet info and insert deleted_date
        List<Pet> pets = member.getPets();
        List<Long> petIds = new ArrayList<>();
        for (Pet pet : pets) {
            //pet.updateDeletedDate(LocalDateTime.now());
            petIds.add(pet.getId());
        }
        List<Long> memoryIds = memoryRepository.findByPetIds(petIds).stream().map(Memory::getId).collect(Collectors.toList());

        petRepository.deleteAllPets(petIds);

        // memory delete
        memoryRepository.deleteAllMemories(petIds);

        // memory images delete
        memoryImageRepository.deleteAllMemoryImages(memoryIds);

        // comment deactivate
        commentRepository.deleteAllComments(petIds);

        return DeactivateMemberResponseDto.builder().dscCode("1").build();
    }

    public MemberProfileResponseDto getMemberProfile(String email) {
        MemberSocial memberSocial = businessUtil.getValidEmail(email);

        return MemberProfileResponseDto.builder()
                .email(memberSocial.getEmail())
                .username(memberSocial.getUsername())
                .phoneNum(memberSocial.getPhoneNum())
                .build();
    }

    public MemberInfoResponseDto changeMemberInfo(MemberInfoUpdateRequestDto memberInfoRequestDto) {
        businessUtil.isValidEmail(memberInfoRequestDto.getEmail());

        memberRepository.UpdateMemberInfo(memberInfoRequestDto);

        em.flush();
        em.clear();

        MemberSocial memberSocial = businessUtil.getValidEmail(memberInfoRequestDto.getEmail());

        return MemberInfoResponseDto.builder()
                .username(memberSocial.getUsername())
                .phoneNum(memberSocial.getPhoneNum())
                .email(memberSocial.getEmail())
                .build();
    }
}
