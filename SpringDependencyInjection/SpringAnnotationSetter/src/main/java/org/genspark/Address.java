package org.genspark;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class Address
{
    private String city;
    private String state;
    private String country;
    private String zipcode;

    @Autowired
    @Qualifier("getCity")
    public void setCity(String city) {
        this.city = city;
    }

    @Autowired
    @Qualifier("getState")
    public void setState(String state) {
        this.state = state;
    }

    @Autowired
    @Qualifier("getCountry")
    public void setCountry(String country) {
        this.country = country;
    }

    @Autowired
    @Qualifier("getZipCode")
    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    @Override
    public String toString() {
        return "Address{" +
                "city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", zipcode='" + zipcode + '\'' +
                '}';
    }
}
