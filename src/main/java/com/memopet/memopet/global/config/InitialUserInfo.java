//package com.memopet.memopet.global.config;
//
//import com.memopet.memopet.domain.member.entity.Member;
//import com.memopet.memopet.domain.member.entity.MemberStatus;
//import com.memopet.memopet.domain.member.repository.MemberRepository;
//import com.memopet.memopet.domain.pet.dto.MemoryResponseDto;
//import com.memopet.memopet.domain.pet.entity.*;
//import com.memopet.memopet.domain.pet.repository.LikesRepository;
//import com.memopet.memopet.domain.pet.repository.MemoryRepository;
//import com.memopet.memopet.domain.pet.repository.PetRepository;
//import com.memopet.memopet.domain.pet.repository.SpeciesRepository;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.List;
//
//@RequiredArgsConstructor
//@Component
//@Slf4j
//public class InitialUserInfo implements CommandLineRunner {
//    // member test data insert
//    private final PasswordEncoder passwordEncoder;
//    private final MemberRepository memberRepository;
//    private final MemoryRepository memoryRepository;
//    private final LikesRepository likesRepository;
//    private final PetRepository petRepository;
//    private final SpeciesRepository speciesRepository;
//    @Override
//    public void run(String... args) throws Exception {
//        Member member1 = Member.builder()
//                .username("Test1")
//                .password(passwordEncoder.encode("123"))
//                .email("user@gmail.com")
//                .loginFailCount(0)
//                .phoneNum("01054232322")
//                .memberStatus(MemberStatus.NORMAL)
//                .roles("ROLE_USER")
//                .activated(true)
//                .build();
//
//        Member member2 = Member.builder()
//                .username("Test2")
//                .password(passwordEncoder.encode("123"))
//                .email("admin@gmail.com")
//                .loginFailCount(0)
//                .phoneNum("01084232322")
//                .memberStatus(MemberStatus.NORMAL)
//                .roles("ROLE_ADMIN")
//                .activated(true)
//                .build();
//
//        memberRepository.saveAll(List.of(member1,member2));
//
//        Species species = Species.builder().largeCategory("포유류").midCategory("개").smallCategory("마티즈").build();
//        speciesRepository.save(species);
//        Pet pet = Pet.builder()
//                .petName("두리")
//                .gender(Gender.F)
//                .petDesc("두리는 이쁘다")
//                .member(member1)
//                .species(species)
//                .petBirth(LocalDate.now())
//                .petDeathDate(LocalDate.now())
//                .petFavs("웃기")
//                .petFavs2("먹기")
//                .petFavs3("달리기")
//                .petProfileUrl("http:/")
//                .backImgUrl("http:/")
//                .petStatus(PetStatus.ACTIVE)
//                .build();
//
//        Pet pet2 = Pet.builder()
//                .petName("방울이")
//                .gender(Gender.F)
//                .petDesc("방울이는 이쁘다")
//                .member(member1)
//                .species(species)
//                .petBirth(LocalDate.now())
//                .petDeathDate(LocalDate.now())
//                .petFavs("웃기")
//                .petFavs2("먹기")
//                .petFavs3("달리기")
//                .petProfileUrl("http:/")
//                .backImgUrl("http:/")
//                .petStatus(PetStatus.ACTIVE)
//                .build();
//        Pet pet3 = Pet.builder()
//                .petName("점박이")
//                .gender(Gender.F)
//                .petDesc("점박이는 이쁘다")
//                .member(member1)
//                .species(species)
//                .petBirth(LocalDate.now())
//                .petDeathDate(LocalDate.now())
//                .petFavs("웃기")
//                .petFavs2("먹기")
//                .petFavs3("달리기")
//                .petProfileUrl("http:/")
//                .backImgUrl("http:/")
//                .petStatus(PetStatus.ACTIVE)
//                .build();
//
//        petRepository.saveAll(List.of(pet, pet2, pet3));
//
//        Memory memory = Memory.builder().pet(pet)
//                .title("방울이와의 산책")
//                .memoryDate(LocalDateTime.now())
//                .memoryDescription("방울이와의 산책이 너무 즐거웠습니다.")
//                .likeCount(0)
//                .memoryImageUrl1("https://images.rawpixel.com/image_800/cHJpdmF0ZS9sci9pbWFnZXMvd2Vic2l0ZS8yMDIzLTA4L3Jhd3BpeGVsX29mZmljZV8xNV9waG90b19vZl9hX2RvZ19ydW5uaW5nX3dpdGhfb3duZXJfYXRfcGFya19lcF9mM2I3MDQyZC0zNWJlLTRlMTQtOGZhNy1kY2Q2OWQ1YzQzZjlfMi5qcGc.jpg")
//                .memoryImageUrl2("https://thumbs.dreamstime.com/b/beagle-dog-isolated-white-background-purebred-103538194.jpg")
//                .audience(Audience.ALL)
//                .build();
//
//        Memory memory1 = Memory.builder().pet(pet)
//                .title("방울이와의 산책")
//                .memoryDate(LocalDateTime.now())
//                .memoryDescription("방울이와의 산책이 너무 즐거웠습니다.")
//                .likeCount(0)
//                .memoryImageUrl1("https://images.rawpixel.com/image_800/cHJpdmF0ZS9sci9pbWFnZXMvd2Vic2l0ZS8yMDIzLTA4L3Jhd3BpeGVsX29mZmljZV8xNV9waG90b19vZl9hX2RvZ19ydW5uaW5nX3dpdGhfb3duZXJfYXRfcGFya19lcF9mM2I3MDQyZC0zNWJlLTRlMTQtOGZhNy1kY2Q2OWQ1YzQzZjlfMi5qcGc.jpg")
//                .memoryImageUrl2("https://thumbs.dreamstime.com/b/beagle-dog-isolated-white-background-purebred-103538194.jpg")
//                .audience(Audience.ALL)
//                .build();
//        Memory memory2 = Memory.builder().pet(pet)
//                .title("방울이와의 산책")
//                .memoryDate(LocalDateTime.now())
//                .memoryDescription("방울이와의 산책이 너무 즐거웠습니다.")
//                .likeCount(0)
//                .memoryImageUrl1("https://images.rawpixel.com/image_800/cHJpdmF0ZS9sci9pbWFnZXMvd2Vic2l0ZS8yMDIzLTA4L3Jhd3BpeGVsX29mZmljZV8xNV9waG90b19vZl9hX2RvZ19ydW5uaW5nX3dpdGhfb3duZXJfYXRfcGFya19lcF9mM2I3MDQyZC0zNWJlLTRlMTQtOGZhNy1kY2Q2OWQ1YzQzZjlfMi5qcGc.jpg")
//                .memoryImageUrl2("https://thumbs.dreamstime.com/b/beagle-dog-isolated-white-background-purebred-103538194.jpg")
//                .audience(Audience.ALL)
//                .build();
//        Memory memory3 = Memory.builder().pet(pet)
//                .title("방울이와의 산책")
//                .memoryDate(LocalDateTime.now())
//                .memoryDescription("방울이와의 산책이 너무 즐거웠습니다.")
//                .likeCount(0)
//                .memoryImageUrl1("https://images.rawpixel.com/image_800/cHJpdmF0ZS9sci9pbWFnZXMvd2Vic2l0ZS8yMDIzLTA4L3Jhd3BpeGVsX29mZmljZV8xNV9waG90b19vZl9hX2RvZ19ydW5uaW5nX3dpdGhfb3duZXJfYXRfcGFya19lcF9mM2I3MDQyZC0zNWJlLTRlMTQtOGZhNy1kY2Q2OWQ1YzQzZjlfMi5qcGc.jpg")
//                .memoryImageUrl2("https://thumbs.dreamstime.com/b/beagle-dog-isolated-white-background-purebred-103538194.jpg")
//                .audience(Audience.ALL)
//                .build();
//        Memory memory4 = Memory.builder().pet(pet)
//                .title("방울이와의 산책")
//                .memoryDate(LocalDateTime.now())
//                .memoryDescription("방울이와의 산책이 너무 즐거웠습니다.")
//                .likeCount(0)
//                .memoryImageUrl1("https://images.rawpixel.com/image_800/cHJpdmF0ZS9sci9pbWFnZXMvd2Vic2l0ZS8yMDIzLTA4L3Jhd3BpeGVsX29mZmljZV8xNV9waG90b19vZl9hX2RvZ19ydW5uaW5nX3dpdGhfb3duZXJfYXRfcGFya19lcF9mM2I3MDQyZC0zNWJlLTRlMTQtOGZhNy1kY2Q2OWQ1YzQzZjlfMi5qcGc.jpg")
//                .memoryImageUrl2("https://thumbs.dreamstime.com/b/beagle-dog-isolated-white-background-purebred-103538194.jpg")
//                .audience(Audience.ALL)
//                .build();
//        Memory memory5 = Memory.builder().pet(pet)
//                .title("방울이와의 산책")
//                .memoryDate(LocalDateTime.now())
//                .memoryDescription("방울이와의 산책이 너무 즐거웠습니다.")
//                .likeCount(0)
//                .memoryImageUrl1("https://images.rawpixel.com/image_800/cHJpdmF0ZS9sci9pbWFnZXMvd2Vic2l0ZS8yMDIzLTA4L3Jhd3BpeGVsX29mZmljZV8xNV9waG90b19vZl9hX2RvZ19ydW5uaW5nX3dpdGhfb3duZXJfYXRfcGFya19lcF9mM2I3MDQyZC0zNWJlLTRlMTQtOGZhNy1kY2Q2OWQ1YzQzZjlfMi5qcGc.jpg")
//                .memoryImageUrl2("https://thumbs.dreamstime.com/b/beagle-dog-isolated-white-background-purebred-103538194.jpg")
//                .audience(Audience.ALL)
//                .build();
//        Memory memory6 = Memory.builder().pet(pet)
//                .title("방울이와의 산책")
//                .memoryDate(LocalDateTime.now())
//                .memoryDescription("방울이와의 산책이 너무 즐거웠습니다.")
//                .likeCount(0)
//                .memoryImageUrl1("https://images.rawpixel.com/image_800/cHJpdmF0ZS9sci9pbWFnZXMvd2Vic2l0ZS8yMDIzLTA4L3Jhd3BpeGVsX29mZmljZV8xNV9waG90b19vZl9hX2RvZ19ydW5uaW5nX3dpdGhfb3duZXJfYXRfcGFya19lcF9mM2I3MDQyZC0zNWJlLTRlMTQtOGZhNy1kY2Q2OWQ1YzQzZjlfMi5qcGc.jpg")
//                .memoryImageUrl2("https://thumbs.dreamstime.com/b/beagle-dog-isolated-white-background-purebred-103538194.jpg")
//                .audience(Audience.ALL)
//                .build();
//        Memory memory7 = Memory.builder().pet(pet)
//                .title("방울이와의 산책")
//                .memoryDate(LocalDateTime.now())
//                .memoryDescription("방울이와의 산책이 너무 즐거웠습니다.")
//                .likeCount(0)
//                .memoryImageUrl1("https://images.rawpixel.com/image_800/cHJpdmF0ZS9sci9pbWFnZXMvd2Vic2l0ZS8yMDIzLTA4L3Jhd3BpeGVsX29mZmljZV8xNV9waG90b19vZl9hX2RvZ19ydW5uaW5nX3dpdGhfb3duZXJfYXRfcGFya19lcF9mM2I3MDQyZC0zNWJlLTRlMTQtOGZhNy1kY2Q2OWQ1YzQzZjlfMi5qcGc.jpg")
//                .memoryImageUrl2("https://thumbs.dreamstime.com/b/beagle-dog-isolated-white-background-purebred-103538194.jpg")
//                .audience(Audience.ALL)
//                .build();
//        Memory memory8 = Memory.builder().pet(pet)
//                .title("방울이와의 산책")
//                .memoryDate(LocalDateTime.now())
//                .memoryDescription("방울이와의 산책이 너무 즐거웠습니다.")
//                .likeCount(0)
//                .memoryImageUrl1("https://images.rawpixel.com/image_800/cHJpdmF0ZS9sci9pbWFnZXMvd2Vic2l0ZS8yMDIzLTA4L3Jhd3BpeGVsX29mZmljZV8xNV9waG90b19vZl9hX2RvZ19ydW5uaW5nX3dpdGhfb3duZXJfYXRfcGFya19lcF9mM2I3MDQyZC0zNWJlLTRlMTQtOGZhNy1kY2Q2OWQ1YzQzZjlfMi5qcGc.jpg")
//                .memoryImageUrl2("https://thumbs.dreamstime.com/b/beagle-dog-isolated-white-background-purebred-103538194.jpg")
//                .audience(Audience.ALL)
//                .build();
//        Memory memory9 = Memory.builder().pet(pet)
//                .title("방울이와의 산책")
//                .memoryDate(LocalDateTime.now())
//                .memoryDescription("방울이와의 산책이 너무 즐거웠습니다.")
//                .likeCount(0)
//                .memoryImageUrl1("https://images.rawpixel.com/image_800/cHJpdmF0ZS9sci9pbWFnZXMvd2Vic2l0ZS8yMDIzLTA4L3Jhd3BpeGVsX29mZmljZV8xNV9waG90b19vZl9hX2RvZ19ydW5uaW5nX3dpdGhfb3duZXJfYXRfcGFya19lcF9mM2I3MDQyZC0zNWJlLTRlMTQtOGZhNy1kY2Q2OWQ1YzQzZjlfMi5qcGc.jpg")
//                .memoryImageUrl2("https://thumbs.dreamstime.com/b/beagle-dog-isolated-white-background-purebred-103538194.jpg")
//                .audience(Audience.ALL)
//                .build();
//        Memory memory10 = Memory.builder().pet(pet)
//                .title("방울이와의 산책")
//                .memoryDate(LocalDateTime.now())
//                .memoryDescription("방울이와의 산책이 너무 즐거웠습니다.")
//                .likeCount(0)
//                .memoryImageUrl1("https://images.rawpixel.com/image_800/cHJpdmF0ZS9sci9pbWFnZXMvd2Vic2l0ZS8yMDIzLTA4L3Jhd3BpeGVsX29mZmljZV8xNV9waG90b19vZl9hX2RvZ19ydW5uaW5nX3dpdGhfb3duZXJfYXRfcGFya19lcF9mM2I3MDQyZC0zNWJlLTRlMTQtOGZhNy1kY2Q2OWQ1YzQzZjlfMi5qcGc.jpg")
//                .memoryImageUrl2("https://thumbs.dreamstime.com/b/beagle-dog-isolated-white-background-purebred-103538194.jpg")
//                .audience(Audience.ALL)
//                .build();
//        Memory memory11 = Memory.builder().pet(pet)
//                .title("방울이와의 산책")
//                .memoryDate(LocalDateTime.now())
//                .memoryDescription("방울이와의 산책이 너무 즐거웠습니다.")
//                .likeCount(0)
//                .memoryImageUrl1("https://images.rawpixel.com/image_800/cHJpdmF0ZS9sci9pbWFnZXMvd2Vic2l0ZS8yMDIzLTA4L3Jhd3BpeGVsX29mZmljZV8xNV9waG90b19vZl9hX2RvZ19ydW5uaW5nX3dpdGhfb3duZXJfYXRfcGFya19lcF9mM2I3MDQyZC0zNWJlLTRlMTQtOGZhNy1kY2Q2OWQ1YzQzZjlfMi5qcGc.jpg")
//                .memoryImageUrl2("https://thumbs.dreamstime.com/b/beagle-dog-isolated-white-background-purebred-103538194.jpg")
//                .audience(Audience.ALL)
//                .build();
//        Memory memory12 = Memory.builder().pet(pet)
//                .title("방울이와의 산책")
//                .memoryDate(LocalDateTime.now())
//                .memoryDescription("방울이와의 산책이 너무 즐거웠습니다.")
//                .likeCount(0)
//                .memoryImageUrl1("https://images.rawpixel.com/image_800/cHJpdmF0ZS9sci9pbWFnZXMvd2Vic2l0ZS8yMDIzLTA4L3Jhd3BpeGVsX29mZmljZV8xNV9waG90b19vZl9hX2RvZ19ydW5uaW5nX3dpdGhfb3duZXJfYXRfcGFya19lcF9mM2I3MDQyZC0zNWJlLTRlMTQtOGZhNy1kY2Q2OWQ1YzQzZjlfMi5qcGc.jpg")
//                .memoryImageUrl2("https://thumbs.dreamstime.com/b/beagle-dog-isolated-white-background-purebred-103538194.jpg")
//                .audience(Audience.ALL)
//                .build();
//        Memory memory13 = Memory.builder().pet(pet)
//                .title("방울이와의 산책")
//                .memoryDate(LocalDateTime.now())
//                .memoryDescription("방울이와의 산책이 너무 즐거웠습니다.")
//                .likeCount(0)
//                .memoryImageUrl1("https://images.rawpixel.com/image_800/cHJpdmF0ZS9sci9pbWFnZXMvd2Vic2l0ZS8yMDIzLTA4L3Jhd3BpeGVsX29mZmljZV8xNV9waG90b19vZl9hX2RvZ19ydW5uaW5nX3dpdGhfb3duZXJfYXRfcGFya19lcF9mM2I3MDQyZC0zNWJlLTRlMTQtOGZhNy1kY2Q2OWQ1YzQzZjlfMi5qcGc.jpg")
//                .memoryImageUrl2("https://thumbs.dreamstime.com/b/beagle-dog-isolated-white-background-purebred-103538194.jpg")
//                .audience(Audience.ALL)
//                .build();
//        Memory memory14 = Memory.builder().pet(pet)
//                .title("방울이와의 산책")
//                .memoryDate(LocalDateTime.now())
//                .memoryDescription("방울이와의 산책이 너무 즐거웠습니다.")
//                .likeCount(0)
//                .memoryImageUrl1("https://images.rawpixel.com/image_800/cHJpdmF0ZS9sci9pbWFnZXMvd2Vic2l0ZS8yMDIzLTA4L3Jhd3BpeGVsX29mZmljZV8xNV9waG90b19vZl9hX2RvZ19ydW5uaW5nX3dpdGhfb3duZXJfYXRfcGFya19lcF9mM2I3MDQyZC0zNWJlLTRlMTQtOGZhNy1kY2Q2OWQ1YzQzZjlfMi5qcGc.jpg")
//                .memoryImageUrl2("https://thumbs.dreamstime.com/b/beagle-dog-isolated-white-background-purebred-103538194.jpg")
//                .audience(Audience.ALL)
//                .build();
//        Memory memory15 = Memory.builder().pet(pet)
//                .title("방울이와의 산책")
//                .memoryDate(LocalDateTime.now())
//                .memoryDescription("방울이와의 산책이 너무 즐거웠습니다.")
//                .likeCount(0)
//                .memoryImageUrl1("https://images.rawpixel.com/image_800/cHJpdmF0ZS9sci9pbWFnZXMvd2Vic2l0ZS8yMDIzLTA4L3Jhd3BpeGVsX29mZmljZV8xNV9waG90b19vZl9hX2RvZ19ydW5uaW5nX3dpdGhfb3duZXJfYXRfcGFya19lcF9mM2I3MDQyZC0zNWJlLTRlMTQtOGZhNy1kY2Q2OWQ1YzQzZjlfMi5qcGc.jpg")
//                .memoryImageUrl2("https://thumbs.dreamstime.com/b/beagle-dog-isolated-white-background-purebred-103538194.jpg")
//                .audience(Audience.ALL)
//                .build();
//        Memory memory16 = Memory.builder().pet(pet)
//                .title("방울이와의 산책")
//                .memoryDate(LocalDateTime.now())
//                .memoryDescription("방울이와의 산책이 너무 즐거웠습니다.")
//                .likeCount(0)
//                .memoryImageUrl1("https://images.rawpixel.com/image_800/cHJpdmF0ZS9sci9pbWFnZXMvd2Vic2l0ZS8yMDIzLTA4L3Jhd3BpeGVsX29mZmljZV8xNV9waG90b19vZl9hX2RvZ19ydW5uaW5nX3dpdGhfb3duZXJfYXRfcGFya19lcF9mM2I3MDQyZC0zNWJlLTRlMTQtOGZhNy1kY2Q2OWQ1YzQzZjlfMi5qcGc.jpg")
//                .memoryImageUrl2("https://thumbs.dreamstime.com/b/beagle-dog-isolated-white-background-purebred-103538194.jpg")
//                .audience(Audience.ALL)
//                .build();
//        Memory memory17 = Memory.builder().pet(pet)
//                .title("방울이와의 산책")
//                .memoryDate(LocalDateTime.now())
//                .memoryDescription("방울이와의 산책이 너무 즐거웠습니다.")
//                .likeCount(0)
//                .memoryImageUrl1("https://images.rawpixel.com/image_800/cHJpdmF0ZS9sci9pbWFnZXMvd2Vic2l0ZS8yMDIzLTA4L3Jhd3BpeGVsX29mZmljZV8xNV9waG90b19vZl9hX2RvZ19ydW5uaW5nX3dpdGhfb3duZXJfYXRfcGFya19lcF9mM2I3MDQyZC0zNWJlLTRlMTQtOGZhNy1kY2Q2OWQ1YzQzZjlfMi5qcGc.jpg")
//                .memoryImageUrl2("https://thumbs.dreamstime.com/b/beagle-dog-isolated-white-background-purebred-103538194.jpg")
//                .audience(Audience.ALL)
//                .build();
//        Memory memory18 = Memory.builder().pet(pet)
//                .title("방울이와의 산책")
//                .memoryDate(LocalDateTime.now())
//                .memoryDescription("방울이와의 산책이 너무 즐거웠습니다.")
//                .likeCount(0)
//                .memoryImageUrl1("https://images.rawpixel.com/image_800/cHJpdmF0ZS9sci9pbWFnZXMvd2Vic2l0ZS8yMDIzLTA4L3Jhd3BpeGVsX29mZmljZV8xNV9waG90b19vZl9hX2RvZ19ydW5uaW5nX3dpdGhfb3duZXJfYXRfcGFya19lcF9mM2I3MDQyZC0zNWJlLTRlMTQtOGZhNy1kY2Q2OWQ1YzQzZjlfMi5qcGc.jpg")
//                .memoryImageUrl2("https://thumbs.dreamstime.com/b/beagle-dog-isolated-white-background-purebred-103538194.jpg")
//                .audience(Audience.ALL)
//                .build();
//        Memory memory19 = Memory.builder().pet(pet)
//                .title("방울이와의 산책")
//                .memoryDate(LocalDateTime.now())
//                .memoryDescription("방울이와의 산책이 너무 즐거웠습니다.")
//                .likeCount(0)
//                .memoryImageUrl1("https://images.rawpixel.com/image_800/cHJpdmF0ZS9sci9pbWFnZXMvd2Vic2l0ZS8yMDIzLTA4L3Jhd3BpeGVsX29mZmljZV8xNV9waG90b19vZl9hX2RvZ19ydW5uaW5nX3dpdGhfb3duZXJfYXRfcGFya19lcF9mM2I3MDQyZC0zNWJlLTRlMTQtOGZhNy1kY2Q2OWQ1YzQzZjlfMi5qcGc.jpg")
//                .memoryImageUrl2("https://thumbs.dreamstime.com/b/beagle-dog-isolated-white-background-purebred-103538194.jpg")
//                .audience(Audience.ALL)
//                .build();
//        Memory memory20 = Memory.builder().pet(pet)
//                .title("방울이와의 산책")
//                .memoryDate(LocalDateTime.now())
//                .memoryDescription("방울이와의 산책이 너무 즐거웠습니다.")
//                .likeCount(0)
//                .memoryImageUrl1("https://images.rawpixel.com/image_800/cHJpdmF0ZS9sci9pbWFnZXMvd2Vic2l0ZS8yMDIzLTA4L3Jhd3BpeGVsX29mZmljZV8xNV9waG90b19vZl9hX2RvZ19ydW5uaW5nX3dpdGhfb3duZXJfYXRfcGFya19lcF9mM2I3MDQyZC0zNWJlLTRlMTQtOGZhNy1kY2Q2OWQ1YzQzZjlfMi5qcGc.jpg")
//                .memoryImageUrl2("https://thumbs.dreamstime.com/b/beagle-dog-isolated-white-background-purebred-103538194.jpg")
//                .audience(Audience.ALL)
//                .build();
//        Memory memory21 = Memory.builder().pet(pet)
//                .title("방울이와의 산책")
//                .memoryDate(LocalDateTime.now())
//                .memoryDescription("방울이와의 산책이 너무 즐거웠습니다.")
//                .likeCount(0)
//                .memoryImageUrl1("https://images.rawpixel.com/image_800/cHJpdmF0ZS9sci9pbWFnZXMvd2Vic2l0ZS8yMDIzLTA4L3Jhd3BpeGVsX29mZmljZV8xNV9waG90b19vZl9hX2RvZ19ydW5uaW5nX3dpdGhfb3duZXJfYXRfcGFya19lcF9mM2I3MDQyZC0zNWJlLTRlMTQtOGZhNy1kY2Q2OWQ1YzQzZjlfMi5qcGc.jpg")
//                .memoryImageUrl2("https://thumbs.dreamstime.com/b/beagle-dog-isolated-white-background-purebred-103538194.jpg")
//                .audience(Audience.ALL)
//                .build();
//        Memory memory22 = Memory.builder().pet(pet)
//                .title("방울이와의 산책")
//                .memoryDate(LocalDateTime.now())
//                .memoryDescription("방울이와의 산책이 너무 즐거웠습니다.")
//                .likeCount(0)
//                .memoryImageUrl1("https://images.rawpixel.com/image_800/cHJpdmF0ZS9sci9pbWFnZXMvd2Vic2l0ZS8yMDIzLTA4L3Jhd3BpeGVsX29mZmljZV8xNV9waG90b19vZl9hX2RvZ19ydW5uaW5nX3dpdGhfb3duZXJfYXRfcGFya19lcF9mM2I3MDQyZC0zNWJlLTRlMTQtOGZhNy1kY2Q2OWQ1YzQzZjlfMi5qcGc.jpg")
//                .memoryImageUrl2("https://thumbs.dreamstime.com/b/beagle-dog-isolated-white-background-purebred-103538194.jpg")
//                .audience(Audience.ALL)
//                .build();
//
//        memoryRepository.saveAll(List.of(memory1,memory2,memory3,memory4,memory5,memory6,memory7,memory8,memory9,memory10,memory11,memory12,memory13,memory14,memory15,memory16,memory17,memory18,memory19,memory20,memory21,memory22));
//
//        Likes likes = Likes.builder().memoryId(memory1).likedOwnPetId(pet.getId()).petId(pet2).build();
//        Likes likes1 = Likes.builder().memoryId(memory2).likedOwnPetId(pet.getId()).petId(pet2).build();
//        Likes likes2 = Likes.builder().memoryId(memory3).likedOwnPetId(pet.getId()).petId(pet2).build();
//
//        likesRepository.saveAll(List.of(likes,likes1,likes2));
//    }
//}
