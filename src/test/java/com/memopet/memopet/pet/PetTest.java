package com.memopet.memopet.pet;

import com.memopet.memopet.domain.member.entity.Authority;
import com.memopet.memopet.domain.member.entity.Member;
import com.memopet.memopet.domain.member.repository.MemberRepository;
import com.memopet.memopet.domain.pet.entity.*;
import com.memopet.memopet.domain.pet.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootTest
@Transactional
@Rollback(value = false)
public class PetTest {

//    @Autowired
//    EntityManager em;
    @Autowired
    PetRepository petRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    SpeciesRepository speciesRepository;
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    MemoryRepository memoryRepository;
    @Autowired
    AlarmRepository alarmRepository;

    @Autowired
    FollowRepository followRepository;

    /**
     * 멤버와 종 객체를 만들어서 펫 객체를 생성 - db 확인 완료
     */
    @Test
    public void petEntity() {

        // 처음에 회원아이디를 가져온다 (테스트환경이므로 그냥 생성해서 저장으로 대체)
        Member member = Member.builder()
                .username("Test")
                .password(passwordEncoder.encode("Test1agfagdasgdasgdgasydgasgdygasyugdsyugayudgasuydugasudgsauyg23"))
                .email("jae@gmail.com")
                .phoneNum(passwordEncoder.encode("01052888888"))
                .authority(Authority.USER)
                .activated(true)
                .build();

        Member savedMemeber = memberRepository.save(member);

        Member findMember = memberRepository.findByEmail("jae@gmail.com");

        System.out.println("member id :" + findMember.getEmail());
//        // 반려동물 종 아이디 생성 후 사용
//        Species species = Species.builder()
//                .largeCategory("포유류")
//                .smallCategory("강아지")
//                .midCategory("씹덕")
//                .build();
//
//        Species savedSpecies = speciesRepository.save(species);

//        // create user object
//        Pet pet = Pet.builder()
//                .species(savedSpecies)
//                .member(findMember)
//                .petName("방울이")
//                .petBirth(LocalDate.now())
//                .petProfileUrl("https://images.unsplash.com/photo-1528301721190-186c3bd85418?q=80&w=1000&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxleHBsb3JlLWZlZWR8Mnx8fGVufDB8fHx8fA%3D%3D")
//                .petFavs("밥먹기")
//                .petDesc("우리 방울이는 밥먹기를 너무 좋아하는 친구입니다.")
//                .gender(Gender.F)
//                .build();
//
//        Pet findPet = petRepository.save(pet);
//
//        System.out.println(">>createdDate="+ findPet.getCreatedDate() + ", modifiedDate=" + findPet.getLastModifiedDate());
//        System.out.println(">>findPet="+ findPet.toString());
    }

    /**
     * comment 객체 생성
     */
    @Test
    public void commentEntity() {
        Member member = Member.builder()
                .username("Test")
                .password(passwordEncoder.encode("Test1agfagdasgdasgdgasydgasgdygasyugdsyugayudgasuydugasudgsauyg23"))
                .email("jae3@gmail.com")
                .phoneNum(passwordEncoder.encode("01052888888"))
                .authority(Authority.USER)
                .activated(true)
                .build();

        Member savedMemeber = memberRepository.save(member);

        Member findMember = memberRepository.findByEmail("jae3@gmail.com");

        System.out.println("member id :" + findMember.getEmail());
        // 반려동물 종 아이디 생성 후 사용
        Species species = Species.builder()
                .largeCategory("포유류")
                .smallCategory("강아지")
                .midCategory("씹덕")
                .build();

        Species savedSpecies = speciesRepository.save(species);
        Pet pet = Pet.builder()
                .species(savedSpecies)
                .member(findMember)
                .petName("방울이")
                .petBirth(LocalDate.now())
                .petProfileUrl("https://images.unsplash.com/photo-1528301721190-186c3bd85418?q=80&w=1000&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxleHBsb3JlLWZlZWR8Mnx8fGVufDB8fHx8fA%3D%3D")
                .petFavs("밥먹기")
                .petDesc("우리 방울이는 밥먹기를 너무 좋아하는 친구입니다.")
                .gender(Gender.F)
                .build();

        Pet findPet = petRepository.save(pet);
        // 빈 추억 객체 생성
        Memory memory = Memory.builder()
                .petId(pet)
                .title("meepy life")
                .memoryDate(LocalDateTime.now())
                .description("MEEP!")
                .audience(Audience.ALL)
                .build();
        memoryRepository.save(memory);
        Comment comment =  Comment.builder()
                .memory(memory)
                .pet(findPet)
                .commenterId(findPet.getId())
                .comment("방울이가 이쁘네요")
                .depth(1)
                .commentGroup(CommentGroup.MEMORY_COMMENT)
                .build();

        Comment savedComment = commentRepository.save(comment);

        System.out.println(">>savedComment="+ savedComment.toString());

    }

