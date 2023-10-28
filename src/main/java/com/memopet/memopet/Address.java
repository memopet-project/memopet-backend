package com.memopet.memopet;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable // 어딘가에 종속(소속)될수있다
@Getter // 값 타입은 변경 불가능하게 설계해야된다
public class Address {
    private String city;
    private String street;
    private String zipcode;
    protected Address() {
    }

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
