package com.memopet.memopet;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {
    @Id
    @GeneratedValue
    @Column(name = "member_id") // db에 저장될 이름
    private Long id;
    private String name;

    @Embedded //해당 클라스에 @Embeddable을 붙여줘야됨
    private Address address;


}