    @Test
    public void alarmEntity() {
        Member member = Member.builder()
                .username("Test")
                .password(passwordEncoder.encode("Test1agfagdasgdasgdgasydgasgdygasyugdsyugayudgasuydugasudgsauyg23"))
                .email("jae1@gmail.com")
                .phoneNum(passwordEncoder.encode("01052888888"))
                .authority(Authority.USER)
                .activated(true)
                .build();

        Member savedMemeber = memberRepository.save(member);

        Member findMember = memberRepository.findByEmail("jae1@gmail.com");

        System.out.println("member id :" + findMember.getEmail());
        // 반려동물 종 아이디 생성 후 사용
        Species species = Species.builder()
                .largeCategory("포유류")
                .smallCategory("강아지")
                .midCategory("씹덕")
                .build();

        Species savedSpecies = speciesRepository.save(species);
        Pet pet = Pet.builder()
                .species(savedSpecies)
                .member(findMember)
                .petName("방울이")
                .petBirth(LocalDate.now())
                .petProfileUrl("https://images.unsplash.com/photo-1528301721190-186c3bd85418?q=80&w=1000&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxleHBsb3JlLWZlZWR8Mnx8fGVufDB8fHx8fA%3D%3D")
                .petFavs("밥먹기")
                .petDesc("우리 방울이는 밥먹기를 너무 좋아하는 친구입니다.")
                .gender(Gender.F)
                .build();

        Pet findPet = petRepository.save(pet);

        Alarm alarm = Alarm.builder()
                .pet(findPet)
                .alarmType("기타")
                .alarmContent("말을 너무 나쁘게 말해요")
                .read_yn("Y")
                .build();

        Alarm savedAlarmContent = alarmRepository.save(alarm);

        System.out.println(">>savedAlarmContent="+ savedAlarmContent.toString());

    }

    @Test
    public void followEntity() {
        Member member = Member.builder()
                .username("Test")
                .password(passwordEncoder.encode("Test1agfagdasgdasgdgasydgasgdygasyugdsyugayudgasuydugasudgsauyg23"))
                .email("jae2@gmail.com")
                .phoneNum(passwordEncoder.encode("01052888888"))
                .authority(Authority.USER)
                .activated(true)
                .build();

        Member savedMemeber = memberRepository.save(member);

        Member findMember = memberRepository.findByEmail("jae2@gmail.com");

        System.out.println("member id :" + findMember.getEmail());
        // 반려동물 종 아이디 생성 후 사용
        Species species = Species.builder()
                .largeCategory("포유류")
                .smallCategory("강아지")
                .midCategory("씹덕")
                .build();

        Species savedSpecies = speciesRepository.save(species);
        Pet pet = Pet.builder()
                .species(savedSpecies)
                .member(findMember)
                .petName("방울이")
                .petBirth(LocalDate.now())
                .petProfileUrl("https://images.unsplash.com/photo-1528301721190-186c3bd85418?q=80&w=1000&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxleHBsb3JlLWZlZWR8Mnx8fGVufDB8fHx8fA%3D%3D")
                .petFavs("밥먹기")
                .petDesc("우리 방울이는 밥먹기를 너무 좋아하는 친구입니다.")
                .gender(Gender.F)
                .build();

        Pet findPet = petRepository.save(pet);

        Follow follow = Follow.builder()
                .pet(findPet)
                .build();

        Follow savedFollowHistory = followRepository.save(follow);

        System.out.println(">>savedFollowHistory="+ savedFollowHistory.toString());
    }

}
