package com.memopet.memopet.domain.member.repository;

import com.memopet.memopet.domain.member.dto.MemberInfoUpdateRequestDto;

public interface CustomMemberRepository{

    void UpdateMemberInfo(MemberInfoUpdateRequestDto memberInfoRequestDto);
}
