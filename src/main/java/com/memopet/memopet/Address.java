package com.memopet.memopet;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter // not able to update data
public class Address {
    private String street;
    private String city;
    private String zipcode;

    protected Address() {
    }

    public Address( String street, String city, String zipcode) {
        this.street = street;
        this.city = city;
        this.zipcode = zipcode;
    }
}
