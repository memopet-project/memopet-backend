package com.memopet.memopet.domain.member.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.memopet.memopet.domain.pet.entity.Pet;
import com.memopet.memopet.global.common.entity.FirstCreatedEntity;
import com.memopet.memopet.global.common.entity.LastModifiedEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member extends FirstCreatedEntity implements UserDetails, Serializable {
    private static final long serialVersionUID = 174726374856727L;

    @Id @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;
    @Column(name = "username", nullable = false)
    private String username;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "encrypt_password", nullable = false)
    private String password;
    @Column(name = "phone_num", nullable = false)
    private String phoneNum;

    @Column(name = "deactivation_reason_comment")
    private String deactivationReasonComment;

    @Column(name = "deactivation_reason")
    private String deactivationReason;

    @Column(name = "deleted_date")
    private LocalDateTime deletedDate;

    @Embedded //해당 클라스에 @Embeddable을 붙여줘야됨
    private Address address;

    @JsonIgnore
    @Column(name = "activated", nullable = false)
    private boolean activated;

    @Column(name="authority", nullable = false)
    private Authority authority;

    @OneToMany(mappedBy = "member")
    private List<Pet> pets = new ArrayList<Pet>();

    /**
     *
     * @return a list of authorities that a member has
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("member"));
    }

    /**
     *
     * @return user's encrypt password
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     *
     * @return true if the session is expired, if not then false
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     *
     * @return true if the account is locked, if not then false
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     *
     * @return true if the password is expired
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     *
     * @return true if the account is enabled
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
