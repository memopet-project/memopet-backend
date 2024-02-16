package com.memopet.memopet.domain.member.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.memopet.memopet.domain.pet.entity.Pet;
import com.memopet.memopet.global.common.entity.FirstCreatedEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member extends FirstCreatedEntity implements Serializable {
    private static final long serialVersionUID = 174726374856727L;

    @Id @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;
    @Column(name = "username", nullable = false)
    private String username;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "encrypt_password")
    private String password;
    @Column(name = "phone_num")
    private String phoneNum;

    @Column(name = "deactivation_reason_comment")
    private String deactivationReasonComment;

    @Column(name = "deactivation_reason")
    private String deactivationReason;

    @Column(name = "login_fail_count")
    private int loginFailCount;

    @Column(name = "member_status")
    private MemberStatus memberStatus;

    @Column(name = "deleted_date")
    private LocalDateTime deletedDate;

    @Embedded //해당 클라스에 @Embeddable을 붙여줘야됨
    private Address address;

    @JsonIgnore
    @Column(name = "activated", nullable = false)
    private boolean activated;

    @Column(name="roles", nullable = false)
    private String roles;

    @OneToMany(mappedBy = "member")
    private List<Pet> pets = new ArrayList<>();

    @Column(name="provider")
    private String provider; //어떤 OAuth인지(google, naver 등)

    @Column(name="providerid")
    private String provideId; // 해당 OAuth 의 key(id)

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<RefreshTokenEntity> refreshTokens;

    /********** 변경감지용 메서드 **************/
    public void increaseLoginFailCount(int loginFailCount) {
        this.loginFailCount = loginFailCount;
    }
    public void changeActivity(boolean isActivated) {
        this.activated = isActivated;
    }

    public void changeMemberStatus(MemberStatus memberStatus) {
        this.memberStatus = memberStatus;
    }


}
