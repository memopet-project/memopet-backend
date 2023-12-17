package com.memopet.memopet;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.AuditorAware;


import java.time.LocalDateTime;
import java.util.Optional;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends LastModifiedEntity {
    @Id
    @GeneratedValue
    @Column(name = "member_id") // db에 저장될 이름
    private Long id;
    @Column(name = "username")
    private String username;
    @Column(name = "user_id")
    private String userId;
    @Column(name = "encrypt_password")
    private String encryptPassword;
    @Column(name = "email")
    private String email;
    @Column(name = "encrypt_phone_num")
    private String encryptPhoneNum;
    @Column(name = "user_status")
    private String status;
    @Column(name = "deleted_date")
    private LocalDateTime deletedDate;

    @Embedded //해당 클라스에 @Embeddable을 붙여줘야됨
    private Address address;

    @ManyToOne(fetch = FetchType.LAZY)
    private Bookmark bookmark;

    public Member(String username) {
        this.username = username;

    }
}
